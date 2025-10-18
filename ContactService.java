package com.medilab.backend.service;

import com.medilab.backend.dto.ContactRequest;
import com.medilab.backend.model.ContactMessage;
import com.medilab.backend.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactMessageRepository contactMessageRepository;

    @Autowired
    private EmailService emailService;

    public ContactMessage createContactMessage(ContactRequest request) {
        ContactMessage contactMessage = new ContactMessage(
            request.getName(),
            request.getEmail(),
            request.getSubject(),
            request.getMessage()
        );

        ContactMessage savedMessage = contactMessageRepository.save(contactMessage);
        
        // Send email notifications
        emailService.sendContactConfirmation(savedMessage);
        
        return savedMessage;
    }

    public List<ContactMessage> getAllContactMessages() {
        return contactMessageRepository.findAllByOrderByCreatedAtDesc();
    }

    public Optional<ContactMessage> getContactMessageById(Long id) {
        return contactMessageRepository.findById(id);
    }

    public List<ContactMessage> getContactMessagesByStatus(ContactMessage.MessageStatus status) {
        return contactMessageRepository.findByStatus(status);
    }

    public List<ContactMessage> getContactMessagesByEmail(String email) {
        return contactMessageRepository.findByEmailOrderByCreatedAtDesc(email);
    }

    public ContactMessage updateContactMessageStatus(Long id, ContactMessage.MessageStatus status) {
        Optional<ContactMessage> messageOpt = contactMessageRepository.findById(id);
        if (messageOpt.isPresent()) {
            ContactMessage message = messageOpt.get();
            message.setStatus(status);
            return contactMessageRepository.save(message);
        }
        throw new RuntimeException("Contact message not found with id: " + id);
    }

    public void deleteContactMessage(Long id) {
        contactMessageRepository.deleteById(id);
    }
}

