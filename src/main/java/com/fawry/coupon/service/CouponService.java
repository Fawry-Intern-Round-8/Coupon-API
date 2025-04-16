package com.fawry.coupon.service;

import com.fawry.coupon.dto.CouponRequestDTO;
import com.fawry.coupon.dto.CouponValidationResponse;
import com.fawry.coupon.entity.Coupon;
import com.fawry.coupon.exception.CouponAlreadyExist;
import com.fawry.coupon.exception.CouponNotFoundException;
import com.fawry.coupon.repo.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CouponService {
    private final CouponRepo couponRepo;

    @Autowired
    public CouponService(CouponRepo couponRepo, CouponRepo couponRepo1) {
        this.couponRepo = couponRepo1;
    }

    public Coupon createCoupon(CouponRequestDTO couponRequestDTO) throws CouponAlreadyExist {
        Coupon coupon = new Coupon();
        coupon.setCode(couponRequestDTO.getCode());
        coupon.setDiscountType(couponRequestDTO.getDiscountType());
        coupon.setValue(couponRequestDTO.getValue());
        coupon.setMaxUsages(couponRequestDTO.getMaxUsages());
        coupon.setCurrentUsages(0);
        coupon.setExpiryDate(couponRequestDTO.getExpiryDate());
        coupon.setCreatedAt(LocalDate.now());
        coupon.setUpdatedAt(LocalDate.now());
        coupon.setIsActive(couponRequestDTO.getIsActive());
        try {
            return couponRepo.save(coupon);
        } catch (Exception e) {
            throw new CouponAlreadyExist("Coupon with this code already exists");
        }
    }

    public List<Coupon> getAllCoupons() {
        return couponRepo.findAll();
    }

    public Coupon getCouponById(Long id) throws CouponNotFoundException {
        return couponRepo.findById(id).orElseThrow(() -> new CouponNotFoundException("Not found a coupon with this id"));
    }

    public Coupon getCouponByCode(String code) throws CouponNotFoundException {
        return couponRepo.findByCode(code).orElseThrow(() -> new CouponNotFoundException("Not found a coupon with this code"));
    }

    public void deleteCoupon(Long id) {
        couponRepo.deleteById(id);
    }

    public CouponValidationResponse validateCoupon(String code) throws CouponNotFoundException {
        Coupon coupon = getCouponByCode(code);
        if (coupon.getIsActive() && coupon.getExpiryDate().isAfter(LocalDate.now()) && coupon.getCurrentUsages() < coupon.getMaxUsages()) {
            return new CouponValidationResponse(true, coupon.getValue(), coupon.getDiscountType());
        } else {
            return new CouponValidationResponse(false);
        }
    }

    public Coupon updateCoupon(Long id, CouponRequestDTO couponRequestDTO) throws CouponAlreadyExist {
        Coupon coupon = getCouponById(id);
        coupon.setCode(couponRequestDTO.getCode());
        coupon.setDiscountType(couponRequestDTO.getDiscountType());
        coupon.setValue(couponRequestDTO.getValue());
        coupon.setMaxUsages(couponRequestDTO.getMaxUsages());
        coupon.setExpiryDate(couponRequestDTO.getExpiryDate());
        coupon.setUpdatedAt(LocalDate.now());
        coupon.setIsActive(couponRequestDTO.getIsActive());
        try {
            return couponRepo.save(coupon);
        } catch (Exception e) {
            throw new CouponAlreadyExist("Coupon with this code already exists");
        }
    }

    public void incrementCouponUsage(String code) {
        Coupon coupon = getCouponByCode(code);
        coupon.setCurrentUsages(coupon.getCurrentUsages() + 1);
        couponRepo.save(coupon);
    }
}
