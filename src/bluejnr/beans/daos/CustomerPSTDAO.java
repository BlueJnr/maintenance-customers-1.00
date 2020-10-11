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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kcuadror
 */
public class CustomerPSTDAO implements CustomerService {

    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    @Override
    public CustomerTO createCustomer(CustomerTO customer) {
        int filas = 0;
        try {
            String sql = "insert into clientes (apellidos, nombres, correo, telefono) values(?,?,?,?)";
            con = DBConn.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, customer.getSurnames());
            pst.setString(2, customer.getNames());
            pst.setString(3, customer.getEmail());
            pst.setString(4, customer.getTelephone());
            filas = pst.executeUpdate();
            con.close();
            System.out.println("grabo con preparedstatement");
        } catch (SQLException ex) {
            System.out.println(Util.error1);
        }
        return findCustomer(customer.getSurnames(), customer.getNames());
    }

    @Override
    public int deleteCustomer(int idCustomer) {
        int filas = 0;
        try {
            String sql = "delete from clientes where idcliente=?";
            con = DBConn.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, idCustomer);
            filas = pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(Util.error1);
        }
        return filas;
    }

    @Override
    public CustomerTO updateCustomer(CustomerTO customer) {
        int filas = 0;
        try {
            String sql = "update clientes set apellidos=?, nombres=?, correo=?, telefono=? where idcliente=?";
            con = DBConn.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, customer.getSurnames());
            pst.setString(2, customer.getNames());
            pst.setString(3, customer.getEmail());
            pst.setString(4, customer.getTelephone());
            pst.setInt(5, customer.getIdCustomer());
            filas = pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(Util.error1);
        }
        return findCustomer(customer.getSurnames(), customer.getNames());
    }

    @Override
    public List<CustomerTO> getCustomers() {
        List<CustomerTO> lCustomer = new ArrayList<>();
        CustomerTO customer;
        try {
            con = DBConn.getConnection();
            pst = con.prepareStatement("select * from clientes");
            rs = pst.executeQuery();
            while (rs.next()) {
                customer = new CustomerTO();
                customer.setIdCustomer(Integer.parseInt(rs.getString(1)));
                customer.setSurnames(rs.getString(2));
                customer.setNames(rs.getString(3));
                customer.setEmail(rs.getString(4));
                customer.setTelephone(rs.getString(5));
                lCustomer.add(customer);
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(Util.error1);
        }
        return lCustomer;
    }

    @Override
    public CustomerTO findCustomer(int idCustomer) {
        CustomerTO customer = null;
        try {
            con = DBConn.getConnection();
            pst = con.prepareStatement("select * from clientes where idcliente=?");
            pst.setInt(1, idCustomer);
            rs = pst.executeQuery();
            if (rs.next()) {
                customer = new CustomerTO();
                customer.setIdCustomer(Integer.parseInt(rs.getString(1)));
                customer.setSurnames(rs.getString(2));
                customer.setNames(rs.getString(3));
                customer.setEmail(rs.getString(4));
                customer.setTelephone(rs.getString(5));
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(Util.error1);
        }
        return customer;
    }

    @Override
    public CustomerTO findCustomer(String surnames, String names) {
        CustomerTO customer = null;
        try {
            con = DBConn.getConnection();
            pst = con.prepareStatement("select * from clientes where apellidos=? and nombres=?");
            pst.setString(1, surnames);
            pst.setString(2, names);
            rs = pst.executeQuery();
            if (rs.next()) {
                customer = new CustomerTO();
                customer.setIdCustomer(Integer.parseInt(rs.getString(1)));
                customer.setSurnames(rs.getString(2));
                customer.setNames(rs.getString(3));
                customer.setEmail(rs.getString(4));
                customer.setTelephone(rs.getString(5));
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(Util.error1);
        }
        return customer;
    }

}
