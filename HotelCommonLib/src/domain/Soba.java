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
public class Soba extends AbstractDomainObject implements Serializable{
    private long sobaId;
    private int sprat;
    private int brojSobe;
    private double cenaSobePoDanu;
    private int brojKreveta;
    private TipSobe tipSobe;

    public Soba() {
    }

    public Soba(long sobaId, int sprat, int brojSobe, double cenaPoDanu, int brojKreveta, TipSobe tipSobe) {
        this.sobaId = sobaId;
        this.sprat = sprat;
        this.brojSobe = brojSobe;
        this.cenaSobePoDanu = cenaPoDanu;
        this.brojKreveta = brojKreveta;
        this.tipSobe = tipSobe;
    }

    public long getSobaId() {
        return sobaId;
    }

    public void setSobaId(long sobaId) {
        this.sobaId = sobaId;
    }

    public int getSprat() {
        return sprat;
    }

    public void setSprat(int sprat) {
        this.sprat = sprat;
    }

    public int getBrojSobe() {
        return brojSobe;
    }

    public void setBrojSobe(int brojSobe) {
        this.brojSobe = brojSobe;
    }

    public double getCenaSobePoDanu() {
        return cenaSobePoDanu;
    }

    public void setCenaSobePoDanu(double cenaSobePoDanu) {
        this.cenaSobePoDanu = cenaSobePoDanu;
    }

    public int getBrojKreveta() {
        return brojKreveta;
    }

    public void setBrojKreveta(int brojKreveta) {
        this.brojKreveta = brojKreveta;
    }

    public TipSobe getTipSobe() {
        return tipSobe;
    }

    public void setTipSobe(TipSobe tipSobe) {
        this.tipSobe = tipSobe;
    }

    @Override
    public String getAllQuery() {
        return "SELECT * FROM Soba inner join tipSobe on soba.tipSobeId = tipSobe.tipSobeId ORDER BY sprat, brojSobe";
    }

    @Override
    public PreparedStatement getQueryForInsert(Connection conn) throws SQLException {
        PreparedStatement ps=conn.prepareStatement("Insert into Soba(sprat,brojSobe,cenaSobePoDanu,brojKreveta,tipSobeId) values(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, sprat);
            ps.setInt(2, brojSobe);
            ps.setDouble(3, cenaSobePoDanu);
            ps.setInt(4, brojKreveta);
            ps.setLong(5, tipSobe.getTipSobaId());
            return ps;
    }

    @Override
    public PreparedStatement getQueryForEdit(Connection conn) throws SQLException {
        PreparedStatement ps=conn.prepareStatement("Update Soba set sprat = (?), brojSobe = (?), cenaSobePoDanu = (?), brojKreveta = (?), tipSobeId = (?) where sobaId = (?)");
            ps.setInt(1, sprat);
            ps.setInt(2, brojSobe);
            ps.setDouble(3, cenaSobePoDanu);
            ps.setInt(4, brojKreveta);
            ps.setLong(5, tipSobe.getTipSobaId());
            ps.setLong(6, sobaId);
            return ps;
    }

    @Override
    public PreparedStatement getQueryForDelete(Connection conn) throws SQLException {
        PreparedStatement ps=conn.prepareStatement("Delete from Soba where sobaId = (?)");
            ps.setLong(1, sobaId);
        return ps;
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        List<AbstractDomainObject> list=new LinkedList<>();
        while(rs.next()){
            long sobaId=rs.getLong("SobaId");
            int sprat=rs.getInt("sprat");
            int brojSobe=rs.getInt("brojSobe");
            double cenaSobePoDanu=rs.getDouble("cenaSobePoDanu");
            int brojKreveta=rs.getInt("brojKreveta");
            
            long tipSobeId=rs.getLong("tipSobeId");
            String nazivTipaSobe=rs.getString("nazivTipaSobe");
            TipSobe tip=new TipSobe(tipSobeId, nazivTipaSobe);
            
            Soba soba=new Soba(sobaId, sprat, brojSobe, cenaSobePoDanu, brojKreveta, tip);
            
            list.add(soba);
        }
        rs.close();
        return list;
    }

    @Override
    public void setId(ResultSet rs) throws SQLException {
        rs.next();
        this.sobaId = rs.getLong(1);
        rs.close();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Soba)
            return ((Soba)obj).getSobaId()==this.sobaId;
        return false;
    }
    
    
    
}
