/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.tipSobe;

import aso.AbstractSO;
import domain.AbstractDomainObject;
import domain.TipSobe;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Boris
 */
public class SOUcitajTipoveSobe extends AbstractSO{
    List<TipSobe> list;
    
    @Override
    protected void validate(AbstractDomainObject entity) throws Exception {
        if(!(entity instanceof TipSobe))
            throw new Exception("Entity nije tipa TipSobe");
    }

    @Override
    protected void execute(AbstractDomainObject entity) throws Exception {
        String query = entity.getAllQuery();
        Statement stat = db.getKonekcija().createStatement();
        ResultSet rs = stat.executeQuery(query);
        list = (List<TipSobe>)(List<?>) entity.getList(rs);
    }

    public List<TipSobe> getList() {
        return list;
    }
    
    
    
}
