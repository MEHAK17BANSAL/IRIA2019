package com.brandappz.dhfl.event.activities;

public class Config {
    //URLs to register.php and confirm.php file


    public static final String REGISTER_URL = "http://socialcampaign.co.in/apps/event_management/services/evntSendOtpJson.php?contact=";

    static final String CONFIRM_URL = "http://socialcampaign.co.in/apps/event_management/services/evntAfterOtp.php?";
    public static final String SMS_ORIGIN = "ANHIVE";
    public static final String OTP_DELIMITER = ":";
    public  static final String Photo = "http://socialcampaign.co.in/apps/event_management/dhfl/quiz/getImgDetail.php?page=1";
    public static final String Video = "http://socialcampaign.co.in/apps/event_management/dhfl/quiz/getImgDetail.php?page=1&type=video";
    //Keys to send username, password, phone and otp
    /*public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";*/
    static final String KEY_PHONE = "contact";
    static final String BRAND_CODE = "QDVTD";
    static final String KEY_OTP = "otp";

    //JSON Tag from response from server
    static final String TAG_RESPONSE= "result";
}
