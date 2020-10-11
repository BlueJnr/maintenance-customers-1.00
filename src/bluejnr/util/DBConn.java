/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bluejnr.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.PropertyResourceBundle;

/**
 *
 * @author kcuadror
 */
public class DBConn {

    private static String driver = null;
    private static String usuario = null;
    private static String password = null;
    private static String url = null;
    static PropertyResourceBundle properties;

    static {
        try {
            properties = new PropertyResourceBundle(new FileInputStream(Util.RUTA));
            url = properties.getString("URL");
            driver = properties.getString("DRIVER");
            usuario = properties.getString("USER");
            password = properties.getString("PASSWORD");

            Class.forName(driver);

        } catch (IOException e) {
            System.out.println("Error:" + e);
        } catch (ClassNotFoundException e) {
            System.out.println(Util.error1);
        }
    }

    /**
     * Obtiene una conexión a la Base de Datos.
     *
     * @return
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, usuario, password);
        } catch (SQLException e) {
            //Error en base de datos no se puede lograr la conexion
            System.out.println("Error:" + e);
        }
        return connection;
    }

}
