/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bluejnr.beans.daos;

import bluejnr.service.CustomerService;
import static bluejnr.util.Util.CST;
import static bluejnr.util.Util.PST;
import static bluejnr.util.Util.STM;

/**
 *
 * @author kcuadror
 */
public class DaoFactory {
    
    private DaoFactory() {
    }
    
    public static DaoFactory getInstance() {
        return DaoFactoryHolder.INSTANCE;
    }
    
    private static class DaoFactoryHolder {

        private static final DaoFactory INSTANCE = new DaoFactory();
    }
    
    public CustomerService getCustomerDao(int tipo){
        CustomerService servicio= null;
        switch(tipo){
            case STM: servicio= new CustomerSTDAO();break;
            case PST: servicio= new CustomerPSTDAO();break;
            case CST: servicio= new CustomerCSTDAO();break;
        }
        return servicio;
    }
}
