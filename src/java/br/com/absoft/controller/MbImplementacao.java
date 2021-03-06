package br.com.absoft.controller;

import br.com.absoft.model.dao.DAOGenerico;
import br.com.absoft.model.entities.Implementacao;
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
public class MbImplementacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    DAOGenerico dao;

    Implementacao implementacao = new Implementacao();

    Ocorrencia ocorrencia = new Ocorrencia();

    PlanoAcao acao = new PlanoAcao();

    private List<Implementacao> implementacoes;

    public String limpImplementacao() {
        implementacao = new Implementacao();
        return editImplementacao();
    }

    private String editImplementacao() {
        return "implementacao";
    }

    public String addImplementacao() {
        if (implementacao.getIdImplementacao() == null) {
            insertImplementacao();
        } else {
            updateImplementacao();
        }
        implementacao = new Implementacao();
        return "home";
    }

    private void insertImplementacao() {
        implementacao.setOcorrencia(ocorrencia);
        implementacao.setPlanoAcao(acao);
        implementacao.setDataCadastro(new Date());
        implementacao.setPessoa(new MbLogin().usuarioLogado());
        dao.inserir(implementacao);

        ocorrencia.setStatus('E'); //Seta o status da ocorrência como Aguardando Análise da Eficácia
        dao.atualizar(ocorrencia); //Atualiza a ocorrência

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

    public Ocorrencia getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public PlanoAcao getAcao() {
        List<PlanoAcao> acoes = dao.listaCondicao(PlanoAcao.class, "ocorrencia.idOcorrencia = " + ocorrencia.getIdOcorrencia() + "and implementado = true");
        for (PlanoAcao placao : acoes) {
            acao = placao;
        }
        return acao;
    }

    public void setAcao(PlanoAcao acao) {
        this.acao = acao;
    }

}
