/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.absoft.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author diego
 */
@FacesConverter(value = "converterStatus")
public class ConverterStatus implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return retornaStatus(value.charAt(0));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return retornaStatus(value.toString().charAt(0));
    }

    private String retornaStatus(char status) {
        String rStatus = null;
        switch (status) {
            case 'A':
                rStatus = "Aguardando aprovação";
                break;
            case 'C':
                rStatus = "Aguardando análise da causa";
                break;
            case 'P':
                rStatus = "Aguardando plano de ação";
                break;
            case 'V':
                rStatus = "Aguardando validação";
                break;
            case 'I':
                rStatus = "Aguardando implementação";
                break;
            case 'E':
                rStatus = "Aguardando analise da eficácia";
                break;
            case 'F':
                rStatus = "Fechada";
                break;
            case 'R':
                rStatus = "Reprovada";
                break;

        }
        return rStatus;
    }
}
