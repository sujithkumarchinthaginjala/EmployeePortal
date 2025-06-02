package com.example.PayslipGenerator.Controller;

import com.example.PayslipGenerator.Model.Leave;
import com.example.PayslipGenerator.Model.LeaveStatus;
import com.example.PayslipGenerator.Service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
//@RequiredArgsConstructor
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @PostMapping("/apply")
    public ResponseEntity<Leave> applyLeave(@RequestBody Leave request) {
        return ResponseEntity.ok(leaveService.applyLeave(request));
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<List<Leave>> getEmployeeLeaves(@PathVariable Long id) {
        return ResponseEntity.ok(leaveService.getEmployeeLeaves(id));
    }

    @PutMapping("/admin/leave/{id}/status")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> updateLeaveStatus(
            @PathVariable Long id,
            @RequestParam LeaveStatus status) {
        leaveService.updateLeaveStatus(id, status);
        return ResponseEntity.ok("Leave status updated to " + status);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Leave>> getAll() {
        return ResponseEntity.ok(leaveService.getAllLeaves());
    }
}
