package com.example.Radhebe.Services;

import com.example.Radhebe.DTO.CreateProductRequest;
import com.example.Radhebe.DTO.UpdateProductRequest;
import com.example.Radhebe.Entity.Product;
import com.example.Radhebe.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts(String name, String model, Boolean inStock, Pageable pageable) {
        Specification<Product> spec = Specification.where(null);

        if (name != null && !name.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                            "%" + name.toLowerCase() + "%"));
        }

        if (model != null && !model.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("model")),
                            "%" + model.toLowerCase() + "%"));
        }

        if (inStock != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("inStock"), inStock));
        }

        Page<Product> page = productRepository.findAll(spec, pageable);
        return page.getContent();
    }

    public Product findById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    public Product createProduct(CreateProductRequest request) {
        // Check if barcode already exists
        if (request.getBarcodeNumber() != null && !request.getBarcodeNumber().isEmpty()) {
            if (productRepository.existsByBarcodeNumber(request.getBarcodeNumber())) {
                throw new ValidationException("A product with this barcode already exists");
            }
        }

        Product product = new Product();
        product.setName(request.getName());
        product.setModel(request.getModel());
        product.setGrossWeight(request.getGrossWeight());
        product.setStoneWeight(request.getStoneWeight());
        product.setS1Weight(request.getS1Weight());
        product.setS2Weight(request.getS2Weight());
        product.setCarats(request.getCarats());
        product.setDescription(request.getDescription());
        product.setImageUrl(request.getImageUrl());
        product.setImages(request.getImages());
        product.setBarcodeNumber(request.getBarcodeNumber());
        product.setInStock(request.getInStock());

        // Calculate net weight
        BigDecimal netWeight = request.getGrossWeight()
                .subtract(request.getStoneWeight())
                .subtract(request.getS1Weight())
                .subtract(request.getS2Weight());
        product.setNetWeight(netWeight);

        return productRepository.save(product);
    }

    public Product updateProduct(UUID id, UpdateProductRequest request) {
        Product product = findById(id);

        if (request.getName() != null) product.setName(request.getName());
        if (request.getModel() != null) product.setModel(request.getModel());
        if (request.getGrossWeight() != null) product.setGrossWeight(request.getGrossWeight());
        if (request.getStoneWeight() != null) product.setStoneWeight(request.getStoneWeight());
        if (request.getS1Weight() != null) product.setS1Weight(request.getS1Weight());
        if (request.getS2Weight() != null) product.setS2Weight(request.getS2Weight());
        if (request.getCarats() != null) product.setCarats(request.getCarats());
        if (request.getDescription() != null) product.setDescription(request.getDescription());
        if (request.getImageUrl() != null) product.setImageUrl(request.getImageUrl());
        if (request.getImages() != null) product.setImages(request.getImages());
        if (request.getBarcodeNumber() != null) product.setBarcodeNumber(request.getBarcodeNumber());
        if (request.getInStock() != null) product.setInStock(request.getInStock());

        // Recalculate net weight if any weight fields changed
        if (request.getGrossWeight() != null || request.getStoneWeight() != null ||
                request.getS1Weight() != null || request.getS2Weight() != null) {
            BigDecimal netWeight = product.getGrossWeight()
                    .subtract(product.getStoneWeight())
                    .subtract(product.getS1Weight())
                    .subtract(product.getS2Weight());
            product.setNetWeight(netWeight);
        }

        return productRepository.save(product);
    }

    public void deleteProduct(UUID id) {
        Product product = findById(id);
        productRepository.delete(product);
    }

    public Long getProductCount(String name, String model, Boolean inStock) {
        Specification<Product> spec = Specification.where(null);

        if (name != null && !name.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                            "%" + name.toLowerCase() + "%"));
        }

        if (model != null && !model.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("model")),
                            "%" + model.toLowerCase() + "%"));
        }

        if (inStock != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("inStock"), inStock));
        }

        return productRepository.count(spec);
    }
}
