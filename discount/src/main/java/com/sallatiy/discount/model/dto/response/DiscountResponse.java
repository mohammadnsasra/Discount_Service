package com.sallatiy.discount.model.dto.response;

import com.sallatiy.discount.model.enums.StatusDiscount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiscountResponse {
    private String id;
    private String name;
    private String code;
    private Double discountValue;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private StatusDiscount status;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private  LocalDateTime updatedAt;
}
