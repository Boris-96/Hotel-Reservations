/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.rezervacija;

import aso.AbstractSO;
import domain.AbstractDomainObject;
import domain.Rezervacija;
import java.sql.PreparedStatement;

/**
 *
 * @author Boris
 */
public class SOОbrisiRezervaciju extends AbstractSO{

    @Override
    protected void validate(AbstractDomainObject entity) throws Exception {
        if(!(entity instanceof Rezervacija))
            throw new Exception("Entity nije tipa Rezervacija");
    }

    @Override
    protected void execute(AbstractDomainObject entity) throws Exception {
        PreparedStatement ps=entity.getQueryForDelete(db.getKonekcija());
        ps.execute();
    }
    
}
