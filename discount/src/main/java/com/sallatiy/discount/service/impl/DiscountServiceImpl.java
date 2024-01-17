package com.sallatiy.discount.service.impl;

import com.sallatiy.discount.costumeExceptions.ConflictException;
import com.sallatiy.discount.costumeExceptions.DateException;
import com.sallatiy.discount.costumeExceptions.NotFoundException;
import com.sallatiy.discount.mapper.DiscountMapper;
import com.sallatiy.discount.model.document.Discount;
import com.sallatiy.discount.model.dto.request.DiscountRequest;
import com.sallatiy.discount.model.dto.request.SearchRequest;
import com.sallatiy.discount.model.dto.response.DiscountProjection;
import com.sallatiy.discount.model.dto.response.DiscountResponse;
import com.sallatiy.discount.model.dto.response.MessageResponse;
import com.sallatiy.discount.model.enums.StatusDiscount;
import com.sallatiy.discount.repository.DiscountRepo;
import com.sallatiy.discount.service.DiscountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DiscountServiceImpl implements DiscountService {
    @Autowired
    private DiscountMapper mapper;
    @Autowired
    private DiscountRepo discountRepo;

    @Override
    public DiscountResponse createDiscount(DiscountRequest request) {
        log.info(String.format("creating a new discount [%s] ", request));
        this.checkDate();
        if (this.discountRepo.findByCodeAndStatusNot(request.getCode(), StatusDiscount.DELETED).isPresent())
            throw new ConflictException("this discount code already exist");
        Discount discount = this.mapper.requestToDiscount(request);
        discount.setCreatedAt(LocalDateTime.now());
        if (discount.getStartDate().isBefore(LocalDateTime.now()))
            throw new DateException(" The Start Date Is Invalid, because is before Date Now");
        if (discount.getEndDate().isBefore(discount.getStartDate()))
            throw new DateException("The end Date should not be before start date");

        if (discount.getStartDate().isAfter(LocalDateTime.now()) ||
                discount.getEndDate().isBefore(LocalDateTime.now()))
            discount.setStatus(StatusDiscount.UNACTIVE);
        else
            discount.setStatus(StatusDiscount.ACTIVE);
        return this.mapper.discountToResponse(this.discountRepo.save(discount));

    }

    @Override
    public DiscountResponse updateById(DiscountRequest request, String id) {
        log.info(String.format("updating  discount [%s] ", request));
        this.checkDate();
        Discount discount = this.discountRepo.findByIdAndStatusNot(id, StatusDiscount.DELETED).orElseThrow(() ->
                new NotFoundException(String.format("does not exist discount by this id [%s]", id)));
        discount.setUpdatedAt(LocalDateTime.now());
        discount.setName(request.getName());
        discount.setCode(request.getCode());
        discount.setDiscountValue(request.getDiscountValue());
        discount.setStartDate(LocalDateTime.parse(request.getStartDate()));
        discount.setEndDate(LocalDateTime.parse(request.getEndDate()));

        if (discount.getStartDate().isBefore(LocalDateTime.now()))
            throw new DateException(" The Start Date Is Invalid, because is before Date Now");
        if (discount.getEndDate().isBefore(discount.getStartDate()))
            throw new DateException("The end Date should not be before start date");
        if (discount.getStartDate().isAfter(LocalDateTime.now()) ||
                discount.getEndDate().isBefore(LocalDateTime.now()))
            discount.setStatus(StatusDiscount.UNACTIVE);
        else
            discount.setStatus(StatusDiscount.ACTIVE);
        return this.mapper.discountToResponse(this.discountRepo.save(discount));
    }

    @Override
    public Page<DiscountProjection> search(SearchRequest request, int page, int size) {
        log.info("searching  discount by status or code [%s] ");
        this.checkDate();
        PageRequest pageRequest = PageRequest.of(page, size);
        if (request.getCode() != null && request.getStatus() != null)
            return this.discountRepo.findByCodeAndStatus(request.getCode(), StatusDiscount.valueOf(request.getStatus()), pageRequest);
        else if (request.getCode() != null)
            return this.discountRepo.findByCode(request.getCode(), pageRequest);
        else if (request.getStatus() != null)
            return this.discountRepo.findByStatus(StatusDiscount.valueOf(request.getStatus()), pageRequest);

        return this.discountRepo.findAll(StatusDiscount.DELETED, pageRequest);
    }


    @Override
    public DiscountResponse findByCode(String code) {
        log.info(String.format("getting discount by code [%s] ", code));
        this.checkDate();
        Discount discount = this.discountRepo.findByCodeAndStatusNot(code, StatusDiscount.DELETED).orElseThrow(() ->
                new NotFoundException(String.format("this discount code [%s] does not exist", code)));
        return this.mapper.discountToResponse(discount);
    }

    @Override
    public DiscountResponse findById(String id) {
        log.info(String.format("getting discount  by id" + id));
        this.checkDate();
        Discount discounts = this.discountRepo.findByIdAndStatusNot(id, StatusDiscount.DELETED).orElseThrow(() ->
                new NotFoundException("this discount does not exist"));
        return this.mapper.discountToResponse((discounts));
    }

    @Override
    public List<DiscountResponse> findByName(String name) {
        log.info(String.format("getting discount  by name" + name));
        this.checkDate();
        List<Discount> discounts = this.discountRepo.findByNameAndStatusNot(name, StatusDiscount.DELETED);
        if (discounts.isEmpty())
            throw new NotFoundException(String.format("does not found any discount by name [%s] ", name));
        return discounts
                .stream()
                .map(e -> mapper.discountToResponse(e)).collect(Collectors.toList());
    }


    @Override
    public DiscountResponse softDelete(String id) {
        log.info(String.format("make status os discount is DELETED  by id" + id));
        this.checkDate();
        Discount discount = this.discountRepo.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("this [%s] discount does not exist", id)));
        discount.setStatus(StatusDiscount.DELETED);
        discount.setDeletedAt(LocalDateTime.now());
        return this.mapper.discountToResponse(this.discountRepo.save(discount));
    }

    @Override
    public MessageResponse deleteDiscount(String id) {
        log.info(String.format("deleting discount  by id" + id));
        this.checkDate();
        Discount discount = this.discountRepo.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("this discount does not exist", id)));
        this.discountRepo.deleteDiscountById(id);
        return this.mapper.toMessageResponse(String.format("The [%s] Cart is deleted successfully", id));
    }

    @Override
    public List<DiscountResponse> getAll() {
        log.info("getting All discount by id");
        this.checkDate();
        return this.discountRepo.findAll()
                .stream()
                .map(e -> mapper.discountToResponse(e))
                .collect(Collectors.toList());
    }

    @Override
    public Page<DiscountProjection> getAll(int page, int size) {
        log.info("getting All discount  by page");
        this.checkDate();
        PageRequest pageRequest = PageRequest.of(page, size);
        return this.discountRepo.findAll(StatusDiscount.DELETED, pageRequest);
    }
    @Override
    public List<StatusDiscount> getStatus() {
        log.info("getting All status ");
        this.checkDate();
        log.info("Fetching cart statuses");
        return Arrays.stream(StatusDiscount.values()).toList();
    }

    @Override
    public void checkDate() {
        log.info("checking date");
        this.discountRepo.findAll().forEach(discount -> {
            StatusDiscount status = (discount.getStartDate().isAfter(LocalDateTime.now()) ||
                    discount.getEndDate().isBefore(LocalDateTime.now()))

//                       StatusDiscount status =( (LocalDateTime.now().isBefore(discount.getStartDate()) &&
//                               LocalDateTime.now().isEqual(discount.getStartDate()) )||
//                               ( LocalDateTime.now().isAfter(discount.getEndDate()) && LocalDateTime.now().isEqual(discount.getEndDate())))
                    ? StatusDiscount.UNACTIVE
                    : StatusDiscount.ACTIVE;

            discount.setStatus(status);
            this.discountRepo.save(discount);
        });

    }
}

