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
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kcuadror
 */
public class CustomerCSTDAO implements CustomerService {

    private Connection con;
    private ResultSet  rs;
    private CallableStatement cst;

    @Override
    public CustomerTO createCustomer(CustomerTO customer) {
        int filas=0;
        try {
            con = DBConn.getConnection();
            cst = con.prepareCall("{CALL ins_customer(?,?,?,?)}");
            cst.setString(1,customer.getSurnames());
            cst.setString(2,customer.getNames());
            cst.setString(3,customer.getEmail());
            cst.setString(4,customer.getTelephone());
            filas = cst.executeUpdate();
            cst.close();
            con.close();
            System.out.println("grabo con callablestatement");
        }catch(SQLException ex){
              try {
                  cst.close();
                  con.close();
              } catch (SQLException ex1) {
                  System.out.println(Util.error1);
              }
        }
        
        return findCustomer(customer.getSurnames(), customer.getNames());
    }

    @Override
    public int deleteCustomer(int idCustomer) {
        int filas=0;
        try {
            con = DBConn.getConnection();
            cst = con.prepareCall("{CALL del_customer(?)}");
            cst.setInt(1,idCustomer);
            filas = cst.executeUpdate();
            cst.close();
            con.close();
        }catch(SQLException ex){
              try {
                  cst.close();
                  con.close();
              } catch (SQLException ex1) {
                  System.out.println(Util.error1);
              }
        }
        
        return filas;
    }

    @Override
    public CustomerTO updateCustomer(CustomerTO customer) {
        int filas=0;
        try {
            con = DBConn.getConnection();
            cst = con.prepareCall("{CALL upd_customer(?,?,?,?,?)}");
            cst.setInt(1,customer.getIdCustomer());
            cst.setString(2,customer.getSurnames());
            cst.setString(3,customer.getNames());
            cst.setString(4,customer.getEmail());
            cst.setString(5,customer.getTelephone());
            filas = cst.executeUpdate();
            cst.close();
            con.close();
        }catch(SQLException ex){
              try {
                  cst.close();
                  con.close();
              } catch (SQLException ex1) {
                  System.out.println(Util.error1);
              }
        }
        
        return findCustomer(customer.getSurnames(), customer.getNames());
    }

    @Override
    public List<CustomerTO> getCustomers() {
        List<CustomerTO> lCustomer = new ArrayList<>();
        CustomerTO customer;
        try {
            con = DBConn.getConnection();
            cst = con.prepareCall("{CALL findAll_customer()}");
            rs = cst.executeQuery();
             while(rs.next()){
               customer=new CustomerTO();
               customer.setIdCustomer(Integer.parseInt(rs.getString(1)));
               customer.setSurnames(rs.getString(2));
               customer.setNames(rs.getString(3));
               customer.setEmail(rs.getString(4));
               customer.setTelephone(rs.getString(5));
               lCustomer.add(customer);
           }rs.close();
            cst.close();
            con.close();
        }catch(SQLException ex){
              try {
                  rs.close();
                  cst.close();
                  con.close();
              } catch (SQLException ex1) {
                  System.out.println(Util.error1);
              }
        }
        return lCustomer;
    }

    @Override
    public CustomerTO findCustomer(int idCustomer) {
        CustomerTO customer = null;
        try{
           con = DBConn.getConnection();
           cst = con.prepareCall("{CALL find_customer(?)}");
           cst.setInt(1,idCustomer);
           rs = cst.executeQuery();
           if(rs.next()){
               customer=new CustomerTO();
               customer.setIdCustomer(Integer.parseInt(rs.getString(1)));
               customer.setSurnames(rs.getString(2));
               customer.setNames(rs.getString(3));
               customer.setEmail(rs.getString(4));
               customer.setTelephone(rs.getString(5));
           }
            rs.close();
            cst.close();
            con.close();
        }catch(SQLException ex){
              try {
                  rs.close();
                  cst.close();
                  con.close();
              } catch (SQLException ex1) {
                  System.out.println(Util.error1);
              }
        }
        return customer;
    }

    @Override
    public CustomerTO findCustomer(String surnames, String names) {
        CustomerTO customer = null;
        try{
           con = DBConn.getConnection();
           cst = con.prepareCall("{CALL findBySurnamesAndNames_customer(?,?)}");
           cst.setString(1, surnames);
           cst.setString(2, names);
           rs = cst.executeQuery();
           if(rs.next()){
               customer=new CustomerTO();
               customer.setIdCustomer(Integer.parseInt(rs.getString(1)));
               customer.setSurnames(rs.getString(2));
               customer.setNames(rs.getString(3));
               customer.setEmail(rs.getString(4));
               customer.setTelephone(rs.getString(5));
           }
            rs.close();
            cst.close();
            con.close();
        }catch(SQLException ex){
              try {
                  rs.close();
                  cst.close();
                  con.close();
              } catch (SQLException ex1) {
                  System.out.println(Util.error1);
              }
        }
        return customer;
    }

}
