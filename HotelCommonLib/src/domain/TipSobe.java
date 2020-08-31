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
public class TipSobe extends AbstractDomainObject implements Serializable{

    private long tipSobaId;
    private String nazivTipaSobe;

    public TipSobe() {
    }

    public TipSobe(long tipSobaId, String nazivTipaSobe) {
        this.tipSobaId = tipSobaId;
        this.nazivTipaSobe = nazivTipaSobe;
    }

    public String getNazivTipaSobe() {
        return nazivTipaSobe;
    }

    public long getTipSobaId() {
        return tipSobaId;
    }

    public void setNazivTipaSobe(String nazivTipaSobe) {
        this.nazivTipaSobe = nazivTipaSobe;
    }

    public void setTipSobaId(long tipSobaId) {
        this.tipSobaId = tipSobaId;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TipSobe)
            return ((TipSobe)obj).getTipSobaId()==this.tipSobaId;
        return false;
    }

    @Override
    public String toString() {
        return nazivTipaSobe;
    }
   
    
    
    
    @Override
    public String getAllQuery(){
        return "SELECT * FROM TipSobe";
    }

    @Override
    public PreparedStatement getQueryForInsert(Connection conn) throws SQLException {
        PreparedStatement ps=conn.prepareStatement("Insert into tipsobe(nazivTipaSobe) values(?)",Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, nazivTipaSobe);
        return ps;
    }

    @Override
    public PreparedStatement getQueryForEdit(Connection conn) throws SQLException {
        PreparedStatement ps=conn.prepareStatement("Update tipSobe set nazivTipaSobe = (?) where tipSobeId = (?)");
        ps.setString(1, nazivTipaSobe);
        ps.setLong(2, tipSobaId);
        return ps;
    }

    @Override
    public PreparedStatement getQueryForDelete(Connection conn) throws SQLException {
        PreparedStatement ps=conn.prepareStatement("Delete from tipsobe where tipSobeId = (?)");
        ps.setLong(1, tipSobaId);
        return ps;
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        List<AbstractDomainObject> list=new LinkedList<>();
        while(rs.next()){
            long tipSobeId=rs.getLong("tipSobeId");
            String nazivTipaSobe=rs.getString("nazivTipaSobe");
            TipSobe tip=new TipSobe(tipSobeId, nazivTipaSobe);
            list.add(tip);
        }
        rs.close();
        return list;
    }

    @Override
    public void setId(ResultSet rs) throws SQLException {
        rs.next();
        this.tipSobaId = rs.getLong(1);
        rs.close();
    }
    
}
