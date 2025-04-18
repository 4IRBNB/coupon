package com.fouribnb.coupon.application.service;

import com.fouribnb.coupon.domain.entity.Coupon;
import com.fouribnb.coupon.domain.repository.CouponRepository;
import com.fouribnb.coupon.presentation.dto.request.CreateCouponRequestDto;
import com.fouribnb.coupon.presentation.dto.request.UpdateCouponRequestDto;
import com.fouribnb.coupon.presentation.dto.response.CreateCouponResponseDto;
import com.fouribnb.coupon.presentation.dto.response.GetCouponResponseDto;
import com.fouribnb.coupon.presentation.dto.response.UpdateCouponResponseDto;
import com.fouribnb.coupon.presentation.mapper.CouponMapper;
import com.fourirbnb.common.exception.ResourceNotFoundException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    @Override
    public CreateCouponResponseDto createCoupon(CreateCouponRequestDto request) {
        Coupon coupon = CouponMapper.createToEntity(request);
        couponRepository.save(coupon);
        return CouponMapper.createToResponse(coupon);
    }

    @Override
    @Transactional(readOnly = true)
    public GetCouponResponseDto getCoupon(UUID id) {
        Coupon coupon = couponRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("쿠폰을 찾을 수 없음"));
        return CouponMapper.getToResponse(coupon);
    }

    @Override
    public UpdateCouponResponseDto updateCoupon(UUID id, UpdateCouponRequestDto request) {
        Coupon coupon = couponRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("쿠폰을 찾을 수 없음"));
        coupon.update(request);
        return CouponMapper.updateToResponse(coupon);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GetCouponResponseDto> getCoupons(Pageable pageable) {
        Page<GetCouponResponseDto> dtos =  couponRepository.findAll(pageable)
                .map(CouponMapper::getToResponse);
        return dtos;
    }

}
