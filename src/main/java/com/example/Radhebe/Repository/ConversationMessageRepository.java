package com.example.Radhebe.Repository;

import com.example.Radhebe.Entity.ConversationMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConversationMessageRepository extends JpaRepository<ConversationMessage, UUID> {

    List<ConversationMessage> findByRoomIdOrderByCreatedAtDesc(UUID roomId);

    Page<ConversationMessage> findByRoomIdOrderByCreatedAtDesc(UUID roomId, Pageable pageable);

    List<ConversationMessage> findByRoomIdAndIsReadFalse(UUID roomId);

    @Query("SELECT cm FROM ConversationMessage cm JOIN FETCH cm.sender WHERE cm.roomId = :roomId ORDER BY cm.createdAt DESC")
    List<ConversationMessage> findByRoomIdWithSenderOrderByCreatedAtDesc(@Param("roomId") UUID roomId);

    @Modifying
    @Query("UPDATE ConversationMessage cm SET cm.isRead = true WHERE cm.roomId = :roomId AND cm.isRead = false")
    int markAllAsReadInRoom(@Param("roomId") UUID roomId);

    Long countByRoomIdAndIsReadFalse(UUID roomId);

    @Query("SELECT cm FROM ConversationMessage cm WHERE cm.roomId = :roomId ORDER BY cm.createdAt DESC")
    Page<ConversationMessage> findLastMessageInRoom(@Param("roomId") UUID roomId, Pageable pageable);
}
