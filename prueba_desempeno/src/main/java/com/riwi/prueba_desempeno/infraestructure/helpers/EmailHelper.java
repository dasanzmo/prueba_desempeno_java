package com.riwi.prueba_desempeno.infraestructure.helpers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EmailHelper {

    @Autowired
    private final JavaMailSender mailSender;

    public void sendEmail(
        String destinity,
        String name,
        String survey
    ){
        //Creamos el mensaje de tipo HTML
        MimeMessage message = mailSender.createMimeMessage();

        //Convertir fecha a String
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //String dateAppointment = date.format(formatter);

        //Leer el html
        String htmlContent = this.readHTMLTemplate(name, survey);

        try {
            message.setFrom(new InternetAddress("danielsanchezm92@gmail.com"));
            message.setSubject("Survey added");

            message.setRecipients(MimeMessage.RecipientType.TO, destinity);
            message.setContent(htmlContent, MediaType.TEXT_HTML_VALUE);

            //eviar el email
            mailSender.send(message);

            System.out.println("Email sent");
        } catch (Exception e) {
            System.out.println("ERROR, email has not sended " + e.getMessage());
        }

    }
    
    private String readHTMLTemplate(String nameClient, String survey){

        //Indicar en donde se encuentra nuestro template
        Path path = Paths.get("src/main/resources/emails/email_template.html");

        try (var lines = Files.lines(path)){

            var html = lines.collect(Collectors.joining());
            
            return html
                    .replace("{name}", nameClient)
                    .replace("{survey}", survey);
        
        } catch (Exception e) {
            System.out.println("No se pudo leer el html");
            throw new RuntimeException();
        }
    }
}


