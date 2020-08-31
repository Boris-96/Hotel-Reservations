/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.klijent;

import aso.AbstractSO;
import domain.AbstractDomainObject;
import domain.Klijent;
import java.sql.PreparedStatement;

/**
 *
 * @author Boris
 */
public class SOIzmeniKlijenta extends AbstractSO{

    @Override
    protected void validate(AbstractDomainObject entity) throws Exception {
        if(!(entity instanceof Klijent))
            throw new Exception("Entity nije tipa Klijent");
    }

    @Override
    protected void execute(AbstractDomainObject entity) throws Exception {
        PreparedStatement ps=entity.getQueryForEdit(db.getKonekcija());
        ps.execute();
    }
    
}
