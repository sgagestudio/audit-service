package com.sgagestudio.audit_service.service;

import com.sgagestudio.audit_service.model.CreateReportRequest;
import org.springframework.stereotype.Service;

@Service
public class AuditServiceImpl implements AuditService {

    private final OpenAiService openAiService;
    private final GmailSenderService gmailSenderService;

    public AuditServiceImpl(OpenAiService openAiService, GmailSenderService gmailSenderService) {
        this.openAiService = openAiService;
        this.gmailSenderService = gmailSenderService;
    }

    @Override
    public void createReport(CreateReportRequest request) {
        String chatResponse = openAiService.processPrompt(request);
        gmailSenderService.sendMail(request.email(), chatResponse, request.language());
    }


}
