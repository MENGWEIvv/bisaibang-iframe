package com.mycompany.myapp.domain;

import java.util.List;

public class EndResult {

    private String name;

    private List<IFrameName> stages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IFrameName> getStages() {
        return stages;
    }

    public void setStages(List<IFrameName> stages) {
        this.stages = stages;
    }

    @Override
    public String toString() {
        return "EndResult{" +
            "name='" + name + '\'' +
            ", stages=" + stages +
            '}';
    }
}
