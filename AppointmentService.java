package com.medilab.backend.service;

import com.medilab.backend.dto.AppointmentRequest;
import com.medilab.backend.model.Appointment;
import com.medilab.backend.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private EmailService emailService;

    public Appointment createAppointment(AppointmentRequest request) {
        Appointment appointment = new Appointment(
            request.getName(),
            request.getEmail(),
            request.getPhone(),
            request.getDate(),
            request.getDepartment(),
            request.getDoctor(),
            request.getMessage()
        );

        Appointment savedAppointment = appointmentRepository.save(appointment);
        
        // Send email notifications
        emailService.sendAppointmentConfirmation(savedAppointment);
        
        return savedAppointment;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    public List<Appointment> getAppointmentsByStatus(Appointment.AppointmentStatus status) {
        return appointmentRepository.findByStatus(status);
    }

    public List<Appointment> getAppointmentsByDepartment(String department) {
        return appointmentRepository.findByDepartment(department);
    }

    public List<Appointment> getAppointmentsByDoctor(String doctor) {
        return appointmentRepository.findByDoctor(doctor);
    }

    public List<Appointment> getAppointmentsByDate(LocalDate date) {
        return appointmentRepository.findByAppointmentDate(date);
    }

    public List<Appointment> getAppointmentsByDateRange(LocalDate startDate, LocalDate endDate) {
        return appointmentRepository.findByAppointmentDateBetween(startDate, endDate);
    }

    public List<Appointment> getAppointmentsByEmail(String email) {
        return appointmentRepository.findByEmailOrderByCreatedAtDesc(email);
    }

    public Appointment updateAppointmentStatus(Long id, Appointment.AppointmentStatus status) {
        Optional<Appointment> appointmentOpt = appointmentRepository.findById(id);
        if (appointmentOpt.isPresent()) {
            Appointment appointment = appointmentOpt.get();
            appointment.setStatus(status);
            return appointmentRepository.save(appointment);
        }
        throw new RuntimeException("Appointment not found with id: " + id);
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}

