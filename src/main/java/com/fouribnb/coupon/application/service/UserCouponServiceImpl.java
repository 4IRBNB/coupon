package com.fouribnb.coupon.application.service;

import com.fouribnb.coupon.domain.entity.Coupon;
import com.fouribnb.coupon.domain.entity.UserCoupon;
import com.fouribnb.coupon.domain.repository.CouponRepository;
import com.fouribnb.coupon.domain.repository.UserCouponRepository;
import com.fouribnb.coupon.presentation.dto.response.GetCouponResponseDto;
import com.fouribnb.coupon.presentation.dto.response.GetMyCouponResponseDto;
import com.fouribnb.coupon.presentation.dto.response.GrantCouponResponseDto;
import com.fouribnb.coupon.presentation.mapper.CouponMapper;
import com.fouribnb.coupon.presentation.mapper.UserCouponMapper;
import com.fourirbnb.common.exception.ResourceNotFoundException;
import com.fourirbnb.common.security.UserInfo;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserCouponServiceImpl implements UserCouponService {

    private final CouponRepository couponRepository;
    private final UserCouponRepository userCouponRepository;

    @Override
    public GrantCouponResponseDto grantUserCoupon(UUID id, UserInfo userInfo) {
        Coupon coupon = couponRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("쿠폰을 찾을 수 없음"));
        UserCoupon userCoupon = UserCouponMapper.GrantToEntity(coupon, userInfo);
        userCoupon.grant(coupon, userInfo.getUserId());
        return UserCouponMapper.grantToResponse(userCoupon);
    }

    @Override
    public Page<GetMyCouponResponseDto> getMyCoupons(Pageable pageable, UserInfo userInfo) {
        Long currentUserId = userInfo.getUserId();
        Page<GetMyCouponResponseDto> dtos =  userCouponRepository.findAllByUserId(pageable, currentUserId)
                .map(UserCouponMapper::getToResponse);
        return dtos;
    }

}
