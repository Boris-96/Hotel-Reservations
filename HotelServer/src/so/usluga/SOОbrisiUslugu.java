/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.usluga;

import aso.AbstractSO;
import domain.AbstractDomainObject;
import domain.Usluga;
import java.sql.PreparedStatement;

/**
 *
 * @author Boris
 */
public class SOОbrisiUslugu extends AbstractSO{

    @Override
    protected void validate(AbstractDomainObject entity) throws Exception {
        if(!(entity instanceof Usluga))
            throw new Exception("Entity nije tipa Usluga");
    }

    @Override
    protected void execute(AbstractDomainObject entity) throws Exception {
        PreparedStatement ps=entity.getQueryForDelete(db.getKonekcija());
        ps.execute();
    }
    
}
