package com.fawry.coupon.controller;

import com.fawry.coupon.dto.ApiResponse;
import com.fawry.coupon.dto.CouponConsumptionResponse;
import com.fawry.coupon.dto.CouponRequestDTO;
import com.fawry.coupon.dto.CouponValidationResponse;
import com.fawry.coupon.entity.Coupon;
import com.fawry.coupon.exception.CouponAlreadyExist;
import com.fawry.coupon.exception.CouponNotFoundException;
import com.fawry.coupon.service.CouponService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/coupons")
public class CouponController {
    private final CouponService couponService;

    @Autowired
    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createCoupon(@Valid @RequestBody CouponRequestDTO couponRequestDTO) {
        try {
            Coupon coupon = couponService.createCoupon(couponRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(coupon));
        } catch (CouponAlreadyExist e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getCoupons(@RequestParam(required = false) Long id, @RequestParam(required = false) String code) {
        try {
            ApiResponse response;
            if (id != null) {
                response = new ApiResponse(List.of(couponService.getCouponById(id)));
            } else if (code != null) {
                response = new ApiResponse(List.of(couponService.getCouponByCode(code)));
            } else {
                response = new ApiResponse(couponService.getAllCoupons());
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (CouponNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCoupon(@PathVariable Long id) {
        couponService.deleteCoupon(id);
    }

    @GetMapping("/validate")
    public ResponseEntity<CouponValidationResponse> validateCoupon(@RequestParam String code) {
        try {
            return ResponseEntity.ok((couponService.validateCoupon(code)));
        } catch (CouponNotFoundException e) {
            throw new CouponNotFoundException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCoupon(@Valid @RequestBody CouponRequestDTO couponRequestDTO, @PathVariable Long id) {
        try {
            Coupon coupon = couponService.updateCoupon(id, couponRequestDTO);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(coupon));
        } catch (CouponAlreadyExist e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(e.getMessage()));
        } catch (CouponNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
        }
    }
}
