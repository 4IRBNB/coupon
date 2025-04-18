package com.fouribnb.coupon.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GrantCouponRequestDto {

    @NotBlank
    private Long userId;
    @NotBlank
    private UUID paymentId;

}
