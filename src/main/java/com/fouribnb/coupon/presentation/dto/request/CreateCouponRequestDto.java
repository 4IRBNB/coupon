package com.fouribnb.coupon.presentation.dto.request;

import com.fouribnb.coupon.domain.entity.CouponStatus;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateCouponRequestDto {

    private Long userId;
    private UUID paymentId;
    private String couponName;
    private int discountValue;

}
