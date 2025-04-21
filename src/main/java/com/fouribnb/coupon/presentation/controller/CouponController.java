package com.fouribnb.coupon.presentation.controller;

import com.fouribnb.coupon.application.service.CouponService;
import com.fouribnb.coupon.presentation.dto.request.CreateCouponRequestDto;
import com.fouribnb.coupon.presentation.dto.request.GrantCouponRequestDto;
import com.fouribnb.coupon.presentation.dto.request.UpdateCouponRequestDto;
import com.fouribnb.coupon.presentation.dto.response.CreateCouponResponseDto;
import com.fouribnb.coupon.presentation.dto.response.GetCouponResponseDto;
import com.fouribnb.coupon.presentation.dto.response.GrantCouponResponseDto;
import com.fouribnb.coupon.presentation.dto.response.UpdateCouponResponseDto;
import com.fourirbnb.common.response.BaseResponse;
import com.fourirbnb.common.response.Pagination;
import com.fourirbnb.common.security.AuthenticatedUser;
import com.fourirbnb.common.security.UserInfo;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
    public BaseResponse<CreateCouponResponseDto> createCoupon(
            @Valid @RequestBody CreateCouponRequestDto requestDto) {
        CreateCouponResponseDto responseDto = couponService.createCoupon(requestDto);
        return BaseResponse.SUCCESS(responseDto, "쿠폰 생성 완료", HttpStatus.OK.value());
    }

    //쿠폰조회
    @GetMapping("/{couponId}")
    public BaseResponse<GetCouponResponseDto> getCoupon(@PathVariable UUID couponId) {
        GetCouponResponseDto responseDto = couponService.getCoupon(couponId);
        return BaseResponse.SUCCESS(responseDto, "쿠폰 단건 조회 완료", HttpStatus.OK.value());
    }

    //쿠폰수정
    @PatchMapping("/{couponId}")
    public BaseResponse<UpdateCouponResponseDto> updateCoupon(@PathVariable UUID couponId,
            @RequestBody UpdateCouponRequestDto requestDto) {
        UpdateCouponResponseDto responseDto = couponService.updateCoupon(couponId, requestDto);
        return BaseResponse.SUCCESS(responseDto, "쿠폰 수정 완료", HttpStatus.OK.value());
    }

    //쿠폰조회(목록)
    @GetMapping
    public BaseResponse<List<GetCouponResponseDto>> getCoupons(Pageable pageable) {
        Page<GetCouponResponseDto> page = couponService.getCoupons(pageable);

        Pagination pagination = new Pagination(
                page.getNumber(),
                (long) page.getSize(),
                page.getTotalPages(),
                (int) page.getTotalElements()
        );
        return BaseResponse.SUCCESS(
                page.getContent(),
                "쿠폰 목록 조회 완료",
                pagination
        );
    }

    //쿠폰삭제
    @DeleteMapping("/{couponId}")
    public ResponseEntity<Void> deleteCoupon(@PathVariable UUID couponId, @AuthenticatedUser UserInfo userInfo) {
        couponService.deleteCoupon(couponId, userInfo);
        return ResponseEntity.noContent().build();
    }

    //쿠폰발급
    //todo. 여러 명이 동시 발급할 때:
    @PatchMapping("/grant/{couponId}")
    public BaseResponse<GrantCouponResponseDto> grantCoupon(@PathVariable UUID couponId,
            @Valid @RequestBody GrantCouponRequestDto requestDto) {
        GrantCouponResponseDto responseDto = couponService.grantCoupon(couponId, requestDto);
        return BaseResponse.SUCCESS(responseDto, "쿠폰 발급 완료", HttpStatus.OK.value());
    }

    //나의쿠폰보기


}
