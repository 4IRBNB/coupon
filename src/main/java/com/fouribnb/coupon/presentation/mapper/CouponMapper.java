package com.fouribnb.coupon.presentation.mapper;

import com.fouribnb.coupon.domain.entity.Coupon;
import com.fouribnb.coupon.domain.entity.CouponStatus;
import com.fouribnb.coupon.presentation.dto.request.CreateCouponRequestDto;
import com.fouribnb.coupon.presentation.dto.response.CreateCouponResponseDto;
import com.fouribnb.coupon.presentation.dto.response.GetCouponResponseDto;
import com.fouribnb.coupon.presentation.dto.response.UpdateCouponResponseDto;

public class CouponMapper {

    public static Coupon createDtoToEntity(CreateCouponRequestDto dto) {
        return Coupon.builder()
                .userId(dto.getUserId())
                .orderId(dto.getOrderId())
                .couponeName(dto.getCouponName())
                .couponStatus(CouponStatus.ACTIVE)
                .discountValue(dto.getDiscountValue())
                .isUsed(false)
                .build();
    }

    public static CreateCouponResponseDto createDtoToResponse(Coupon coupon) {
        return CreateCouponResponseDto.builder()
                .userId(coupon.getUserId())
                .orderId(coupon.getOrderId())
                .couponName(coupon.getCouponeName())
                .couponStatus(coupon.getCouponStatus())
                .discountValue(coupon.getDiscountValue())
                .isUsed(coupon.isUsed())
                .build();
    }

    public static GetCouponResponseDto getDtoToResponse(Coupon coupon) {
        return GetCouponResponseDto.builder()
                .userId(coupon.getUserId())
                .orderId(coupon.getOrderId())
                .couponName(coupon.getCouponeName())
                .couponStatus(coupon.getCouponStatus())
                .discountValue(coupon.getDiscountValue())
                .isUsed(coupon.isUsed())
                .build();
    }

    public static UpdateCouponResponseDto updateDtoToResponse(Coupon coupon) {
        return UpdateCouponResponseDto.builder()
                .userId(coupon.getUserId())
                .orderId(coupon.getOrderId())
                .couponName(coupon.getCouponeName())
                .couponStatus(coupon.getCouponStatus())
                .discountValue(coupon.getDiscountValue())
                .isUsed(coupon.isUsed())
                .build();
    }

}
