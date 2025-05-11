package com.ann.chambitasWeb.service;

import com.ann.chambitasWeb.config.FrontendProperties;
import com.ann.chambitasWeb.utils.email.EmailBuilder;
import com.ann.chambitasWeb.utils.email.EmailDirector;
import com.ann.chambitasWeb.utils.email.VerificationEmailBuilder;

//import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final FrontendProperties frontendProperties;

    @Autowired
    public EmailService(JavaMailSender mailSender, FrontendProperties frontendProperties) {
        this.mailSender = mailSender;
        this.frontendProperties = frontendProperties;
    }

    public void enviarCorreoVerificacion(String destinatario, String nombre, String link) {
        EmailBuilder builder = new VerificationEmailBuilder(mailSender, frontendProperties.getVerificationUrl());
        EmailDirector director = new EmailDirector(builder);
        MimeMessage mensaje = director.construir(destinatario, link, nombre);

        try {
            mailSender.send(mensaje);
        } catch (MailException e) {
            throw new RuntimeException("Error al enviar correo de verificación", e);
        }
    }
}
