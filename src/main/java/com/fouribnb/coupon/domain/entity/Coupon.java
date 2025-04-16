package com.fouribnb.coupon.domain.entity;

import com.fouribnb.coupon.presentation.dto.request.UpdateCouponRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "p_coupon")
@Getter
@NoArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, name = "coupon_id")
    private UUID id;

    @Column(name = "user_id")
    private Long userId;

    @Column( name = "order_id")
    private UUID orderId;

    @Column(nullable = false, name = "coupon_name")
    private String couponeName;

    @Column(nullable = false, name = "coupon_status")
    @Enumerated(EnumType.STRING)
    private CouponStatus couponStatus;

    @Column(nullable = false, name = "discount_value")
    private int discountValue;

    @Column(nullable = false, name = "is_used")
    private boolean isUsed;

    @Builder
    public Coupon(Long userId, UUID orderId, String couponeName, CouponStatus couponStatus,
            int discountValue, boolean isUsed) {
        this.userId = userId;
        this.orderId = orderId;
        this.couponeName = couponeName;
        this.couponStatus = couponStatus;
        this.discountValue = discountValue;
        this.isUsed = isUsed;
    }

    public void update(UpdateCouponRequestDto dto){
        this.couponeName = dto.getCouponName();
        this.discountValue = dto.getDiscountValue();
    }
}

