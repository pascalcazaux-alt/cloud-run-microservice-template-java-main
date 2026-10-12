package com.cloudrun.microservicetemplate.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Parameter {
    public String Data1;
    public String Data2;

    @JsonProperty("Data1")
    public String getData1() {
        return Data1;
    }
    @JsonProperty("Data2")
    public String getData2() {
        return Data2;
    }

}
