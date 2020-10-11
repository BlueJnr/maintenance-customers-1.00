/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bluejnr.util;

import java.util.ResourceBundle;

/**
 *
 * @author kcuadror
 */
public class Util {
    public static final String RUTA= "C:\\Datos.ini";
    public static final int STM=0;
    public static final int PST=1;
    public static final int CST=2;
    public static  int opc;
    public static String error1;
    static{
        ResourceBundle properties = ResourceBundle.getBundle("cjava.util.mensajes");
        error1 = properties.getString("error1");
        opc = Integer.parseInt(properties.getString("opc"));
    }
    
}