package com.fouribnb.coupon.domain.entity;

import com.fouribnb.coupon.presentation.dto.request.GrantCouponRequestDto;
import com.fourirbnb.common.exception.OperationNotAllowedException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
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

    @Column(nullable = false, name = "coupon_status")
    @Enumerated(EnumType.STRING)
    private CouponStatus couponStatus;

    @Column(nullable = false, name = "is_used")
    private boolean isUsed;


    public void grant(GrantCouponRequestDto dto) {
        if(this.couponStatus == CouponStatus.valueOf("ACTIVE")){
            if(this.isUsed == false){
                this.userId = dto.getUserId();
            }else {
                throw new OperationNotAllowedException("이미 사용된 쿠폰입니다");
            }
        }else {
            throw new OperationNotAllowedException("만료된 쿠폰입니다");
        }
    }

}
