package com.Inportia;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailLogic {
	
	static Properties mail_properties;
    static Session getMailSession;
	 static MimeMessage generateMailMessage;
	   
	public static void sendMail(String reciepient, String content) throws MessagingException{
		EmailLogic.email_sending_logic(reciepient, content);
	}
	
	 private static void email_sending_logic(String reciepient, String content) throws MessagingException
	   {
		   System.out.println("setting up mail properties....");
			 mail_properties = System.getProperties();
			 mail_properties.put("mail.smtp.port", "587");
			 mail_properties.put("mail.smtp.auth", "true");
			 mail_properties.put("mail.smtp.starttls.enable", "true");
			 mail_properties.put("mail.debug","true");
			 System.out.println("Mail Server Properties have been setup successfully..");
			 
			 System.out.println("setting up mail session....");
			 getMailSession = Session.getDefaultInstance(mail_properties, null);
			 generateMailMessage = new MimeMessage(getMailSession);
			 generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(reciepient));
//			 generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("test2@crunchify.com"));
			 generateMailMessage.setSubject("Inportia reports");
			 String emailBody = content;
			 generateMailMessage.setContent(emailBody, "text/html");
			 System.out.println("Mail Session has been created successfully..");
			 
			 Transport transport = getMailSession.getTransport("smtp");
			 
				// Enter your correct gmail UserID and Password
				// if you have 2FA enabled then provide App Specific Password
				transport.connect("smtp.gmail.com", "sohail.chd14@gmail.com", "Bsdlinux2123");
				transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
				transport.close();
	   }

}
