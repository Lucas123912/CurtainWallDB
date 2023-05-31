package com.wall;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wall.domain.Device;
import com.wall.service.DeviceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class WallApplicationTests {
    @Autowired
    DeviceService deviceService;
    @Test
    void Test1() {
        Device temp=deviceService.selectById(2);
        System.out.println(temp.toString());
    }
    @Test
    void Test2() {
        List<Device> temp=deviceService.selectByNo("29FA1867");
        System.out.println(temp.toString());
    }

    @Test
    void Test3() {
        Device temp=deviceService.selectOneByNo("29FA1867");
        System.out.println(temp.toString());
    }

    @Test
    void Test4() {
        List<Device> temp=deviceService.selectXYZException();
        for (Device device : temp) {
            System.out.println("no: " + device.getNo() + ", time: " + device.getTime());
        }
    }

    @Test
    void Test5() {
        List<Device> temp=deviceService.selectByNoLimit("29FA1867");
        for (Device device : temp) {
            System.out.println(", no: " + device.getNo() + ", time: " + device.getTime()+ ", X: " + device.getX()+ ", Y: " + device.getY()+ ", Z: " + device.getZ());
        }
    }
    @Test
    void Test6() {
        IPage<Device> page= deviceService.pageByNo("29FA1867",1,20);
        System.out.println("当前页码值:"+page.getCurrent());
        System.out.println("每页显示数:"+page.getSize());
        System.out.println("一共多少页:"+page.getPages());
        System.out.println("一共多少条数据:"+page.getTotal());
        System.out.println("数据值:"+page.getRecords());
    }

    @Test
    void Test7() {
        deviceService.removeByNo("234ABC");
    }
}
