# MediLab Java Backend Documentation

## Overview
A comprehensive Spring Boot REST API backend for the MediLab medical website, providing appointment booking, contact management, doctor profiles, and department information.

## 🚀 Live Deployment
- **API Base URL**: https://8080-ia6a9zl7p5d6jas4gcngi-a4ea9ac5.manusvm.computer
- **API Documentation**: https://8080-ia6a9zl7p5d6jas4gcngi-a4ea9ac5.manusvm.computer/swagger-ui.html
- **H2 Database Console**: https://8080-ia6a9zl7p5d6jas4gcngi-a4ea9ac5.manusvm.computer/h2-console

## 🏗️ Architecture

### Technology Stack
- **Framework**: Spring Boot 3.2.0
- **Java Version**: 17
- **Database**: H2 (in-memory for development)
- **Build Tool**: Maven
- **API Documentation**: SpringDoc OpenAPI (Swagger)
- **Email**: Spring Mail with JavaMail

### Project Structure
```
medilab-backend/
├── src/main/java/com/medilab/backend/
│   ├── MedilabBackendApplication.java
│   ├── config/
│   │   └── CorsConfig.java
│   ├── controller/
│   │   ├── AppointmentController.java
│   │   ├── ContactController.java
│   │   └── DoctorController.java
│   ├── dto/
│   │   ├── AppointmentRequest.java
│   │   └── ContactRequest.java
│   ├── model/
│   │   ├── Appointment.java
│   │   ├── ContactMessage.java
│   │   ├── Doctor.java
│   │   └── Department.java
│   ├── repository/
│   │   ├── AppointmentRepository.java
│   │   ├── ContactMessageRepository.java
│   │   ├── DoctorRepository.java
│   │   └── DepartmentRepository.java
│   └── service/
│       ├── AppointmentService.java
│       ├── ContactService.java
│       ├── DoctorService.java
│       └── EmailService.java
├── src/main/resources/
│   ├── application.properties
│   └── data.sql
├── pom.xml
└── start.sh
```

## 📊 Database Schema

### Appointments Table
- `id` (Long, Primary Key)
- `name` (String, required)
- `email` (String, required, validated)
- `phone` (String, required)
- `appointment_date` (Date, required)
- `department` (String, required)
- `doctor` (String, required)
- `message` (Text, optional)
- `status` (Enum: PENDING, CONFIRMED, CANCELLED)
- `created_at` (Timestamp, auto-generated)

### Contact Messages Table
- `id` (Long, Primary Key)
- `name` (String, required)
- `email` (String, required, validated)
- `subject` (String, required)
- `message` (Text, required)
- `status` (Enum: NEW, READ, REPLIED)
- `created_at` (Timestamp, auto-generated)

### Doctors Table
- `id` (Long, Primary Key)
- `name` (String, required)
- `specialization` (String, required)
- `title` (String, required)
- `bio` (Text)
- `image_url` (String)
- `email` (String, validated)
- `phone` (String)

### Departments Table
- `id` (Long, Primary Key)
- `name` (String, required, unique)
- `description` (Text)
- `head_doctor_id` (Long, Foreign Key to Doctors)

## 🔌 API Endpoints

### Appointments API (`/api/appointments`)
- `POST /` - Create new appointment
- `GET /` - Get all appointments
- `GET /{id}` - Get appointment by ID
- `PUT /{id}/status` - Update appointment status
- `DELETE /{id}` - Delete appointment
- `GET /status/{status}` - Get appointments by status
- `GET /department/{department}` - Get appointments by department
- `GET /doctor/{doctor}` - Get appointments by doctor
- `GET /date/{date}` - Get appointments by date
- `GET /date-range` - Get appointments by date range
- `GET /email/{email}` - Get appointments by email

### Contact API (`/api/contact`)
- `POST /` - Send contact message
- `GET /` - Get all contact messages
- `GET /{id}` - Get contact message by ID
- `PUT /{id}/status` - Update message status
- `DELETE /{id}` - Delete contact message
- `GET /status/{status}` - Get messages by status
- `GET /email/{email}` - Get messages by email

### Doctors API (`/api/doctors`)
- `POST /` - Create new doctor
- `GET /` - Get all doctors
- `GET /{id}` - Get doctor by ID
- `PUT /{id}` - Update doctor
- `DELETE /{id}` - Delete doctor
- `GET /specialization/{specialization}` - Get doctors by specialization
- `GET /search?name={name}` - Search doctors by name

