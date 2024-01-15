package Utilities;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {

    private static final String SENDER_EMAIL = "carcaremailer@gmail.com";
    private static final String PASSWORD = "cwjb sxdk roqe qqzg";

    public static void sendMailCustomer(String recipient, String name) {
        Properties properties = createEmailProperties();
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, PASSWORD);
            }
        });

        Message message = prepareCustomerMessage(session, recipient, name);

        try {
            Transport.send(message);
            System.out.println("Email sent successfully to: " + recipient);
        } catch (MessagingException ex) {
            Logger.getLogger(MailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void sendMailEmployee(String recipient, String name, String role) {
        Properties properties = createEmailProperties();
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, PASSWORD);
            }
        });

        Message message = prepareEmployeeMessage(session, recipient, name, role);

        try {
            Transport.send(message);
            System.out.println("Email sent successfully to: " + recipient);
        } catch (MessagingException ex) {
            Logger.getLogger(MailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Properties createEmailProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        return properties;
    }

    private static Message prepareCustomerMessage(Session session, String recipient, String name) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(SENDER_EMAIL));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Hello " + name + " Your Repair/Restorations is completed");
            message.setText("Notice regarding completion of repair/restoration\n\nWe have completed your Repair/Restorations Please visit us to get your vehicle...\n\nThank you for choosing CarCare,\nCar Care Group");
            return message;
        } catch (AddressException ex) {
            Logger.getLogger(MailUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(MailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static Message prepareEmployeeMessage(Session session, String recipient, String name, String role) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(SENDER_EMAIL));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Hello " + name + " You are assigned for work ! ");
            message.setText("Notice regarding allocation to "+role+" Work \nPlease visit the Service Center as soon as possible...\n\nCarCare Center");
            return message;
        } catch (AddressException ex) {
            Logger.getLogger(MailUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(MailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
