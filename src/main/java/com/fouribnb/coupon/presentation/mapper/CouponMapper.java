package com.fouribnb.coupon.presentation.mapper;

import com.fouribnb.coupon.domain.entity.Coupon;
import com.fouribnb.coupon.domain.entity.CouponStatus;
import com.fouribnb.coupon.domain.entity.UserCoupon;
import com.fouribnb.coupon.presentation.dto.request.CreateCouponRequestDto;
import com.fouribnb.coupon.presentation.dto.response.CreateCouponResponseDto;
import com.fouribnb.coupon.presentation.dto.response.GetCouponResponseDto;
import com.fouribnb.coupon.presentation.dto.response.GrantCouponResponseDto;
import com.fouribnb.coupon.presentation.dto.response.UpdateCouponResponseDto;
import com.fourirbnb.common.security.UserInfo;

public class CouponMapper {

    public static Coupon createToEntity(CreateCouponRequestDto dto) {
        return Coupon.builder()
                .couponName(dto.getCouponName())
                .discountValue(dto.getDiscountValue())
                .couponStatus(CouponStatus.ACTIVE)
                .build();
    }

    public static CreateCouponResponseDto createToResponse(Coupon coupon) {
        return CreateCouponResponseDto.builder()
                .couponId(coupon.getId())
                .couponName(coupon.getCouponName())
                .discountValue(coupon.getDiscountValue())
                .couponStatus(coupon.getCouponStatus())
                .build();
    }

    public static GetCouponResponseDto getToResponse(Coupon coupon) {
        return GetCouponResponseDto.builder()
                .couponId(coupon.getId())
                .couponName(coupon.getCouponName())
                .discountValue(coupon.getDiscountValue())
                .couponStatus(coupon.getCouponStatus())
                .build();
    }

    public static UpdateCouponResponseDto updateToResponse(Coupon coupon) {
        return UpdateCouponResponseDto.builder()
                .couponId(coupon.getId())
                .couponName(coupon.getCouponName())
                .discountValue(coupon.getDiscountValue())
                .couponStatus(coupon.getCouponStatus())
                .build();
    }

}
