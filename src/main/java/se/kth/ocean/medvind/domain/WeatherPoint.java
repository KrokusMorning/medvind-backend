package se.kth.ocean.medvind.domain;

import java.util.Date;

public class WeatherPoint {

    private Date time;
    private Double temp;
    private int wd;
    private Double ws;
    private Double pMin;
    private Double pMax;
    private int directionMatch;
    private double rating;




    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public int getWd() {
        return wd;
    }

    public void setWd(int wd) {
        this.wd = wd;
    }

    public Double getWs() {
        return ws;
    }

    public void setWs(Double ws) {
        this.ws = ws;
    }

    public Double getpMin() {
        return pMin;
    }

    public void setpMin(Double pMin) {
        this.pMin = pMin;
    }

    public Double getpMax() {
        return pMax;
    }

    public void setpMax(Double pMax) {
        this.pMax = pMax;
    }

    public int getDirectionMatch() {
        return directionMatch;
    }

    public void setDirectionMatch(int directionMatch) {
        this.directionMatch = directionMatch;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
