package com.fouribnb.coupon.presentation.dto.request;

import com.fouribnb.coupon.domain.entity.CouponStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateCouponRequestDto {

    private Long userId;
    private String couponName;

    @NotNull
    @Positive
    private Long discountValue;

}
