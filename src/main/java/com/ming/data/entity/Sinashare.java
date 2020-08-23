package com.ming.data.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * SinaShare
 * @author 
 */
@Data
public class Sinashare implements Serializable {
    private Integer id;

    private String name;

    private Double yesterdayprice;

    private Double currentprice;

    private Double todayprice;

    private String range;

    private Integer buy1;

    private Double buy1price;

    private Integer buy2;

    private Double buy2price;

    private Integer buy3;

    private Double buy3price;

    private Integer buy4;

    private Double buy4price;

    private Integer buy5;

    private Double buy5price;

    private Integer sell1;

    private Double sell1price;

    private Integer sell2;

    private Double sell2price;

    private Integer sell3;

    private Double sell3price;

    private Integer sell4;

    private Double sell4price;

    private Integer sell5;

    private Double sell5price;

    private Integer buynum;

    private Double money;

    private Date currenttime;

    /**
     * 买大资金
     */
    private String type;

    /**
     * 买大资金
     */
    private String selltype;

    private Date create;

    /**
     * 小买资金
     */
    private String smailbuytype;

    /**
     * 小卖资金
     */
    private String smailselltype;

    private String rangetype;

    private static final long serialVersionUID = 1L;
}