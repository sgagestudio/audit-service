package com.sgagestudio.audit_service.service;

import com.sgagestudio.audit_service.model.CreateReportRequest;

public interface AuditService {
    void createReport(CreateReportRequest request);
}
