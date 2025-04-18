package com.fouribnb.coupon.presentation.dto.request;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GrantCouponRequestDto {

    private Long userId;
    private UUID orderId;

}
