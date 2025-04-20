package com.fouribnb.coupon.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GrantCouponRequestDto {

    @NotNull
    private Long userId;

}
