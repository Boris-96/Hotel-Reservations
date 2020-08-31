/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.rezervacija;

import aso.AbstractSO;
import domain.AbstractDomainObject;
import domain.Rezervacija;
import domain.RezervacijaSobe;
import domain.RezervacijaUsluge;
import domain.Soba;
import domain.Usluga;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Boris
 */
public class SOZapamtiRezervaciju extends AbstractSO{

    @Override
    protected void validate(AbstractDomainObject entity) throws Exception {
        if(!(entity instanceof Rezervacija))
            throw new Exception("Entity nije tipa Rezervacija");
    }

    @Override
    protected void execute(AbstractDomainObject entity) throws Exception {
        PreparedStatement ps=entity.getQueryForInsert(db.getKonekcija());
        ps.execute();
        ResultSet rs=ps.getGeneratedKeys();
        entity.setId(rs);
        Rezervacija r = (Rezervacija) entity;
        for (Soba s : r.getSobe()) {
            RezervacijaSobe rezs = new RezervacijaSobe(r, s);
            PreparedStatement ps1=rezs.getQueryForInsert(db.getKonekcija());
            ps1.execute();
        }
        for (RezervacijaUsluge u: r.getUsluge()) {
            RezervacijaUsluge rezu = new RezervacijaUsluge(r, u.getUsluga(), u.getBrojDanaUsluge());
            PreparedStatement ps2=rezu.getQueryForInsert(db.getKonekcija());
            ps2.execute();
        }
    }
    
}
