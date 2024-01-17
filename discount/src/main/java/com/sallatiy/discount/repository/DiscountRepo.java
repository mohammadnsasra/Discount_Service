package com.sallatiy.discount.repository;

import com.sallatiy.discount.model.document.Discount;
import com.sallatiy.discount.model.dto.response.DiscountProjection;
import com.sallatiy.discount.model.enums.StatusDiscount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface DiscountRepo {
    public Discount save(Discount discount);

    public Optional<Discount> findById(String id);

    public Optional<Discount> findByCodeAndStatusNot(String code, StatusDiscount status);

    public Optional<Discount> findByIdAndStatusNot(String id, StatusDiscount status);
    Page<DiscountProjection> findAll(StatusDiscount status, PageRequest pageRequest);
    public Page<DiscountProjection> findByCodeAndStatus(String code, StatusDiscount status, PageRequest pageRequest);
    public Page<DiscountProjection> findByCode(String code, PageRequest pageRequest);
    public Page<DiscountProjection> findByStatus(StatusDiscount status, PageRequest pageRequest);

    public List<Discount> findAll();

    void deleteDiscountById(String id);

    public List<Discount> findByNameAndStatusNot(String name, StatusDiscount status);

}