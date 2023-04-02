package mediatheque;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
	private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	private static Properties props;
	Session session;
	private static final String username = "ouistitive@gmail.com";
    private static final String password = "mbbytpuflamarcwq";
    private String mailReceveur;
    private String titre;
    private String message;
    
    static {
    	props = System.getProperties();
	    props.setProperty("mail.smtp.host", "smtp.gmail.com");
	    props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
	    props.setProperty("mail.smtp.socketFactory.fallback", "false");
	    props.setProperty("mail.smtp.port", "465");
	    props.setProperty("mail.smtp.socketFactory.port", "465");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.debug", "true");
	    props.put("mail.store.protocol", "pop3");
	    props.put("mail.transport.protocol", "smtp");
    }
	
	public Mail(String adresseMail, String titre, String message) {
		this.mailReceveur = adresseMail;
		this.titre = titre;
		this.message = message;
	}
	
	public void envoyer() {
		try {
			session = Session.getDefaultInstance(props, 
			new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}});
			
			Message msg = new MimeMessage(session);
			
			msg.setFrom(new InternetAddress("username"));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailReceveur, false));
			msg.setSubject(titre);
			msg.setText(message);
			msg.setSentDate(new Date());
			Transport.send(msg);
			//System.out.println("Message sent.");
	    }
	    catch (MessagingException e) { 
	      System.out.println("Erreur d'envoi, cause: " + e);
	    }
	}
}
