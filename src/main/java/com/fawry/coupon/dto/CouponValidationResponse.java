package com.fawry.coupon.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fawry.coupon.entity.DiscountType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CouponValidationResponse {
    private Boolean isValid;
    private Integer value;
    private DiscountType discountType;

    public CouponValidationResponse(Boolean isValid) {
        this.isValid = isValid;
    }
}
