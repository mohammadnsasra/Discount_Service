package com.sallatiy.discountService.controller;


import com.sallatiy.discountService.model.dto.request.DiscountRequest;
import com.sallatiy.discountService.model.dto.request.SearchRequest;
import com.sallatiy.discountService.model.dto.response.DiscountProjection;
import com.sallatiy.discountService.model.dto.response.DiscountResponse;
import com.sallatiy.discountService.model.dto.response.MessageResponse;
import com.sallatiy.discountService.model.enums.StatusDiscount;
import com.sallatiy.discountService.service.DiscountService;
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
    @PutMapping("/{id}")
    public ResponseEntity<DiscountResponse> updateById(@Valid @RequestBody DiscountRequest request,@PathVariable String id) {
       return ResponseEntity.ok(this.discountService.updateById(request,id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscountResponse> findById(@PathVariable String id){
        return ResponseEntity.ok(this.discountService.findById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteDiscount(@PathVariable String id) {
        return ResponseEntity.ok(this.discountService.deleteDiscount(id));
    }
    @PutMapping("/soft-delete/{id}")
    public ResponseEntity<DiscountResponse> softDelete(@PathVariable String id) {
        return ResponseEntity.ok(this.discountService.softDelete(id));
    }

    @GetMapping("/find-by-code/{code}")
    public ResponseEntity<DiscountResponse> findByCode(@PathVariable String code) {
        return ResponseEntity.ok(this.discountService.findByCode(code));
    }
    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<List<DiscountResponse>> findByName(@PathVariable String name){
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
