package com.fouribnb.coupon.application.service;

import com.fouribnb.coupon.presentation.dto.response.GetCouponResponseDto;
import com.fouribnb.coupon.presentation.dto.response.GetMyCouponResponseDto;
import com.fouribnb.coupon.presentation.dto.response.GrantCouponResponseDto;
import com.fourirbnb.common.security.UserInfo;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserCouponService {

    GrantCouponResponseDto grantUserCoupon(UUID id, UserInfo userInfo);

    Page<GetMyCouponResponseDto> getMyCoupons(Pageable pageable, UserInfo userInfo);
}
