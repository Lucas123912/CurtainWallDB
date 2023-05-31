package com.wall.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;


import java.sql.Timestamp;

@Data
@TableName(value = "device")
//@TableIndex(name = "idx_xyz", columnList = "x,y,z")
public class Device {
    @TableField(value = "no")
    private String no;
    @TableField(value = "x")
    private float x;
    @TableField(value = "y")
    private float y;
    @TableField(value = "z")
    private float z;
    @TableField(value = "time")
    private Timestamp time;
}
