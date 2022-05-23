package com.darkcoder.mapper;

import com.darkcoder.entity.Activity;
import com.darkcoder.entity.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Darkcoder
 * @since 2022-04-07
 */
public interface NoticeMapper extends BaseMapper<Notice> {
    @Select("SELECT * FROM notice;")
    List<Notice> getAllNotice();
}
