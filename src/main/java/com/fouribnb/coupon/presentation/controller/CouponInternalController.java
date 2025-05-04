package com.fouribnb.coupon.presentation.controller;

import com.fouribnb.coupon.application.service.CouponService;
import com.fouribnb.coupon.application.service.UserCouponService;
import com.fouribnb.coupon.presentation.dto.response.GetCouponResponseDto;
import com.fouribnb.coupon.presentation.dto.response.GetMyCouponResponseDto;
import com.fourirbnb.common.response.BaseResponse;
import com.fourirbnb.common.response.Pagination;
import com.fourirbnb.common.security.AuthenticatedUser;
import com.fourirbnb.common.security.RoleCheck;
import com.fourirbnb.common.security.UserInfo;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/coupons")
public class CouponInternalController {

    private final CouponService couponService;
    private final UserCouponService userCouponService;

    //쿠폰조회
    @RoleCheck({"MASTER", "MANAGER", "HOST", "CUSTOMER"})
    @GetMapping("/{couponId}")
    public BaseResponse<GetCouponResponseDto> getCoupon(@PathVariable UUID couponId) {
        GetCouponResponseDto responseDto = couponService.getCoupon(couponId);
        return BaseResponse.SUCCESS(responseDto, "쿠폰 단건 조회 완료", HttpStatus.OK.value());
    }

    //나의쿠폰목록보기
    @GetMapping("/me")
    public BaseResponse<List<GetMyCouponResponseDto>> getMyCoupons(Pageable pageable,
            @AuthenticatedUser UserInfo userInfo){
        Page<GetMyCouponResponseDto> page = userCouponService.getMyCoupons(pageable, userInfo);

        Pagination pagination = new Pagination(
                page.getNumber(),
                (long) page.getSize(),
                page.getTotalPages(),
                (int) page.getTotalElements()
        );
        return BaseResponse.SUCCESS(
                page.getContent(),
                "나의쿠폰목록 조회 완료",
                pagination
        );
    }
}
