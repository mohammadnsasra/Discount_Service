package com.sallatiy.discountService.model.dto.response;


import com.sallatiy.discountService.model.enums.StatusDiscount;

import java.time.LocalDateTime;

public interface DiscountProjection {
    String getId();
    String getName();
    String getCode();
    double getDiscountValue();
    LocalDateTime getStartDate();
    LocalDateTime getEndDate();
    StatusDiscount getStatus();
}