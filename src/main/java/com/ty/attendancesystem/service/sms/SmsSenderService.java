package com.ty.attendancesystem.service.sms;

import com.ty.attendancesystem.message.sms.SmsRequest;

public interface SmsSenderService {
  void sendSms(SmsRequest smsRequest);
}
