/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bluejnr.service;

import bluejnr.beans.CustomerTO;
import java.util.List;

/**
 *
 * @author kcuadror
 */
public interface CustomerService {
    public CustomerTO createCustomer(CustomerTO customer);
    public int deleteCustomer(int idCustomer);
    public CustomerTO updateCustomer(CustomerTO customer);
    public List<CustomerTO> getCustomers();
    public CustomerTO findCustomer(int idCustomer);
    public CustomerTO findCustomer(String surnames, String names);
}