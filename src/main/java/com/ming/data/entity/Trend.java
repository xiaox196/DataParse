package com.ming.data.entity;

/**
 * @author alun
 * @data 2018/12/28
 */
public class Trend {
    private int id;
    private String singleTrend;
    private String sizeTrend;
    private String zodiacSignsTrend;
    private String colorWaveTrend;
    private String numberTrend;
    private String zfTrend;
    private String periods;
    private String year;
    private String ncTrend;

    public void setPeriods(String periods) {
        this.periods = periods;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPeriods() {
        return periods;
    }

    public String getYear() {
        return year;
    }

    public String getSingleTrend() {
        return singleTrend;
    }

    public void setSingleTrend(String singleTrend) {
        this.singleTrend = singleTrend;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSizeTrend(String sizeTrend) {
        this.sizeTrend = sizeTrend;
    }

    public void setZodiacSignsTrend(String zodiacSignsTrend) {
        this.zodiacSignsTrend = zodiacSignsTrend;
    }

    public void setColorWaveTrend(String colorWaveTrend) {
        this.colorWaveTrend = colorWaveTrend;
    }

    public void setNumberTrend(String numberTrend) {
        this.numberTrend = numberTrend;
    }

    public void setZfTrend(String zfTrend) {
        this.zfTrend = zfTrend;
    }

    public void setNcTrend(String ncTrend) {
        this.ncTrend = ncTrend;
    }

    public int getId() {
        return id;
    }

    public String getSizeTrend() {
        return sizeTrend;
    }

    public String getZodiacSignsTrend() {
        return zodiacSignsTrend;
    }

    public String getColorWaveTrend() {
        return colorWaveTrend;
    }

    public String getNumberTrend() {
        return numberTrend;
    }

    public String getZfTrend() {
        return zfTrend;
    }

    public String getNcTrend() {
        return ncTrend;
    }
}
