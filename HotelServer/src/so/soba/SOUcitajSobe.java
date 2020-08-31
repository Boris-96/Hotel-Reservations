/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.soba;

import aso.AbstractSO;
import domain.AbstractDomainObject;
import domain.Rezervacija;
import domain.Soba;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Boris
 */
public class SOUcitajSobe extends AbstractSO{
    private List<Soba> list;

    @Override
    protected void validate(AbstractDomainObject entity) throws Exception {
        if(!(entity instanceof Soba))
            throw new Exception("Entity nije tipa Soba");
    }

    @Override
    protected void execute(AbstractDomainObject entity) throws Exception {
        String query = entity.getAllQuery();
        Statement stat = db.getKonekcija().createStatement();
        ResultSet rs = stat.executeQuery(query);
        list = (List<Soba>)(List<?>) entity.getList(rs);
    }

    public List<Soba> getList() {
        return list;
    }
    
}
