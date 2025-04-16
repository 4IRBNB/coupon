package com.fouribnb.coupon.domain.entity;

public enum CouponStatus {
    ACTIVE("활성"),
    EXPIRE("만료");

    private final String description;

    CouponStatus(String value) {
        this.description = value;
    }

    public String getValue() {
        return description;
    }
}
