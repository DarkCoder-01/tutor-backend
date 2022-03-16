package com.darkcoder.common.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author 徐泽爽
 * @version 1.0
 * @date 2022/2/16 22:55
 */
@Data
public class ChooseStudentDto implements Serializable {
    @NotNull(message = "导师号不能为空")
    private Integer tutorId;

    @NotNull(message = "学生列表不能为空")
    private List<Integer> studentList;
}
