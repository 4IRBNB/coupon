package com.fouribnb.coupon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fouribnb.coupon.application.service.CouponServiceImpl;
import com.fouribnb.coupon.domain.entity.Coupon;
import com.fouribnb.coupon.domain.entity.CouponStatus;
import com.fouribnb.coupon.domain.repository.CouponRepository;
import com.fouribnb.coupon.presentation.dto.request.CreateCouponRequestDto;
import com.fouribnb.coupon.presentation.dto.response.CreateCouponResponseDto;
import com.fouribnb.coupon.presentation.dto.response.GetCouponResponseDto;
import com.fourirbnb.common.exception.ResourceNotFoundException;
import com.fourirbnb.common.security.UserInfo;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
public class CouponServiceTest {

    @Mock
    private CouponRepository couponRepository;   // 목 객체

    @Mock
    private UserInfo userInfo;

    @InjectMocks
    private CouponServiceImpl couponService;     // 목 주입받는 테스트 대상



    @Test
    @DisplayName("쿠폰생성__성공")
    void createCoupon__success() {
        //given
        //dto 입력
        CreateCouponRequestDto req = CreateCouponRequestDto.builder()
                .userId(null)
                .couponName("할인1000")
                .discountValue(1000L)
                .build();

        when(couponRepository.save(any(Coupon.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        //when: 실제 테스트 대상 메서드 호출
        CreateCouponResponseDto res = couponService.createCoupon(req);

        //then: 저장 호출과 반환값 검증
        //저장 메서드 호출됐는지 검사
        verify(couponRepository).save(any(Coupon.class));
        //상태 검증
        assertThat(res.getCouponName()).isEqualTo("할인1000");
        assertThat(res.getDiscountValue()).isEqualTo(1000L);
        assertThat(res.getIsUsed()).isFalse();
        assertThat(res.getUserId()).isNull();
        assertThat(res.getPaymentId()).isNull();

    }

    @Test
    @DisplayName("쿠폰_단건조회__성공")
    void getCoupon__success() {
        //given
        UUID couponId = UUID.randomUUID();
        Coupon coupon = Coupon.builder()
                .userId(1L)
                .couponName("SPRING10")
                .discountValue(1000L)
                .couponStatus(CouponStatus.ACTIVE)
                .isUsed(false)
                .build();

        // 테스트 전용으로 'id'를 강제로 세팅
        ReflectionTestUtils.setField(coupon, "id", couponId);

        when(couponRepository.findById(couponId)).thenReturn(Optional.of(coupon));

        //when: 실제 테스트 대상 메서드 호출
        GetCouponResponseDto res = couponService.getCoupon(couponId);

        //then: 저장 호출과 반환값 검증
        //상태 검증
        assertThat(res.getCouponId()).isEqualTo(couponId);
        assertThat(res.getUserId()).isEqualTo(1L);
        assertThat(res.getPaymentId()).isNull();
        assertThat(res.getCouponName()).isEqualTo("SPRING10");
        assertThat(res.getCouponStatus()).isEqualTo(CouponStatus.ACTIVE);
        assertThat(res.getDiscountValue()).isEqualTo(1000L);
        assertThat(res.getIsUsed()).isFalse();

    }

    @Test
    @DisplayName("존재하지 않는 쿠폰 ID로 조회")
    void getCoupon_notFound() {
        // given
        UUID couponId = UUID.randomUUID();
        when(couponRepository.findById(couponId)).thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> couponService.getCoupon(couponId))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("쿠폰을 찾을 수 없음");
    }







}
