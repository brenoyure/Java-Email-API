package br.albatross.apis.email;

import java.io.Serializable;

/**
 * Representa os dados do envio (como remetente, destinatário...) do e-mail (<strong>Não</strong> pode ficar nulo).
 */
public class DadosDoEnvio implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
	 * 
	 * O Remetente <strong>(FROM)</strong> do e-mail (<strong>Não</strong> podendo ficar em branco).
	 */
    private String remetente;

	/**
	 * 
	 * O Destinatário <strong>(TO)</strong> do e-mail, podendo ser um ou vários. 
	 * 
	 * (<strong>Não</strong> podendo ficar em branco).
	 */
    private String destinatario;

	/**
	 * 
	 * (Opcional) Os contatos (e-mails) que receberão cópia <strong>(CC)</strong> deste e-mail.
	 */
    private String copiaPara;

    public DadosDoEnvio() {

    }

    public DadosDoEnvio(String remetente, String destinatario, String copiaPara) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.copiaPara = copiaPara;
    }

	/**
	 * 
	 * Define o Remetente <strong>(FROM)</strong> do e-mail (<strong>Não</strong> podendo ficar em branco).
	 */
    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

	/**
	 * <p>Define o Destinatário <strong>(TO)</strong> do e-mail, podendo ser um ou vários.</p>
	 * exemplo: destinatario1@email.br ou destinatario1@email.br, destinatario2@email.br, destinatario3@email.br... 
	 */
    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

	/**
	 * (Opcional) Define os contatos (e-mails) que receberão cópia <strong>(CC)</strong> deste e-mail.
	 * @param copiaPara
	 */
    public void setCopiaPara(String copiaPara) {
        this.copiaPara = copiaPara;
    }

    public String getRemetente() {
        return remetente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getCopiaPara() {
        return copiaPara;
    }

}
