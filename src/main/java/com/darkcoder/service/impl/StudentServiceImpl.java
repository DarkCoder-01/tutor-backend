package com.darkcoder.service.impl;

import com.darkcoder.entity.Student;
import com.darkcoder.mapper.StudentMapper;
import com.darkcoder.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Darkcoder
 * @since 2022-02-16
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
