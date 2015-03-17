package br.com.absoft.controller;

import br.com.absoft.mails.OcorrrenciaMail;
import br.com.absoft.model.dao.DAOGenerico;
import br.com.absoft.model.entities.Ocorrencia;
import br.com.absoft.model.entities.Pessoa;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class MbOcorrencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    DAOGenerico dao;

    private Ocorrencia ocorrencia = new Ocorrencia();

    private List<Ocorrencia> ocorrencias;

    private boolean opcoes;

    public String limpOcorrencia() {
        ocorrencia = new Ocorrencia();
        return "insertocorrencia";//editOcorrencia();
    }

    public String editOcorrencia() {
        if (ocorrencia.getIdOcorrencia() == null) {
            return null;
        } else {
            return "insertocorrencia";
        }

    }

    public String addOcorrencia() {
        if (ocorrencia.getIdOcorrencia() == null) {
            insertOcorrencia();
        } else {
            updateOcorrencia();
        }
//        ocorrencia = new Ocorrencia();
        return "ocorrencias";
    }

    public void insertOcorrencia() {
        ocorrencia.setDataCadastro(new Date()); //Pega a Data Atual
        ocorrencia.setRevisao(0); //Seta {0} como Sem Revisão
        ocorrencia.setStatus('A'); // Seta o Status como A - Aguardando aprovação

        ocorrencia.setUsuario(new MbLogin().usuarioLogado().getUsuario()); //Pega o Usuario logado

        //Grava a Ocorrencia 
        dao.inserir(ocorrencia);

        //Exibe a Mensagem de Sucesso
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));

        //Retorna o Aprovador
        Pessoa aprovador = (Pessoa) dao.recupera(Pessoa.class, ocorrencia.getPessoa().getIdPessoa());

        //Envia o E-mail para o Aprovador em outra Thread
        new OcorrrenciaMail(aprovador,
                ocorrencia.getIdOcorrencia(),
                ocorrencia.getTipo(),
                ocorrencia.getTitulo(),
                ocorrencia.getDescricao(),
                '1').start();

    }

    public void updateOcorrencia() {
        ocorrencia.setRevisao(addRevisao());
        dao.atualizar(ocorrencia);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
    }

    private Integer addRevisao() {
        return ocorrencia.getRevisao() + 1; //Pega a Revisão e adiciona mais 1
    }

    public void deleteOcorrencia() {
        try {
            dao.excluir(ocorrencia);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
        }
    }

    public void habilitaBotao() {
        this.opcoes = false;
    }

    public String aprovar() {
        ocorrencia.setStatus('C');
        dao.atualizar(ocorrencia);

        Pessoa responsavel = responsavelSetor(ocorrencia.getSetor().getIdSetor());

        try {//Envia o E-mail para o responsável do setor em outra Thread
            new OcorrrenciaMail(responsavel,
                    ocorrencia.getIdOcorrencia(),
                    ocorrencia.getTipo(),
                    ocorrencia.getTitulo(),
                    ocorrencia.getDescricao(),
                    '2').start();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Não foi possível enviar o e-mail para o responsável do setor, \npois não tem Responsável cadastrado neste setor!", ""));
        }

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "A ocorrência foi aprovada!", ""));
        return "home";
    }

    public String reprovar() {
        ocorrencia.setStatus('R');
        dao.atualizar(ocorrencia);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "A ocorrência foi reprovada!", ""));
        return "home";
    }

    public String retornaTipo(char tip) {
        String tipo = null;
        switch (tip) {
            case 'N':
                tipo = "Não-conformidade";
                break;
            case 'M':
                tipo = "Nota de melhoria";
                break;
            case 'A':
                tipo = "Ação preventiva";
                break;
        }
        return tipo;
    }

    private Pessoa responsavelSetor(int idSetor) {
        List<Pessoa> pessoas = dao.listaCondicao(Pessoa.class, "setor.idSetor = " + idSetor + " AND responsavelSetor = 'S' ");
        Pessoa pes = null;
        for (Pessoa pessoa : pessoas) {
            pes = pessoa;
        }
        return pes;
    }

    public MbOcorrencia() {
        this.opcoes = true;
    }

    public Ocorrencia getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public List<Ocorrencia> getOcorrencias() {
        ocorrencias = dao.lista(Ocorrencia.class);
        return ocorrencias;
    }

    public void setOcorrencias(List<Ocorrencia> ocorrencias) {
        this.ocorrencias = ocorrencias;
    }

    public boolean isOpcoes() {
        return opcoes;
    }

    public void setOpcoes(boolean opcoes) {
        this.opcoes = opcoes;
    }

}
