/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.zaposleni;

import aso.AbstractSO;
import domain.AbstractDomainObject;
import domain.Zaposleni;
import java.sql.PreparedStatement;

/**
 *
 * @author Boris
 */
public class SOIzmeniZaposlenog extends AbstractSO{

    @Override
    protected void validate(AbstractDomainObject entity) throws Exception {
        if(!(entity instanceof Zaposleni))
            throw new Exception("Entity nije tipa Zaposleni");
    }

    @Override
    protected void execute(AbstractDomainObject entity) throws Exception {
        PreparedStatement ps=entity.getQueryForEdit(db.getKonekcija());
        ps.execute();
    }
    
}
