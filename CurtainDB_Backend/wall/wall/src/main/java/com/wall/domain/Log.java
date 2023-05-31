package com.wall.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@TableName(value = "log")
public class Log {
    @TableField(value = "id")
    private Integer id;
    @TableField(value = "no")
    private String no;
    @TableField(value = "sps")
    private Integer sps;
    @TableField(value = "start")
    private Timestamp startTime;
    @TableField(value = "stop")
    private Timestamp stopTime;
}
