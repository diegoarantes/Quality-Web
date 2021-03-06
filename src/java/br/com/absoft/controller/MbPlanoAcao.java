package br.com.absoft.controller;

import br.com.absoft.model.dao.DAOGenerico;
import br.com.absoft.model.entities.Ocorrencia;
import br.com.absoft.model.entities.PlanoAcao;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class MbPlanoAcao implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    DAOGenerico dao;

    private Ocorrencia ocorrencia = new Ocorrencia();
    PlanoAcao planoAcao = new PlanoAcao();

    private List<PlanoAcao> planosAcao;

    public String limpPlanoAcao() {
        return editPlanoAcao();
    }

    private String editPlanoAcao() {
        planoAcao = new PlanoAcao();
        return "planosdeacao";
    }

    public String addPlanoAcao() {
        if (planoAcao.getIdPlanoAcao() == null) {
            planoAcao.setDataCadastro(new Date());
            planoAcao.setOcorrencia(ocorrencia);
            planoAcao.setImplementado(false);
            planoAcao.setPessoa(new MbLogin().usuarioLogado());
            insertPlanoAcao();
        } else {
            updatePlanoAcao();
            return null;
        }
        planoAcao = new PlanoAcao();
        return "home";
    }

    private void insertPlanoAcao() {
        dao.inserir(planoAcao);

        ocorrencia.setStatus('V'); //Seta o status da ocorrência como Aguardando Validação
        dao.atualizar(ocorrencia); //Atualiza a ocorrência

        planoAcao = new PlanoAcao();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
    }

    private void updatePlanoAcao() {
        dao.atualizar(planoAcao);
        planoAcao = new PlanoAcao();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
    }

    public void deletePlanoAcao() {
        try {
            dao.excluir(planoAcao);
        } catch (Exception ex) {

        }
    }

    public String aprovar() {
        planoAcao.setImplementado(true);
        dao.atualizar(planoAcao);

        ocorrencia.setStatus('I'); //Seta o status da ocorrência como Aguardando Implementação
        dao.atualizar(ocorrencia); //Atualiza a ocorrência

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Validação realizada com sucesso!", ""));
        return "home";

    }

    public String reprovar() {
        planoAcao.setImplementado(false);
        dao.atualizar(planoAcao);

        ocorrencia.setStatus('P'); //Seta o status da ocorrência como Aguardando Plano de ação
        dao.atualizar(ocorrencia); //Atualiza a ocorrência

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Validação realizada com sucesso!", ""));
        return "home";
    }

    public void retornaPlanoAprovado() {
        List<PlanoAcao> planos = dao.listaCondicao(PlanoAcao.class, "ocorrencia.idOcorrencia = " + ocorrencia.getIdOcorrencia()
                + " and "
                + "implementado = 1");
        for (PlanoAcao plano : planos) {
            planoAcao = plano;
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
        planosAcao = dao.listaCondicao(PlanoAcao.class, "ocorrencia.idOcorrencia = " + ocorrencia.getIdOcorrencia());
        return planosAcao;
    }

    public void setPlanosAcao(List<PlanoAcao> planosAcao) {
        this.planosAcao = planosAcao;
    }

    public Ocorrencia getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

}
