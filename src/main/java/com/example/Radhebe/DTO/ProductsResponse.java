package com.example.Radhebe.DTO;

import com.example.Radhebe.Entity.Product;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class ProductsResponse {

    private List<Product> content;
    private Long totalElements;
}