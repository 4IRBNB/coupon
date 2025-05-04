package com.fouribnb.coupon.domain.entity;

import com.fouribnb.coupon.presentation.dto.request.GrantCouponRequestDto;
import com.fouribnb.coupon.presentation.dto.request.UpdateCouponRequestDto;
import com.fourirbnb.common.domain.BaseEntity;
import com.fourirbnb.common.exception.OperationNotAllowedException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;


@Entity
@Table(name = "p_coupon")
@Getter
@NoArgsConstructor
@FilterDef(name = "deletedFilter")
@Filter(name = "deletedFilter", condition = "deleted_at IS NULL")
public class Coupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, name = "coupon_id")
    private UUID id;

    @Column(nullable = false, name = "coupon_name")
    private String couponName;

    @Column(nullable = false, name = "discount_value")
    private Long discountValue;

    @Builder
    public Coupon(String couponName, Long discountValue) {
        this.couponName = couponName;
        this.discountValue = discountValue;
    }

    public void update(UpdateCouponRequestDto dto){
        if(dto.getCouponName() != null){
            this.couponName = dto.getCouponName();
        }
        if (dto.getDiscountValue() != null) {
            this.discountValue = dto.getDiscountValue();
        }
    }


}

