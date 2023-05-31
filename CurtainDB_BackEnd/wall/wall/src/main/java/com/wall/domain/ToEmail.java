package com.wall.domain;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;


@Data
public class ToEmail {

    /**
     * 邮件接收方，可多人
     */
    private String[] tos;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;

}
