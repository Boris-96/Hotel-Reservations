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
public class Zaposleni extends AbstractDomainObject implements Serializable{
    private long zaposleniId;
    private String imeZaposleni;
    private String prezimeZaposleni;
    private String emailZaposleni;
    private String brojPasosaZaposleni;
    private String telefonZaposleni;
    private String adresaZaposleni;
    private String username;
    private String password;
    private boolean radniStatus;

    public Zaposleni() {
    }

    public Zaposleni(long zaposleniId, String imeZaposleni, String prezimeZaposleni, String emailZaposleni, String brojPasosaZaposleni, String telefonZaposleni, String adresaZaposleni, String username, String password, boolean radniStatus) {
        this.zaposleniId = zaposleniId;
        this.imeZaposleni = imeZaposleni;
        this.prezimeZaposleni = prezimeZaposleni;
        this.emailZaposleni = emailZaposleni;
        this.brojPasosaZaposleni = brojPasosaZaposleni;
        this.telefonZaposleni = telefonZaposleni;
        this.adresaZaposleni = adresaZaposleni;
        this.username = username;
        this.password = password;
        this.radniStatus = radniStatus;
    }

    public boolean isRadniStatus() {
        return radniStatus;
    }

    public void setRadniStatus(boolean radniStatus) {
        this.radniStatus = radniStatus;
    }

    public long getZaposleniId() {
        return zaposleniId;
    }

    public void setZaposleniId(long zaposleniId) {
        this.zaposleniId = zaposleniId;
    }

    public String getImeZaposleni() {
        return imeZaposleni;
    }

    public void setImeZaposleni(String imeZaposleni) {
        this.imeZaposleni = imeZaposleni;
    }

    public String getPrezimeZaposleni() {
        return prezimeZaposleni;
    }

    public void setPrezimeZaposleni(String prezimeZaposleni) {
        this.prezimeZaposleni = prezimeZaposleni;
    }

    public String getEmailZaposleni() {
        return emailZaposleni;
    }

    public void setEmailZaposleni(String emailZaposleni) {
        this.emailZaposleni = emailZaposleni;
    }

    public String getBrojPasosaZaposleni() {
        return brojPasosaZaposleni;
    }

    public void setBrojPasosaZaposleni(String brojPasosaZaposleni) {
        this.brojPasosaZaposleni = brojPasosaZaposleni;
    }

    public String getTelefonZaposleni() {
        return telefonZaposleni;
    }

    public void setTelefonZaposleni(String telefonZaposleni) {
        this.telefonZaposleni = telefonZaposleni;
    }

    public String getAdresaZaposleni() {
        return adresaZaposleni;
    }

    public void setAdresaZaposleni(String adresaZaposleni) {
        this.adresaZaposleni = adresaZaposleni;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getAllQuery() {
        return "SELECT * FROM Zaposleni";
    }

    @Override
    public PreparedStatement getQueryForInsert(Connection conn) throws SQLException {
        PreparedStatement ps=conn.prepareStatement("Insert into Zaposleni(imeZaposleni,prezimeZaposleni,emailZaposleni,brojPasosaZaposleni,telefonZaposleni,adresaZaposleni,username,password,radniStatus) values(?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, imeZaposleni);
            ps.setString(2, prezimeZaposleni);
            ps.setString(3, emailZaposleni);
            ps.setString(4, brojPasosaZaposleni);
            ps.setString(5, telefonZaposleni);
            ps.setString(6, adresaZaposleni);
            ps.setString(7, username);
            ps.setString(8, password);
            ps.setBoolean(9, radniStatus);
            return ps;
    }

    @Override
    public PreparedStatement getQueryForEdit(Connection conn) throws SQLException {
        PreparedStatement ps=conn.prepareStatement("Update Zaposleni set imeZaposleni = (?), prezimeZaposleni = (?), emailZaposleni = (?), brojPasosaZaposleni = (?), telefonZaposleni = (?), adresaZaposleni = (?), username = (?), password = (?), radniStatus = (?) where zaposleniId = (?)");
            ps.setString(1, imeZaposleni);
            ps.setString(2, prezimeZaposleni);
            ps.setString(3, emailZaposleni);
            ps.setString(4, brojPasosaZaposleni);
            ps.setString(5, telefonZaposleni);
            ps.setString(6, adresaZaposleni);
            ps.setString(7, username);
            ps.setString(8, password);
            ps.setBoolean(9, radniStatus);
            ps.setLong(10, zaposleniId);
            return ps;
    }

    @Override
    public PreparedStatement getQueryForDelete(Connection conn) throws SQLException {
        PreparedStatement ps=conn.prepareStatement("Delete from Zaposleni where zaposleniId = (?)");
            ps.setLong(1, zaposleniId);
        return ps;
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        List<AbstractDomainObject> list=new LinkedList<>();
        while(rs.next()){
            long zaposleniId=rs.getLong("zaposleniId");
            String imeZaposleni=rs.getString("imeZaposleni");
            String prezimeZaposleni=rs.getString("prezimeZaposleni");
            String emailZaposleni=rs.getString("emailZaposleni");
            String brojPasosaZaposleni=rs.getString("brojPasosaZaposleni");
            String telefonZaposleni=rs.getString("telefonZaposleni");
            String adresaZaposleni=rs.getString("adresaZaposleni");
            String username=rs.getString("username");
            String password=rs.getString("password");
            boolean radniStatus=rs.getBoolean("radniStatus");
            
            Zaposleni zaposleni=new Zaposleni(zaposleniId, imeZaposleni, prezimeZaposleni, emailZaposleni, brojPasosaZaposleni, telefonZaposleni, adresaZaposleni, username, password, radniStatus);
            
            list.add(zaposleni);
        }
        rs.close();
        return list;
    }

    @Override
    public void setId(ResultSet rs) throws SQLException {
        rs.next();
        this.zaposleniId = rs.getLong(1);
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
        final Zaposleni other = (Zaposleni) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return imeZaposleni +" "+ prezimeZaposleni;
    }
    
    
    
}
