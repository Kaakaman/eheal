-- Insert sample doctors
INSERT INTO doctors (name, specialization, title, bio, image_url, email, phone) VALUES
('Dr. Walter White', 'Cardiology', 'Chief Medical Officer', 'Experienced cardiologist with over 20 years of practice. Specializes in heart surgery and cardiovascular diseases.', '/assets/img/doctors/doctors-1.jpg', 'walter.white@medilab.com', '+1-555-0101'),
('Dr. Sarah Johnson', 'Anesthesiology', 'Senior Anesthesiologist', 'Expert in pain management and anesthesia with extensive experience in complex surgical procedures.', '/assets/img/doctors/doctors-2.jpg', 'sarah.johnson@medilab.com', '+1-555-0102'),
('Dr. William Anderson', 'Cardiology', 'Cardiologist', 'Specialized in interventional cardiology and cardiac catheterization procedures.', '/assets/img/doctors/doctors-3.jpg', 'william.anderson@medilab.com', '+1-555-0103'),
('Dr. Amanda Jepson', 'Neurosurgery', 'Chief Neurosurgeon', 'Leading neurosurgeon with expertise in brain and spinal cord surgeries.', '/assets/img/doctors/doctors-4.jpg', 'amanda.jepson@medilab.com', '+1-555-0104');

-- Insert sample departments
INSERT INTO departments (name, description, head_doctor_id) VALUES
('Cardiology', 'Comprehensive heart and cardiovascular care with state-of-the-art facilities and experienced specialists.', 1),
('Neurology', 'Advanced neurological care including brain and nervous system disorders treatment.', 4),
('Anesthesiology', 'Professional anesthesia services for all surgical procedures with patient safety as top priority.', 2),
('Emergency Medicine', 'Round-the-clock emergency medical services with rapid response capabilities.', NULL);

-- Insert sample appointments (for demonstration)
INSERT INTO appointments (name, email, phone, appointment_date, department, doctor, message, status, created_at) VALUES
('John Doe', 'john.doe@email.com', '+1-555-1001', '2025-08-10', 'Cardiology', 'Dr. Walter White', 'Regular checkup for heart condition', 'PENDING', CURRENT_TIMESTAMP),
('Jane Smith', 'jane.smith@email.com', '+1-555-1002', '2025-08-12', 'Neurology', 'Dr. Amanda Jepson', 'Follow-up consultation', 'CONFIRMED', CURRENT_TIMESTAMP);

-- Insert sample contact messages (for demonstration)
INSERT INTO contact_messages (name, email, subject, message, status, created_at) VALUES
('Michael Brown', 'michael.brown@email.com', 'Insurance Coverage Question', 'I would like to know if my insurance covers the cardiac procedures at your facility.', 'NEW', CURRENT_TIMESTAMP),
('Lisa Wilson', 'lisa.wilson@email.com', 'Appointment Scheduling', 'I need to schedule an urgent consultation with a neurologist.', 'READ', CURRENT_TIMESTAMP);

