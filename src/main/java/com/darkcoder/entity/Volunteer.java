package com.darkcoder.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 
 * </p>
 *
 * @author Darkcoder
 * @since 2022-02-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Volunteer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生ID，主键
     */
    @NotNull(message = "学号不能为空")
    @TableId(value = "student_id")
    private Integer studentId;

    /**
     * 志愿导师ID
     */
    private Integer volunteerTutor;

    /**
     * 导师是否确认，0表示未确认，1表示确认
     */
    private boolean tutorConfirm;

    /**
     * 志愿提交时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime submitTime;

    /**
     * 导师确认时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime confirmTime;
}
