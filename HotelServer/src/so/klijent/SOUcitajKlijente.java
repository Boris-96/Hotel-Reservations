/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.klijent;

import aso.AbstractSO;
import domain.AbstractDomainObject;
import domain.Klijent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Boris
 */
public class SOUcitajKlijente extends AbstractSO{
    List<Klijent> list;
    
    @Override
    protected void validate(AbstractDomainObject entity) throws Exception {
        if(!(entity instanceof Klijent))
            throw new Exception("Entity nije tipa Klijent");
    }

    @Override
    protected void execute(AbstractDomainObject entity) throws Exception {
        String query = entity.getAllQuery();
        Statement stat = db.getKonekcija().createStatement();
        ResultSet rs = stat.executeQuery(query);
        list = (List<Klijent>)(List<?>) entity.getList(rs);
    }

    public List<Klijent> getList() {
        return list;
    }
    
    
}
