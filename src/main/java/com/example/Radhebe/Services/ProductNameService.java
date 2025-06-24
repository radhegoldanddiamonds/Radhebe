package com.example.Radhebe.Services;
import com.example.Radhebe.Entity.ProductName;
import com.example.Radhebe.Repository.ProductNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductNameService {

    @Autowired
    private ProductNameRepository productNameRepository;

    public List<ProductName> getAllProductNames() {
        return productNameRepository.findAll();
    }

    public ProductName createProductName(String name) {
        ProductName productName = new ProductName(name);
        return productNameRepository.save(productName);
    }

    public boolean deleteProductName(String id) {
        Optional<ProductName> productName = productNameRepository.findById(UUID.fromString(id));
        if (productName.isPresent()) {
            productNameRepository.deleteById(UUID.fromString(id));
            return true;
        }
        return false;
    }
}
