/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.tipSobe;

import aso.AbstractSO;
import domain.AbstractDomainObject;
import domain.TipSobe;
import java.sql.PreparedStatement;

/**
 *
 * @author Boris
 */
public class SOZapamtiTipSobe extends AbstractSO{

    @Override
    protected void validate(AbstractDomainObject entity) throws Exception {
        if(!(entity instanceof TipSobe))
            throw new Exception("Entity nije tipa TipSobe");
    }

    @Override
    protected void execute(AbstractDomainObject entity) throws Exception {
        PreparedStatement ps=entity.getQueryForInsert(db.getKonekcija());
        ps.execute();
    }
    
}
