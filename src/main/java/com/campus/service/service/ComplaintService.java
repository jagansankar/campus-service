package com.campus.service.service;

import com.campus.service.entity.Complaint;
import com.campus.service.entity.User;
import com.campus.service.repository.ComplaintRepository;
import com.campus.service.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.campus.service.enums.ComplaintStatus;
import java.util.List;
import com.campus.service.enums.Priority;
@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final UserRepository userRepository;

    public ComplaintService(
            ComplaintRepository complaintRepository,
            UserRepository userRepository
    ) {
        this.complaintRepository = complaintRepository;
        this.userRepository = userRepository;
    }

    public Complaint createComplaint(Complaint complaint, String email) {

        User student = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        complaint.setStudent(student);

        if (complaint.getStatus() == null) {
            complaint.setStatus(ComplaintStatus.OPEN);
        }

        if (complaint.getPriority() == null) {
            complaint.setPriority(Priority.MEDIUM);
        }

        return complaintRepository.save(complaint);
    }

    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    public Complaint getComplaintById(Long id) {
        return complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
    }
}