package com.example.cyber_crime_detection.service;

import com.example.cyber_crime_detection.model.Complaint;
import com.example.cyber_crime_detection.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

@Service
public class ComplaintService {
    @Autowired
    private ComplaintRepository repository;

    public Complaint registerComplaint(Complaint complaint) {
        Complaint saved = repository.save(complaint);
        // Generate CYB ID
        saved.setGeneratedId("CYB" + (1000 + saved.getId()));
        return repository.save(saved);
    }

    public List<Complaint> getAllComplaints() {
        return repository.findAll();
    }

    public Optional<Complaint> getComplaintByGeneratedId(String generatedId) {
        return repository.findByGeneratedId(generatedId);
    }

    public Complaint updateStatus(String generatedId, String status) {
        Optional<Complaint> opt = repository.findByGeneratedId(generatedId);
        if (opt.isPresent()) {
            Complaint complaint = opt.get();
            complaint.setStatus(status);
            return repository.save(complaint);
        }
        throw new RuntimeException("Complaint not found with ID: " + generatedId);
    }

    public Map<String, Object> getReport() {
        List<Complaint> all = repository.findAll();
        long open = all.stream().filter(c -> !c.getStatus().equals("RESOLVED") && !c.getStatus().equals("CLOSED")).count();
        long resolved = all.stream().filter(c -> c.getStatus().equals("RESOLVED")).count();
        long highPriority = all.stream().filter(c -> "HIGH".equals(c.getPriority())).count();
        
        Map<String, Long> categoryCounts = new HashMap<>();
        String mostReported = "None";
        long maxCount = 0;

        for (Complaint c : all) {
            long count = categoryCounts.getOrDefault(c.getCrimeType(), 0L) + 1;
            categoryCounts.put(c.getCrimeType(), count);
            if (count > maxCount) {
                maxCount = count;
                mostReported = c.getCrimeType();
            }
        }

        Map<String, Object> report = new HashMap<>();
        report.put("totalComplaints", all.size());
        report.put("openComplaints", open);
        report.put("resolvedComplaints", resolved);
        report.put("highPriorityCases", highPriority);
        report.put("mostReportedCategory", mostReported);

        return report;
    }
}
