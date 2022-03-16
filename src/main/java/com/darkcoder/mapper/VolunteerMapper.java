package com.darkcoder.mapper;

import com.darkcoder.entity.Volunteer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Darkcoder
 * @since 2022-02-18
 */
public interface VolunteerMapper extends BaseMapper<Volunteer> {
    //删除指定学生的志愿
    @Delete("DELETE FROM volunteer WHERE student_id = #{ studentId };")
    Integer removeOldVolunteerOfStudent(@Param("studentId") Integer studentId);

    @Insert("INSERT INTO volunteer " +
            "SET (student_id, volunteer_tutor, tutor_confirm, submit_time, confirm_time) " +
            "VALUES (#{ studentId }, #{ volunteerTutor }, #{ tutorConfirm }, #{ submitTime }, #{ confirmTime })")
    Integer updateVolunteerOfStudent(@Param("studentId") Integer studentId,
                                     @Param("volunteerTutor") Integer volunteerTutor,
                                     @Param("tutorConfirm") Integer tutorConfirm,
                                     @Param("submitTime") LocalDateTime submitTime,
                                     @Param("confirmTime") Integer confirmTime
                                     );

    @Update("UPDATE volunteer SET tutor_confirm = 1, confirm_time = #{ confirmTime } WHERE student_id = #{ studentId } AND volunteer_tutor = #{ volunteerTutor };")
    Integer tutorConfirmVolunteer(@Param("confirmTime") LocalDateTime confirmTime,
                                  @Param("studentId") Integer studentId,
                                  @Param("volunteerTutor") Integer volunteerTutor
    );
}
