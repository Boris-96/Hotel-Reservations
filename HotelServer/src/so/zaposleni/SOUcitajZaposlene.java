/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.zaposleni;

import aso.AbstractSO;
import domain.AbstractDomainObject;
import domain.Zaposleni;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Boris
 */
public class SOUcitajZaposlene extends AbstractSO{
    private List<Zaposleni> list;    
    
    @Override
    protected void validate(AbstractDomainObject entity) throws Exception {
        if(!(entity instanceof Zaposleni))
            throw new Exception("Entity nije tipa Zaposleni");
    }

    @Override
    protected void execute(AbstractDomainObject entity) throws Exception {
        String query = entity.getAllQuery();
        Statement stat = db.getKonekcija().createStatement();
        ResultSet rs = stat.executeQuery(query);
        list = (List<Zaposleni>)(List<?>) entity.getList(rs);
    }

    public List<Zaposleni> getList() {
        return list;
    }
    
    
    
}
