package com.mycompany.myapp.domain;


import java.util.List;

public class IFrameName {

    private String stage;

    private List<ResultTime> times;

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public List<ResultTime> getTimes() {
        return times;
    }

    public void setTimes(List<ResultTime> times) {
        this.times = times;
    }

    @Override
    public String toString() {
        return "IFrameName{" +
            "stage='" + stage + '\'' +
            ", times=" + times +
            '}';
    }
}
