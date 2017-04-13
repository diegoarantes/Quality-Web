package br.com.absoft.util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailUtil {

    Properties props = new Properties();
    Session session;

    /**
     * Configurações de conexão
     */
    private String smtp = "186.233.231.147";//"smtp.gmail.com";
    private String porta = "587"; //465
    private boolean seguro = false; // true
    private String email = "quality@incopostes.com";//"absoft.quality@gmail.com";
    private String senha = "system.1231";//"system.1231";

    /**
     *
     * @param destino e-mail de destino
     * @param assunto assunto do email
     * @param mensagem mensagem em html
     */
    public void enviaEmail(String destino, String assunto, String mensagem) {

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email)); //Remetente

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destino)); //Destinatário(s)
            message.setSubject(assunto);//Assunto
            message.setContent(montaMensagem(mensagem), "text/html; charset=utf-8"); //monta a mensagem e difine o charset
            /**
             * Método para enviar a mensagem criada
             */
            Transport.send(message);

            System.out.println("Mensagem Enviada com Sucesso! ");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public JavaMailUtil() {
        /**
         * Parâmetros de conexão com servidor
         */
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", smtp);
        props.put("mail.smtp.socketFactory.port", porta); //25
        props.put("mail.smtp.socketFactory.fallback", "false");

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", porta); //25

        if (seguro) {
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class   
        }

        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, senha);
            }
        });

        /**
         * Ativa Debug para sessão
         */
        session.setDebug(false);
    }

    /**
     *
     * @param msg mensagem
     * @return retorna o html da mensagem com cabeçalho e rodapé
     */
    private String montaMensagem(String msg) {
        String header = "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>\n"
                + "<html xmlns='http://www.w3.org/1999/xhtml'>\n"
                + "<body leftmargin='0' topmargin='0' marginwidth='0' marginheight='0'>\n"
                + "<center>\n"
                + "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
                + "<tbody style=\"background:#FFFFFF;\">\n"
                + "\n"
                + "<tr><td><p style=\"padding: 10px; color: #606060;font-family: Helvetica;font-size: 15px;\">";

        String footer = "</p></td></tr>\n"
                + "\n"
                + "</tbody>\n"
                + "</table>\n"
                + "</center>\n"
                + "</body>\n"
                + "</html>";

        String Mensagem = msg;

        return Mensagem;
    }
}
