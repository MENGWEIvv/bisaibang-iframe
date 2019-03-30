package com.mycompany.myapp.domain;

import java.util.List;

public class ResultTime {

    private String time;

    private List<Iframe> groups;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Iframe> getGroups() {
        return groups;
    }

    public void setGroups(List<Iframe> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "ResultTime{" +
            "time='" + time + '\'' +
            ", groups=" + groups +
            '}';
    }
}

