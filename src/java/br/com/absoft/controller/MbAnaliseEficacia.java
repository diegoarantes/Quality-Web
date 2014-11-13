package br.com.absoft.controller;

import br.com.absoft.model.dao.DAOGenerico;
import br.com.absoft.model.entities.AnaliseEficacia;
import br.com.absoft.model.entities.Ocorrencia;
import br.com.absoft.suport.BbUsuarioLogado;
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
public class MbAnaliseEficacia implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    DAOGenerico dao;

    private Ocorrencia ocorrencia = new Ocorrencia();
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
        if (analiseEficacia.getIdAnaliseEficacia() == null) {
            analiseEficacia.setOcorrencia(ocorrencia);
            analiseEficacia.setPessoa(BbUsuarioLogado.user);
            analiseEficacia.setDataCadastro(new Date());
            insertAnaliseEficacia();
        } else {
            updateAnaliseEficacia();
        }
        analiseEficacia = new AnaliseEficacia();
        return null;
    }

    private void insertAnaliseEficacia() {
        analiseEficacia.setDataCadastro(new Date());
        analiseEficacia.setOcorrencia(ocorrencia);

        if (analiseEficacia.getEficaz() == 'S') {
            ocorrencia.setDataFechamento(new Date()); //Seta a Data de Fechamento
            ocorrencia.setStatus('F'); //Seta o status da ocorrência como Fechada/Eficaz
            dao.atualizar(ocorrencia); //Atualiza a ocorrência
        } else {

            ocorrencia.setStatus('P'); //Seta o status da ocorrência como Aguardando Plano de Acao Novamente
            dao.atualizar(ocorrencia); //Atualiza a ocorrência
        }
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

    public Ocorrencia getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

}
