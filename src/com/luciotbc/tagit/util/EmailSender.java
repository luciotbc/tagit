package com.luciotbc.tagit.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

	public static boolean sendRegistrationEmail(String email, String name,
			String pass) throws UnsupportedEncodingException {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		String msgBody = "Olá, "
				+ name
				+ " /n /n Seja bem vindo a Tag-It! /n para fazer login use seu e-mail e a senha "
				+ pass
				+ " /n/n Qualquer dúvida basta entrar em contato respondendo este E-mail. /n Esperamos que tenha uma boa experiência com a Tag-It.";

		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("luciotbc@gmail.com"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					email, name));
			msg.setSubject("[Tag-It] Seu cadastro efetuado com sucesso!");
			msg.setText(msgBody);
			Transport.send(msg);

		} catch (AddressException e) {
			return false;
		} catch (MessagingException e) {
			return false;
		}
		return true;
	}
}
