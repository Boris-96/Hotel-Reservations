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
import java.util.Objects;

/**
 *
 * @author Boris
 */
public class Klijent extends AbstractDomainObject implements Serializable{
    
    private long klijentId;
    private String imeKlijent;
    private String prezimeKlijent;
    private String emailKlijent;
    private String telefonKlijent;
    private String brojPasosaKlijent;
    private String adresaKlijent;

    public Klijent() {
    }

    public Klijent(long klijentId, String imeKlijent, String prezimeKlijent, String emailKlijent, String telefonKlijent, String brojPasosaKlijent, String adresaKlijent) {
        this.klijentId = klijentId;
        this.imeKlijent = imeKlijent;
        this.prezimeKlijent = prezimeKlijent;
        this.emailKlijent = emailKlijent;
        this.telefonKlijent = telefonKlijent;
        this.brojPasosaKlijent = brojPasosaKlijent;
        this.adresaKlijent = adresaKlijent;
    }

    public long getKlijentId() {
        return klijentId;
    }

    public void setKlijentId(long klijentId) {
        this.klijentId = klijentId;
    }

    public String getImeKlijent() {
        return imeKlijent;
    }

    public void setImeKlijent(String imeKlijent) {
        this.imeKlijent = imeKlijent;
    }

    public String getPrezimeKlijent() {
        return prezimeKlijent;
    }

    public void setPrezimeKlijent(String prezimeKlijent) {
        this.prezimeKlijent = prezimeKlijent;
    }

    public String getEmailKlijent() {
        return emailKlijent;
    }

    public void setEmailKlijent(String emailKlijent) {
        this.emailKlijent = emailKlijent;
    }

    public String getTelefonKlijent() {
        return telefonKlijent;
    }

    public void setTelefonKlijent(String telefonKlijent) {
        this.telefonKlijent = telefonKlijent;
    }

    public String getBrojPasosaKlijent() {
        return brojPasosaKlijent;
    }

    public void setBrojPasosaKlijent(String brojPasosaKlijent) {
        this.brojPasosaKlijent = brojPasosaKlijent;
    }

    public String getAdresaKlijent() {
        return adresaKlijent;
    }

    public void setAdresaKlijent(String adresaKlijent) {
        this.adresaKlijent = adresaKlijent;
    }
  
    @Override
    public String getAllQuery() {
        return "SELECT * FROM Klijent";
    }

    @Override
    public PreparedStatement getQueryForInsert(Connection conn) throws SQLException {
         PreparedStatement ps=conn.prepareStatement("Insert into Klijent(imeKlijent,prezimeKlijent,emailKlijent,brojPasosaKlijent,telefonKlijent,adresaKlijent) values(?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, imeKlijent);
            ps.setString(2, prezimeKlijent);
            ps.setString(3, emailKlijent);
            ps.setString(4, brojPasosaKlijent);
            ps.setString(5, telefonKlijent);
            ps.setString(6, adresaKlijent);
            return ps;
    }

    @Override
    public PreparedStatement getQueryForEdit(Connection conn) throws SQLException {
        PreparedStatement ps=conn.prepareStatement("Update Klijent set imeKlijent = (?), prezimeKlijent = (?), emailKlijent = (?), brojPasosaKlijent = (?), telefonKlijent = (?),adresaKlijent = (?) where klijentId = (?)");
            ps.setString(1, imeKlijent);
            ps.setString(2, prezimeKlijent);
            ps.setString(3, emailKlijent);
            ps.setString(4, brojPasosaKlijent);
            ps.setString(5, telefonKlijent);
            ps.setString(6, adresaKlijent);
            ps.setLong(7, klijentId);
        return ps;
    }

    @Override
    public PreparedStatement getQueryForDelete(Connection conn) throws SQLException {
        PreparedStatement ps=conn.prepareStatement("Delete from Klijent where klijentId = (?)");
            ps.setLong(1, klijentId);
        return ps;
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        List<AbstractDomainObject> list=new LinkedList<>();
        while(rs.next()){
            long klijentId=rs.getLong("klijentId");
            String imeKlijent=rs.getString("imeKlijent");
            String prezimeKlijent=rs.getString("prezimeKlijent");
            String emailKlijent=rs.getString("emailKlijent");
            String brojPasosaKLijent=rs.getString("brojPasosaKlijent");
            String telefonKlijent=rs.getString("telefonKlijent");
            String adresaKlijent=rs.getString("adresaKlijent");
            Klijent k = new Klijent(klijentId, imeKlijent, prezimeKlijent, emailKlijent, telefonKlijent, brojPasosaKLijent, adresaKlijent);
            list.add(k);
        }
        rs.close();
        return list;
    }

    @Override
    public void setId(ResultSet rs) throws SQLException {
        rs.next();
        this.klijentId = rs.getLong(1);
        rs.close();
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Klijent other = (Klijent) obj;
        if (!Objects.equals(this.brojPasosaKlijent, other.brojPasosaKlijent)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return imeKlijent +" "+prezimeKlijent;
    }
    
    
}
