package com.darkcoder.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class Tutor implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "tutor_id")
    private Integer tutorId;

    private String name;

    private String title;

    private String researchDirection;

    private Integer studentMax;

    private Integer currentChoose;

    private Integer currentMatched;

    private String office;

    private String phone;

    private String mail;

    private String personalDescription;

    private String researchResult;


}
