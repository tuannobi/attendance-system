package com.ty.attendancesystem.service.mail;

import com.ty.attendancesystem.message.mail.MailRequest;

public interface MailSenderService {
    void sendEmailWithAttachment();
    void sendEmail(MailRequest mailRequest);
}
