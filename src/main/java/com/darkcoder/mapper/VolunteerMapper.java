package com.darkcoder.mapper;

import com.darkcoder.common.dto.MatchInfo;
import com.darkcoder.entity.Volunteer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Darkcoder
 * @since 2022-02-18
 */
public interface VolunteerMapper extends BaseMapper<Volunteer> {
    @Select("SELECT student.student_id, student.\"name\" AS student_name, tutor.tutor_id, tutor.\"name\" AS tutor_name, volunteer.tutor_confirm \n" +
            "FROM volunteer JOIN student ON volunteer.student_id = student.student_id \n" +
            "JOIN tutor ON volunteer.volunteer_tutor = tutor.tutor_id;")
    List<MatchInfo> getMatchInfo();

    @Update("UPDATE volunteer SET tutor_confirm = 1, confirm_time = #{ confirmTime } WHERE student_id = #{ studentId } AND volunteer_tutor = #{ volunteerTutor };")
    Integer tutorConfirmVolunteer(@Param("confirmTime") LocalDateTime confirmTime,
                                  @Param("studentId") Integer studentId,
                                  @Param("volunteerTutor") Integer volunteerTutor
    );
}
