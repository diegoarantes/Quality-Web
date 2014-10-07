package br.com.absoft.controller;

import br.com.absoft.model.dao.DAOGenerico;
import br.com.absoft.model.entities.AnaliseDaCausa;
import br.com.absoft.model.entities.Ocorrencia;

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
public class MbAnaliseDaCausa implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    DAOGenerico dao;

    AnaliseDaCausa analiseDaCausa = new AnaliseDaCausa();

    private List<AnaliseDaCausa> analisesDaCausa;

    public String limpAnaliseDaCausa() {
        analiseDaCausa = new AnaliseDaCausa();
        return editAnaliseDaCausa();

    }

    private String editAnaliseDaCausa() {
        return "analisesdacausa";
    }

    public String addAnaliseDaCausa() {
        if (analiseDaCausa.getIdAnaliseDaCausa() == null || analiseDaCausa.getIdAnaliseDaCausa() == 0) {
            insertAnaliseDaCausa();
        } else {
            updateAnaliseDaCausa();
        }

        return null;
    }

    private void insertAnaliseDaCausa() {
        analiseDaCausa.setDataCadastro(new Date());
        analiseDaCausa.setCausaRaiz('N');

        dao.inserir(analiseDaCausa);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
    }

    private void updateAnaliseDaCausa() {
        dao.atualizar(analiseDaCausa);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
    }

    public void deleteAnaliseDaCausa() {
        try {
            dao.excluir(analiseDaCausa);
        } catch (Exception ex) {

        }
    }

    public MbAnaliseDaCausa() {
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setIdOcorrencia(0);
        analiseDaCausa.setOcorrencia(ocorrencia);
    }

    public AnaliseDaCausa getAnaliseDaCausa() {
        return analiseDaCausa;
    }

    public void setAnaliseDaCausa(AnaliseDaCausa analiseDaCausa) {
        this.analiseDaCausa = analiseDaCausa;
    }

    public List<AnaliseDaCausa> getAnalisesDaCausa() {

        analisesDaCausa = dao.listaCondicao(AnaliseDaCausa.class, "ocorrencia.idOcorrencia = " + analiseDaCausa.getOcorrencia().getIdOcorrencia());
        return analisesDaCausa;
    }

    public void setAnalisesDaCausa(List<AnaliseDaCausa> analisesDaCausa) {
        this.analisesDaCausa = analisesDaCausa;
    }

}
