package com.spring.DataWarehouse.response;

public class Statistic {
    String stage;
    String info;

    public Statistic() {
    }

    public Statistic(String stage, String info) {
        this.stage = stage;
        this.info = info;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
