package com.fouribnb.coupon.domain.repository;

import com.fouribnb.coupon.domain.entity.Coupon;
import java.util.Optional;
import java.util.UUID;

public interface CouponRepository {
    Coupon save(Coupon coupon);

    Optional<Coupon> findById(UUID id);

}
