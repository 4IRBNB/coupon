package com.fouribnb.coupon.presentation.dto.request;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateCouponRequestDto {

    private String couponName;
    private Long discountValue;

}
