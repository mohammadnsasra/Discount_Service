package com.sallatiy.discount.model.dto.request;

import com.sallatiy.discount.costumeAnnotations.TrimmedString;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiscountRequest {
    @NotNull(message = "the name is required")
    @NotEmpty(message = "the name should not be empty")
    @NotBlank(message = "the name should not be blank")
    @TrimmedString(message = "the name must not have leading or trailing spaces")
    private String name;
    @NotNull(message = "the code is required")
    @NotEmpty(message = "the code should not be empty")
    @NotBlank(message = "the code should not be blank")
    @TrimmedString(message = "the code must not have leading or trailing spaces")
    private String code;
    @NotNull(message = "the discountValue is required")
    @Min(value = 0,message = "the min of discount value is 0")
    @Max(value = 1,message = "the max of discount value is 1")
    private Double discountValue;
    @NotNull(message = "the startDate is required")
    @NotEmpty(message = "the startDate should not be empty")
    @NotBlank(message = "the startDate should not be blank")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$", message = " the startDate must be in this format dd-MM-yyyyThh:mm:ss")
    private String startDate;
    @NotNull(message = "the endDate is required")
    @NotEmpty(message = "the endDate should not be empty")
    @NotBlank(message = "the endDate should not be blank")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$", message = "the endDate must be in this formatTdd-MM-yyyy hh:mm:ss")
    private String endDate;


}
