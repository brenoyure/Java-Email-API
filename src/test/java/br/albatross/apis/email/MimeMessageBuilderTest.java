package br.albatross.apis.email;

import static java.lang.System.lineSeparator;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.mail.Address;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;

@ExtendWith(MockitoExtension.class)
public class MimeMessageBuilderTest {

    @InjectMocks
    private MimeMessageBuilder builder;

    @Mock
    private Properties mockedSessionProperties;

    @Mock
    private Session mockedMailSession;
    
    @Captor
    private ArgumentCaptor<Message> messageCaptor; 

    private static String corpoDaMensagem;

    @Mock
    private static Email email;

    @BeforeAll
    static void init() {

        corpoDaMensagem = new StringBuilder()

          .append("Prezados,")
          .append(lineSeparator())
          .append("Falamos do(a) Empresa Cliente LTDA,")
          .append(lineSeparator())
          .append("Segue em anexo o formulário preenchido para abertura de solicitação de garantia para o equipamento: AVCL486XPTO.")
          .append(lineSeparator())
          .append(lineSeparator())
          .append("Prezado atendente do fornecedor Fornecedor LTDA: ")
          .append(lineSeparator())
          .append(lineSeparator())
          .append("O formulário possui as informações necessárias sobre a solicitação, como, a descrição detalhada do problema,")
          .append(lineSeparator())
          .append("horários disponíveis, como também, os dados de endereço.")
          .append(lineSeparator())
          .append(lineSeparator())
          .append("As solicitações de garantia são realizadas através de um sistema interno de Solicitações de Garantia, com ")
          .append(lineSeparator())
          .append("o objetivo de automatizar os envios de solicitações para diversos fornecedores.")
          .append(lineSeparator())
          .append(lineSeparator())
          .append("Atenciosamente,")
          .append(lineSeparator())
          .append("--")
          .append(lineSeparator())
          .append("Empresa Cliente LTDA - Matriz da Empresa Cliente LTDA")
          .append(lineSeparator())
          .append("FortalCity/Ceará")
          .append(lineSeparator())
          .append("Serviço de Tecnologia da Informação (Empresa Cliente LTDA)")
          .append(lineSeparator())
          .append("(85)3386-4214, (85)3386-4416")
          .append(lineSeparator())
          .append("atendimento.cliente@mail.br, cliente.ltda@mail.br")
          .toString();

    }

    @BeforeEach
    void beforeEach() {
        BDDMockito
            .given(email.getAssunto()).willReturn("[Ticket#20240101486] Problema Mouse Fornecedor LTDA - Empresa Cliente Inc");

        BDDMockito
            .given(email.getRemetente()).willReturn("905f2dd9fbc8bb");
        
        BDDMockito
            .given(email.getDestinatario()).willReturn("atendimento.garantia@fornecedor.com, suporte@garantia.com");        
        
        BDDMockito
            .given(email.getCorpoDaMensagem())
            .willReturn(corpoDaMensagem);        
        
        BDDMockito
            .given(mockedMailSession.getProperties())
            .willReturn(mockedSessionProperties);  
    }
    
    @Test
    void test() throws NoSuchMethodException, SecurityException, IOException, MessagingException {

        Method messageDeliveryAndRecipiendDataMethod = 
                builder.getClass().getDeclaredMethod("setMessageDeliveryAndRecipientData", Message.class, Email.class);

        Method buildMultiPartMethod = 
                builder.getClass().getDeclaredMethod("buildMultiPart", Email.class);

        Assertions
            .assertTrue(messageDeliveryAndRecipiendDataMethod.trySetAccessible());        

        Assertions
            .assertTrue(buildMultiPartMethod.trySetAccessible());        

        BDDMockito
            .given(email.getCopiaPara()).willReturn("copia1@email.com, copia2@email.br");

        Message message = 
                builder.createMessage(email, mockedMailSession);

        Assertions
            .assertEquals(email.getAssunto(), message.getSubject());

        Assertions
            .assertEquals(email.getRemetente(), message.getFrom()[0].toString());

        Address[] recipientsCC = message.getRecipients(Message.RecipientType.CC);

        String[] recipientsCCStringArray = new String[recipientsCC.length];

        for (int i = 0; i <  recipientsCC.length; i++) {
             recipientsCCStringArray[i] = recipientsCC[i].toString();
        }

        Assertions
            .assertNotNull(recipientsCC);

        String[] copiaPara = email.getCopiaPara().split(", ");

        Assertions
            .assertArrayEquals(copiaPara, recipientsCCStringArray);

    }

}
