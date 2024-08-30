package br.albatross.apis.email;

public class EmailDadosDoEnvioImpl implements DadosDoEnvio {

    private static final long serialVersionUID = 1L;

    private String remetente;

    private String destinatario;

    private String copiaPara;

    public EmailDadosDoEnvioImpl() {

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
