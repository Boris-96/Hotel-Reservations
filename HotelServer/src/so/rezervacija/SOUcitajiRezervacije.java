/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.rezervacija;

import aso.AbstractSO;
import domain.AbstractDomainObject;
import domain.Rezervacija;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Boris
 */
public class SOUcitajiRezervacije extends AbstractSO{
    private List<Rezervacija> list;
    
    @Override
    protected void validate(AbstractDomainObject entity) throws Exception {
        if(!(entity instanceof Rezervacija))
            throw new Exception();
    }

    @Override
    protected void execute(AbstractDomainObject entity) throws Exception {
        String query = entity.getAllQuery();
        Statement stat = db.getKonekcija().createStatement();
        ResultSet rs = stat.executeQuery(query);
        list = (List<Rezervacija>)(List<?>) entity.getList(rs);
    }

    public List<Rezervacija> getList() {
        return list;
    }
    
    
}
