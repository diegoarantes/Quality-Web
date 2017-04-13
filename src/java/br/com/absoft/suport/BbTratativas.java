/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.absoft.suport;

import br.com.absoft.model.entities.Ocorrencia;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author diego
 */
@Named
@SessionScoped
public class BbTratativas implements Serializable {

    private static final long serialVersionUID = 1L;

    Ocorrencia ocorrencia = new Ocorrencia();

    private boolean desAprovacao;
    private boolean desAnaliseCausa;
    private boolean desPlanoAcao;
    private boolean desValidacao;
    private boolean desImplementacao;
    private boolean desAnaliseEficacia;

    public Ocorrencia getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public boolean isDesAprovacao() {
        return ocorrencia.getStatus() == 'A';
    }

    public void setDesAprovacao(boolean desAprovacao) {
        this.desAprovacao = desAprovacao;
    }
   
    
    public boolean isDesAnaliseCausa() {
        return ocorrencia.getStatus() == 'C'; //Se for diferente de C
    }

    public void setDesAnaliseCausa(boolean desAnaliseCausa) {
        this.desAnaliseCausa = desAnaliseCausa;
    }

    public boolean isDesPlanoAcao() {
        return ocorrencia.getStatus() == 'P';
    }

    public void setDesPlanoAcao(boolean desPlanoAcao) {
        this.desPlanoAcao = desPlanoAcao;
    }

    public boolean isDesValidacao() {
        return ocorrencia.getStatus() == 'V';
    }

    public void setDesValidacao(boolean desValidacao) {
        this.desValidacao = desValidacao;
    }

    public boolean isDesImplementacao() {
        return ocorrencia.getStatus() == 'I';
    }

    public void setDesImplementacao(boolean desImplementacao) {
        this.desImplementacao = desImplementacao;
    }

    public boolean isDesAnaliseEficacia() {
        return ocorrencia.getStatus() == 'E';
    }

    public void setDesAnaliseEficacia(boolean desAnaliseEficacia) {
        this.desAnaliseEficacia = desAnaliseEficacia;
    }

}
