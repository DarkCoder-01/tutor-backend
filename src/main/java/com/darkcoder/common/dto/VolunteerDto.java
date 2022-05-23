package com.darkcoder.common.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author 徐泽爽
 * @version 1.0
 * @date 2022/2/16 22:55
 */
@Data
public class VolunteerDto implements Serializable {
    @NotNull(message = "学号不能为空")
    private Integer studentId;

    @NotNull(message = "志愿导师不能为空")
    private Integer volunteerTutor;
}
