package com.wall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wall.domain.Device;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DeviceService extends IService<Device> {
    public Device selectById(int id);

    public List<Device> selectByNo(String no);

    public void removeByNo(String no);
    public Device selectOneByNo(String no);

    public List<Device> selectXYZException();

    public List<Device> selectByNoLimit(String no);

    public IPage<Device> pageByNo(String no,int pageCurrent,int pageSize);
    public IPage<Device> pageByCsv(int pageCurrent,int pageSize);
    public String warningEmail(List<Device> deviceList);

    public String importDevicesFromCSV(MultipartFile csvFile);
}
