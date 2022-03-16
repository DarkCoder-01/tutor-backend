package com.darkcoder.controller;



import com.darkcoder.common.dto.ChooseStudentDto;
import com.darkcoder.common.dto.VolunteerDto;
import com.darkcoder.common.lang.Result;
import com.darkcoder.entity.Volunteer;
import com.darkcoder.mapper.VolunteerMapper;
import com.darkcoder.service.VolunteerService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Darkcoder
 * @since 2022-02-18
 */
@RestController
public class VolunteerController {

    @Autowired
    VolunteerService volunteerService;

    @Resource
    VolunteerMapper volunteerMapper;

    @RequiresAuthentication
    @PostMapping("/volunteer/submit")
    public Object submitVolunteer(@Validated @RequestBody VolunteerDto volunteerDto) {
        volunteerMapper.removeOldVolunteerOfStudent(volunteerDto.getStudentId());
        List<Integer> volunteerList = volunteerDto.getVolunteerList();

        LocalDateTime nowDate = LocalDateTime.now();

        Volunteer volunteer = new Volunteer();

        volunteerList.forEach(volunteerTutor -> {

            volunteer.setStudentId(volunteerDto.getStudentId());
            volunteer.setVolunteerTutor(volunteerTutor);
            volunteer.setTutorConfirm(0);
            volunteer.setSubmitTime(nowDate);

            volunteerService.save(volunteer);
        });

        return Result.succ("志愿提交成功");
    }

    @RequiresAuthentication
    @PostMapping("/volunteer/tutorConfirm")
    public Object tutorChooseStudent(@Validated @RequestBody ChooseStudentDto chooseStudentDto) {

        List<Integer> studentList = chooseStudentDto.getStudentList();

        LocalDateTime nowDate = LocalDateTime.now();

        studentList.forEach(studentId -> volunteerMapper.tutorConfirmVolunteer(nowDate, studentId, chooseStudentDto.getTutorId()));

        return Result.succ("提交成功！");
    }
}
