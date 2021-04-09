package com.ty.attendancesystem.service.mail;

import com.ty.attendancesystem.message.mail.MailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderServiceImpl implements MailSenderService{

    private JavaMailSender javaMailSender;

    @Autowired
    public MailSenderServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmailWithAttachment() {

    }

    @Override
    public void sendEmail(MailRequest mailRequest) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(mailRequest.getFromEmail());
        msg.setTo(mailRequest.getToEmail());
        msg.setSubject(mailRequest.getSubject());
        msg.setText(mailRequest.getContent());
        javaMailSender.send(msg);
    }
}
