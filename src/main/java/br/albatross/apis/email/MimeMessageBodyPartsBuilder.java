package br.albatross.apis.email;

import java.io.File;
import java.io.IOException;

import jakarta.enterprise.context.RequestScoped;
import jakarta.mail.BodyPart;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeBodyPart;

/**
 * 
 * Retorna um array de BodyParts do JavaMail (MimeMessage)
 * 
 * @author breno.brito
 */
@RequestScoped
public class MimeMessageBodyPartsBuilder {

	public BodyPart[] buildBodyParts(String emailTextBody, File[] anexos) throws IOException, MessagingException {

		MimeBodyPart[] parts = new MimeBodyPart[(1 + anexos.length)];

		for (int i = 0; i < anexos.length; i++) {
			var anexoBodypart = new MimeBodyPart();
			anexoBodypart.attachFile(anexos[i]);
			parts[i] = anexoBodypart;
		}

		var textBodyPart = new MimeBodyPart();
		textBodyPart.setText(emailTextBody);
		parts[parts.length - 1] = textBodyPart;

		return parts;

	}

}
