package com.fawry.coupon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "coupon_consumption")
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponConsumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @Column(name = "order_id", nullable = false)
    private String orderId;

    @Column(name = "customer_email", nullable = false)
    private String customerEmail;

    @Column(name = "applied_value", nullable = false)
    private Integer appliedValue;

    @Column(name = "consumed_at", nullable = false, updatable = false)
    private LocalDateTime consumedAt;

    @PrePersist
    protected void onCreate() {
        consumedAt = LocalDateTime.now();
    }
}