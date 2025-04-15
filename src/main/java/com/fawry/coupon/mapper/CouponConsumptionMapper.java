package com.fawry.coupon.mapper;

import com.fawry.coupon.dto.CouponConsumptionResponse;
import com.fawry.coupon.entity.CouponConsumption;

import java.util.List;

public class CouponConsumptionMapper {
    public static CouponConsumptionResponse toDTO(CouponConsumption entity) {
        return new CouponConsumptionResponse(
                entity.getId(),
                entity.getCoupon().getId(),
                entity.getOrderId(),
                entity.getConsumedAt()
        );
    }

    public static List<CouponConsumptionResponse> toDTOList(List<CouponConsumption> entities) {
        return entities.stream()
                .map(CouponConsumptionMapper::toDTO)
                .toList();
    }
}
