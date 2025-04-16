package com.fawry.coupon.repo;

import com.fawry.coupon.entity.CouponConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponConsumptionRepo extends JpaRepository<CouponConsumption, Long> {
    List<CouponConsumption> findByCouponId(Long couponId);

    List<CouponConsumption> findByOrderId(String orderId);
}
