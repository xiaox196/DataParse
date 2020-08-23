package com.ming.data.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * apiExeResult
 * @author 
 */
@Data
public class Apiexeresult implements Serializable {
    private Integer id;

    private String result;

    private Integer apiId;

    private Date creatTime;

    private static final long serialVersionUID = 1L;
}