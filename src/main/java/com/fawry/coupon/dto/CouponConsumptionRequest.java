package com.fawry.coupon.dto;

import lombok.Data;

@Data
public class CouponConsumptionRequest {
    private String code;
    private String orderId;
}
