package com.ann.chambitasWeb.utils.email;

import jakarta.mail.internet.MimeMessage;

public class EmailDirector {

    private final EmailBuilder builder;

    public EmailDirector(EmailBuilder builder) {
        this.builder = builder;
    }

    public MimeMessage construir(String destinatario, String link, String nombre) {
        builder.reset();
        builder.setRecipient(destinatario);
        builder.setSubject();
        builder.setBody(link,nombre);
        return builder.getEmail();
    }
}
