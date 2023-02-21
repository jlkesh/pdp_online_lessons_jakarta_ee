package uz.pdp.jakarta_ee.jakarta_ee.services;

import lombok.NonNull;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.awt.event.WindowFocusListener;
import java.util.Properties;

public class MailtrapService {

    private static MailtrapService mailtrapService;

    public static MailtrapService getMailtrapService() {
        if (mailtrapService == null) {
            mailtrapService = new MailtrapService();
        }
        return mailtrapService;
    }

    private static final String username = "bb393a74503ab1";
    private static final String password = "ef91a0b57ee41a";

    public static void sendActivationEmail(@NonNull String userID) {
        try {
            var properties = getProperties();
            var session = getSession(properties, username, password);
            var message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress("recipient@gmail.com"));
            message.setSubject("This is Subject For Test Message");
            var multipart = new MimeMultipart();
            ;
            var contentMessage = new MimeBodyPart();
            String body = """
                    <!DOCTYPE html>
                    <html lang="en">
                    <head>
                        <meta charset="UTF-8">
                        <title>Activation Page</title>
                        <style>
                            body {
                                background-color: darkorange;
                            }
                        </style>
                    </head>
                    <body>
                    <h1>Welcome To Bla Bla</h1>
                    <h2>In order to activate your account click here 👇</h2>
                    <div>
                        <a href="http://localhost:8080/activation?token=%s" target="_blank">activate</a>
                    </div>
                    </body>
                    </html>
                    """.formatted(userID);
            contentMessage.setContent(body, "text/html");
            multipart.addBodyPart(contentMessage);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("Message Sent Successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.mailtrap.io");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        return properties;
    }


    private static Session getSession(Properties properties, String username, String password) {
        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }
}