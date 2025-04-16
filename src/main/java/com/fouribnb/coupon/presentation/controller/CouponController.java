package com.fouribnb.coupon.presentation.controller;

import com.fouribnb.coupon.application.service.CouponService;
import com.fouribnb.coupon.presentation.dto.request.CreateCouponRequestDto;
import com.fouribnb.coupon.presentation.dto.response.CreateCouponResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coupons")
public class CouponController {

    private final CouponService couponService;

    //쿠폰생성
    @PostMapping
    public ResponseEntity<CreateCouponResponseDto> createLodge(
            @RequestBody CreateCouponRequestDto requestDto) {
        CreateCouponResponseDto responseDto = couponService.createCoupon(requestDto);
        return ResponseEntity.ok(responseDto);
    }

}
