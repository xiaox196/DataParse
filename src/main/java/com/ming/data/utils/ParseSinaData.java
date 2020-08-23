package com.ming.data.utils;
import com.ming.data.entity.Sinashare;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @author alun
 * @data 2019/11/15
 */
public class ParseSinaData {

    public static Sinashare getSinaShareData(String result) {
        Sinashare sinaShare = null;
        try {
            sinaShare = new Sinashare();
            Timestamp d = new Timestamp(System.currentTimeMillis());
            if (!result.equals("\\n") || !result.equals("") || result != null) {
                String[] myresult = result.split("=");
                if (myresult.length > 1) {
                    String name = myresult[0];
                    String splitData = myresult[1];
                    String data = splitData.substring(1, splitData.length() - 3);
                    if (!StringUtil.isEmpty(data)) {
                        String[] datas = data.split(",");
                        sinaShare = new Sinashare();
                        sinaShare.setName(datas[0]);
                        sinaShare.setTodayprice(StringUtil.toDouble(datas[1]));
                        double YesterdayPrice = StringUtil.toDouble(datas[2]);
                        sinaShare.setYesterdayprice(YesterdayPrice);
                        sinaShare.setCurrentprice(StringUtil.toDouble(datas[3]));
//                        sinaShare.setTodayTopPrice(StringUtil.toDouble(datas[4]));
//                        sinaShare.setTodayTopPrice(StringUtil.toDouble(datas[5]));

                        sinaShare.setBuynum(StringUtil.toInt(datas[8]) / 100);
                        sinaShare.setMoney(StringUtil.toDouble(datas[9]) / 10000);

                        int buy1 = StringUtil.toInt(datas[10]) / 100;
                        sinaShare.setBuy1(buy1);
                        int buy2 = StringUtil.toInt(datas[12]) / 100;
                        sinaShare.setBuy2(buy2);
                        int buy3 = StringUtil.toInt(datas[14]) / 100;
                        sinaShare.setBuy3(buy3);
                        int buy4 = StringUtil.toInt(datas[16]) / 100;
                        sinaShare.setBuy4(buy4);
                        int buy5 = StringUtil.toInt(datas[18]) / 100;
                        sinaShare.setBuy5(buy5);

                        sinaShare.setBuy1price(StringUtil.toDouble(datas[11]));
                        sinaShare.setBuy2price(StringUtil.toDouble(datas[13]));
                        sinaShare.setBuy3price(StringUtil.toDouble(datas[15]));
                        sinaShare.setBuy4price(StringUtil.toDouble(datas[17]));
                        sinaShare.setBuy5price(StringUtil.toDouble(datas[19]));

                        sinaShare.setSell1(StringUtil.toInt(datas[20]) / 100);
                        sinaShare.setSell1price(StringUtil.toDouble(datas[21]));
                        sinaShare.setSell2(StringUtil.toInt(datas[22]) / 100);
                        sinaShare.setSell2price(StringUtil.toDouble(datas[23]));
                        sinaShare.setSell3(StringUtil.toInt(datas[24]) / 100);
                        sinaShare.setSell3price(StringUtil.toDouble(datas[25]));
                        sinaShare.setSell4(StringUtil.toInt(datas[26]) / 100);
                        sinaShare.setSell4price(StringUtil.toDouble(datas[27]));
                        sinaShare.setSell5(StringUtil.toInt(datas[28]) / 100);
                        sinaShare.setSell5price(StringUtil.toDouble(datas[29]));

                        double count = SinasShareUtils.checkIsBuyMax(sinaShare);
                        if (SinasShareUtils.checkIsBig(sinaShare.getBuy1price(), count)) {
                            sinaShare.setType("B");
                        } else {
                            sinaShare.setType("S");
                        }

                        double sellCount = SinasShareUtils.checkIsSellMax(sinaShare);
                        if (SinasShareUtils.checkIsBig(sinaShare.getSell1price(), sellCount)) {
                            sinaShare.setSelltype("B");
                        } else {
                            sinaShare.setSelltype("S");
                        }

                        if (SinasShareUtils.checkIsSmail(sinaShare.getSell1price(), sellCount)) {
                            sinaShare.setSmailselltype("B");
                        } else {
                            sinaShare.setSmailselltype("S");
                        }

                        if (SinasShareUtils.checkIsSmail(sinaShare.getSell1(), count)) {
                            sinaShare.setSmailbuytype("B");
                        } else {
                            sinaShare.setSmailbuytype("S");
                        }

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String string = "";
                        if (name.contains("sz")) {
                            string = datas[datas.length - 2] + " " + datas[datas.length - 1];
                        } else {
                            string = datas[datas.length - 3] + " " + datas[datas.length - 2];
                        }
                        sinaShare.setCurrenttime(sdf.parse(string));
                        String range = MathUtils.NumberFormat(StringUtil.toDouble(datas[2]), StringUtil.toDouble(datas[3]));
                        sinaShare.setRange(range);
                        sinaShare.setRangetype(calcRange(range));
                        sinaShare.setCreate(d);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sinaShare;
    }

    /*
计算实时解析数据

规则：
1.range 大于-5小于-4  标记为
2.range大于-8小于-6.5
3.range大于-6.5小于-5
4.range大于-4小于-3
* */
    public static String calcRange(String range) {
        String info = null;
        if (null != range) {
            if (range.contains("%")) {
                range = range.split("%")[0];
            }
            double num = Double.parseDouble(range);
            if (num > -4 && num < -3) {
                info = "R1";
            } else if (num > -5 && num < -4) {
                info = "R2";
            } else if (num > -6 && num < -5) {
                info = "R3";
            } else if (num > -7 && num < -6) {
                info = "R4";
            } else if (num > -8 && num < -7) {
                info = "R5";
            } else if (num > 3 && num < 4) {
                info = "R6";
            } else if (num > 4 && num < 5) {
                info = "R7";
            } else if (num > 5 && num < 6) {
                info = "R8";
            } else if (num > -3 && num < -2) {
                info = "R9";
            } else if (num > -2 && num < -1) {
                info = "R10";
            } else if (num > -1 && num < 0) {
                info = "R11";
            } else {
                info = "R0";
            }
        }
        return info;
    }

    public static void main(String[] args) {
        System.out.println(calcRange("-5.5"));
    }

}
