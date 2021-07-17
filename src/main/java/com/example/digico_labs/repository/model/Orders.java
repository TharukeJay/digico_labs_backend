package com.example.digico_labs.repository.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Orders {
    private String packageType;
    private String qty;
    private String subTotal;
    private String total;
}
