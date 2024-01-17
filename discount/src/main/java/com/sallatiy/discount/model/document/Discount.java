package com.sallatiy.discount.model.document;

import com.sallatiy.discount.model.enums.StatusDiscount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "discount")
public class Discount {
    @Id
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
