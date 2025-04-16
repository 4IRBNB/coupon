package com.fouribnb.coupon.infrastructure.repository;

import com.fouribnb.coupon.domain.entity.Coupon;
import com.fouribnb.coupon.domain.repository.CouponRepository;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPACouponRepository extends CouponRepository, JpaRepository<Coupon, UUID> {

}
