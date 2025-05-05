package com.fouribnb.coupon.presentation.mapper;

import com.fouribnb.coupon.domain.entity.Coupon;
import com.fouribnb.coupon.domain.entity.CouponStatus;
import com.fouribnb.coupon.domain.entity.UserCoupon;
import com.fouribnb.coupon.presentation.dto.request.CreateCouponRequestDto;
import com.fouribnb.coupon.presentation.dto.response.CreateCouponResponseDto;
import com.fouribnb.coupon.presentation.dto.response.GetCouponResponseDto;
import com.fouribnb.coupon.presentation.dto.response.GetMyCouponResponseDto;
import com.fouribnb.coupon.presentation.dto.response.GrantCouponResponseDto;
import com.fouribnb.coupon.presentation.dto.response.UpdateCouponResponseDto;
import com.fourirbnb.common.security.UserInfo;

public class UserCouponMapper {

    public static GetMyCouponResponseDto getToResponse(UserCoupon userCoupon) {
        return GetMyCouponResponseDto.builder()
                .userCouponId(userCoupon.getId())
                .couponId(userCoupon.getCoupon().getId())
                .userId(userCoupon.getUserId())
                .paymentId(userCoupon.getPaymentId())
                .couponName(userCoupon.getCoupon().getCouponName())
                .discountValue(userCoupon.getCoupon().getDiscountValue())
                .couponStatus(userCoupon.getCoupon().getCouponStatus())
                .isUsed(userCoupon.isUsed())
                .build();
    }


    public static UserCoupon GrantToEntity(Coupon coupon, UserInfo userInfo) {
        return UserCoupon.builder()
                .coupon(coupon)
                .userId(userInfo.getUserId())
                .paymentId(null)
                .isUsed(false)
                .build();
    }

    public static GrantCouponResponseDto grantToResponse(UserCoupon userCoupon) {
        return GrantCouponResponseDto.builder()
                .userCouponId(userCoupon.getId())
                .couponId(userCoupon.getCoupon().getId())
                .userId(userCoupon.getUserId())
                .paymentId(userCoupon.getPaymentId())
                .couponName(userCoupon.getCoupon().getCouponName())
                .discountValue(userCoupon.getCoupon().getDiscountValue())
                .couponStatus(userCoupon.getCoupon().getCouponStatus())
                .isUsed(userCoupon.isUsed())
                .build();
    }
}
