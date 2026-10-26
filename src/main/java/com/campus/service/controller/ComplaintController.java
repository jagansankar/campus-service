package com.campus.service.controller;
import com.campus.service.dto.ComplaintResponse;
import com.campus.service.entity.Complaint;
import com.campus.service.service.ComplaintService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.campus.service.dto.CreateComplaintRequest;
import jakarta.validation.Valid;
import com.campus.service.dto.AssignComplaintRequest;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping
    public ComplaintResponse createComplaint(
            @Valid @RequestBody CreateComplaintRequest request,
            Authentication authentication
    ) {
        Complaint complaint = new Complaint();

        complaint.setTitle(request.getTitle());
        complaint.setDescription(request.getDescription());
        complaint.setCategory(request.getCategory());
        if (request.getPriority() != null) {
            complaint.setPriority(request.getPriority());
        }
        Complaint savedComplaint = complaintService.createComplaint(
                complaint,
                authentication.getName()
        );

        return new ComplaintResponse(
                savedComplaint.getId(),
                savedComplaint.getTitle(),
                savedComplaint.getDescription(),
                savedComplaint.getCategory(),
                savedComplaint.getPriority(),
                savedComplaint.getStatus(),
                savedComplaint.getStudent().getId(),
                savedComplaint.getStudent().getName(),
                savedComplaint.getStudent().getEmail(),
                savedComplaint.getStudent().getRole(),
                savedComplaint.getAssignedStaff() == null
                        ? null
                        : savedComplaint.getAssignedStaff().getId(),
                savedComplaint.getAssignedStaff() == null
                        ? null
                        : savedComplaint.getAssignedStaff().getName(),
                savedComplaint.getAssignedStaff() == null
                        ? null
                        : savedComplaint.getAssignedStaff().getEmail(),
                savedComplaint.getCreatedAt(),
                savedComplaint.getUpdatedAt()
        );
    }

    @GetMapping
    public List<ComplaintResponse> getAllComplaints() {
        return complaintService.getAllComplaints()
                .stream()
                .map(complaint -> new ComplaintResponse(
                        complaint.getId(),
                        complaint.getTitle(),
                        complaint.getDescription(),
                        complaint.getCategory(),
                        complaint.getPriority(),
                        complaint.getStatus(),
                        complaint.getStudent().getId(),
                        complaint.getStudent().getName(),
                        complaint.getStudent().getEmail(),
                        complaint.getStudent().getRole(),
                        complaint.getAssignedStaff() == null
                                ? null
                                : complaint.getAssignedStaff().getId(),
                        complaint.getAssignedStaff() == null
                                ? null
                                : complaint.getAssignedStaff().getName(),
                        complaint.getAssignedStaff() == null
                                ? null
                                : complaint.getAssignedStaff().getEmail(),
                        complaint.getCreatedAt(),
                        complaint.getUpdatedAt()
                ))
                .toList();
    }

    @GetMapping("/{id}")
    public ComplaintResponse getComplaintById(@PathVariable Long id) {

        Complaint complaint = complaintService.getComplaintById(id);

        return new ComplaintResponse(
                complaint.getId(),
                complaint.getTitle(),
                complaint.getDescription(),
                complaint.getCategory(),
                complaint.getPriority(),
                complaint.getStatus(),
                complaint.getStudent().getId(),
                complaint.getStudent().getName(),
                complaint.getStudent().getEmail(),
                complaint.getStudent().getRole(),
                complaint.getAssignedStaff() == null
                        ? null
                        : complaint.getAssignedStaff().getId(),
                complaint.getAssignedStaff() == null
                        ? null
                        : complaint.getAssignedStaff().getName(),
                complaint.getAssignedStaff() == null
                        ? null
                        : complaint.getAssignedStaff().getEmail(),
                complaint.getCreatedAt(),
                complaint.getUpdatedAt()
        );
    }

    @PatchMapping("/{id}/assign")
    public ComplaintResponse assignComplaint(
            @PathVariable Long id,
            @Valid @RequestBody AssignComplaintRequest request
    ) {
        Complaint complaint = complaintService.assignComplaint(
                id,
                request.getStaffId()
        );

        return new ComplaintResponse(
                complaint.getId(),
                complaint.getTitle(),
                complaint.getDescription(),
                complaint.getCategory(),
                complaint.getPriority(),
                complaint.getStatus(),
                complaint.getStudent().getId(),
                complaint.getStudent().getName(),
                complaint.getStudent().getEmail(),
                complaint.getStudent().getRole(),
                complaint.getAssignedStaff() == null
                        ? null
                        : complaint.getAssignedStaff().getId(),
                complaint.getAssignedStaff() == null
                        ? null
                        : complaint.getAssignedStaff().getName(),
                complaint.getAssignedStaff() == null
                        ? null
                        : complaint.getAssignedStaff().getEmail(),
                complaint.getCreatedAt(),
                complaint.getUpdatedAt()
        );
    }
}