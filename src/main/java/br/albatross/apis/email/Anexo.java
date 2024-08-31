package br.albatross.apis.email;

import java.io.Serializable;

public class Anexo implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String nome;
    private final byte[] arquivo;

    public Anexo(String nome, byte[] arquivo) {
        this.nome = nome;
        this.arquivo = arquivo;
    }

    public String getNome() {
        return nome;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

}
