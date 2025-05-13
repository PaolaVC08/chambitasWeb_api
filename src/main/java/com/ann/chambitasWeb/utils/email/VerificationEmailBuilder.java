package com.ann.chambitasWeb.utils.email;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.ann.chambitasWeb.exceptions.EmailBuildException;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

public class VerificationEmailBuilder implements EmailBuilder {

    private final JavaMailSender mailSender;
    private final String verificationUrlBase;

    private MimeMessage message;
    private MimeMessageHelper helper;
    

    public VerificationEmailBuilder(JavaMailSender mailSender, String verificationUrlBase) {
        this.mailSender = mailSender;
        this.verificationUrlBase = verificationUrlBase;
        reset();
    }

    @Override
    public void reset() {
        message = mailSender.createMimeMessage();
        try {
            helper = new MimeMessageHelper(message, true, "UTF-8");
        } catch (MessagingException e) {
            throw new EmailBuildException("Error al inicializar el mensaje", e);
        }
    }

    @Override
    public void setRecipient(String recipient) {
        try {
            helper.setTo(recipient);
        } catch (MessagingException e) {
            throw new EmailBuildException("Error al establecer destinatario", e);
        }
    }

    @Override
    public void setSubject() {
        try {
            helper.setSubject("Bienvenido a ChambitasWeb - Verifica tu cuenta");
        } catch (MessagingException e) {
            throw new EmailBuildException("Error al establecer asunto", e);
        }
    }

    @Override
    public void setBody(String token, String nombre) {
        try {
            String verificationLink = verificationUrlBase + token;
            String htmlContent = VerificationEmailTemplate.build(nombre, verificationLink);
            helper.setText(htmlContent, true);
        } catch (MessagingException e) {
            throw new EmailBuildException("Error al establecer cuerpo del correo", e);
        }
    }

    @Override
    public MimeMessage getEmail() {
        return message;
    }
}