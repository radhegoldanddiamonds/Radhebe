package com.example.Radhebe.Repository;

import com.example.Radhebe.Entity.ConversationRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConversationRoomRepository extends JpaRepository<ConversationRoom, UUID> {

    Optional<ConversationRoom> findByUserId(UUID userId);

    List<ConversationRoom> findByUserIdOrderByUpdatedAtDesc(UUID userId);

    List<ConversationRoom> findAllByOrderByUpdatedAtDesc();

    @Query("SELECT cr FROM ConversationRoom cr JOIN FETCH cr.user WHERE cr.userId = :userId")
    Optional<ConversationRoom> findByUserIdWithUser(@Param("userId") UUID userId);

    @Query("SELECT cr FROM ConversationRoom cr JOIN FETCH cr.user ORDER BY cr.updatedAt DESC")
    List<ConversationRoom> findAllWithUserOrderByUpdatedAtDesc();

    @Query("SELECT COUNT(cm) FROM ConversationMessage cm WHERE cm.roomId = :roomId AND cm.isRead = false")
    Long countUnreadMessages(@Param("roomId") UUID roomId);
}
