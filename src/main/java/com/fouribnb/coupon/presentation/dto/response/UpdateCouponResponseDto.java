package com.fouribnb.coupon.presentation.dto.response;

import com.fouribnb.coupon.domain.entity.CouponStatus;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateCouponResponseDto {
    private UUID couponId;
    private String couponName;
    private Long discountValue;
    private CouponStatus couponStatus;

}
