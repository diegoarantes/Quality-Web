package br.com.absoft.controller;

import br.com.absoft.converters.ConverterSHA1;
import br.com.absoft.model.dao.DAOGenerico;
import br.com.absoft.model.entities.Pessoa;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named
@SessionScoped
public class MbPessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    DAOGenerico dao;

    private Pessoa pessoa = new Pessoa();

    private List<Pessoa> pessoas;

    private String confereSenha;

    public String limpPessoa() {
        pessoa = new Pessoa();
        return editPessoa();
    }

    private String editPessoa() {
        return "pessoas";
    }

    public String addPessoa() {
        if (pessoa.getIdPessoa() == null) {
            insertPessoa();
        } else {
            updatePessoa();
        }
        pessoa = new Pessoa();
        return null;
    }

    private void insertPessoa() {
        //Compara as Senhas

        if (pessoa.getSenha() == null ? ConverterSHA1.cipher(confereSenha) == null : pessoa.getSenha().equals(ConverterSHA1.cipher(confereSenha))) {
            pessoa.setStatus('A'); //Seta o Status como Ativo

            dao.inserir(pessoa);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
            //Retorna o Parâmetro sucesso para o contexto
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.addCallbackParam("sucesso", true);
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "As senhas não conferem !", ""));
        }

    }

    private void updatePessoa() {
        dao.atualizar(pessoa);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
        //Retorna o Parâmetro sucesso para o contexto
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.addCallbackParam("sucesso", true);
    }

    public void inativaPessoa() {
        pessoa.setStatus('I'); //Seta o Status como Inativo
        dao.atualizar(pessoa);
        pessoa = new Pessoa();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuário inativado !", ""));
    }

    public MbPessoa() {
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Pessoa> getPessoas() {
        pessoas = dao.lista(Pessoa.class);
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public String getConfereSenha() {
        return confereSenha;
    }

    public void setConfereSenha(String confereSenha) {
        this.confereSenha = confereSenha;
    }

}
