package com.fouribnb.coupon.presentation.dto.request;

import com.fouribnb.coupon.domain.entity.CouponStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateCouponRequestDto {

    @NotBlank
    private Long userId;

    private String couponName;

    @NotBlank
    @Positive
    private Long discountValue;

}
