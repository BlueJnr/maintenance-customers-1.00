/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bluejnr.beans;

/**
 *
 * @author kcuadror
 */
public class CustomerTO {
    private int idCustomer;
    private String surnames;
    private String names;
    private String email;
    private String telephone;

    public CustomerTO() {
    }

    public CustomerTO(int idCustmer, String surnames, String names, String email, String telephone) {
        this.idCustomer = idCustmer;
        this.surnames = surnames;
        this.names = names;
        this.email = email;
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "CustomerTO{" + "idCustmer=" + idCustomer + ", surnames=" + surnames + ", names=" + names + ", email=" + email + ", telephone=" + telephone + '}';
    }
    
}
