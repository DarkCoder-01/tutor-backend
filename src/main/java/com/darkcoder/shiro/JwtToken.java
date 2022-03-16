package com.darkcoder.shiro;

import com.darkcoder.util.JwtUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;

public class JwtToken implements AuthenticationToken {

    @Autowired
    JwtUtils jwtUtils;

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
