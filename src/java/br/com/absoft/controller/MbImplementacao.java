package br.com.absoft.controller;

import br.com.absoft.model.dao.DAOGenerico;
import br.com.absoft.model.entities.Implementacao;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class MbImplementacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    DAOGenerico dao;

    Implementacao implementacao = new Implementacao();

    private List<Implementacao> implementacoes;


    public String limpImplementacao() {
        implementacao = new Implementacao();
        return editImplementacao();
    }

    private String editImplementacao() {
        return "implementacao";
    }

    public String addImplementacao() {
        if (implementacao.getIdImplementacao() == null || implementacao.getIdImplementacao() == 0) {
            insertImplementacao();
        } else {
            updateImplementacao();
        }
        return null;
    }

    private void insertImplementacao() {
        dao.inserir(implementacao);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
    }

    private void updateImplementacao() {
        dao.atualizar(implementacao);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
    }

    public void deleteImplementacao() {
        try {
            dao.excluir(implementacao);
        } catch (Exception ex) {
          
        }
    }

    public MbImplementacao() {
    }

    public Implementacao getImplementacao() {
        return implementacao;
    }

    public void setImplementacao(Implementacao implementacao) {
        this.implementacao = implementacao;
    }

    public List<Implementacao> getImplementacoes() {
        implementacoes = dao.lista(Implementacao.class);
        return implementacoes;
    }

    public void setImplementacoes(List<Implementacao> implementacoes) {
        this.implementacoes = implementacoes;
    }

}
