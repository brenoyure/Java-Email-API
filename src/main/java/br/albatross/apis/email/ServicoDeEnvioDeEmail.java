package br.albatross.apis.email;

import java.io.File;
import java.io.IOException;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;

/**
 * Responsável por enviar emails de uma dada <code>Session</code>.
 */
@Stateless
public class ServicoDeEnvioDeEmail {

	@Inject
	private MimeMessageBuilder builder;

	public void enviar(Email email, Session session) {
		try {
			Message mensagem = builder.buildMessage(email, session);
			Transport.send(mensagem);
			deleteTempFiles(email.getAnexos());
		}
           catch (IOException e)        { throw new RuntimeException(e); }
		   catch (AddressException e)   { throw new RuntimeException(e); }
           catch (MessagingException e) { throw new RuntimeException(e); }
	}

	private void deleteTempFiles(File[] filesToDelete) {
		for (File uploadedFile : filesToDelete) {
			if (uploadedFile.exists()) {
				if (uploadedFile.canWrite()) {
					uploadedFile.delete();
				}
			}
		}
	}

}
