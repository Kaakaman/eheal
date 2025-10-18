package com.medilab.backend.controller;

import com.medilab.backend.dto.ContactRequest;
import com.medilab.backend.model.ContactMessage;
import com.medilab.backend.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contact")
@Tag(name = "Contact", description = "Contact message management API")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    @Operation(summary = "Send a contact message")
    public ResponseEntity<ContactMessage> createContactMessage(@Valid @RequestBody ContactRequest request) {
        try {
            ContactMessage contactMessage = contactService.createContactMessage(request);
            return new ResponseEntity<>(contactMessage, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    @Operation(summary = "Get all contact messages")
    public ResponseEntity<List<ContactMessage>> getAllContactMessages() {
        List<ContactMessage> messages = contactService.getAllContactMessages();
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get contact message by ID")
    public ResponseEntity<ContactMessage> getContactMessageById(@PathVariable Long id) {
        Optional<ContactMessage> message = contactService.getContactMessageById(id);
        return message.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get contact messages by status")
    public ResponseEntity<List<ContactMessage>> getContactMessagesByStatus(@PathVariable ContactMessage.MessageStatus status) {
        List<ContactMessage> messages = contactService.getContactMessagesByStatus(status);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Get contact messages by email")
    public ResponseEntity<List<ContactMessage>> getContactMessagesByEmail(@PathVariable String email) {
        List<ContactMessage> messages = contactService.getContactMessagesByEmail(email);
        return ResponseEntity.ok(messages);
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Update contact message status")
    public ResponseEntity<ContactMessage> updateContactMessageStatus(
            @PathVariable Long id, 
            @RequestParam ContactMessage.MessageStatus status) {
        try {
            ContactMessage message = contactService.updateContactMessageStatus(id, status);
            return ResponseEntity.ok(message);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete contact message")
    public ResponseEntity<Void> deleteContactMessage(@PathVariable Long id) {
        try {
            contactService.deleteContactMessage(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

