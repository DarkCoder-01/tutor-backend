package com.darkcoder.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.darkcoder.common.lang.Result;
import com.darkcoder.entity.Tutor;
import com.darkcoder.mapper.TutorMapper;
import com.darkcoder.service.TutorService;
import com.darkcoder.util.JwtUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Darkcoder
 * @since 2022-02-16
 */
@RestController
public class TutorController {
    @Autowired
    TutorService tutorService;

    @Autowired
    JwtUtils jwtUtils;

    @Resource
    TutorMapper tutorMapper;

    //根据前端提交的currentPage和pageSize进行分页查询
    @RequiresAuthentication
    @GetMapping("/tutorList")
    public Object tutorList(@RequestParam(defaultValue = "1") Integer currentPage, @RequestParam(defaultValue = "5") Integer pageSize) {
        Page page = new Page(currentPage, pageSize);
        QueryWrapper<Tutor> queryWrapper = new QueryWrapper<>();
        IPage iPage = tutorService.page(page, queryWrapper.orderByDesc("tutor_id"));
        return Result.succ(iPage);
    }

    @RequiresAuthentication
    @GetMapping("/tutorInfo")
    public Object tutorInfo(@RequestParam Integer tutorId) {
        Tutor tutor = tutorService.getOne(new QueryWrapper<Tutor>().eq("tutor_id", tutorId));
        return Result.succ(tutor);
    }

    @RequiresAuthentication
    @GetMapping("/myTutor")
    public Object tutorOfStudent(@RequestParam Integer studentId) {
        return Result.succ(tutorMapper.getTutorInfoOf(studentId, 1));
    }

    @RequiresAuthentication
    @GetMapping("/myVolunteerTutor")
    public Object volunteerTutorOf(@RequestParam Integer studentId) {
        return Result.succ(tutorMapper.getTutorInfoOf(studentId, 0));
    }

    @RequiresAuthentication
    @PostMapping("/tutorInfo/edit")
    public Object editTutorInfo(@Validated @RequestBody Tutor tutor, HttpServletRequest request) {
        //从登录凭证中取出username
        String userName = jwtUtils.getClaimByToken(request.getHeader("token")).getSubject();
        Integer tutorId = Integer.valueOf(userName);

        if(tutor.getTutorId() != null) {
            //只能编辑自己的信息
            Assert.isTrue(tutor.getTutorId().equals(tutorId), "无权限编辑");

            Tutor tutorInfo = tutorService.getOne(new QueryWrapper<Tutor>().eq("tutor_id", tutorId));

            BeanUtil.copyProperties(tutor, tutorInfo, "tutor_id", "name", "current_choose");

            tutorService.saveOrUpdate(tutorInfo);

            return Result.succ("修改个人信息成功！");
        } else {
            return Result.succ("修改个人信息失败！");
        }
    }

    @RequiresAuthentication
    @GetMapping("/tutor/delete")
    public Object deleteTutor(@RequestParam Integer tutorId) {
        tutorService.remove(new QueryWrapper<Tutor>().eq("tutor_id", tutorId));
        return Result.succ(null);
    }

    @RequiresAuthentication
    @PostMapping("/tutor/edit")
    public Object deleteTutor(@Validated @RequestBody Tutor tutor) {
        tutorService.saveOrUpdate(tutor);
        return Result.succ(null);
    }
}