## 🔧 Configuration

### Application Properties
```properties
# Server Configuration
server.port=8080
server.address=0.0.0.0

# Database Configuration (H2 in-memory)
spring.datasource.url=jdbc:h2:mem:medilabdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password

# JPA Configuration
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.defer-datasource-initialization=true
spring.sql.init.mode=always

# Email Configuration
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=${MAIL_USERNAME:your-email@gmail.com}
spring.mail.password=${MAIL_PASSWORD:your-app-password}
```

### CORS Configuration
- Allows all origins (`*`)
- Supports all HTTP methods (GET, POST, PUT, DELETE, OPTIONS)
- Allows all headers
- Credentials enabled

## 📧 Email Notifications

### Appointment Confirmations
- Patient receives confirmation email with appointment details
- Admin receives notification email with patient information

### Contact Message Confirmations
- Sender receives acknowledgment email
- Admin receives notification with message details

### Email Templates
- Professional HTML-formatted emails
- Includes all relevant information
- Branded with MediLab identity

## 🛡️ Security & Validation

### Input Validation
- Bean Validation (JSR-303) annotations
- Email format validation
- Required field validation
- Data type validation

### Error Handling
- Graceful error responses
- Proper HTTP status codes
- Detailed error messages for development
- Email sending failures don't break functionality

## 🚀 Deployment

### Local Development
```bash
# Clone and build
cd medilab-backend
mvn clean compile

# Run application
mvn spring-boot:run

# Or run JAR file
java -jar target/medilab-backend-1.0.0.jar
```

### Production Deployment
- Built as executable JAR file
- Includes embedded Tomcat server
- Configurable via environment variables
- Ready for containerization (Docker)

## 📝 Sample Data
The application includes sample data:
- 4 doctors with different specializations
- 4 departments (Cardiology, Neurology, Anesthesiology, Emergency Medicine)
- Sample appointments and contact messages for testing

## 🔗 Frontend Integration

### JavaScript Example
```javascript
// Book appointment
const appointmentData = {
    name: "John Doe",
    email: "john@example.com",
    phone: "+1-555-1234",
    date: "2025-08-15",
    department: "Cardiology",
    doctor: "Dr. Walter White",
    message: "Regular checkup"
};

fetch('https://8080-ia6a9zl7p5d6jas4gcngi-a4ea9ac5.manusvm.computer/api/appointments', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json',
    },
    body: JSON.stringify(appointmentData)
})
.then(response => response.json())
.then(data => console.log('Appointment created:', data));
```

### Integration with MediLab Website
- Replace PHP forms with JavaScript API calls
- Update form action URLs to point to backend endpoints
- Add error handling and success messages
- Implement real-time form validation

## 🧪 Testing

### API Testing
- Use Swagger UI for interactive testing
- All endpoints documented with examples
- Request/response schemas provided
- Try-it-out functionality available

### Database Testing
- H2 console available for direct database access
- Sample data pre-loaded for testing
- In-memory database resets on restart

## 📈 Monitoring & Logging

### Application Logs
- Spring Boot default logging
- SQL query logging enabled
- Request/response logging
- Error stack traces

### Health Monitoring
- Spring Boot Actuator endpoints available
- Application health checks
- Metrics collection ready

## 🔮 Future Enhancements

### Recommended Improvements
1. **Authentication & Authorization**
   - JWT token-based authentication
   - Role-based access control (Admin, Doctor, Patient)
   - Secure admin endpoints

2. **Database Migration**
   - PostgreSQL or MySQL for production
   - Database migration scripts
   - Connection pooling

3. **Advanced Features**
   - Appointment scheduling conflicts detection
   - Email templates with HTML formatting
   - File upload for medical documents
   - SMS notifications
   - Calendar integration

4. **Performance Optimization**
   - Redis caching
   - Database indexing
   - API rate limiting
   - Response compression

5. **Monitoring & Analytics**
   - Application performance monitoring
   - Business metrics tracking
   - Error tracking and alerting

## 📞 Support

For technical support or questions about the MediLab Java Backend:
- Review the API documentation at the Swagger UI endpoint
- Check the application logs for error details
- Verify database connectivity via H2 console
- Ensure proper CORS configuration for frontend integration

