package com.fawry.coupon.exception;

public class CouponAlreadyExist extends RuntimeException {
    public CouponAlreadyExist(String message) {
        super(message);
    }
}
