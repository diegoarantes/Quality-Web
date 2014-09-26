package br.com.absoft.controller;

import br.com.absoft.mails.OcorrrenciaMail;
import br.com.absoft.model.dao.DAOGenerico;
import br.com.absoft.model.entities.Ocorrencia;
import br.com.absoft.model.entities.Pessoa;
import br.com.absoft.suport.BbUsuarioLogado;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class MbOcorrencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    DAOGenerico dao;

    private Ocorrencia ocorrencia = new Ocorrencia();

    private List<Ocorrencia> ocorrencias;

    public String limpOcorrencia() {
        ocorrencia = new Ocorrencia();
        return "insertocorrencia";//editOcorrencia();
    }

    public String editOcorrencia() {
        if (ocorrencia.getIdOcorrencia() == null || ocorrencia.getIdOcorrencia() == 0) {
            return null;
        } else {
            return "insertocorrencia";
        }

    }

    public String addOcorrencia() {
        if (ocorrencia.getIdOcorrencia() == null || ocorrencia.getIdOcorrencia() == 0) {
            insertOcorrencia();
        } else {
            updateOcorrencia();
        }
        return "ocorrencias";
    }

    public void insertOcorrencia() {
        ocorrencia.setDataCadastro(new Date()); //Pega a Data Atual
        ocorrencia.setRevisao(0); //Seta {0} como Sem Revisão
        
        ocorrencia.setUsuario(BbUsuarioLogado.user.getUsuario()); //Pega o Usuario logado


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
                ocorrencia.getDescricao()).start();

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

        }
    }

    public MbOcorrencia() {
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

}
