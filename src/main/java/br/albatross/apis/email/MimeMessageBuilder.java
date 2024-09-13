package br.albatross.apis.email;

import static jakarta.mail.Message.RecipientType.CC;
import static jakarta.mail.Message.RecipientType.TO;

import java.io.IOException;

import jakarta.ejb.Stateless;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

/**
 * <p>Responsável por criar um <code>MimeMessage</code> do Jakarta (Java) Mail a partir de um Session fornecido.</p>
 */
@Stateless
public class MimeMessageBuilder {

    /**
     * Cria um <code>MimeMessage</code> do JavaMail a partir do <code><strong>Email</strong></code> e <code><strong>Session</strong></code> fornecidos.
     * 
     * @param tipo que será utilizado para criação do <code>Message</code>.
     * @param session que será utilizado na criação do Message.
     * @return Message
     * @throws IOException
     * @throws AddressException
     * @throws MessagingException
     */
	public Message createMessage(Email email, Session session) throws IOException, MessagingException {

		Message message = new MimeMessage(session);
		setMessageDeliveryAndRecipientData(message, email);
		message.setSubject(email.getAssunto());
        message.setContent(buildMultiPart(email));

		return message;

	}

    private void setMessageDeliveryAndRecipientData(Message message, Email email) throws AddressException, MessagingException {
        message.setFrom(new InternetAddress(email.getRemetente()));
        message.setRecipients(TO, InternetAddress.parse(email.getDestinatario()));

        if (email.getCopiaPara() != null) {
            if (!email.getCopiaPara().isBlank()) {
                message.setRecipients(CC, InternetAddress.parse(email.getCopiaPara()));
            }
        }
    }

    private MimeMultipart buildMultiPart(Email email) throws IOException, MessagingException {

        MimeMultipart multiPart = new MimeMultipart();

        for (Anexo anexo : email.getAnexos()) {
            MimeBodyPart bodyPart = new MimeBodyPart();
            bodyPart.attachFile(anexo.toFile());
            multiPart.addBodyPart(bodyPart);
        }

        MimeBodyPart textBodyPart = new MimeBodyPart();
        textBodyPart.setText(email.getCorpoDaMensagem());
        multiPart.addBodyPart(textBodyPart);

        return multiPart;

    }

}
