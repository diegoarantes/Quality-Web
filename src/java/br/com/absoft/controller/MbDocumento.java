package br.com.absoft.controller;

import br.com.absoft.model.dao.DAOGenerico;
import br.com.absoft.model.entities.Documento;
import br.com.absoft.suport.BbUsuarioLogado;
import br.com.absoft.util.StringUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author diego
 */
@ManagedBean
@SessionScoped
public class MbDocumento implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String pasta = "/uploads/documentos/"; //Pasta onde o arquivo será gravado

    private UploadedFile file;

    @EJB
    DAOGenerico dao;

    private Documento documento = new Documento();
    List<Documento> documentos;

    public MbDocumento() {
    }

    private String getPastaReal() { //Recupera a Pasta real onde o servidor está instalado
        FacesContext aFacesContext = FacesContext.getCurrentInstance();
        ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();

        String pastaReal = context.getRealPath("/");
        return pastaReal;
    }

    public void upload() {

        //---------------------
        documento.setDataRevisao(new Date());
//        documento.setTitulo("sad");
        documento.setRevisao(1);
        documento.setCaminho(" ");

        documento.setUsuario(BbUsuarioLogado.user.getUsuario());

        documento.setStatus('V');

        dao.inserir(documento);

        //---------------------
        try {
            copyFile(file.getFileName(), file.getInputstream());
        } catch (IOException e) {
        }

    }

    private void copyFile(String fileName, InputStream in) {
        try {
            //Monta o caminho em uma String
            String path = getPastaReal() + pasta + documento.getIdDocumento() + "/";

            //Cria o caminho onde o arquivo será gravado
            File caminho = new File(path);

            //Verifica se não Existe cria a pasta
            if (!caminho.exists()) {
                caminho.mkdirs();
            }

            //Adiciona o nome do arquivo no caminho e remove os acentos
            caminho = new File(path + StringUtil.removerAcentos(fileName));

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
        documento.setCaminho(pasta + documento.getIdDocumento() + "/" + StringUtil.removerAcentos(fileName));

        dao.atualizar(documento);
        
        //Mensagem de sucesso
        FacesMessage msg = new FacesMessage("O documento " + documento.getTitulo() + " foi enviado com sucesso!.", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        //Limpa a Variável
        documento = new Documento();

    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public List<Documento> getDocumentos() {
        return dao.lista(Documento.class);
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

}
