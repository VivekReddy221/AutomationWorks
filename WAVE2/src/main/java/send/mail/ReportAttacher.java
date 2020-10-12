package send.mail;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class ReportAttacher 
{
	
	public void sendReport() throws EmailException
	 {
		try{
			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("nanda.mjustin", "nbkndmkd"));
			email.setSSLOnConnect(true);
			email.setFrom("nanda.mjustin@gmail.com");
			email.setSubject("TestMail");
			email.setMsg("This is a test mail ... :-)");
			email.addTo("vivekanandam@wavity.com");
			email.send();
			
		}
	 catch (EmailException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	 }

}
