package org.email.writer.models;

import lombok.Data;

@Data
public class EmailRequest {
    private String emailContent;
    private String tone;
}
