package com.fouribnb.coupon.domain.repository;

import com.fouribnb.coupon.domain.entity.Coupon;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CouponRepository {

    Page<Coupon> findAll(Pageable pageable);

    Coupon save(Coupon coupon);

    Optional<Coupon> findById(UUID id);

    Page<Coupon> findAllByUserId(Pageable pageable, Long currentUserId);
}
