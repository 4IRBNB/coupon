package com.fouribnb.coupon.domain.entity;

import com.fouribnb.coupon.presentation.dto.request.GrantCouponRequestDto;
import com.fouribnb.coupon.presentation.dto.request.UpdateCouponRequestDto;
import com.fourirbnb.common.domain.BaseEntity;
import com.fourirbnb.common.exception.OperationNotAllowedException;
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
public class Coupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, name = "coupon_id")
    private UUID id;

    @Column(name = "user_id")
    private Long userId;

    @Column( name = "payment_id")
    private UUID paymentId;

    @Column(nullable = false, name = "coupon_name")
    private String couponName;

    @Column(nullable = false, name = "coupon_status")
    @Enumerated(EnumType.STRING)
    private CouponStatus couponStatus;

    @Column(nullable = false, name = "discount_value")
    private int discountValue;

    @Column(nullable = false, name = "is_used")
    private boolean isUsed;

    @Builder
    public Coupon(Long userId, UUID paymentId, String couponName, CouponStatus couponStatus,
            int discountValue, boolean isUsed) {
        this.userId = userId;
        this.paymentId = paymentId;
        this.couponName = couponName;
        this.couponStatus = couponStatus;
        this.discountValue = discountValue;
        this.isUsed = isUsed;
    }

    public void update(UpdateCouponRequestDto dto){
        this.couponName = dto.getCouponName();
        this.discountValue = dto.getDiscountValue();
    }

    public void grant(GrantCouponRequestDto dto) {
        if(this.couponStatus == CouponStatus.valueOf("ACTIVE")){
            if(this.isUsed == false){
                this.userId = dto.getUserId();
                this.paymentId = dto.getPaymentId();
            }else {
                throw new OperationNotAllowedException("이미 사용된 쿠폰입니다");
            }
        }else {
            throw new OperationNotAllowedException("만료된 쿠폰입니다");
        }
    }
}

