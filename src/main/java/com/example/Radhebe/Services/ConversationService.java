package com.example.Radhebe.Services;

import com.example.Radhebe.DTO.CreateRoomRequest;
import com.example.Radhebe.DTO.SendMessageRequest;
import com.example.Radhebe.Entity.ConversationMessage;
import com.example.Radhebe.Entity.ConversationRoom;
import com.example.Radhebe.Repository.ConversationMessageRepository;
import com.example.Radhebe.Repository.ConversationRoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ConversationService {

    @Autowired
    private ConversationRoomRepository roomRepository;

    @Autowired
    private ConversationMessageRepository messageRepository;

    public List<ConversationRoom> getConversationRooms(UUID userId) {
        if (userId != null) {
            return roomRepository.findByUserIdOrderByUpdatedAtDesc(userId);
        }
        return roomRepository.findAllByOrderByUpdatedAtDesc();
    }

    public ConversationRoom createRoom(CreateRoomRequest request) {
        // Check if room already exists for this user
        Optional<ConversationRoom> existingRoom = roomRepository.findByUserId(request.getUserId());
        if (existingRoom.isPresent()) {
            return existingRoom.get();
        }

        ConversationRoom room = new ConversationRoom();
        room.setUserId(request.getUserId());
        return roomRepository.save(room);
    }

    public List<ConversationMessage> getMessages(UUID roomId, Pageable pageable) {
        Page<ConversationMessage> page = messageRepository
                .findByRoomIdOrderByCreatedAtDesc(roomId, pageable);
        return page.getContent();
    }

    public ConversationMessage sendMessage(SendMessageRequest request) {
        // Validate room exists
        ConversationRoom room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Conversation room not found"));

        ConversationMessage message = new ConversationMessage();
        message.setRoomId(request.getRoomId());
        message.setSenderId(request.getSenderId());
        message.setMessage(request.getMessage());
        message.setIsRead(false);

        ConversationMessage savedMessage = messageRepository.save(message);

        // Update room's last message time
        room.setLastMessageAt(LocalDateTime.now());
        roomRepository.save(room);

        return savedMessage;
    }

    public void markAsRead(UUID messageId) {
        ConversationMessage message = messageRepository.findById(messageId)
                .orElseThrow(() -> new ResourceNotFoundException("Message not found"));
        message.setIsRead(true);
        messageRepository.save(message);
    }
}
