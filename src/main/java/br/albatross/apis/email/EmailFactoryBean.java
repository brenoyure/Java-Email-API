package br.albatross.apis.email;

import jakarta.enterprise.context.RequestScoped;

/**
 * 
 * Factory utilizada para obter uma instância de Email
 * 
 * @author breno.brito
 */
@RequestScoped
public class EmailFactoryBean {

    public Email newInstance() {
        return new EmailImpl(new EmailDadosDoEnvioImpl());
    }

}
