package com.darkcoder.service.impl;

import com.darkcoder.entity.Users;
import com.darkcoder.mapper.UsersMapper;
import com.darkcoder.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Darkcoder
 * @since 2022-02-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

}
