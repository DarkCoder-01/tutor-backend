package com.darkcoder.mapper;

import com.darkcoder.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Darkcoder
 * @since 2022-02-16
 */
public interface UserMapper extends BaseMapper<User> {

    @Update("UPDATE user SET current_step = #{ currentStep } WHERE username = #{ username };")
    Integer setCurrentStep(@Param("currentStep") Integer currentStep,
                           @Param("username") String username
    );

    @Select("SELECT current_step FROM user WHERE username = #{ username };")
    Integer getCurrentStep(@Param("username") String username);
}
