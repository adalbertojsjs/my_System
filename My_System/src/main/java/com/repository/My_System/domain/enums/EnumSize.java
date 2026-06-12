package com.repository.My_System.domain.enums;

import java.math.BigDecimal;

public enum EnumSize {

    SMALL(BigDecimal.valueOf(4.0)),
    LARGE(BigDecimal.valueOf(5.0));

    private final BigDecimal price;

    EnumSize(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
