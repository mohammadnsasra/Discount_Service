package com.sallatiy.discount.service;


import com.sallatiy.discount.model.dto.request.DiscountRequest;
import com.sallatiy.discount.model.dto.request.SearchRequest;
import com.sallatiy.discount.model.dto.response.DiscountProjection;
import com.sallatiy.discount.model.dto.response.DiscountResponse;
import com.sallatiy.discount.model.dto.response.MessageResponse;
import com.sallatiy.discount.model.enums.StatusDiscount;
import org.springframework.data.domain.Page;
import java.util.List;


public interface DiscountService {
    public DiscountResponse createDiscount(DiscountRequest request);

    public List<StatusDiscount> getStatus() ;
    public DiscountResponse findById(String id);
    public DiscountResponse findByCode(String code);
    public DiscountResponse softDelete(String id);
    MessageResponse deleteDiscount(String id);
    public List<DiscountResponse> getAll();
    public Page<DiscountProjection> getAll(int page, int size);
    public List<DiscountResponse> findByName(String name);
    public DiscountResponse updateById(DiscountRequest request,String id);
    public Page<DiscountProjection> search(SearchRequest request, int page, int size);
    public void checkDate();



}
