/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aso;

import db.DBBroker;
import domain.AbstractDomainObject;



/**
 *
 * @author Boris
 */
public abstract class AbstractSO {
    protected DBBroker db;
    
    public AbstractSO() {
        db = DBBroker.getInstance();
    }

    public final void templateExecute(AbstractDomainObject entity) throws Exception {
        try {
            validate(entity);
            execute(entity);
            commitTransaction();
        } catch (Exception ex) {
            rollbackTransaction();
            throw ex;
        }
    }

    protected abstract void validate(AbstractDomainObject entity) throws Exception;

    protected abstract void execute(AbstractDomainObject entity) throws Exception;

    private void commitTransaction() throws Exception {
        db.getKonekcija().commit();
    }

    private void rollbackTransaction() throws Exception {
        db.getKonekcija().rollback();
    }
}
