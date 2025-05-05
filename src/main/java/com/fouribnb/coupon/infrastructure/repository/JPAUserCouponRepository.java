package com.fouribnb.coupon.infrastructure.repository;

import com.fouribnb.coupon.domain.entity.UserCoupon;
import com.fouribnb.coupon.domain.repository.UserCouponRepository;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAUserCouponRepository extends UserCouponRepository,
        JpaRepository<UserCoupon, UUID> {

}
