package com.cl3880.finprodpref.controller;

import com.cl3880.finprodpref.dto.LikedProductRequest;
import com.cl3880.finprodpref.dto.LikedProductResponse;
import com.cl3880.finprodpref.service.LikedProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/liked-products")
@CrossOrigin(origins = "${cors.allowed-origins}", allowCredentials = "true")
public class LikedProductController {

    private final LikedProductService likedProductService;

    public LikedProductController(LikedProductService likedProductService) {
        this.likedProductService = likedProductService;
    }

    @PostMapping
    public ResponseEntity<Void> addLikedProduct(@Valid @RequestBody LikedProductRequest request) {
        likedProductService.addLikedProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{userId}")
    public List<LikedProductResponse> getLikedProducts(@PathVariable String userId) {
        return likedProductService.getLikedProducts(userId);
    }

    @DeleteMapping("/{userId}/{productNo}/{account}")
    public ResponseEntity<Void> deleteLikedProduct(
            @PathVariable String userId,
            @PathVariable String productNo,
            @PathVariable String account) {

        likedProductService.deleteLikedProduct(userId, productNo, account);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateLikedProduct(@Valid @RequestBody LikedProductRequest request) {
        likedProductService.updateLikedProduct(request);
        return ResponseEntity.noContent().build();
    }
} 