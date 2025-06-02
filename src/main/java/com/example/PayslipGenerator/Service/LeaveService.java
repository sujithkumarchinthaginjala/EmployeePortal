package com.example.PayslipGenerator.Service;

import com.example.PayslipGenerator.Model.Leave;
import com.example.PayslipGenerator.Model.LeaveStatus;
import com.example.PayslipGenerator.Repository.LeaveRequestRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
//@RequiredArgsConstructor
public class LeaveService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    public Leave applyLeave(Leave leaveRequest) {
        leaveRequest.setStatus(LeaveStatus.PENDING);
        leaveRequest.setAppliedDate(LocalDate.now());
        return leaveRequestRepository.save(leaveRequest);
    }

    public List<Leave> getEmployeeLeaves(Long employeeId) {
        return leaveRequestRepository.findByEmployeeId(employeeId);
    }

    public void updateLeaveStatus(Long id, LeaveStatus status) {
        Leave leave = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Leave not found with ID: " + id));

        leave.setStatus(status);
        leaveRequestRepository.save(leave);
    }


    public List<Leave> getAllLeaves() {
        return leaveRequestRepository.findAll();
    }
}
