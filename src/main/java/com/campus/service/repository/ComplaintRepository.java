package com.campus.service.repository;

import com.campus.service.entity.User;
import com.campus.service.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    List<Complaint> findByStudent(User student);

    List<Complaint> findByAssignedStaff(User assignedStaff);
}