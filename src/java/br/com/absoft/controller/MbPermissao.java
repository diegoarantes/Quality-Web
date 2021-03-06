/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.absoft.controller;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author diego
 */
@Named
@RequestScoped
public class MbPermissao implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean permissaoAdmin;
    private boolean permissaoRd;

    public MbPermissao() {
        if (new MbLogin().usuarioLogado().getPermissao().equals("ROLE_ADMIN")) {
            permissaoAdmin = true;
            permissaoRd = true;
        } else if (new MbLogin().usuarioLogado().getPermissao().equals("ROLE_GER")) {
            permissaoRd = true;
            permissaoAdmin = false;
        } else {
            permissaoRd = false;
            permissaoAdmin = false;
        }
    }

    public boolean isPermissaoAdmin() {

        return permissaoAdmin;
    }

    public void setPermissaoAdmin(boolean permissaoAdmin) {
        this.permissaoAdmin = permissaoAdmin;
    }

    public boolean isPermissaoRd() {
        return permissaoRd;
    }

    public void setPermissaoRd(boolean permissaoRd) {
        this.permissaoRd = permissaoRd;
    }

}
