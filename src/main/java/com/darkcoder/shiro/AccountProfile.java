package com.darkcoder.shiro;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountProfile implements Serializable {

    private String username;

    private Integer role_id;

}
