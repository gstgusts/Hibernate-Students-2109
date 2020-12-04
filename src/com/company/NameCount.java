package com.company;

import lombok.Data;

@Data
public class NameCount {
    private Long count;
    private String name;

    public NameCount(Object data) {
        var params = (Object[]) data;

        count = (Long)params[0];
        name = (String)params[1];
    }
}
