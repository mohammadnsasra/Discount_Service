package com.sallatiy.discount.repository.impl;

import com.sallatiy.discount.model.document.Discount;
import com.sallatiy.discount.model.dto.response.DiscountProjection;
import com.sallatiy.discount.model.enums.StatusDiscount;
import com.sallatiy.discount.repository.DiscountRepo;
import com.sallatiy.discount.repository.mongo.DiscountRepoMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class DiscountRepoImpl implements DiscountRepo {
    @Autowired
    private DiscountRepoMongo discountRepoMongo;
    @Override
    public Discount save(Discount discount) {
        return this.discountRepoMongo.save(discount);
    }


    public Optional<Discount> findById(String id){
        return this.discountRepoMongo.findById(id);
    }

    @Override
    public Optional<Discount> findByCodeAndStatusNot(String code, StatusDiscount status) {
        return this.discountRepoMongo.findByCodeAndStatusNot(code,status);
    }

    @Override
    public Optional<Discount> findByIdAndStatusNot(String id, StatusDiscount status) {
        return this.discountRepoMongo.findByIdAndStatusNot(id,status);
    }

    @Override
    public Page<DiscountProjection> findAll(StatusDiscount status, PageRequest pageRequest) {
        return this.discountRepoMongo.findByStatusNot(status,pageRequest);
    }

    @Override
    public Page<DiscountProjection> findByCodeAndStatus(String code, StatusDiscount status, PageRequest pageRequest) {
        return this.discountRepoMongo.findByCodeContainingAndStatus(code,status,pageRequest);
    }

    @Override
    public Page<DiscountProjection> findByCode(String code, PageRequest pageRequest) {
        return this.discountRepoMongo.findByCodeContainingAndStatusNot(code,StatusDiscount.DELETED,pageRequest);
    }

    @Override
    public Page<DiscountProjection> findByStatus(StatusDiscount status, PageRequest pageRequest) {
        return this.discountRepoMongo.findByStatus(status,pageRequest);
    }

    @Override
    public List<Discount> findByNameAndStatusNot(String name, StatusDiscount status) {
        return this.discountRepoMongo.findByNameContainingAndStatusNot(name,status);
    }

    @Override
    public List<Discount> findAll() {
        return this.discountRepoMongo.findByStatusNot(StatusDiscount.DELETED);
    }

    @Override
    public void deleteDiscountById(String id) {
         this.discountRepoMongo.deleteById(id);
    }



}
