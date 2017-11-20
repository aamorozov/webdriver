package utils;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class SendEmail {
  public static void sendEmail(String from, String pass, String to, String subject, String body) {
    Properties props = System.getProperties();
    String host = "smtp.gmail.com";
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.user", from);
    props.put("mail.smtp.password", pass);
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");

    Session session = Session.getDefaultInstance(props);
    MimeMessage message = new MimeMessage(session);

    try {
      // Set from address
      message.setFrom(new InternetAddress(from));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
      // Set subject
      message.setSubject(subject);
      message.setText(body);

      BodyPart objMessageBodyPart = new MimeBodyPart();

      objMessageBodyPart.setText("Please Find The Attached Report File!");

      Multipart multipart = new MimeMultipart();

      multipart.addBodyPart(objMessageBodyPart);

      objMessageBodyPart = new MimeBodyPart();
      String filename = System.getProperty("user.dir") + "/webdriver.pdf";
      DataSource source = new FileDataSource(filename);

      objMessageBodyPart.setDataHandler(new DataHandler(source));

      objMessageBodyPart.setFileName(filename);

      multipart.addBodyPart(objMessageBodyPart);

      message.setContent(multipart);
      Transport transport = session.getTransport("smtp");
      transport.connect(host, from, pass);
      transport.sendMessage(message, message.getAllRecipients());
      transport.close();
    } catch (MessagingException me) {
      me.printStackTrace();
    }
  }
}
