package com.sallatiy.discount.repository.mongo;

import com.sallatiy.discount.model.document.Discount;
import com.sallatiy.discount.model.dto.response.DiscountProjection;
import com.sallatiy.discount.model.enums.StatusDiscount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiscountRepoMongo extends MongoRepository<Discount,String> {
    public Optional<Discount> findByCodeAndStatusNot(String code, StatusDiscount status);
    public Optional<Discount>  findByIdAndStatusNot(String id,StatusDiscount status);
    public List<Discount> findByNameContainingAndStatusNot(String name, StatusDiscount status);
    List<Discount> findByStatusNot(StatusDiscount status);
    Page<DiscountProjection> findByStatusNot(StatusDiscount status, PageRequest pageRequest);
    Page<DiscountProjection> findByCodeContainingAndStatus(String code, StatusDiscount status, PageRequest pageRequest);
    Page<DiscountProjection> findByCodeContainingAndStatusNot(String code, StatusDiscount status, PageRequest pageRequest);
    Page<DiscountProjection> findByStatus(StatusDiscount status, PageRequest pageRequest);
}
