package com.course.utils;

import com.course.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigFile {
    private static ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CHINA);

    //InterfaceName对应model层的枚举，必须按照我规定的接口来传
    public static String getUrl(InterfaceName name) {
        String address = bundle.getString("test.url");
        String uri = "";
        String testUrl;//最终的测试地址
        if (name == InterfaceName.GETUSERLIST) {
            uri = bundle.getString("getUserList.uri");
        }
        if (name == InterfaceName.ADDUSERINFO) {
            uri = bundle.getString("addUser.uri");
        }
        if (name == InterfaceName.UPDATEUSERINFO) {
            uri = bundle.getString("updateUserInfo.uri");
        }
        if (name == InterfaceName.GETUSERINFO) {
            uri = bundle.getString("getUserInfo.uri");
        }
        if (name == InterfaceName.LOGIN) {
            uri = bundle.getString("login.uri");
        }
        testUrl = address + uri;
        return testUrl;
    }
}
