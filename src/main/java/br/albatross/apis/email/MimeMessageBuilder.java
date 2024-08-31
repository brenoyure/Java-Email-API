package br.albatross.apis.email;

import static jakarta.mail.Message.RecipientType.CC;
import static jakarta.mail.Message.RecipientType.TO;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import jakarta.enterprise.context.RequestScoped;
import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

/**
 * <p>Responsável por criar um <code>Message</code> a partir de um <code>Tipo</code> e um Session fornecido.</p>
 * <p>Esta interface pode ser utilizada na camada de negócio da aplicação para criar um Message a partir de um <code>Tipo</code> fornecido</p>
 * @param <T> o tipo do e-mail
 */
@RequestScoped
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
	public Message buildMessage(Email email, Session session) throws IOException, MessagingException {
		Message mensagem = new MimeMessage(session);
		setMessageDeliveryAndRecipientData(mensagem, email);

		mensagem.setSubject(email.getAssunto());
		mensagem.setContent(new MimeMultipart(buildBodyParts(email)));

		return mensagem;

	}
	
    private void setMessageDeliveryAndRecipientData(Message message, Email email) throws AddressException, MessagingException {
        message.setFrom(new InternetAddress(email.getDadosDoEnvio().getRemetente()));
        message.setRecipients(TO, InternetAddress.parse(email.getDadosDoEnvio().getDestinatario()));

        if (email.getDadosDoEnvio().getCopiaPara() != null) {
            if (!email.getDadosDoEnvio().getCopiaPara().isBlank()) {
                message.setRecipients(CC, InternetAddress.parse(email.getDadosDoEnvio().getCopiaPara()));
            }
        }
    }

    private BodyPart[] buildBodyParts(Email email) throws IOException, MessagingException {

        Anexo[] anexos = email.getAnexos();
        int bodyPartsCount = (email.getAnexos() == null || anexos.length == 0) ? 1 : anexos.length + 1;
        boolean possuiAnexos = bodyPartsCount > 1;

        MimeBodyPart[] parts = new MimeBodyPart[bodyPartsCount];

        if (possuiAnexos) {

            for (int i = 0; i < anexos.length; i++) {
                Anexo anexo = anexos[i];
                var anexoBodypart = new MimeBodyPart();
                File anexoFile = new File(anexo.getNome());
                anexoFile.createNewFile();

                try (OutputStream os = new BufferedOutputStream(new FileOutputStream(anexoFile))) {
                    os.write(anexo.getArquivo());
                    anexoBodypart.attachFile(anexoFile);
                    parts[i] = anexoBodypart;
                }

            }

        }

        var textBodyPart = new MimeBodyPart();
        textBodyPart.setText(email.getCorpoDaMensagem());
        parts[bodyPartsCount - 1] = textBodyPart;

        return parts;

    }    

}
