package com.campus.service.service;

import com.campus.service.entity.Complaint;
import com.campus.service.entity.User;
import com.campus.service.repository.ComplaintRepository;
import com.campus.service.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.campus.service.enums.ComplaintStatus;
import com.campus.service.exception.InvalidStaffException;

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

    public Complaint assignComplaint(Long complaintId, Long staffId) {

        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        User staff = userRepository.findById(staffId)
                .orElseThrow(() -> new InvalidStaffException("Staff user not found"));

        if (!"STAFF".equals(staff.getRole())) {
            throw new InvalidStaffException("User is not a staff member");
        }

        complaint.setAssignedStaff(staff);
        complaint.setStatus(ComplaintStatus.ASSIGNED);

        return complaintRepository.save(complaint);
    }


    public Complaint updateComplaintStatus(
            Long complaintId,
            ComplaintStatus newStatus
    ) {

        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        ComplaintStatus currentStatus = complaint.getStatus();

        if (currentStatus == ComplaintStatus.OPEN
                && newStatus != ComplaintStatus.ASSIGNED
                && newStatus != ComplaintStatus.REJECTED) {

            throw new IllegalStateException(
                    "OPEN complaint can only move to ASSIGNED or REJECTED"
            );
        }

        if (currentStatus == ComplaintStatus.ASSIGNED
                && newStatus != ComplaintStatus.IN_PROGRESS
                && newStatus != ComplaintStatus.REJECTED) {

            throw new IllegalStateException(
                    "ASSIGNED complaint can only move to IN_PROGRESS or REJECTED"
            );
        }

        if (currentStatus == ComplaintStatus.IN_PROGRESS
                && newStatus != ComplaintStatus.RESOLVED) {

            throw new IllegalStateException(
                    "IN_PROGRESS complaint can only move to RESOLVED"
            );
        }

        if (currentStatus == ComplaintStatus.RESOLVED
                && newStatus != ComplaintStatus.CLOSED) {

            throw new IllegalStateException(
                    "RESOLVED complaint can only move to CLOSED"
            );
        }

        if (currentStatus == ComplaintStatus.CLOSED
                || currentStatus == ComplaintStatus.REJECTED) {

            throw new IllegalStateException(
                    "Closed or rejected complaints cannot be updated"
            );
        }

        complaint.setStatus(newStatus);

        return complaintRepository.save(complaint);
    }

}