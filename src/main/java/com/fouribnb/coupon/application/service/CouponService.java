package com.fouribnb.coupon.application.service;

import com.fouribnb.coupon.presentation.dto.request.CreateCouponRequestDto;
import com.fouribnb.coupon.presentation.dto.response.CreateCouponResponseDto;


public interface CouponService {

    CreateCouponResponseDto createCoupon(CreateCouponRequestDto request);

}
