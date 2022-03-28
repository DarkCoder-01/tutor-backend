package com.darkcoder.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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
    private LocalDateTime submitTime;

    /**
     * 导师确认时间
     */
    private LocalDateTime confirmTime;

    private Integer roundNo;
}
