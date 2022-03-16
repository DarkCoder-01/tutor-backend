package com.darkcoder.mapper;

import com.darkcoder.entity.Student;
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
public interface StudentMapper extends BaseMapper<Student> {
    //根据导师号查选择了该导师的学生列表
    @Select("SELECT student.student_id, name, major, sex, mail, phone, personal_description, specialty, tutor_expectation " +
            "FROM student " +
            "JOIN volunteer " +
            "ON volunteer.student_id = student.student_id " +
            "WHERE volunteer.volunteer_tutor = #{ tutorId } AND volunteer.tutor_confirm = #{ tutorConfirm };")
    List<Student> getStudentListByTutorId(@Param("tutorId") Integer tutorId,
                                          @Param("tutorConfirm") Integer tutorConfirm
    );
}
