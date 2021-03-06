package br.com.absoft.controller;

import br.com.absoft.model.dao.DAOGenerico;
import br.com.absoft.model.entities.Documento;
import br.com.absoft.util.StringUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author diego
 */
@Named
@SessionScoped
public class MbDocumento implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String pasta = "/uploads/documentos/"; //Pasta onde o arquivo será gravado

    private UploadedFile file;

    @EJB
    DAOGenerico dao;

    private Documento documento = new Documento();
    private Documento documentoRev = new Documento();
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
        documento.setCaminho(" ");

        documento.setUsuario(new MbLogin().usuarioLogado().getUsuario());

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

    public void apagar() throws Exception {
        //Armazena o caminho do arquivo
        File dfile = new File(documento.getCaminho());

        //Comando para apagar o arquivo da pasta
        dfile.delete();

        //Comando para apagar o arquivo no banco de Dados
        dao.excluir(documento);

        //Limpa a Variável
        documento = new Documento();
    }

    public void revisar() {
        documentoRev.setTitulo(documento.getTitulo());
        documentoRev.setPessoa(documento.getPessoa());
        documentoRev.setRevisao(documento.getRevisao() + 1);
        documentoRev.setTipoDocumento(documento.getTipoDocumento());
        documentoRev.setSetor(documento.getSetor());
    }

    public void gravarRevisao() {
        documento.setStatus('O');
        dao.atualizar(documento);

        documento = documentoRev;

        documentoRev = new Documento();

        upload();

    }

    public String retonaStatus(char stat) {
        String status = null;
        switch (stat) {
            case 'V':
                status = "Vigente";
                break;
            case 'O':
                status = "Obsoleto";
                break;
        }

        return status;
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

    public Documento getDocumentoRev() {
        return documentoRev;
    }

    public void setDocumentoRev(Documento documentoRev) {
        this.documentoRev = documentoRev;
    }

}
