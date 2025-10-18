package com.medilab.backend.repository;

import com.medilab.backend.model.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
    
    List<ContactMessage> findByStatus(ContactMessage.MessageStatus status);
    
    List<ContactMessage> findByEmailOrderByCreatedAtDesc(String email);
    
    List<ContactMessage> findAllByOrderByCreatedAtDesc();
}

