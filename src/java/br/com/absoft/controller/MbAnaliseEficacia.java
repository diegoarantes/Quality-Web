package br.com.absoft.controller;

import br.com.absoft.model.dao.DAOGenerico;
import br.com.absoft.model.entities.AnaliseEficacia;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class MbAnaliseEficacia implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    DAOGenerico dao;

    AnaliseEficacia analiseEficacia = new AnaliseEficacia();

    private List<AnaliseEficacia> analisesDaEficacia;

    public String limpAnaliseEficacia() {
        analiseEficacia = new AnaliseEficacia();
        return editAnaliseEficacia();
    }

    private String editAnaliseEficacia() {
        return "analiseeficacia";
    }

    public String addAnaliseEficacia() {
        if (analiseEficacia.getIdAnaliseEficacia() == null || analiseEficacia.getIdAnaliseEficacia() == 0) {
            insertAnaliseEficacia();
        } else {
            updateAnaliseEficacia();
        }
        return null;
    }

    private void insertAnaliseEficacia() {
        dao.inserir(analiseEficacia);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
    }

    private void updateAnaliseEficacia() {
        dao.atualizar(analiseEficacia);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
    }

    public void deleteAnaliseEficacia() {
        try {
            dao.excluir(analiseEficacia);
        } catch (Exception ex) {

        }
    }

    public MbAnaliseEficacia() {
    }

    public AnaliseEficacia getAnaliseEficacia() {
        return analiseEficacia;
    }

    public void setAnaliseEficacia(AnaliseEficacia analiseEficacia) {
        this.analiseEficacia = analiseEficacia;
    }

    public List<AnaliseEficacia> getAnalisesDaEficacia() {
        analisesDaEficacia = dao.lista(AnaliseEficacia.class);
        return analisesDaEficacia;
    }

    public void setAnalisesDaEficacia(List<AnaliseEficacia> analisesDaEficacia) {
        this.analisesDaEficacia = analisesDaEficacia;
    }

}
