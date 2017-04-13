package br.com.absoft.controller;

import br.com.absoft.converters.ConverterSHA1;
import br.com.absoft.model.dao.DAOGenerico;
import br.com.absoft.model.entities.Pessoa;
import br.com.absoft.util.Mensagem;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Diego Arantes
 */
@Named(value = "mbLogin")
@RequestScoped
public class MbLogin implements Serializable{

    @EJB
    DAOGenerico dao;

    String usuario;
    String senha;

    Mensagem msg = new Mensagem();

    public MbLogin() {
    }

    public String efetuarLogin() {

        //Prevenção de SQL INJECTION
        usuario = usuario.replaceAll("'", "/");

        Pessoa usu = null;

        List<Pessoa> usrBanco = dao.listaCondicao(Pessoa.class, "(usuario = '" + usuario + "') AND senha = '" + ConverterSHA1.cipher(senha) + "'");
        for (Pessoa usr : usrBanco) {
            usu = usr;
        }

        if (usu != null) {
            //Adiciona o usuário logado na sessão
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", usu);

            return "/restrict/home.jsf";
        } else {
            msg.retornaErro("Usuário ou senha inválidos!");
            return "";
        }
    }

    /**
     * Efetua logout do usuário do sistema
     *
     * @return retorna para página de login
     */
    public String efetuarLogout() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.invalidate();
        return "/login.jsf";
    }

    public Pessoa usuarioLogado() {
        return (Pessoa) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
    }

    /* GETTERS E SETTERS */
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
