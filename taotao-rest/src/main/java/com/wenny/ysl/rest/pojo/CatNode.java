package com.wenny.ysl.rest.pojo;

//import org.codehaus.jackson.annotate.JsonProperty;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CatNode {

    @JsonProperty(value = "n")
    private String name;
    @JsonProperty(value = "u")
    private String url;
    @JsonProperty(value = "i")
    private List<?> item;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<?> getItem() {
        return item;
    }

    public void setItem(List<?> item) {
        this.item = item;
    }
}
