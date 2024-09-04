package br.albatross.apis.email;

import static java.io.File.createTempFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

public class Anexo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private byte[] arquivo;

    public Anexo() {

    }

    public Anexo(String nome, byte[] arquivo) {
        this.nome = nome;
        this.arquivo = arquivo;
    }

    public File toFile() throws IOException {
        String fileName = nome.replace(fileExtension(nome), "");
        String fileExtension = fileExtension(nome);

        File anexoTempFile = createTempFile(fileName, fileExtension);

        try (OutputStream os = new BufferedOutputStream(new FileOutputStream(anexoTempFile))) {
            os.write(arquivo);
        }

        return anexoTempFile;

    }

    /**
     *
     * Retorna a substring do nome do arquivo que representa sua extensão.
     * Basicamente a String após a última ocorrência do caractére ponto.
     *
     * @param fileName
     * @return file extension
     */
    private String fileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.'));
    }

    public String getNome() {
        return nome;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

}
