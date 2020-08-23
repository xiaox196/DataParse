package com.ming.data.utils;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;


/**
 * @author alun
 * @data 2020/5/9
 */
public class ExcelUtils {

    public static void save(Workbook workbook,String path){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
            workbook.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
