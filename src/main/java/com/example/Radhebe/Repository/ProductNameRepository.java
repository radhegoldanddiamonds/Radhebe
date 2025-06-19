package com.example.Radhebe.Repository;

import com.example.Radhebe.Entity.ProductName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductNameRepository extends JpaRepository<ProductName, UUID> {

    boolean existsByNameIgnoreCase(String name);

    Optional<ProductName> findByNameIgnoreCase(String name);

    List<ProductName> findAllByOrderByNameAsc();

    @Query("SELECT pn.name FROM ProductName pn ORDER BY pn.name ASC")
    List<String> findAllNames();
}
