package com.sgagestudio.audit_service.controller;

import com.sgagestudio.audit_service.model.CreateReportRequest;
import com.sgagestudio.audit_service.service.AuditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/audit")
@CrossOrigin(origins = "*")
public class AuditController {

    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @PostMapping("/createReport")
    public ResponseEntity<Void> createReposRequest(@RequestBody CreateReportRequest request) {
        auditService.createReport(request);

        return ResponseEntity.accepted().build();
    }

}
