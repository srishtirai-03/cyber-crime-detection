package com.example.cyber_crime_detection.controller;

import com.example.cyber_crime_detection.model.Complaint;
import com.example.cyber_crime_detection.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    @Autowired
    private ComplaintService service;

    @PostMapping
    public ResponseEntity<Complaint> register(@RequestBody Complaint complaint) {
        return ResponseEntity.ok(service.registerComplaint(complaint));
    }

    @GetMapping
    public ResponseEntity<List<Complaint>> getAll() {
        return ResponseEntity.ok(service.getAllComplaints());
    }

    @GetMapping("/{generatedId}")
    public ResponseEntity<Complaint> getById(@PathVariable String generatedId) {
        return service.getComplaintByGeneratedId(generatedId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{generatedId}/status")
    public ResponseEntity<Complaint> updateStatus(@PathVariable String generatedId, @RequestBody Map<String, String> payload) {
        String status = payload.get("status");
        if (status == null) {
            return ResponseEntity.badRequest().build();
        }
        try {
            return ResponseEntity.ok(service.updateStatus(generatedId, status));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/report")
    public ResponseEntity<Map<String, Object>> getReport() {
        return ResponseEntity.ok(service.getReport());
    }
}
