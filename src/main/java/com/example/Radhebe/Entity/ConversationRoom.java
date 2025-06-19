package com.example.Radhebe.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "conversation_rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ConversationRoom extends BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "last_message_at")
    private LocalDateTime lastMessageAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private Profile user;

    @OneToMany(mappedBy = "roomId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("createdAt DESC")
    private List<ConversationMessage> messages = new ArrayList<>();

    @PrePersist
    private void prePersist() {
        if (lastMessageAt == null) {
            lastMessageAt = LocalDateTime.now();
        }
    }
}
