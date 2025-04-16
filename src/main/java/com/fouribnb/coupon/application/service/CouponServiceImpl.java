package com.fouribnb.coupon.application.service;

import com.fouribnb.coupon.domain.entity.Coupon;
import com.fouribnb.coupon.domain.repository.CouponRepository;
import com.fouribnb.coupon.presentation.dto.request.CreateCouponRequestDto;
import com.fouribnb.coupon.presentation.dto.response.CreateCouponResponseDto;
import com.fouribnb.coupon.presentation.mapper.CouponMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;

    @Override
    public CreateCouponResponseDto createCoupon(CreateCouponRequestDto request) {
        Coupon coupon = CouponMapper.createDtoToEntity(request);
        couponRepository.save(coupon);
        return CouponMapper.createDtoToResponse(coupon);
    }
}
