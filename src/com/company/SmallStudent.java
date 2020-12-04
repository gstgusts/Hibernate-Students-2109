package com.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmallStudent {
    private String name;
    private String surname;

    public SmallStudent(Object obj) {
        var arr = (Object[])obj;
        name = (String)arr[0];
        surname = (String)arr[1];
    }
}
