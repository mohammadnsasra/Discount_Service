package com.sallatiy.discount.mapper;

import com.sallatiy.discount.model.document.Discount;
import com.sallatiy.discount.model.dto.request.DiscountRequest;
import com.sallatiy.discount.model.dto.response.DiscountResponse;
import com.sallatiy.discount.model.dto.response.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;


@Component
@Slf4j
public class DiscountMapper {
    public Discount requestToDiscount(DiscountRequest request){
        log.info("Mapping DiscountRequest to Discount: {}", request);
        return Discount.builder().name(request.getName())
                .code(request.getCode()).discountValue(request.getDiscountValue())
                .startDate(LocalDateTime.parse(request.getStartDate()))
                .endDate(LocalDateTime.parse(request.getEndDate()))
                .build();
    }
    public DiscountResponse discountToResponse(Discount discount){
        log.info("Mapping Discount to DiscountResponse: {}", discount);
        return DiscountResponse.builder().id(discount.getId())
                .name(discount.getName()).code(discount.getCode())
                .discountValue(discount.getDiscountValue())
                .startDate(discount.getStartDate())
                .status(discount.getStatus())
                .endDate(discount.getEndDate())
                .createdAt(discount.getCreatedAt()).deletedAt(discount.getDeletedAt())
                .updatedAt(discount.getUpdatedAt()).build();
    }
    public MessageResponse toMessageResponse(String msg){
        log.info("Creating message response: {}", msg);
        return MessageResponse.builder()
                .message(msg)
                .build();
    }
}
