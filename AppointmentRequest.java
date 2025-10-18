package com.medilab.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class AppointmentRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone is required")
    private String phone;

    @NotNull(message = "Appointment date is required")
    private LocalDate date;

    @NotBlank(message = "Department is required")
    private String department;

    @NotBlank(message = "Doctor is required")
    private String doctor;

    private String message;

    // Constructors
    public AppointmentRequest() {}

    public AppointmentRequest(String name, String email, String phone, LocalDate date, 
                             String department, String doctor, String message) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.date = date;
        this.department = department;
        this.doctor = doctor;
        this.message = message;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getDoctor() { return doctor; }
    public void setDoctor(String doctor) { this.doctor = doctor; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}

