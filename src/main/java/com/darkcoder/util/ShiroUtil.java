package com.darkcoder.util;

import org.apache.shiro.SecurityUtils;

public class ShiroUtil {

    public static String getProfile() {
        return (String) SecurityUtils.getSubject().getPrincipal();
    }

}
