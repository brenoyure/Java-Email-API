package br.albatross.apis.email;

import java.io.File;

public class EmailImpl implements Email {

    private static final long serialVersionUID = 1L;

    private String assunto;

    private String corpoDaMensagem;

    private File[] anexos;

    private DadosDoEnvio dadosDoEnvio;

    public EmailImpl(DadosDoEnvio dadosDoEnvio) {
        this.dadosDoEnvio = dadosDoEnvio;
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

    public File[] getAnexos() {
        return anexos;
    }

    public void setAnexos(File[] anexos) {
        this.anexos = anexos;
    }

    public void setDadosDoEnvio(DadosDoEnvio dadosDoEnvio) {
        this.dadosDoEnvio = dadosDoEnvio;
    }

    public DadosDoEnvio getDadosDoEnvio() {
        return this.dadosDoEnvio;
    }

}
