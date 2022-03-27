package com.darkcoder.mapper;

import com.darkcoder.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Darkcoder
 * @since 2022-02-16
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT username, role_id FROM `user` LIMIT #{ start }, #{ step };")
    List<User> getPage(@Param("start") Integer start,
                       @Param("step") Integer step
                    );
}
