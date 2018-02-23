/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.ejb;

import com.udea.modelo.DiscountCode;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author raul.gomezr
 */
@Stateless
public class DiscountCodeManager implements DiscountManagerLocal {

    @PersistenceContext(unitName = "com.udea_Lab2-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;
    
   
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<DiscountCode> getDiscountCodes() {
        Query query = em.createNamedQuery("DiscountCode.findAll");
        return query.getResultList();
    }
}
