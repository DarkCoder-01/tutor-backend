package com.darkcoder.mapper;

import com.darkcoder.entity.Tutor;
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
public interface TutorMapper extends BaseMapper<Tutor> {

    @Select("SELECT DISTINCT tutor.tutor_id, name, title, research_direction, office, phone, mail, personal_description, research_result " +
            "FROM tutor " +
            "JOIN volunteer " +
            "ON tutor.tutor_id = volunteer.volunteer_tutor " +
            "WHERE volunteer.tutor_confirm = #{ tutorConfirm } AND volunteer.student_id = #{ studentId };"
    )
    List<Tutor> getTutorInfoOf(@Param("studentId") Integer studentId,
                               @Param("tutorConfirm") Integer tutorConfirm
    );
//        @Select("SELECT DISTINCT tutor.tutor_id, name, title, research_direction, office, phone, mail, personal_description, research_result, volunteer.tutor_confirm " +
//                "FROM tutor " +
//                "JOIN volunteer " +
//                "ON tutor.tutor_id = volunteer.volunteer_tutor " +
//                "WHERE volunteer.student_id = #{ studentId };"
//        )
//        List<Tutor> getTutorInfoOf(@Param("studentId") Integer studentId);
}
