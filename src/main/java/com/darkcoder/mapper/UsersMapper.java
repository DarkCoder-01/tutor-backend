package com.darkcoder.mapper;

import com.darkcoder.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Darkcoder
 * @since 2022-02-16
 */
public interface UsersMapper extends BaseMapper<Users> {

    @Select("SELECT username, role_id FROM users LIMIT #{ start }, #{ step };")
    List<Users> getPage(@Param("start") Integer start,
                        @Param("step") Integer step
                    );
}
