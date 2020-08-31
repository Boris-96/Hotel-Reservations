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
import java.util.List;

/**
 *
 * @author Boris
 */
public class SOIzmeniRezevaciju extends AbstractSO{

    @Override
    protected void validate(AbstractDomainObject entity) throws Exception {
        if(!(entity instanceof Rezervacija))
            throw new Exception("Entity nije tipa Rezervacija");
    }

    @Override
    protected void execute(AbstractDomainObject entity) throws Exception {
        List<Rezervacija> list=(List<Rezervacija>)(List<?>)entity.getList(db.getKonekcija().createStatement().executeQuery(entity.getAllQuery()));
        
        
        PreparedStatement ps=entity.getQueryForEdit(db.getKonekcija());
        ps.execute();
        
        Rezervacija rezNova=(Rezervacija) entity;
        Rezervacija rez=list.get(list.indexOf(rezNova));
        for (Soba soba : rez.getSobe()) {
            if(!rezNova.getSobe().contains(soba)){
                RezervacijaSobe rezs=new RezervacijaSobe(rez, soba);
                PreparedStatement psDS=rezs.getQueryForDelete(db.getKonekcija());
                psDS.execute();
            }
            
        }
        for(RezervacijaUsluge usluga:rez.getUsluge()){
            if(!rezNova.getUsluge().contains(usluga)){
                RezervacijaUsluge rezu= new RezervacijaUsluge(rez, usluga.getUsluga(),usluga.getBrojDanaUsluge());
                PreparedStatement psDU=rezu.getQueryForDelete(db.getKonekcija());
                psDU.execute();
            }
        }
        
        for(Soba soba: rezNova.getSobe()){
            if(!rez.getSobe().contains(soba)){
                RezervacijaSobe rezs=new RezervacijaSobe(rezNova, soba);
                PreparedStatement psIS=rezs.getQueryForInsert(db.getKonekcija());
                psIS.execute();
            }
        }
        for(RezervacijaUsluge usluga: rezNova.getUsluge()){
            if(!rez.getUsluge().contains(usluga)){
                RezervacijaUsluge rezu=new RezervacijaUsluge(rezNova, usluga.getUsluga(),usluga.getBrojDanaUsluge());
                PreparedStatement psIU=rezu.getQueryForInsert(db.getKonekcija());
                psIU.execute();
            }
        }
    }
    
}
