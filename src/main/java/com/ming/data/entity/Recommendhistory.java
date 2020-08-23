package com.ming.data.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * recommendHistory
 * @author 
 */
@Data
public class Recommendhistory implements Serializable {
    private Integer id;

    private String name;

    private Date createtime;

    private Integer selllargebuyandrangel2;

    private Integer amselllebuyandafterbuylesell;

    private Integer spbuylgselltwo;

    private double score;

    private static final long serialVersionUID = 1L;
}