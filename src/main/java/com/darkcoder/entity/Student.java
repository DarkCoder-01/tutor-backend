package com.darkcoder.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 
 * </p>
 *
 * @author Darkcoder
 * @since 2022-02-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Student implements Serializable {

    
    private static final long serialVersionUID = 1L;

    @NotNull(message = "学号不能为空")
    @TableId(value = "student_id")
    private Integer studentId;

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotBlank(message = "专业不能为空")
    private String major;

    @NotBlank(message = "性别不能为空")
    private String sex;

    @NotBlank(message = "邮箱不能为空")
    private String mail;

    @NotBlank(message = "手机号不能为空")
    private String phone;

    @NotBlank(message = "个人简介不能为空")
    private String personalDescription;

    @NotBlank(message = "特长不能为空")
    private String specialty;

    @NotBlank(message = "导师期望不能为空")
    private String tutorExpectation;


}
