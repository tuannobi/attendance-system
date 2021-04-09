package com.ty.attendancesystem.message.mail;

public class MailRequest {
    String fromEmail;
    String toEmail;
    String replyTo;
    String subject;
    String content;

    public MailRequest() {
    }

    public MailRequest(String toEmail, String subject, String content) {
        this.toEmail = toEmail;
        this.subject = subject;
        this.content = content;
    }

    public MailRequest(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }
}
