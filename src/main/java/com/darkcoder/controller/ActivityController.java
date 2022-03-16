package com.darkcoder.controller;


import cn.hutool.core.bean.BeanUtil;
import com.darkcoder.common.lang.Result;
import com.darkcoder.entity.Activity;
import com.darkcoder.mapper.ActivityMapper;
import com.darkcoder.service.ActivityService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Darkcoder
 * @since 2022-02-20
 */
@RestController
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @Resource
    ActivityMapper activityMapper;

    @RequiresAuthentication
    @PostMapping("/activity/add")
    public Object addActivity(@Validated @RequestBody Activity activity) {
        activityService.saveOrUpdate(activity);
        return Result.succ(null);
    }

    @RequiresAuthentication
    @GetMapping("/activity/list")
    public Object listActivity() {
        return Result.succ(activityMapper.getAllActivity());
    }

    @RequiresAuthentication
    @GetMapping("/activity/toggle")
    public Object openActivity(@RequestParam Integer id, @RequestParam boolean enable) {
        activityMapper.toggleActivityEnable( id, enable );
        return Result.succ(null);
    }

    @RequiresAuthentication
    @GetMapping("/activity/delete")
    public Object deleteActivity(@RequestParam Integer id) {
        activityService.removeById(id);
        return Result.succ(null);
    }

    @RequiresAuthentication
    @GetMapping("/activity/current")
    public Object currentEnabledActivity() {
        return Result.succ(activityMapper.getCurrentActivity());
    }
}
