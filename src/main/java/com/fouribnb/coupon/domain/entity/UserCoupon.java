package com.fouribnb.coupon.domain.entity;

import com.fourirbnb.common.exception.OperationNotAllowedException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "p_user_coupon")
@Getter
@NoArgsConstructor
public class UserCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, name = "user_coupon_id")
    private UUID id;

    //
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "coupon_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_usercoupon_coupon")
    )
    private Coupon coupon;

    @Column(name = "user_id")
    private Long userId;

    @Column( name = "payment_id")
    private UUID paymentId;


    @Column(nullable = false, name = "is_used")
    private boolean isUsed;

    @Builder
    public UserCoupon(Coupon coupon, Long userId, UUID paymentId, CouponStatus couponStatus,
            boolean isUsed) {
        this.coupon = coupon;
        this.userId = userId;
        this.paymentId = paymentId;
        this.isUsed = isUsed;
    }

    public void grant(Coupon coupon, Long userId) {
        if(coupon.getCouponStatus() == CouponStatus.valueOf("ACTIVE")){
            if(!this.isUsed){
                this.userId = userId;
            }else {
                throw new OperationNotAllowedException("이미 사용된 쿠폰입니다");
            }
        }else {
            throw new OperationNotAllowedException("만료된 쿠폰입니다");
        }
    }

}
