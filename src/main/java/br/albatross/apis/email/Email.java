package br.albatross.apis.email;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.enterprise.context.Dependent;
import jakarta.validation.constraints.NotBlank;

@Dependent
public class Email implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
	 * 
	 * O assunto do e-mail (<strong>Não</strong> podendo ficar em branco).
	 */
    @NotBlank
    private String assunto;

	/**
	 * 
	 * O corpo da mensagem do e-mail (<strong>Não</strong> podendo ficar em branco).
	 */
    @NotBlank
    private String corpoDaMensagem;

	/**
	 * 
	 * O(s) anexo(s) do e-mail.
	 */
    private Set<Anexo> anexos = new HashSet<>();

    /**
     *
     * O Remetente <strong>(FROM)</strong> do e-mail (<strong>Não</strong> podendo ficar em branco).
     */
    @NotBlank @jakarta.validation.constraints.Email
    private String remetente;

    /**
     *
     * O Destinatário <strong>(TO)</strong> do e-mail, podendo ser um ou vários.
     *
     * (<strong>Não</strong> podendo ficar em branco).
     */
    @NotBlank
    private String destinatario;

    /**
     *
     * (Opcional) Os contatos (e-mails) que receberão cópia <strong>(CC)</strong> deste e-mail.
     */
    private String copiaPara;

    public Email() {

    }

    public void adicionarAnexo(Anexo anexo) {
        this.anexos.add(anexo);
    }

    public void adicionarAnexo(File file) throws IOException {
        try (InputStream fileInputStream = new BufferedInputStream(new FileInputStream(file))) {
            Anexo anexo = new Anexo(file.getName(), fileInputStream.readAllBytes());
            this.anexos.add(anexo);
        }
    }

    public void adicionarAnexo(String nomeDoArquivo, InputStream inputStream) throws IOException {
        Anexo anexo = new Anexo(nomeDoArquivo, inputStream.readAllBytes());
        this.anexos.add(anexo);
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getCorpoDaMensagem() {
        return corpoDaMensagem;
    }

    public void setCorpoDaMensagem(String corpoDaMensagem) {
        this.corpoDaMensagem = corpoDaMensagem;
    }

    public Set<Anexo> getAnexos() {
        return anexos;
    }

    public void setAnexos(Set<Anexo> anexos) {
        this.anexos = anexos;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getCopiaPara() {
        return copiaPara;
    }

    public void setCopiaPara(String copiaPara) {
        this.copiaPara = copiaPara;
    }

}
