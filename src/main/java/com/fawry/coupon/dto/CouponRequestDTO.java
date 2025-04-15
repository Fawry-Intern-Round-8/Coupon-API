package com.fawry.coupon.dto;

import com.fawry.coupon.entity.DiscountType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CouponRequestDTO {
    @NotBlank(message = "Coupon code cannot be empty")
    @Size(min = 3, max = 20, message = "Coupon code must be between 3 and 20 characters")
    private String code;

    @NotNull(message = "Coupon name cannot be empty")
    private DiscountType discountType;

    @NotNull(message = "Discount value is required")
    @Min(value = 1, message = "Discount value must be at least 1")
    private Integer value;

    @NotNull(message = "Maximum usage count is required")
    @Min(value = 1, message = "Maximum usage count must be at least 1")
    private Integer maxUsages;

    @NotNull(message = "Expiry date is required")
    @Future(message = "Expiry date must be in the future")
    private LocalDate expiryDate;

    private Boolean isActive = true;
}