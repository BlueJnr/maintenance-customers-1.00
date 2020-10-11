/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bluejnr.beans.daos;

import bluejnr.beans.CustomerTO;
import bluejnr.service.CustomerService;
import bluejnr.util.DBConn;
import bluejnr.util.Util;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kcuadror
 */
public class CustomerSTDAO implements CustomerService {

    private Connection con;
    private Statement st;
    private ResultSet rs;

    @Override
    public CustomerTO createCustomer(CustomerTO customer) {
        int filas=0;
        try {
            String sql= "insert into clientes (apellidos, nombres, correo, telefono) "+
                    "values('"+customer.getSurnames()+"',"
                    + "'" + customer.getNames() + "',"
                    + "'" + customer.getEmail() + "',"
                    + "'" + customer.getTelephone() + "')";
            con = DBConn.getConnection();
            st = con.createStatement();
            filas = st.executeUpdate(sql);
            con.close();
            System.out.println("grabo con statement");
        } catch (SQLException ex) {
            System.out.println(Util.error1);
        }
        return findCustomer(customer.getSurnames(), customer.getNames());
    }

    @Override
    public int deleteCustomer(int idCustomer) {
        int filas=0;
        try {
            String sql= "delete from clientes where idcliente="+idCustomer+"";
            con = DBConn.getConnection();
            st = con.createStatement();
            filas = st.executeUpdate(sql);
            con.close();
        } catch (SQLException ex) {
            System.out.println(Util.error1);
        }
        return filas;
    }

    @Override
    public CustomerTO updateCustomer(CustomerTO customer) {
        int filas=0;
        try {
            String sql= "update clientes set apellidos='"+customer.getSurnames()
                    +"', nombres='"+customer.getNames()
                    +"', correo='"+customer.getEmail()
                    +"', telefono='"+customer.getTelephone()
                    +"' where idcliente="+customer.getIdCustomer()+"";
            con = DBConn.getConnection();
            st = con.createStatement();
            filas = st.executeUpdate(sql);
            con.close();
        } catch (SQLException ex) {
            System.out.println(Util.error1);
        }
        return findCustomer(customer.getSurnames(), customer.getNames());
    }

    @Override
    public List<CustomerTO> getCustomers() {
        List<CustomerTO> lCustomer = new ArrayList<>();
        CustomerTO customer;
        try{
           con = DBConn.getConnection();
           st = con.createStatement();
           rs = st.executeQuery("select * from clientes");
           while(rs.next()){
               customer=new CustomerTO();
               customer.setIdCustomer(Integer.parseInt(rs.getString(1)));
               customer.setSurnames(rs.getString(2));
               customer.setNames(rs.getString(3));
               customer.setEmail(rs.getString(4));
               customer.setTelephone(rs.getString(5));
               lCustomer.add(customer);
           }
           con.close();
        }catch(SQLException ex){
            System.out.println(Util.error1);
        }
        return lCustomer;
    }

    @Override
    public CustomerTO findCustomer(int idCustomer) {
        CustomerTO customer = null;
        try{
           con = DBConn.getConnection();
           st = con.createStatement();
           rs = st.executeQuery("select * from clientes where idcliente="+idCustomer+"");
           if(rs.next()){
               customer=new CustomerTO();
               customer.setIdCustomer(Integer.parseInt(rs.getString(1)));
               customer.setSurnames(rs.getString(2));
               customer.setNames(rs.getString(3));
               customer.setEmail(rs.getString(4));
               customer.setTelephone(rs.getString(5));
           }
           con.close();
        }catch(SQLException ex){
            System.out.println(Util.error1);
        }
        return customer;
    }

    @Override
    public CustomerTO findCustomer(String surnames, String names) {
        CustomerTO customer = null;
        try{
           con = DBConn.getConnection();
           st = con.createStatement();
           rs = st.executeQuery("select * from clientes where apellidos="+surnames+" and nombres="+names+"");
           if(rs.next()){
               customer=new CustomerTO();
               customer.setIdCustomer(Integer.parseInt(rs.getString(1)));
               customer.setSurnames(rs.getString(2));
               customer.setNames(rs.getString(3));
               customer.setEmail(rs.getString(4));
               customer.setTelephone(rs.getString(5));
           }
           con.close();
        }catch(SQLException ex){
            System.out.println(Util.error1);
        }
        return customer;
    }

}
