package br.albatross.apis.email;

/**
 * 
 * Factory utilizada para obter uma instância de Email
 * 
 * @author breno.brito
 */
public class EmailFactoryBean {

    public static Email newInstance() {
        return new EmailImpl(new EmailDadosDoEnvioImpl());
    }

}
