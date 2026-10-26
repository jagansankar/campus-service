package com.campus.service.dto;
import com.campus.service.enums.Priority;
import java.time.LocalDateTime;
import com.campus.service.enums.ComplaintStatus;
import com.campus.service.enums.ComplaintCategory;
public class ComplaintResponse {

    private Long id;
    private String title;
    private String description;
    private ComplaintCategory category;
    private Priority priority;
    private ComplaintStatus status;

    private Long studentId;
    private String studentName;
    private String studentEmail;
    private String studentRole;

    private Long assignedStaffId;
    private String assignedStaffName;
    private String assignedStaffEmail;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ComplaintResponse(
            Long id,
            String title,
            String description,
            ComplaintCategory category,
            Priority priority,
            ComplaintStatus status,
            Long studentId,
            String studentName,
            String studentEmail,
            String studentRole,
            Long assignedStaffId,
            String assignedStaffName,
            String assignedStaffEmail,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.priority = priority;
        this.status = status;
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentRole = studentRole;
        this.assignedStaffId = assignedStaffId;
        this.assignedStaffName = assignedStaffName;
        this.assignedStaffEmail = assignedStaffEmail;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ComplaintCategory getCategory() {
        return category;
    }

    public Priority getPriority() {
        return priority;
    }

    public ComplaintStatus getStatus() {
        return status;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getStudentRole() {
        return studentRole;
    }

    public Long getAssignedStaffId() {
        return assignedStaffId;
    }

    public String getAssignedStaffName() {
        return assignedStaffName;
    }

    public String getAssignedStaffEmail() {
        return assignedStaffEmail;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}