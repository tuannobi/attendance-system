package com.ty.attendancesystem.controller;

import com.ty.attendancesystem.message.sms.SmsRequest;
import com.ty.attendancesystem.service.sms.SmsSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/sms")
public class SMSRestController {
  private final SmsSenderService smsSenderService;

  @Autowired
  public SMSRestController(SmsSenderService smsSenderService) {
    this.smsSenderService = smsSenderService;
  }

  @PostMapping
  public void sendSms(@RequestBody SmsRequest smsRequest) {
    smsSenderService.sendSms(smsRequest);
  }

}
