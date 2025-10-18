# MediLab Java Backend Requirements

## Overview
Based on analysis of the MediLab website, the backend needs to support the following functionality:

## Core Features Required

### 1. Appointment Booking System
- **Endpoint**: POST /api/appointments
- **Fields**:
  - name (String)
  - email (String)
  - phone (String)
  - date (Date)
  - department (String)
  - doctor (String)
  - message (String, optional)
- **Functionality**: Store appointment requests and send email notifications

### 2. Contact Form System
- **Endpoint**: POST /api/contact
- **Fields**:
  - name (String)
  - email (String)
  - subject (String)
  - message (String)
- **Functionality**: Store contact messages and send email notifications

### 3. Data Management
- **Doctors Management**: CRUD operations for doctor profiles
- **Departments Management**: CRUD operations for medical departments
- **Appointments Management**: View and manage appointment requests
- **Contact Messages**: View and manage contact form submissions

### 4. Additional Features
- **Email Notifications**: Send confirmation emails for appointments and contact forms
- **Data Validation**: Validate all input data
- **CORS Support**: Enable cross-origin requests from the frontend
- **Error Handling**: Proper error responses and logging

## Technology Stack
- **Framework**: Spring Boot (Java)
- **Database**: H2 (in-memory for development) / PostgreSQL (for production)
- **Email**: JavaMail API
- **Build Tool**: Maven
- **API Documentation**: Swagger/OpenAPI

## Database Schema

### Appointments Table
- id (Long, Primary Key)
- name (String)
- email (String)
- phone (String)
- appointment_date (Date)
- department (String)
- doctor (String)
- message (Text)
- status (String) - PENDING, CONFIRMED, CANCELLED
- created_at (Timestamp)

### Contact Messages Table
- id (Long, Primary Key)
- name (String)
- email (String)
- subject (String)
- message (Text)
- status (String) - NEW, READ, REPLIED
- created_at (Timestamp)

### Doctors Table
- id (Long, Primary Key)
- name (String)
- specialization (String)
- title (String)
- bio (Text)
- image_url (String)
- email (String)
- phone (String)

### Departments Table
- id (Long, Primary Key)
- name (String)
- description (Text)
- head_doctor_id (Long, Foreign Key)

