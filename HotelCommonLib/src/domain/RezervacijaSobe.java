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
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Boris
 */
public class RezervacijaSobe extends AbstractDomainObject implements Serializable{
    private Rezervacija rezervacija;
    private Soba soba;

    public RezervacijaSobe() {
    }

    public RezervacijaSobe(Rezervacija rezervacija, Soba soba) {
        this.rezervacija = rezervacija;
        this.soba = soba;
    }

    public Soba getSoba() {
        return soba;
    }

    public void setSoba(Soba soba) {
        this.soba = soba;
    }

    public Rezervacija getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
    }

    @Override
    public String getAllQuery() {
        return "SELECT * FROM RezervacijaSobe";
    }

    @Override
    public PreparedStatement getQueryForInsert(Connection conn) throws SQLException {
        PreparedStatement ps=conn.prepareStatement("Insert into RezervacijaSobe(rezervacijaId,sobaId) values(?,?)",Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1,rezervacija.getRezervacijaId());
            ps.setLong(2,soba.getSobaId());
            return ps;
    }

    @Override
    public PreparedStatement getQueryForEdit(Connection conn) throws SQLException {
         throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PreparedStatement getQueryForDelete(Connection conn) throws SQLException {
        PreparedStatement ps=conn.prepareStatement("Delete from RezervacijaSobe where rezervacijaId = (?) and sobaId = (?)");
            ps.setLong(1, rezervacija.getRezervacijaId());
            ps.setLong(2, soba.getSobaId());
        return ps;
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        List<AbstractDomainObject> list=new LinkedList<>();
        while(rs.next()){
            long rezervacijaID = rs.getLong("rezervacijaId");
            long sobaID = rs.getLong("sobaId");
        }
        return null;
    }

    @Override
    public void setId(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
    
}
