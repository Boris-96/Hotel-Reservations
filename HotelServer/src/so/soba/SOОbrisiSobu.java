/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.soba;

import aso.AbstractSO;
import domain.AbstractDomainObject;
import domain.Soba;
import java.sql.PreparedStatement;

/**
 *
 * @author Boris
 */
public class SOОbrisiSobu extends AbstractSO{

    @Override
    protected void validate(AbstractDomainObject entity) throws Exception {
        if(!(entity instanceof Soba))
            throw new Exception("Entity nije tipa Soba");
    }

    @Override
    protected void execute(AbstractDomainObject entity) throws Exception {
        PreparedStatement ps=entity.getQueryForDelete(db.getKonekcija());
        ps.execute();
    }
    
}
