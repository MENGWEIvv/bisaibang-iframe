package com.mycompany.myapp.domain;

import java.util.List;

public class ResultJSON {

    private String iframe;

    private List<Iframe> data;

    public String getIframe() {
        return iframe;
    }

    public void setIframe(String iframe) {
        this.iframe = iframe;
    }

    public List<Iframe> getData() {
        return data;
    }

    public void setData(List<Iframe> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultJSON{" +
            "iframe='" + iframe + '\'' +
            ", data=" + data +
            '}';
    }
}
