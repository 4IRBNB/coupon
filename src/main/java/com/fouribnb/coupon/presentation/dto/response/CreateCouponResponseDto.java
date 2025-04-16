package com.fouribnb.coupon.presentation.dto.response;

import com.fouribnb.coupon.domain.entity.CouponStatus;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateCouponResponseDto {

    private UUID couponId;
    private Long userId;
    private UUID orderId;
    private String couponName;
    private CouponStatus couponStatus;
    private int discountValue;
    private Boolean isUsed;

}
