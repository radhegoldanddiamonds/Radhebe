package com.example.Radhebe.Repository;

import com.example.Radhebe.Entity.CartItem;
import com.example.Radhebe.Entity.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, UUID> {

    List<CartItem> findByUserIdOrderByAddedAtDesc(UUID userId);

    List<CartItem> findByStatus(CartStatus status);

    List<CartItem> findAll();

    List<CartItem> findByUserIdAndStatus(UUID userId, CartStatus status);

    Optional<CartItem> findByUserIdAndProductIdAndStatus(UUID userId, UUID productId, String status);

    @Query("SELECT ci FROM CartItem ci JOIN FETCH ci.product WHERE ci.userId = :userId")
    List<CartItem> findByUserIdWithProduct(@Param("userId") UUID userId);

    @Query("SELECT ci FROM CartItem ci JOIN FETCH ci.product JOIN FETCH ci.user WHERE ci.status = :status")
    List<CartItem> findByStatusWithDetails(@Param("status") CartStatus status);

    Long countByUserId(UUID userId);

    Long countByStatus(CartStatus status);

    @Modifying
    @Query("UPDATE CartItem ci SET ci.status = :newStatus WHERE ci.id = :id")
    int updateStatus(@Param("id") UUID id, @Param("newStatus") CartStatus newStatus);
}
