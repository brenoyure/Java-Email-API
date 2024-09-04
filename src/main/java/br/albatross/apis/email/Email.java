package br.albatross.apis.email;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Email implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
	 * 
	 * O assunto do e-mail (<strong>Não</strong> podendo ficar em branco).
	 */
    private String assunto;

	/**
	 * 
	 * O corpo da mensagem do e-mail (<strong>Não</strong> podendo ficar em branco).
	 */
    private String corpoDaMensagem;

	/**
	 * 
	 * O(s) anexo(s) do e-mail.
	 */
    private Set<Anexo> anexos = new HashSet<>();

	/**
	 * 
	 * Os Dados do Envio (como remetente, destinatário...) do e-mail (<strong>Não</strong> pode ficar nulo).
	 */
    private DadosDoEnvio dadosDoEnvio;

    public Email() {

    }

    public Email(DadosDoEnvio dadosDoEnvio) {
        this.dadosDoEnvio = dadosDoEnvio;
    }

    public Email(String assunto, String corpoDaMensagem, Set<Anexo> anexos, DadosDoEnvio dadosDoEnvio) {
        this.assunto = assunto;
        this.corpoDaMensagem = corpoDaMensagem;
        this.anexos = anexos;
        this.dadosDoEnvio = dadosDoEnvio;
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

    public DadosDoEnvio getDadosDoEnvio() {
        return dadosDoEnvio;
    }

    public void setDadosDoEnvio(DadosDoEnvio dadosDoEnvio) {
        this.dadosDoEnvio = dadosDoEnvio;
    }

}
