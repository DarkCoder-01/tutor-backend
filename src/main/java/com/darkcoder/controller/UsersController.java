package com.darkcoder.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.darkcoder.common.dto.LoginDto;
import com.darkcoder.common.lang.Result;
import com.darkcoder.entity.Users;
import com.darkcoder.mapper.UsersMapper;
import com.darkcoder.service.UsersService;
import com.darkcoder.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UsersController {

    @Autowired
    UsersService usersService;

    @Autowired
    JwtUtils jwtUtils;

    @Resource
    UsersMapper usersMapper;

    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {

        Users users = usersService.getOne(new QueryWrapper<Users>().eq("username", loginDto.getUsername()));
        Assert.notNull(users, "用户不存在");

        if (!users.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            return Result.fail("密码不正确");
        }
        String jwt = jwtUtils.generateToken(users.getUsername());

        response.setHeader("token", jwt);
        response.setHeader("Access-Control-Expose-Headers", "token");

        return Result.succ(MapUtil.builder()
                .put("username", users.getUsername())
                .put("roleId", users.getRoleId())
                .map()
        );
    }

    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.succ("退出登录成功！");
    }

    @RequiresAuthentication
    @PostMapping("/userList")
    public Result userList(@RequestParam(defaultValue = "1") Integer currentPage, @RequestParam(defaultValue = "5") Integer pageSize) {
        return Result.succ(usersMapper.getPage((currentPage - 1) * pageSize, pageSize));
    }
}
