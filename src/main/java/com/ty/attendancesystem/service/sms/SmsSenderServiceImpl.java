package com.ty.attendancesystem.service.sms;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import com.ty.attendancesystem.config.TwilioConfig;
import com.ty.attendancesystem.message.sms.SmsRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsSenderServiceImpl implements SmsSenderService{

  private static final Logger LOGGER = LoggerFactory.getLogger(SmsSenderServiceImpl.class);

  private final TwilioConfig twilioConfig;

  @Autowired
  public SmsSenderServiceImpl(TwilioConfig twilioConfig) {
    this.twilioConfig = twilioConfig;
  }

  @Override
  public void sendSms(SmsRequest smsRequest) {
    if(isPhoneNumberValid(smsRequest.getPhoneNumber())) {
      PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
      PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());
      String message = smsRequest.getMessage();
      MessageCreator creator = Message.creator(to, from, message);
      creator.create();
      LOGGER.info("Send sms {}" + smsRequest);
    } else {
      throw new IllegalArgumentException("Phone number [" + smsRequest.getPhoneNumber() + "] is not a valid number");
    }
  }

  private boolean isPhoneNumberValid(String phoneNumber) {

    return true;
  }
}
