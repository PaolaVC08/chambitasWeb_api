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


/* 
    @Override
    public void setBody(String token, String nombre) {
        try {
            String verificationLink = "https://localhost:8080/verify?token=" + token;

            String htmlContent = """
                        <html>
                            <body style='font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;'>
                                <div style='max-width: 600px; margin: auto; background-color: #ffffff; padding: 30px; border-radius: 10px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);'>
                                    <h2 style='color: #6a1b9a;'>¡Hola %s!</h2>
                                    <p style='font-size: 16px; color: #333;'>Bienvenido a <strong>ChambitasWeb</strong>. Estamos muy emocionados de que te unas a nuestra comunidad.</p>
                                    <p style='font-size: 16px; color: #333;'>Para disfrutar de todo nuestro contenido y funcionalidades, por favor confirma tu cuenta haciendo clic en el siguiente botón:</p>
                                    <div style='text-align: center; margin: 30px 0;'>
                                        <a href='%s' style='background-color: #6a1b9a; color: white; padding: 12px 24px; text-decoration: none; border-radius: 5px; font-weight: bold;'>Confirmar mi cuenta</a>
                                    </div>
                                    <p style='font-size: 14px; color: #555;'>O copia y pega este enlace en tu navegador:</p>
                                    <p style='font-size: 14px; color: #007bff;'>%s</p>
                                    <p style='font-size: 14px; color: #777;'>Si tú no solicitaste esta cuenta, puedes ignorar este mensaje.</p>
                                    <p style='font-size: 14px; color: #777;'>Gracias,<br>El equipo de ChambitasWeb</p>
                                </div>
                            </body>
                        </html>
                    """
                    .formatted(nombre, verificationLink, verificationLink);

            helper.setText(htmlContent, true);
        } catch (MessagingException e) {
            throw new EmailBuildException("Error al establecer cuerpo del correo", e);
        }
    }
*/