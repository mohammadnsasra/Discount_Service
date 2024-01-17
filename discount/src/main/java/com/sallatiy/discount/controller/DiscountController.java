package com.sallatiy.discount.controller;


import com.sallatiy.discount.model.dto.request.DiscountRequest;
import com.sallatiy.discount.model.dto.request.SearchRequest;
import com.sallatiy.discount.model.dto.response.DiscountProjection;
import com.sallatiy.discount.model.dto.response.DiscountResponse;
import com.sallatiy.discount.model.dto.response.MessageResponse;
import com.sallatiy.discount.model.enums.StatusDiscount;
import com.sallatiy.discount.service.DiscountService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("api/v1/discount")
@Slf4j
public class DiscountController {
    @Autowired
    private DiscountService discountService;
    @PostMapping
    public ResponseEntity<DiscountResponse> createDiscount(@Valid @RequestBody DiscountRequest request) {
        return ResponseEntity.ok(this.discountService.createDiscount(request));
    }
    @PutMapping
    public ResponseEntity<DiscountResponse> updateById(@Valid @RequestBody DiscountRequest request,@RequestParam String id) {
       return ResponseEntity.ok(this.discountService.updateById(request,id));
    }

    @GetMapping
    public ResponseEntity<DiscountResponse> findById(@RequestParam String id){
        return ResponseEntity.ok(this.discountService.findById(id));
    }
    @DeleteMapping
    public ResponseEntity<MessageResponse> deleteDiscount(@RequestParam String id) {
        return ResponseEntity.ok(this.discountService.deleteDiscount(id));
    }
    @PutMapping("/soft-delete")
    public ResponseEntity<DiscountResponse> softDelete(@RequestParam String id) {
        return ResponseEntity.ok(this.discountService.softDelete(id));
    }

    @GetMapping("/find-by-code")
    public ResponseEntity<DiscountResponse> findByCode(@RequestParam String code) {
        return ResponseEntity.ok(this.discountService.findByCode(code));
    }
    @GetMapping("/find-by-name")
    public ResponseEntity<List<DiscountResponse>> findByName(@RequestParam String name){
        return ResponseEntity.ok(this.discountService.findByName(name));
    }
    @GetMapping("/get-statues")
    public List<StatusDiscount> getStatus() {
        return this.discountService.getStatus();
    }
    @GetMapping("/find-all")
    public ResponseEntity<List<DiscountResponse>> getAll() {
        return ResponseEntity.ok(this.discountService.getAll());
    }
    @GetMapping("/find-all-by-page")
    public ResponseEntity<Page<DiscountProjection>> getAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                           @RequestParam(name = "size", defaultValue = "10") int size
    ){
        return ResponseEntity.ok(this.discountService.getAll(page,size));
    }
    @GetMapping("/search")
    public ResponseEntity<Page<DiscountProjection>> search( @RequestBody SearchRequest request,
                                                           @RequestParam(name = "page", defaultValue = "0") int page,
                                                           @RequestParam(name = "size", defaultValue = "10") int size){
        return ResponseEntity.ok(this.discountService.search(request,page,size));
    }
}
