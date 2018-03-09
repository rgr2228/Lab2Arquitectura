/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.controller;

import com.udea.entity.Customer;
import com.udea.entity.DiscountCode;
import com.udea.session.DiscountCodeManagerLocal;
import com.udea.session.ZipCodeManagerLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;
/**
 *
 * @author raul.gomezr
 */

@Named(value = "customerMBean")
@SessionScoped
public class CustomerMBean implements Serializable{

    @EJB
    private ZipCodeManagerLocal zipCodeManager;

    @EJB
    private DiscountCodeManagerLocal discountCodeManager;

    @EJB
    private com.udea.session.CustomerManager customerManager;
    
    private List<Customer> customers;
    private Customer customer;
    
    /**
     * Creates a new instance of CustomerMBean
     */
    public CustomerMBean() {
    }
    
    public List<Customer> getCustomers(){
        if((customers==null) || customers.isEmpty())
            refresh();
            return customers;     
    }
    
    public Customer getDetails()
    {
        return customer;
    }
    
    public String showDetails(Customer customer)
    {
        this.customer = customer;
        return "DETAILS";
    }
    
    public String update()
    {
        System.out.println("###UPDATE###");
        customer = customerManager.update(customer);
        return "SAVED";
    }
    
    public String list()
    {
        System.out.println("###List###");
        return "LIST";
    }
    
    private void refresh()
    {
        customers = customerManager.getAllCustomers();
    }
    
    public javax.faces.model.SelectItem[] getDiscountCodes()
    {
        SelectItem[] options = null;
        List<DiscountCode> discountCodes = discountCodeManager.getDiscountCodes();
        int disCodeSize = discountCodes.size();
        if(discountCodes!=null && disCodeSize>0)
        {
            int i = 0;
            options = new SelectItem[disCodeSize];
            for(DiscountCode dc: discountCodes)
            {
                options[i++] = 
                 new SelectItem(dc.getDiscountCode(),
                         dc.getDiscountCode() + "(" + dc.getRate() + "%)");
            }
        }
        return options;
    }
    
}
