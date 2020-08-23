package com.ming.data.utils;

import com.ming.data.entity.Sinashare;

import java.util.Arrays;

public class SinasShareUtils {

    public static double checkIsBuyMax(Sinashare sinaShare){
        double numbers[]=new double[]{sinaShare.getBuy1(),sinaShare.getBuy2(),sinaShare.getBuy3(),sinaShare.getBuy4(),sinaShare.getBuy5()};
        Arrays.sort(numbers);
        return numbers[numbers.length-1];
    }

    public static double checkIsSellMax(Sinashare sinaShare){
        double numbers[]=new double[]{sinaShare.getSell1(),sinaShare.getSell2(),sinaShare.getSell3(),
                sinaShare.getSell4(),sinaShare.getSell5()};
        Arrays.sort(numbers);
        return numbers[numbers.length-1];
    }

    public static boolean checkIsBig(double price,double count){
        if(price!=0){
            if(price*count>12000){
                return true;
            }
        }
        return false;
    }

    /*大于400w标记*/
    public static boolean checkIsSmail(double price,double count){
        if(price!=0){
            if(price*count>40000){
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {

    }

}
