package com.ming.data.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * groups
 * @author 
 */
@Data
public class Groups implements Serializable {
    private Integer id;

    private Integer groupId;

    private String name;

    private Date createTime;

    private String operator;

    private static final long serialVersionUID = 1L;
}