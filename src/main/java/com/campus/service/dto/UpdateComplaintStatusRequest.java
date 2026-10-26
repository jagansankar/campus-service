package com.campus.service.dto;

import com.campus.service.enums.ComplaintStatus;
import jakarta.validation.constraints.NotNull;

public class UpdateComplaintStatusRequest {

    @NotNull(message = "Status is required")
    private ComplaintStatus status;

    public ComplaintStatus getStatus() {
        return status;
    }

    public void setStatus(ComplaintStatus status) {
        this.status = status;
    }
}