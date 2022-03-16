package com.darkcoder.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.darkcoder.common.lang.Result;
import com.darkcoder.entity.Student;
import com.darkcoder.entity.Tutor;
import com.darkcoder.mapper.StudentMapper;
import com.darkcoder.service.StudentService;
import com.darkcoder.util.JwtUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Darkcoder
 * @since 2022-02-16
 */
@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    @Autowired
    JwtUtils jwtUtils;

    @Resource
    StudentMapper studentMapper;

    @RequiresAuthentication
    @GetMapping("/studentInfo")
    public Object getStudentInfoById(@RequestParam String studentId) {
        Student student = studentService.getOne(new QueryWrapper<Student>().eq("student_id", studentId));
        return Result.succ(student);
    }

    @RequiresAuthentication
    @PostMapping("/studentInfo/edit")
    public Object editStudentInfo(@Validated @RequestBody Student studentInfo, HttpServletRequest request) {
        //从登录凭证中取出username
        String userName = jwtUtils.getClaimByToken(request.getHeader("token")).getSubject();
        Integer studentId = Integer.valueOf(userName);

        if(studentInfo.getStudentId() != null) {
            //只能编辑自己的信息
            Assert.isTrue(studentInfo.getStudentId().equals(studentId), "无权限编辑");

            //如果token中的username和提交的student_id一致，查数据库准备修改
            Student student = studentService.getOne(new QueryWrapper<Student>().eq("student_id", studentId));
            //学号、姓名、专业不能编辑，拷贝的时候忽略
            BeanUtil.copyProperties(studentInfo, student, "student_id", "name", "major");

            //执行更新操作
            studentService.saveOrUpdate(student);

            return Result.succ("修改个人信息成功！");
        }
        else {
            return Result.succ("修改个人信息失败！");
        }
    }

    @RequiresAuthentication
    @GetMapping("/studentList")
    public Object getStudentListByTutorId(@RequestParam Integer tutorId) {
        return Result.succ(studentMapper.getStudentListByTutorId(tutorId, 0));
    }

    @RequiresAuthentication
    @GetMapping("/studentListConfirmed")
    public Object getStudentListConfirmed(@RequestParam Integer tutorId) {
        return Result.succ(studentMapper.getStudentListByTutorId(tutorId, 1));
    }

    @RequiresAuthentication
    @GetMapping("/student/list")
    public Object getStudentList(@RequestParam(defaultValue = "1") Integer currentPage, @RequestParam(defaultValue = "5") Integer pageSize) {
        Page page = new Page(currentPage, pageSize);
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        IPage iPage = studentService.page(page, queryWrapper.orderByDesc("student_id"));
        return Result.succ(iPage);
    }

    @RequiresAuthentication
    @GetMapping("/student/delete")
    public Object deleteStudent(@RequestParam String studentId) {
        studentService.remove(new QueryWrapper<Student>().eq("student_id", studentId));
        return Result.succ(null);
    }

    @RequiresAuthentication
    @PostMapping("/student/edit")
    public Object editStudent(@Validated @RequestBody Student student) {
        studentService.saveOrUpdate(student);
        return Result.succ(null);
    }
}
