/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.absoft.controller;

import br.com.absoft.model.dao.DAOGenerico;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

/**
 *
 * @author diego
 */
@ManagedBean
@SessionScoped
public class MbRelatorio {

    @EJB
    DAOGenerico dao;

    Date data1 = new Date();
    Date data2 = new Date();
    SimpleDateFormat formatador = new SimpleDateFormat("yyyy");
    int ano = Integer.parseInt(formatador.format(data1));
    char tipo = 'N';

    public void relatorioGeral() throws SQLException, JRException, IOException {
        HashMap param = new HashMap();
        param.put("data1", data1);
        param.put("data2", data2);

        imprimeRelatorio(param, "relatorioGeral.jasper");
    }

    public void relatorioAnual() throws SQLException, JRException, IOException {
        HashMap param = new HashMap();
        param.put("ano", ano);
        param.put("tipo", tipo);
        imprimeRelatorio(param, "relatorioAnual.jasper");
    }

    public Date getData1() {
        return data1;
    }

    public void setData1(Date data1) {
        this.data1 = data1;
    }

    public Date getData2() {
        return data2;
    }

    public void setData2(Date data2) {
        this.data2 = data2;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    /*
     Recebe os parámetros do tipo HashMap ex: parametros.put("data2", data2);
     Recebe o nome do arquivo do relatorio ex: relatorio.jasper
     */
    public void imprimeRelatorio(HashMap param, String relatorio) throws SQLException, JRException, IOException {

        Connection con = dao.getConnection();

        FacesContext facesContext = FacesContext.getCurrentInstance();

        facesContext.responseComplete();

        ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();

        JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/relatorios/" + relatorio), param, con);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        JRPdfExporter exporter = new JRPdfExporter();

        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
        exporter.exportReport();

        byte[] bytes = baos.toByteArray();

        if (bytes != null && bytes.length > 0) {

            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

            response.setContentType("application/pdf");

            response.setHeader("Content-disposition", "inline; filename=\"relatorioQuality.pdf\"");

            response.setContentLength(bytes.length);

            ServletOutputStream outputStream = response.getOutputStream();

            outputStream.write(bytes, 0, bytes.length);

            outputStream.flush();

            outputStream.close();

        }

    }

}
