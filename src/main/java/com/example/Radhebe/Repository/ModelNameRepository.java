package com.example.Radhebe.Repository;

import com.example.Radhebe.Entity.ModelName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ModelNameRepository extends JpaRepository<ModelName, UUID> {

    boolean existsByNameIgnoreCase(String name);

    Optional<ModelName> findByNameIgnoreCase(String name);

    List<ModelName> findAllByOrderByNameAsc();

    @Query("SELECT mn.name FROM ModelName mn ORDER BY mn.name ASC")
    List<String> findAllNames();
}
