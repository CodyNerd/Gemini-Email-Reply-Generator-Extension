package org.email.writer.services;

import io.github.cdimascio.dotenv.Dotenv;
import org.email.writer.models.EmailRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailGeneratorService {


    private final String geminiApiUrl;
    private final String geminiApiKey;

    public EmailGeneratorService() {
        Dotenv dotenv = Dotenv.load(); // Loads .env file from root
        this.geminiApiUrl = dotenv.get("GEMINI_API_URL");
        this.geminiApiKey = dotenv.get("GEMINI_API_KEY");
    }

    public String generateEmailReply(EmailRequest emailRequest){
        // Build the prompt
        String prompt = buildPrompt(emailRequest);
        // Craft a Request
        Map<String, Object> createRequest = Map.of(
                "contents" , new Object[]{
                        Map.of("parts" , new Object[]{
                                Map.of("text" , prompt)
                })
                }
        );
        // Do the request and get response
        // Return the reponse
    }

    private String buildPrompt(EmailRequest emailRequest) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Generate a professional email reply for the following email content. Please don't generate the subject line ");
        if(emailRequest.getTone() != null && !emailRequest.getTone().isEmpty()){
            prompt.append("Use a ").append(emailRequest.getTone()).append(" tone.");
        }
        prompt.append("\nOriginal email: \n").append(emailRequest.getEmailContent());
        return prompt.toString();
    }
}
