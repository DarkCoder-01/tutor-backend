package com.darkcoder.controller;


import com.darkcoder.common.lang.Result;
import com.darkcoder.entity.Notice;
import com.darkcoder.mapper.NoticeMapper;
import com.darkcoder.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Darkcoder
 * @since 2022-04-07
 */
@RestController
public class NoticeController {
    @Autowired
    NoticeService noticeService;

    @Resource
    NoticeMapper noticeMapper;

    @PostMapping("/notice/add")
    public Result addNotice(@Validated @RequestBody Notice notice) {
        LocalDateTime nowDate = LocalDateTime.now();
        notice.setCreateTime(nowDate);

        noticeService.saveOrUpdate(notice);
        return Result.succ(null);
    }

    @GetMapping("/notice/list")
    public Result getNoticeList() {
        return Result.succ(noticeMapper.getAllNotice());
    }

    @GetMapping("/notice/delete")
    public Object deleteNotice(@RequestParam Integer id) {
        noticeService.removeById(id);
        return Result.succ(null);
    }

    @GetMapping("/notice/detail")
    public Object noticeDetail(@RequestParam Integer id) {
        return Result.succ(noticeService.getById(id));
    }
}
