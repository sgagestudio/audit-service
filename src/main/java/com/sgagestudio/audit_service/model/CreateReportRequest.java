package com.sgagestudio.audit_service.model;

import java.util.List;

public record CreateReportRequest(
        String fullName,
        String email,
        String companyName,
        String companySize,       // "Micro", "SME", "Large"
        String industry,
        List<String> activeDepartments,  // ["Sales", "HR", "Support"]
        List<String> toolsInUse,         // ["Excel", "Email", "ERP"]
        String digitalMaturity,          // "Low", "Medium", "High"
        String repetitiveTasks,
        String currentProblems,
        List<String> interestInAI,       // ["I want to implement AI", "I'm curious"]
        String additionalComments,
        String language
) {}
