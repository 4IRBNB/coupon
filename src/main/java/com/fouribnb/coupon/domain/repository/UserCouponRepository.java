package com.fouribnb.coupon.domain.repository;

import com.fouribnb.coupon.domain.entity.Coupon;
import com.fouribnb.coupon.domain.entity.UserCoupon;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserCouponRepository {

    Page<UserCoupon> findAllByUserId(Pageable pageable, Long currentUserId);
    Optional<UserCoupon> findByUserIdAndCouponId(Long userId, UUID couponId);
}
