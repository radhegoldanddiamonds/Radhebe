package com.example.Radhebe.Controllers;

import com.example.Radhebe.DTO.CreateRoomRequest;
import com.example.Radhebe.DTO.SendMessageRequest;
import com.example.Radhebe.Entity.ConversationMessage;
import com.example.Radhebe.Entity.ConversationRoom;
import com.example.Radhebe.Services.ConversationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/conversations")
@CrossOrigin(origins = "*")
public class ConversationController {

    @Autowired
    private ConversationService conversationService;

    @GetMapping("/rooms")
    public ResponseEntity<List<ConversationRoom>> getConversationRooms(
            @RequestParam(required = false) UUID userId) {
        List<ConversationRoom> rooms = conversationService.getConversationRooms(userId);
        return ResponseEntity.ok(rooms);
    }

    @PostMapping("/rooms")
    public ResponseEntity<ConversationRoom> createConversationRoom(
            @Valid @RequestBody CreateRoomRequest request) {
        ConversationRoom room = conversationService.createRoom(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(room);
    }

    @GetMapping("/rooms/{roomId}/messages")
    public ResponseEntity<List<ConversationMessage>> getMessages(
            @PathVariable UUID roomId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {

        Pageable pageable = PageRequest.of(page, size);
        List<ConversationMessage> messages = conversationService.getMessages(roomId, pageable);
        return ResponseEntity.ok(messages);
    }

    @PostMapping("/messages")
    public ResponseEntity<ConversationMessage> sendMessage(
            @Valid @RequestBody SendMessageRequest request) {
        ConversationMessage message = conversationService.sendMessage(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PutMapping("/messages/{id}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable UUID id) {
        conversationService.markAsRead(id);
        return ResponseEntity.ok().build();
    }
}
