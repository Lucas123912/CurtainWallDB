package com.wall.controller;


import com.wall.common.Result;
import com.wall.common.Status;
import com.wall.domain.Device;
import com.wall.domain.Log;
import com.wall.mapper.LogMapper;
import com.wall.service.DeviceService;
import com.wall.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/log")
public class LogController {
    @Autowired
    LogService logService;

    @Autowired
    DeviceService deviceService;

    @PostMapping("/list")
    public Result<List<Log>> list(){

        List<Log> logList=logService.list();

        return Result.success(logList);
    }

    @PostMapping("/heatlist")
    public Result<List<Status>> visualize(){

        List<Log> logList=logService.list();
        List<Device> deviceList=deviceService.selectXYZException();

        List<String> nameAndTimeList1 = logList.stream()
                .map(deviceData -> deviceData.getNo() + "," + deviceData.getStartTime())
                .collect(Collectors.toList());
        List<String> nameAndTimeList2 = deviceList.stream()
                .map(deviceData -> deviceData.getNo() + "," + deviceData.getTime())
                .collect(Collectors.toList());

        List<Status> statusList = new ArrayList<>();
        for (Log log : logList) {
            boolean isMatched = false;
            for (Device device : deviceList) {
                if (log.getNo().equals(device.getNo()) && log.getStartTime().equals(device.getTime())) {
                    statusList.add(new Status(log.getNo(), log.getStartTime(), 1));
                    isMatched = true;
                    break;
                }
            }
            if (!isMatched) {
                statusList.add(new Status(log.getNo(), log.getStartTime(), 2));
            }
        }
        return Result.success(statusList);
//        System.out.println("statusList:");
//        for (Status status : statusList) {
//            System.out.println(status.getNo() + "\t" + status.getTime() + "\t" + status.getStatus());
//        }
    }
}




