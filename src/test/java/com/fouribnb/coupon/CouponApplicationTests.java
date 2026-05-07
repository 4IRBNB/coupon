package com.fouribnb.coupon;

import com.fouribnb.coupon.domain.repository.CouponRepository;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class CouponApplicationTests {

    @Autowired
    private TestRestTemplate rest;

    @Autowired
    private CouponRepository couponRepository;

//    @BeforeEach
//    void init() {  //테스트 시작할 때마다 쿠폰 테이블 비우기
//        couponRepository.deleteAll();
//    }
}


