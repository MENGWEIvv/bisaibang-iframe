package com.mycompany.myapp.domain;

import java.util.List;

public class ResultTime {

    private String time;

    private List<String> groups;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
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

