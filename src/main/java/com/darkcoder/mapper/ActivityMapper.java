package com.darkcoder.mapper;

import com.darkcoder.entity.Activity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
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
 * @since 2022-02-20
 */
public interface ActivityMapper extends BaseMapper<Activity> {

//    @Select("SELECT * FROM activity WHERE NOW() BETWEEN start_time AND end_time AND enable = 1;")
    @Select("SELECT * FROM activity WHERE enable = 1;")
    List<Activity> getCurrentActivity();

    @Select("SELECT * FROM activity order by type;")
    List<Activity> getAllActivity();

    @Update("UPDATE activity SET enable = #{ enable } WHERE id = #{ id };")
    Integer toggleActivityEnable(@Param("id") Integer id,
                                 @Param("enable") boolean enable
    );
}
