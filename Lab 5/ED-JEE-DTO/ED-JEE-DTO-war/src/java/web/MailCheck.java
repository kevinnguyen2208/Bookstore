
package web;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailCheck {

    public static void sendMail(String recipient) {

        String smtpServer = "smtp.gmail.com";
        String from = "labtaskswinburne@gmail.com";
        String to = recipient;
        String subject = "Details Updated";
        String body = "Hi, your details for this test website have been updated.\n"
                + "If this was not you, please contact us immediately at labtaskswinburne@gmail.com ...";
        String emailUser = from;
        String password = "labtask123";
        
        try {
            Properties props = System.getProperties();

            // -- Attaching to default Session, or we could start a new one --
            props.put("mail.smtp.host", smtpServer);
            props.put("mail.smtp.port", 587);
            props.put("mail.smtp.auth", true);
            props.put("mail.smtp.starttls.enable", true);

            // -- prepare a password authenticator
            MyAuthenticator myPA = new MyAuthenticator(emailUser, password); // see MyAuthenticator class

            // get a session
            Session session = Session.getInstance(props, myPA);

            // -- Create a new message --
            Message msg = new MimeMessage(session);

            // -- Set the FROM and TO fields --
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));

            // -- Set the subject and body text --
            msg.setSubject(subject);
            msg.setText(body);

            // -- Set some other header information --
            msg.setHeader("X-Mailer", "Gmail");
            msg.setSentDate(new Date());

            // -- Send the message --
            Transport.send(msg, emailUser, password);

            System.out.println("Message sent OK.");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}