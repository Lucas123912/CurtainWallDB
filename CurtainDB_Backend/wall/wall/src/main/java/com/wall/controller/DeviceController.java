package com.wall.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wall.common.Result;
import com.wall.domain.Device;
import com.wall.service.DeviceService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/api/device")
public class DeviceController {
    @Autowired
    DeviceService deviceService;

    @PostMapping("/xyzException")
    public Result<List<Device>> xyzExceptionList(){
        //List<Device> deviceList = deviceService.list();
        //Device xMax= deviceList.stream().max(Comparator.comparing(Device::getX)).get();
        //Device xMin= deviceList.stream().min(Comparator.comparing(Device::getX)).get();
//        List<Device> deviceList=deviceService.selectXYZException();
//        deviceService.warningEmail(deviceList);
        List<Device> deviceList = deviceService.selectXYZException();
        CompletableFuture.runAsync(() -> {
            deviceService.warningEmail(deviceList);
        });
        return Result.success(deviceList);
    }

    @GetMapping("/pageList/{no}")
    public Result<List<Device>> pageListByNo(@PathVariable("no") String no){
        int pageSize=100;
        int pageCurrent=1;
        IPage<Device> page= deviceService.pageByNo(no,pageCurrent,pageSize);
        List<Device> deviceList=page.getRecords();
        System.out.println("当前页码值:"+page.getCurrent());
        System.out.println("每页显示数:"+page.getSize());
        System.out.println("一共多少页:"+page.getPages());
        System.out.println("一共多少条数据:"+page.getTotal());
        System.out.println("数据值:"+page.getRecords());

        return Result.success(deviceList);
    }

    @PostMapping("/import")
    public Result<List<Device>> importDevicesFromCSV(@RequestParam("file") MultipartFile csvFile) {
        String no=deviceService.importDevicesFromCSV(csvFile);
        System.out.println(csvFile);
        int pageSize=50;
        int pageCurrent=1;
        IPage<Device> page= deviceService.pageByNo(no,pageCurrent,pageSize);
        List<Device> deviceList=page.getRecords();
        return Result.success(deviceList);
    }

}
