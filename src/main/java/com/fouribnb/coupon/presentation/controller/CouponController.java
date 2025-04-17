package com.fouribnb.coupon.presentation.controller;

import com.fouribnb.coupon.application.service.CouponService;
import com.fouribnb.coupon.presentation.dto.request.CreateCouponRequestDto;
import com.fouribnb.coupon.presentation.dto.response.CreateCouponResponseDto;
import com.fouribnb.coupon.presentation.dto.response.GetCouponResponseDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<CreateCouponResponseDto> createCoupon(
            @RequestBody CreateCouponRequestDto requestDto) {
        CreateCouponResponseDto responseDto = couponService.createCoupon(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    //쿠폰조회
    @GetMapping("/{couponId}")
    public ResponseEntity<GetCouponResponseDto> getCoupon(@PathVariable UUID couponId) {
        GetCouponResponseDto responseDto = couponService.getCoupon(couponId);
        return ResponseEntity.ok(responseDto);
    }

}
