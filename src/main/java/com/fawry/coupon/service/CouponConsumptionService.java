package com.fawry.coupon.service;

import com.fawry.coupon.dto.CouponConsumptionRequest;
import com.fawry.coupon.dto.CouponConsumptionResponse;
import com.fawry.coupon.entity.CouponConsumption;
import com.fawry.coupon.mapper.CouponConsumptionMapper;
import com.fawry.coupon.repo.CouponConsumptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CouponConsumptionService {
    private final CouponConsumptionRepo couponConsumptionRepo;
    private final CouponService couponService;

    @Autowired
    public CouponConsumptionService(CouponConsumptionRepo couponConsumptionRepo, CouponService couponService) {
        this.couponConsumptionRepo = couponConsumptionRepo;
        this.couponService = couponService;
    }

    public CouponConsumption createCouponHistory(CouponConsumptionRequest couponRequestDTO) {
        couponService.validateCoupon(couponRequestDTO.getCode());

        CouponConsumption couponConsumption = new CouponConsumption();
        couponConsumption.setCoupon(couponService.getCouponByCode(couponRequestDTO.getCode()));
        couponConsumption.setOrderId(couponRequestDTO.getOrderId());
        couponConsumption.setConsumedAt(LocalDate.now());

        couponService.incrementCouponUsage(couponRequestDTO.getCode());

        return couponConsumptionRepo.save(couponConsumption);
    }

    public List<CouponConsumptionResponse> getCouponHistory() {
        return CouponConsumptionMapper.toDTOList(couponConsumptionRepo.findAll());
    }

    public List<CouponConsumptionResponse> getCouponHistoryByCouponId(Long couponId) {
        return CouponConsumptionMapper.toDTOList(couponConsumptionRepo.findByCouponId(couponId));
    }

    public List<CouponConsumptionResponse> getCouponHistoryByOrderId(String orderId) {
        return CouponConsumptionMapper.toDTOList(couponConsumptionRepo.findByOrderId(orderId));
    }
}
