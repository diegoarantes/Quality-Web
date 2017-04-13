package br.com.absoft.mails;

import br.com.absoft.model.entities.Pessoa;
import br.com.absoft.util.JavaMailUtil;

public class OcorrrenciaMail extends Thread {

    String email;
    String nome;
    Integer numero;
    String titulo;
    char tipo;
    String descricao;

    //---------------
    String texto;

    public OcorrrenciaMail(Pessoa pessoa, Integer nro, char tipo, String titulo, String descricao, char tip) {

        this.email = pessoa.getEmail();
        this.nome = pessoa.getNome();
        this.numero = nro;
        this.tipo = tipo;
        this.titulo = titulo;
        this.descricao = descricao;

        switch (tip) {
            case '1':
                texto = "Foi aberta uma ocorrência você deverá definir se foi aprovada ou não.";
                break;
            case '2':
                texto = "Foi aberta uma ocorrência para o seu setor, você deverá tomar ações <br />para que a mesma seja solucionada.";
                break;
        }

    }

    /**
     * Monta e envia a Mensagem para o Aprovador
     */
    @Override
    public void run() {
        System.out.println("------- INICIADO A TAREFA DE ENVIO DE E-MAIL");

        String tipoOcorrencia = null;

        //Define o Tipo pelo Char correspondente
        switch (this.tipo) {
            case 'N':
                tipoOcorrencia = "Não-Conformidade";
                break;
            case 'A':
                tipoOcorrencia = "Ação Preventiva";
                break;
            case 'M':
                tipoOcorrencia = "Nota de Melhoria";
                break;
        }
        //Monta a Mensagem
        String msg = "Olá " + this.nome + ",<br/> <br/>\n"
                + texto + "<br/><br/>\n"
                + "			<em>Detalhes da Ocorrência: </em><br />\n"
                + "			<strong>Nº</strong> " + this.numero + "<br />\n"
                + "			<strong>Tipo: </strong>" + tipoOcorrencia + "<br />\n"
                + "			<strong>Título: </strong>" + this.titulo + "<br /><br />\n"
                + "			<strong>Descrição: </strong>" + this.descricao + "\n"
                + "		        <br /><br/ >\n"
                + "			Atenciosamente,<br />\n"
                + "			<strong>ABSoft </strong>- <span style=\"color:#0066cc;\"><strong>Quality</strong></span>";

        //Dispara o E-mail
        new JavaMailUtil().enviaEmail(this.email, "ABSoft - Quality: Ocorrencia Nr. " + this.numero, msg);

    }

}
