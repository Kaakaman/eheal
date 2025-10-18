package com.medilab.backend.service;

import com.medilab.backend.model.Appointment;
import com.medilab.backend.model.ContactMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${app.mail.from}")
    private String fromEmail;

    @Value("${app.mail.admin}")
    private String adminEmail;

    public void sendAppointmentConfirmation(Appointment appointment) {
        try {
            // Send confirmation to patient
            SimpleMailMessage patientMessage = new SimpleMailMessage();
            patientMessage.setFrom(fromEmail);
            patientMessage.setTo(appointment.getEmail());
            patientMessage.setSubject("Appointment Confirmation - MediLab");
            patientMessage.setText(buildAppointmentConfirmationText(appointment));
            mailSender.send(patientMessage);

            // Send notification to admin
            SimpleMailMessage adminMessage = new SimpleMailMessage();
            adminMessage.setFrom(fromEmail);
            adminMessage.setTo(adminEmail);
            adminMessage.setSubject("New Appointment Request - MediLab");
            adminMessage.setText(buildAppointmentNotificationText(appointment));
            mailSender.send(adminMessage);
        } catch (Exception e) {
            // Log error but don't fail the appointment creation
            System.err.println("Failed to send appointment emails: " + e.getMessage());
        }
    }

    public void sendContactConfirmation(ContactMessage contactMessage) {
        try {
            // Send confirmation to sender
            SimpleMailMessage senderMessage = new SimpleMailMessage();
            senderMessage.setFrom(fromEmail);
            senderMessage.setTo(contactMessage.getEmail());
            senderMessage.setSubject("Message Received - MediLab");
            senderMessage.setText(buildContactConfirmationText(contactMessage));
            mailSender.send(senderMessage);

            // Send notification to admin
            SimpleMailMessage adminMessage = new SimpleMailMessage();
            adminMessage.setFrom(fromEmail);
            adminMessage.setTo(adminEmail);
            adminMessage.setSubject("New Contact Message - MediLab");
            adminMessage.setText(buildContactNotificationText(contactMessage));
            mailSender.send(adminMessage);
        } catch (Exception e) {
            // Log error but don't fail the contact message creation
            System.err.println("Failed to send contact emails: " + e.getMessage());
        }
    }

    private String buildAppointmentConfirmationText(Appointment appointment) {
        return String.format(
            "Dear %s,\n\n" +
            "Thank you for booking an appointment with MediLab. We have received your request with the following details:\n\n" +
            "Appointment Date: %s\n" +
            "Department: %s\n" +
            "Doctor: %s\n" +
            "Phone: %s\n\n" +
            "We will contact you soon to confirm your appointment.\n\n" +
            "Best regards,\n" +
            "MediLab Team",
            appointment.getName(),
            appointment.getAppointmentDate(),
            appointment.getDepartment(),
            appointment.getDoctor(),
            appointment.getPhone()
        );
    }

    private String buildAppointmentNotificationText(Appointment appointment) {
        return String.format(
            "New appointment request received:\n\n" +
            "Patient: %s\n" +
            "Email: %s\n" +
            "Phone: %s\n" +
            "Date: %s\n" +
            "Department: %s\n" +
            "Doctor: %s\n" +
            "Message: %s\n" +
            "Submitted: %s",
            appointment.getName(),
            appointment.getEmail(),
            appointment.getPhone(),
            appointment.getAppointmentDate(),
            appointment.getDepartment(),
            appointment.getDoctor(),
            appointment.getMessage() != null ? appointment.getMessage() : "No message",
            appointment.getCreatedAt()
        );
    }

    private String buildContactConfirmationText(ContactMessage contactMessage) {
        return String.format(
            "Dear %s,\n\n" +
            "Thank you for contacting MediLab. We have received your message regarding \"%s\".\n\n" +
            "We will review your message and get back to you as soon as possible.\n\n" +
            "Best regards,\n" +
            "MediLab Team",
            contactMessage.getName(),
            contactMessage.getSubject()
        );
    }

    private String buildContactNotificationText(ContactMessage contactMessage) {
        return String.format(
            "New contact message received:\n\n" +
            "From: %s\n" +
            "Email: %s\n" +
            "Subject: %s\n" +
            "Message: %s\n" +
            "Submitted: %s",
            contactMessage.getName(),
            contactMessage.getEmail(),
            contactMessage.getSubject(),
            contactMessage.getMessage(),
            contactMessage.getCreatedAt()
        );
    }
}

