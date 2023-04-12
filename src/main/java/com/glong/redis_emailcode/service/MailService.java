package com.glong.redis_emailcode.service;

public interface MailService {
    //发送邮件
    boolean sendMail(String email, String code);
}
