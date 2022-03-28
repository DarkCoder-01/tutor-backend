package com.darkcoder.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.darkcoder.entity.Users;
import com.darkcoder.service.UsersService;
import com.darkcoder.util.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UsersService usersService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        JwtToken jwtToken = (JwtToken) token;

        String userName = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();

        Users users = usersService.getOne(new QueryWrapper<Users>().eq("username", userName));
        if (users == null) {
            throw new UnknownAccountException("账户不存在");
        }

        AccountProfile accountProfile = new AccountProfile();
        accountProfile.setUsername(userName);
        accountProfile.setRole_id(users.getRoleId());

        return new SimpleAuthenticationInfo(accountProfile, jwtToken.getCredentials(), getName());
    }
}
