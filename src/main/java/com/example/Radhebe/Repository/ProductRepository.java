package com.example.Radhebe.Repository;

import com.example.Radhebe.Entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>, JpaSpecificationExecutor<Product> {

    boolean existsByBarcodeNumber(String barcodeNumber);

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByModelContainingIgnoreCase(String model);

    List<Product> findByInStock(Boolean inStock);

    List<Product> findByNameContainingIgnoreCaseAndInStock(String name, Boolean inStock);

    List<Product> findByModelContainingIgnoreCaseAndInStock(String model, Boolean inStock);

    @Query("SELECT COUNT(p) FROM Product p WHERE " +
            "(:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:model IS NULL OR LOWER(p.model) LIKE LOWER(CONCAT('%', :model, '%'))) AND " +
            "(:inStock IS NULL OR p.inStock = :inStock)")
    Long countWithFilters(@Param("name") String name,
                          @Param("model") String model,
                          @Param("inStock") Boolean inStock);

    @Query("SELECT p FROM Product p WHERE " +
            "(:grossWeightMin IS NULL OR p.grossWeight >= :grossWeightMin) AND " +
            "(:grossWeightMax IS NULL OR p.grossWeight <= :grossWeightMax) AND " +
            "(:netWeightMin IS NULL OR p.netWeight >= :netWeightMin) AND " +
            "(:netWeightMax IS NULL OR p.netWeight <= :netWeightMax)")
    List<Product> findByWeightRange(@Param("grossWeightMin") BigDecimal grossWeightMin,
                                    @Param("grossWeightMax") BigDecimal grossWeightMax,
                                    @Param("netWeightMin") BigDecimal netWeightMin,
                                    @Param("netWeightMax") BigDecimal netWeightMax,
                                    Pageable pageable);
}
