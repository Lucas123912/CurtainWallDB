package com.wall.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.wall.common.Result;
import com.wall.domain.Device;
import com.wall.domain.ToEmail;
import com.wall.mapper.DeviceMapper;
import com.wall.service.DeviceService;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements DeviceService {
    @Resource
    DeviceMapper deviceMapper;

    @Autowired
    private JavaMailSender mailSender;
    public Device selectById(int id){

        return deviceMapper.selectById(id);
    }

    public List<Device> selectByNo(String no){
        QueryWrapper<Device> queryWrapper=new QueryWrapper();
        queryWrapper.select().eq("no",no);
        List<Device> deviceList = deviceMapper.selectList(queryWrapper);
        return  deviceList;
    }

    public Device selectOneByNo(String no){
        QueryWrapper<Device> queryWrapper=new QueryWrapper();
        queryWrapper.select().eq("no",no);
        queryWrapper.last("LIMIT 1");
        Device device = deviceMapper.selectOne(queryWrapper);
        return  device;
    }

    //TODO 根据设备名查询部分数据
    public List<Device> selectByNoLimit(String no){
        List<Device> deviceList = new ArrayList<>();
        QueryWrapper<Device> queryWrapper = new QueryWrapper<>();
        queryWrapper.select().eq("no", no);
        //流式条件查询
        this.deviceMapper.dynamicSelectLargeDataByDeviceNo(queryWrapper,resultContext ->
            Optional.ofNullable(resultContext.getResultObject()).ifPresent(deviceList::add)
        );
//        this.deviceMapper.dynamicSelectLargeDataByDeviceNo(queryWrapper, resultContext ->
//                Optional.ofNullable(resultContext.getResultObject())
//                .ifPresent(deviceList::add));
        return deviceList;

    }
    public IPage<Device> pageByNo(String no,int pageCurrent,int pageSize){
        IPage<Device> page = new Page<>(pageCurrent, pageSize);
        QueryWrapper<Device> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("no", no);
        deviceMapper.selectPage(page, queryWrapper);
        return page;
    }

    public IPage<Device> pageByCsv(int pageCurrent,int pageSize){
        IPage<Device> page = new Page<>(pageCurrent, pageSize);
        QueryWrapper<Device> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*");
        deviceMapper.selectPage(page, queryWrapper);
        return page;
    }


    //查询XYZ数据差值异常
    public List<Device> selectXYZException(){
        QueryWrapper<Device> queryWrapper=new QueryWrapper();
        queryWrapper.select("no","time") // 选择需要查询的列，可以根据具体需要选择
                .having("max(x) - min(x) > 1")
                .or()
                .having("max(y) - min(y) > 1")
                .or()
                .having("max(z) - min(z) > 1")
                .groupBy("no","time");
        List<Device> deviceList = deviceMapper.selectList(queryWrapper);



        return  deviceList;
    }

    @Value("${spring.mail.username}")
    private String from;
    public String warningEmail(List<Device> deviceList) {
        //创建简单邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        //谁发的
        message.setFrom(from);


        //谁要接收
        message.setTo("13515979444@163.com");
        //邮件标题
        message.setSubject("幕墙警报信息");
        //邮件内容
        String prefix="实时异常信息如下：\n";
        StringBuilder emailContent = new StringBuilder();

        emailContent.append(prefix);
        System.out.println(deviceList);
        for (Device device : deviceList) {
            String no = device.getNo();
            Timestamp time = device.getTime();
            emailContent.append("no: ").append(no).append(", time: ").append(time).append('\n');
        }
        message.setText(emailContent.toString());
//        //谁要接收
//        message.setTo(toEmail.getTos());
//        //邮件标题
//        message.setSubject(toEmail.getSubject());
//        //邮件内容
//        message.setText(toEmail.getContent());
        try {
            mailSender.send(message);
            return "邮件发送成功";
        } catch (MailException e) {
            e.printStackTrace();
            return "邮件发送失败";
        }
    }

    public String importDevicesFromCSV(MultipartFile csvFile) {
        String no="";
        try (CSVReader reader = new CSVReader(new InputStreamReader(csvFile.getInputStream()))) {
            String[] line;

            while ((line = reader.readNext()) != null) {
                no=line[0];
                Device device = new Device();
                device.setNo(line[0]);
                device.setX(Float.parseFloat(line[1]));
                device.setY(Float.parseFloat(line[2]));
                device.setZ(Float.parseFloat(line[3]));
                device.setTime(Timestamp.valueOf(line[4]));
                deviceMapper.insert(device);
            }
            return no;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            // 处理读取CSV文件异常
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return no;
    }

    public void removeByNo(String no){
        QueryWrapper<Device> queryWrapper=new QueryWrapper();
        queryWrapper.select().eq("no",no);
        deviceMapper.delete(queryWrapper);
    }
}
