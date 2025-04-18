package com.fouribnb.coupon.presentation.mapper;

import com.fouribnb.coupon.domain.entity.Coupon;
import com.fouribnb.coupon.domain.entity.CouponStatus;
import com.fouribnb.coupon.presentation.dto.request.CreateCouponRequestDto;
import com.fouribnb.coupon.presentation.dto.response.CreateCouponResponseDto;
import com.fouribnb.coupon.presentation.dto.response.GetCouponResponseDto;
import com.fouribnb.coupon.presentation.dto.response.GrantCouponResponseDto;
import com.fouribnb.coupon.presentation.dto.response.UpdateCouponResponseDto;

public class CouponMapper {

    public static Coupon createToEntity(CreateCouponRequestDto dto) {
        return Coupon.builder()
                .userId(dto.getUserId())
                .couponName(dto.getCouponName())
                .couponStatus(CouponStatus.ACTIVE)
                .discountValue(dto.getDiscountValue())
                .isUsed(false)
                .build();
    }

    public static CreateCouponResponseDto createToResponse(Coupon coupon) {
        return CreateCouponResponseDto.builder()
                .couponId(coupon.getId())
                .userId(coupon.getUserId())
                .paymentId(coupon.getPaymentId())
                .couponName(coupon.getCouponName())
                .couponStatus(coupon.getCouponStatus())
                .discountValue(coupon.getDiscountValue())
                .isUsed(coupon.isUsed())
                .build();
    }

    public static GetCouponResponseDto getToResponse(Coupon coupon) {
        return GetCouponResponseDto.builder()
                .couponId(coupon.getId())
                .userId(coupon.getUserId())
                .paymentId(coupon.getPaymentId())
                .couponName(coupon.getCouponName())
                .couponStatus(coupon.getCouponStatus())
                .discountValue(coupon.getDiscountValue())
                .isUsed(coupon.isUsed())
                .build();
    }

    public static UpdateCouponResponseDto updateToResponse(Coupon coupon) {
        return UpdateCouponResponseDto.builder()
                .couponId(coupon.getId())
                .userId(coupon.getUserId())
                .paymentId(coupon.getPaymentId())
                .couponName(coupon.getCouponName())
                .couponStatus(coupon.getCouponStatus())
                .discountValue(coupon.getDiscountValue())
                .isUsed(coupon.isUsed())
                .build();
    }

    public static GrantCouponResponseDto GrantToResponse(Coupon coupon) {
        return GrantCouponResponseDto.builder()
                .couponId(coupon.getId())
                .userId(coupon.getUserId())
                .paymentId(coupon.getPaymentId())
                .couponName(coupon.getCouponName())
                .couponStatus(coupon.getCouponStatus())
                .discountValue(coupon.getDiscountValue())
                .isUsed(coupon.isUsed())
                .build();
    }
}
