package com.ty.attendancesystem.config;

import com.twilio.Twilio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioInitiazer {

  private final static Logger LOGGER = LoggerFactory.getLogger(TwilioInitiazer.class);

  private final TwilioConfig twilioConfig;

  @Autowired
  public TwilioInitiazer(TwilioConfig twilioConfig){
    this.twilioConfig=twilioConfig;
    Twilio.init(twilioConfig.getAccountSid(),
        twilioConfig.getAuthToken()
    );
    LOGGER.info("Twilio initialized ... with account sid {} ", twilioConfig.getAccountSid());
  }




}
