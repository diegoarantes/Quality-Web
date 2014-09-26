package br.com.absoft.controller;

import br.com.absoft.model.dao.DAOGenerico;
import br.com.absoft.model.entities.PlanoAcao;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class MbPlanoAcao implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
    @EJB
    DAOGenerico dao;

    PlanoAcao planoAcao = new PlanoAcao();

    private List<PlanoAcao> planosAcao;


    public String limpPlanoAcao() {
        return editPlanoAcao();
    }

    private String editPlanoAcao() {
        planoAcao = new PlanoAcao();
        return "planosdeacao";
    }

    private String addPlanoAcao() {
        if (planoAcao.getIdPlanoAcao() == null || planoAcao.getIdPlanoAcao() == 0) {
            insertPlanoAcao();
        } else {
            updatePlanoAcao();
        }
        return null;
    }

    private void insertPlanoAcao() {
        dao.inserir(planoAcao);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
    }

    private void updatePlanoAcao() {
        dao.atualizar(planoAcao);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
    }

    public void deletePlanoAcao() {
        try {
            dao.excluir(planoAcao);
        } catch (Exception ex) {
           
        }
    }

    public MbPlanoAcao() {
    }

    public PlanoAcao getPlanoAcao() {
        return planoAcao;
    }

    public void setPlanoAcao(PlanoAcao planoAcao) {
        this.planoAcao = planoAcao;
    }

    public List<PlanoAcao> getPlanosAcao() {
        planosAcao = dao.lista(PlanoAcao.class);
        return planosAcao;
    }

    public void setPlanosAcao(List<PlanoAcao> planosAcao) {
        this.planosAcao = planosAcao;
    }

}
