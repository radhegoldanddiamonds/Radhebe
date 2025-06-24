package com.example.Radhebe.Controllers;

import com.example.Radhebe.Entity.ProductName;
import com.example.Radhebe.Services.ProductNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product-names")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductNameController {

    @Autowired
    private ProductNameService productNameService;

    @GetMapping
    public ResponseEntity<List<ProductName>> getAllProductNames() {
        List<ProductName> productNames = productNameService.getAllProductNames();
        return ResponseEntity.ok(productNames);
    }

    @PostMapping
    public ResponseEntity<ProductName> createProductName(@RequestBody    Map<String, String> request) {
        String name = request.get("name");
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        ProductName productName = productNameService.createProductName(name.trim());
        return ResponseEntity.ok(productName);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductName(@PathVariable String id) {
        boolean deleted = productNameService.deleteProductName(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
