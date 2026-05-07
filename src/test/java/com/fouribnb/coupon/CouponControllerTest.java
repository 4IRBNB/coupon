package com.fouribnb.coupon;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fouribnb.coupon.application.service.CouponService;
import com.fouribnb.coupon.application.service.UserCouponService;
import com.fouribnb.coupon.domain.entity.CouponStatus;
import com.fouribnb.coupon.presentation.controller.CouponController;
import com.fouribnb.coupon.presentation.controller.CouponInternalController;
import com.fouribnb.coupon.presentation.dto.response.GrantCouponResponseDto;
import com.fourirbnb.common.security.UserInfo;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@Import(value = {com.fourirbnb.common.security.SecurityConfig
        , com.fouribnb.common.config.WebMvcConfig
})
class CouponControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CouponService couponService;

    @MockBean
    private UserCouponService userCouponService;

    @Test
    @WithMockUser(username="42", roles={"CUSTOMER"})
    void grantCoupon_asCustomer_shouldReturn200() throws Exception {
        UUID couponId = UUID.randomUUID();
        GrantCouponResponseDto dummyDto = GrantCouponResponseDto.builder()
                .userCouponId(UUID.randomUUID())
                .couponId(couponId)
                .userId(42L)
                .couponStatus(CouponStatus.ACTIVE)
                .isUsed(false)
                .build();

        // service 호출 모킹
        when(userCouponService.grantUserCoupon(eq(couponId), any(UserInfo.class)))
                .thenReturn(dummyDto);

        mockMvc.perform(patch("/api/coupons/grant/{couponId}", couponId))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data.userCouponId").exists())
        .andExpect(jsonPath("$.data.userId").value(42))
        .andExpect(jsonPath("$.message").value("쿠폰 발급 완료"));

        // service가 한 번 호출됐는지 검증
        verify(userCouponService).grantUserCoupon(eq(couponId), any(UserInfo.class));
    }

    @Test
    @WithMockUser(username = "42", roles = {"HOST"})  // CUSTOMER가 아닌 역할
    void grantCoupon_wrongRole() throws Exception {
        UUID couponId = UUID.randomUUID();

        mockMvc.perform(patch("/api/coupons/grant/{couponId}", couponId))
                .andExpect(status().isForbidden());          // 403 Forbidden 예상

        // 서비스가 아예 호출되지 않아야 한다
        verifyNoInteractions(userCouponService);
    }

}
