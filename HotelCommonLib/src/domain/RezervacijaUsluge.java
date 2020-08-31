/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Boris
 */
public class RezervacijaUsluge extends AbstractDomainObject implements Serializable{
    private Rezervacija rezervacija;
    private Usluga usluga;
    private int brojDanaUsluge;

    public RezervacijaUsluge() {
    }

    public RezervacijaUsluge(Rezervacija rezervacija, Usluga usluga, int brojDanaUsluge) {
        this.rezervacija = rezervacija;
        this.usluga = usluga;
        this.brojDanaUsluge = brojDanaUsluge;
    }

    public Usluga getUsluga() {
        return usluga;
    }

    public void setUsluga(Usluga usluga) {
        this.usluga = usluga;
    }

    public Rezervacija getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
    }

    public int getBrojDanaUsluge() {
        return brojDanaUsluge;
    }

    public void setBrojDanaUsluge(int brojDanaUsluge) {
        this.brojDanaUsluge = brojDanaUsluge;
    }
    
   @Override
    public String getAllQuery() {
        return "SELECT * FROM RezervacijaUsluge";
    }

    @Override
    public PreparedStatement getQueryForInsert(Connection conn) throws SQLException {
         PreparedStatement ps=conn.prepareStatement("Insert into RezervacijaUsluge(rezervacijaId,uslugaId,brojDanaUsluge) values(?,?,?)",Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1,rezervacija.getRezervacijaId());
            ps.setLong(2,usluga.getUslugaId());
            ps.setInt(3, brojDanaUsluge);
            return ps;
    }

    @Override
    public PreparedStatement getQueryForEdit(Connection conn) throws SQLException {
        PreparedStatement ps=conn.prepareStatement("Update RezervacijaUsluge set uslugaId = (?), brojDanaUsluge = (?) where rezervacijaId = (?)");
            ps.setLong(1, usluga.getUslugaId());
            ps.setInt(2, brojDanaUsluge);
            ps.setLong(3, rezervacija.getRezervacijaId());
            return ps;
    }

    @Override
    public PreparedStatement getQueryForDelete(Connection conn) throws SQLException {
        PreparedStatement ps=conn.prepareStatement("Delete from RezervacijaUsluge where rezervacijaId = (?) and uslugaId = (?)");
            ps.setLong(1, rezervacija.getRezervacijaId());
            ps.setLong(2, usluga.getUslugaId());
        return ps;
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setId(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof RezervacijaUsluge){
            RezervacijaUsluge r=(RezervacijaUsluge)o;
            if(this.rezervacija.getRezervacijaId()== r.getRezervacija().getRezervacijaId() && this.usluga.getUslugaId()==r.getUsluga().getUslugaId())
                return true;
        }
        return false;
    }
    
    
}
