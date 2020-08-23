package com.ming.data.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * @author alun
 * @data 2020/4/15
 */
@RestController
public class BaseCls {
    @Autowired
    public HttpServletRequest request;
    public  static SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
    public  static SimpleDateFormat df_year= new SimpleDateFormat("yyyy-MM-dd");
    public DecimalFormat de = new DecimalFormat("0.00");
}
