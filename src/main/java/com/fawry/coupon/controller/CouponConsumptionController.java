package com.fawry.coupon.controller;

import com.fawry.coupon.dto.CouponConsumptionRequest;
import com.fawry.coupon.dto.CouponConsumptionResponse;
import com.fawry.coupon.entity.CouponConsumption;
import com.fawry.coupon.service.CouponConsumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/coupons/transactions")
public class CouponConsumptionController {
    private final CouponConsumptionService couponConsumptionService;

    @Autowired
    public CouponConsumptionController(CouponConsumptionService couponConsumptionService) {
        this.couponConsumptionService = couponConsumptionService;
    }

    @PostMapping
    public CouponConsumption createCouponHistory(@RequestBody CouponConsumptionRequest couponRequestDTO) {
        return couponConsumptionService.createCouponHistory(couponRequestDTO);
    }

    @GetMapping
    public List<CouponConsumptionResponse> getCouponHistory(@RequestParam(required = false) Long couponId, @RequestParam(required = false) String orderId) {
        if (couponId != null) {
            return couponConsumptionService.getCouponHistoryByCouponId(couponId);
        } else if (orderId != null) {
            return couponConsumptionService.getCouponHistoryByOrderId(orderId);
        } else {
            return couponConsumptionService.getCouponHistory();
        }
    }
}
