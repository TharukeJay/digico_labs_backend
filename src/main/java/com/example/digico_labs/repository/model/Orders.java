package com.example.digico_labs.repository.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class Orders {
    private List<PackageData> packageData;
    private String total;

    @Setter
    @Getter
    @ToString
    public static class PackageData {
        private String packageType;
        private String qty;
        private String subTotal;
    }
}
