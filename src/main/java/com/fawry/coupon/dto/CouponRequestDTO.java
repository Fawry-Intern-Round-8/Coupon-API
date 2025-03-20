package com.fawry.coupon.dto;

import com.fawry.coupon.entity.DiscountType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CouponRequestDTO {
    private String code;
    private DiscountType discountType;
    private Integer value;
    private Integer maxUsages;
    private LocalDateTime expiryDate;
    private Boolean isActive;
}