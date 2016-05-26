/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;

import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Gilberto Adan González Silva
 */
public class ParametrosSP {

    /**
     * @return the parametros
     */
    public List<Parametro> getParametros() {
        return parametros;
    }

    /**
     * @param parametros the parametros to set
     */
    public void setParametros(List<Parametro> parametros) {
        this.parametros = parametros;
    }
    
    private List<Parametro> parametros;
    
    //private Parametro[] parametros;
    public void agregarParametro(String valor, String nombre, String tipo){
        
        Parametro parametro;
        
        parametro = new Parametro();
        
        parametro.tipo      =   tipo;
        parametro.nombre    =   nombre;
        parametro.valor     =   valor;
        
        parametros.add(parametro);
    }
        
    public void agregarParametro(Calendar calendar, String nombre, String tipo){
        
        Parametro parametro;
        
        parametro = new Parametro();
        
        parametro.tipo      =   tipo;
        parametro.nombre    =   nombre;
        parametro.calendar  =   calendar;
        
        parametros.add(parametro);
    }
}
