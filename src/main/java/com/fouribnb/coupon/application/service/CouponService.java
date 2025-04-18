package com.fouribnb.coupon.application.service;

import com.fouribnb.coupon.presentation.dto.request.CreateCouponRequestDto;
import com.fouribnb.coupon.presentation.dto.request.UpdateCouponRequestDto;
import com.fouribnb.coupon.presentation.dto.response.CreateCouponResponseDto;
import com.fouribnb.coupon.presentation.dto.response.GetCouponResponseDto;
import com.fouribnb.coupon.presentation.dto.response.UpdateCouponResponseDto;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CouponService {

    Page<GetCouponResponseDto> getCoupons(Pageable pageable);

    CreateCouponResponseDto createCoupon(CreateCouponRequestDto request);

    GetCouponResponseDto getCoupon(UUID id);

    UpdateCouponResponseDto updateCoupon(UUID id, UpdateCouponRequestDto request);

    void deleteCoupon(UUID couponId);
}
