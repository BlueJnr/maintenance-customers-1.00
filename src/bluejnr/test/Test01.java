/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bluejnr.test;

import bluejnr.beans.CustomerTO;
import bluejnr.beans.daos.DaoFactory;
import bluejnr.service.CustomerService;
import bluejnr.util.Util;
import java.util.List;

/**
 *
 * @author kcuadror
 */
public class Test01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //poblar la lista
        DaoFactory factory = DaoFactory.getInstance();
        CustomerService service = factory.getCustomerDao(Util.CST);
        
        CustomerTO customerTO = new CustomerTO();
        customerTO.setNames("KentJ");
        customerTO.setSurnames("RamosC");
        customerTO.setEmail("jnr@gmail.com");
        customerTO.setTelephone("123456789");
        
        service = factory.getCustomerDao(Util.PST);
        List<CustomerTO> lista= service.getCustomers();
        
        //agregar un customer
        lista.forEach((d) -> {
            System.out.println(d);
        });
        
        System.out.println(
        service.createCustomer(customerTO));
    }
    
}
