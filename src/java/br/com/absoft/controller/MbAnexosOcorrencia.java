/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.absoft.controller;

import br.com.absoft.model.dao.DAOGenerico;
import br.com.absoft.model.entities.ArquivosOcorrencia;
import br.com.absoft.model.entities.Ocorrencia;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author diego
 */
@ManagedBean
@SessionScoped
public class MbAnexosOcorrencia {

    @EJB
    DAOGenerico dao;

    Ocorrencia ocorrencia = new Ocorrencia();
    ArquivosOcorrencia arquivoOcorrencia = new ArquivosOcorrencia();
    List<ArquivosOcorrencia> arquivos;

    private final String pasta = "/uploads/ocorrencias/"; //Pasta onde o arquivo será gravado

    public String getPastaReal() { //Recupera a Pasta real onde o servidor está instalado
        FacesContext aFacesContext = FacesContext.getCurrentInstance();
        ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();

        String pastaReal = context.getRealPath("/");
        return pastaReal;
    }

    public void upload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("O arquivo " + event.getFile().getFileName() + " foi enviado com sucesso!.", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
        } catch (IOException e) {
        }

    }

    public void copyFile(String fileName, InputStream in) {
        try {
            //Monta o caminho em uma String
            String path = getPastaReal() + pasta + ocorrencia.getIdOcorrencia() + "/";

            //Cria o caminho onde o arquivo será gravado
            File caminho = new File(path);

            //Verifica se não Existe cria a pasta
            if (!caminho.exists()) {
                caminho.mkdirs();
            }

            //Adiciona o nome do arquivo no caminho
            caminho = new File(path + fileName);

            //Seta o destino
            OutputStream out = new FileOutputStream(caminho);

            int read = 0;
            byte[] bytes = new byte[1024];

            //Grava o arquivo
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

            System.out.println("New file created!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        //----------------------------
        arquivoOcorrencia.setCaminho(pasta + ocorrencia.getIdOcorrencia() + "/" + fileName);
        arquivoOcorrencia.setOcorrencia(ocorrencia);
        dao.inserir(arquivoOcorrencia);

        //Limpa a Variável
        arquivoOcorrencia = new ArquivosOcorrencia();

    }

    public void Apagar() throws Exception {
        //Armazena o caminho do arquivo
        File file = new File(arquivoOcorrencia.getCaminho());

        //Comando para apagar o arquivo da pasta
        file.delete();

        //Comando para apagar o arquivo no banco de Dados
        dao.excluir(arquivoOcorrencia);

        //Limpa a Variável
        arquivoOcorrencia = new ArquivosOcorrencia();
    }

    public Ocorrencia getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public ArquivosOcorrencia getArquivoOcorrencia() {
        return arquivoOcorrencia;
    }

    public void setArquivoOcorrencia(ArquivosOcorrencia arquivoOcorrencia) {
        this.arquivoOcorrencia = arquivoOcorrencia;
    }

    public List<ArquivosOcorrencia> getArquivos() {
        arquivos = dao.listaCondicao(ArquivosOcorrencia.class, "ocorrencia.idOcorrencia = " + ocorrencia.getIdOcorrencia());
        return arquivos;
    }

    public void setArquivos(List<ArquivosOcorrencia> arquivos) {
        this.arquivos = arquivos;
    }

}
