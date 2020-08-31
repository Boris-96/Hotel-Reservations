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
public class Usluga extends AbstractDomainObject implements Serializable{
    private long uslugaId;
    private String nazivUsluge;
    private double cenaUslugePoDanu;

    public Usluga() {
    }

    public Usluga(long uslugaId, String nazivUsluge, double cenaUslugePoDanu) {
        this.uslugaId = uslugaId;
        this.nazivUsluge = nazivUsluge;
        this.cenaUslugePoDanu = cenaUslugePoDanu;
    }

    public double getCenaUslugePoDanu() {
        return cenaUslugePoDanu;
    }

    public void setCenaUslugePoDanu(double cenaUslugePoDanu) {
        this.cenaUslugePoDanu = cenaUslugePoDanu;
    }

    public long getUslugaId() {
        return uslugaId;
    }

    public void setUslugaId(long uslugaId) {
        this.uslugaId = uslugaId;
    }

    public String getNazivUsluge() {
        return nazivUsluge;
    }

    public void setNazivUsluge(String nazivUsluge) {
        this.nazivUsluge = nazivUsluge;
    }

    @Override
    public String getAllQuery() {
        return "SELECT * FROM Usluga";
    }

    @Override
    public PreparedStatement getQueryForInsert(Connection conn) throws SQLException {
        PreparedStatement ps=conn.prepareStatement("Insert into Usluga(cenaUslugePoDanu,nazivUsluge) values(?,?)",Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, cenaUslugePoDanu);
            ps.setString(2, nazivUsluge);
            return ps;
    }

    @Override
    public PreparedStatement getQueryForEdit(Connection conn) throws SQLException {
        PreparedStatement ps=conn.prepareStatement("Update Usluga set cenaUslugePoDanu = (?), nazivUsluge = (?) where uslugaId = (?)");
        ps.setDouble(1, cenaUslugePoDanu);
        ps.setString(2, nazivUsluge);
        ps.setLong(3, uslugaId);
        return ps;
    }

    @Override
    public PreparedStatement getQueryForDelete(Connection conn) throws SQLException {
        PreparedStatement ps=conn.prepareStatement("Delete from Usluga where uslugaId = (?)");
            ps.setLong(1, uslugaId);
        return ps;
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        List<AbstractDomainObject> list = new LinkedList<>();
        while(rs.next()){
            long uslugaId=rs.getInt("uslugaId");
            String nazivUsluge=rs.getString("nazivUsluge");
            double cenaUslugePoDanu=rs.getDouble("cenaUslugePoDanu");
            
            
            Usluga usluga=new Usluga(uslugaId, nazivUsluge, cenaUslugePoDanu);
            
            list.add(usluga);
        }
        rs.close();
        return list;
    }

    @Override
    public void setId(ResultSet rs) throws SQLException {
        rs.next();
        this.uslugaId = rs.getLong(1);
        rs.close();
    } 

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Usluga)
            return ((Usluga)obj).getUslugaId()==this.uslugaId;
        return false;
    }
    
    
}
