package com.wall.common;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Status {
    private String no;
    private Timestamp time;
    private int status;
    public Status(String no, Timestamp time, int status) {
        this.no = no;
        this.time = time;
        this.status = status;
    }
}
