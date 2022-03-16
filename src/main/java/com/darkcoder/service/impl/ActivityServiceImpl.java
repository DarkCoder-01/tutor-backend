package com.darkcoder.service.impl;

import com.darkcoder.entity.Activity;
import com.darkcoder.mapper.ActivityMapper;
import com.darkcoder.service.ActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Darkcoder
 * @since 2022-02-20
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

}
