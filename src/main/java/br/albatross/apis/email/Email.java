package br.albatross.apis.email;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface Email extends Serializable {

	/**
	 * 
	 * @return O assunto do e-mail (<strong>Não</strong> podendo ficar em branco).
	 */
    @NotBlank
	String getAssunto();

	/**
	 * 
	 * @return O(s) anexo(s) do e-mail.
	 */
	Anexo[] getAnexos();

	/**
	 * 
	 * @return O corpo da mensagem do e-mail (<strong>Não</strong> podendo ficar em branco).
	 */
	@NotBlank
	String getCorpoDaMensagem();

	/**
	 * 
	 * @return Os Dados do Envio (como remetente, destinatário...) do e-mail (<strong>Não</strong> pode ficar nulo).
	 */
	@NotNull
	DadosDoEnvio getDadosDoEnvio();
	
	/**
	 * 
	 * Define assunto do e-mail (<strong>Não</strong> podendo ficar em branco).
	 */
	void setAssunto(@NotBlank String assunto);

	/**
	 * 
	 * Define o(s) anexo(s) do e-mail (<strong>Não</strong> podendo ficar nulo ou vazio).
	 */
	void setAnexos(Anexo[] anexos);

	/**
	 * 
	 * Define o corpo da mensagem do e-mail (<strong>Não</strong> podendo ficar em branco).
	 */
	void setCorpoDaMensagem(@NotBlank String corpoDaMensagem);

	/**
	 * 
	 * Define os Dados do Envio (como remetente, destinatário...) do e-mail (<strong>Não</strong> podendo ficar nulo).
	 */
	void setDadosDoEnvio(@NotNull DadosDoEnvio dadosDoEnvio);

}
