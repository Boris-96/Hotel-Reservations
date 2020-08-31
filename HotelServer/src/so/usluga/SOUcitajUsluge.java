/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.usluga;

import aso.AbstractSO;
import domain.AbstractDomainObject;
import domain.Usluga;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Boris
 */
public class SOUcitajUsluge extends AbstractSO{
    List<Usluga> list;
    
    @Override
    protected void validate(AbstractDomainObject entity) throws Exception {
        if(!(entity instanceof Usluga))
            throw new Exception("Entity nije tipa Usluga");
    }

    @Override
    protected void execute(AbstractDomainObject entity) throws Exception {
        String query = entity.getAllQuery();
        Statement stat = db.getKonekcija().createStatement();
        ResultSet rs = stat.executeQuery(query);
        list = (List<Usluga>)(List<?>) entity.getList(rs);
    }

    public List<Usluga> getList() {
        return list;
    }
    
}
