package com.ann.chambitasWeb.utils.email;

import jakarta.mail.internet.MimeMessage;

public interface EmailBuilder {
    void reset();
    void setRecipient(String recipient);
    void setSubject();
    void setBody(String link,String nombre); 
    MimeMessage getEmail(); 
}
