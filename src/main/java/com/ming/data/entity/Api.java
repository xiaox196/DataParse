package com.ming.data.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * api
 * @author 
 */
@Data
public class Api implements Serializable {
    private Integer id;

    @NotBlank(message = "名称不能为空")
    private String name;
    @NotBlank(message = "方法不能为空")
    private String method;
    @NotBlank(message = "路径不能为空")
    private String path;

    private String params;

    private String remark;

    private Date createTime;

    private String creater;

    private String project;
    @NotBlank(message = "url地址不能为空")
    private String root;

    private Integer port;

    private Integer groups;

    private static final long serialVersionUID = 1L;
}