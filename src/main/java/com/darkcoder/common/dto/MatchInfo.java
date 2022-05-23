package com.darkcoder.common.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class MatchInfo implements Serializable {
    private Integer studentId;

    private String studentName;

    private Integer tutorId;

    private String tutorName;

    private boolean tutorConfirm;
}
