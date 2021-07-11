package com.ty.attendancesystem.demo;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
public class TwilioDemo {

    public static final String ACCOUNT_SID = "AC9ffb1850bb693ff2d4c5cb1487a0ddd8";
    public static final String AUTH_TOKEN = "1a24fc3913266feae9d882b5a73c94d5";

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber("+84377158365"), // to
                        new PhoneNumber("+16162131749"), // from
                        "Where's Wallace?")
                .create();

        System.out.println(message.getSid());
    }
}
