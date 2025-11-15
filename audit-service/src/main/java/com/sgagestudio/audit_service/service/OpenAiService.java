package com.sgagestudio.audit_service.service;

import com.sgagestudio.audit_service.model.CreateReportRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component
public class OpenAiService {

    private final ChatClient chatClient;

    public OpenAiService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String processPrompt(CreateReportRequest request) {
        return chatClient.prompt()
                .user(configPrompt(request))
                .call()
                .content();
    }

    private String configPrompt(CreateReportRequest req) {
        return String.format("""
        You are a senior digital transformation consultant and AI strategist. 
        Generate a professional, high-level business audit report based on the following company profile:

        - Company Name: %s
        - Size: %s
        - Industry: %s
        - Active Departments: %s
        - Tools Currently in Use: %s
        - Stated Digital Maturity Level: %s
        - Noted Repetitive Tasks: %s
        - Identified Problems or Inefficiencies: %s
        - Interest or Openness to AI Integration: %s
        - Additional Notes from Company: %s

        🔍 Goals of the report:
        - Evaluate the digital maturity of the business in context.
        - Identify gaps and opportunities for automation or optimization.
        - Prioritize actions that deliver high-impact improvements quickly.
        - Highlight the potential return on investment (ROI) of AI adoption.
        - Provide tailored and actionable recommendations by department.
        - Maintain a professional, concise and executive-oriented tone.

        🛠️ Report requirements:
        - The full result must be a single HTML document with inline styling.
        - Do NOT include markdown or code blocks (no triple backticks).
        - Use visually structured formatting with section headers, bullet points, and summary blocks.
        - Style using ONLY inline CSS (already provided).
        - Use dark gray or black text, no blue/purple or bright colors.
        - Key data labels should be in bold.
        - The final section must include a clear Call-To-Action encouraging the client to contact for implementation help. The CTA must be part of the HTML body.
        - Rank problems and opportunities by impact and urgency.
        - Include a 3-phase action plan (short, medium, long-term).
        - Suggest 2–3 KPIs the company should monitor post-implementation.
        - Compare the company’s digital maturity to industry averages where applicable.
        - Evaluate if existing tools can be enhanced, not just replaced.
        - Use plain, non-technical language suited for non-expert business owners.

        🌐 Language: The full HTML report must be written in: **%s**

        📢 Example CTA text in %s:
        - Spanish: ¿Querés un análisis más profundo o que te ayudemos a implementar alguna de las soluciones sugeridas? 
          Contactanos y te guiamos paso a paso.

          Podés escribirnos a <strong>sgagestudio.info@gmail.com</strong>, llamarnos al <strong>722853202</strong> o visitar <a href="https://sgagestudio.com">https://sgagestudio.com</a> para más información.

        - English: Would you like a deeper analysis or help implementing any of the suggested solutions? 
          Contact us and we’ll guide you step by step.

          You can reach us at <strong>sgagestudio.info@gmail.com</strong>, call us at <strong>722853202</strong>, or visit <a href="https://sgagestudio.com">https://sgagestudio.com</a> for more information.

        💡 Be assertive and helpful, but concise. Avoid vague wording.

        Include the following CSS <style> tag exactly as-is inside the HTML <head>:
        
        <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f6f8;
            color: #333;
            margin: 0;
            padding: 40px;
        }

        .report-container {
            background: #ffffff;
            padding: 30px 40px;
            border-radius: 8px;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
        }

        h1, h2, h3 {
            color: #2c3e50;
            border-bottom: 1px solid #e0e0e0;
            padding-bottom: 4px;
        }

        h1 {
            font-size: 26px;
            margin-bottom: 20px;
        }

        h2 {
            font-size: 20px;
            margin-top: 30px;
        }

        h3 {
            font-size: 18px;
            margin-top: 25px;
        }

        p {
            font-size: 15px;
            line-height: 1.6;
        }

        ul {
            margin: 12px 0;
            padding-left: 20px;
        }

        li {
            margin-bottom: 8px;
        }

        .cta {
            background-color: #2c3e50;
            color: #ffffff;
            padding: 15px;
            border-radius: 6px;
            margin-top: 30px;
            text-align: center;
        }

        .cta a {
            color: #ffffff;
            text-decoration: underline;
        }
        </style>
        """,
                req.companyName(),
                req.companySize(),
                req.industry(),
                String.join(", ", req.activeDepartments()),
                String.join(", ", req.toolsInUse()),
                req.digitalMaturity(),
                req.repetitiveTasks(),
                req.currentProblems(),
                String.join(", ", req.interestInAI()),
                req.additionalComments(),
                req.language(),
                req.language()
        );
    }


}
