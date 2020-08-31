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
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Boris
 */
public class Rezervacija extends AbstractDomainObject implements Serializable{
    private long rezervacijaId;
    private Date datumOd;
    private Date datumDo;
    private Date datumRezervacije;
    private double ukupnaCena;
    private Klijent klijent;
    private Zaposleni zaposleni;
    private List<RezervacijaUsluge> usluge;
    private List<Soba> sobe;

    public Rezervacija() {
        usluge=new ArrayList<>();
        sobe=new ArrayList<>();
    }

    public Rezervacija(long rezervacijaId, Date datumOd, Date datumDo, Date datumRezervacije, double ukupnaCena, Klijent klijent, Zaposleni zaposleni) {
        usluge=new ArrayList<>();
        sobe=new ArrayList<>();
        this.rezervacijaId = rezervacijaId;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.datumRezervacije = datumRezervacije;
        this.ukupnaCena = ukupnaCena;
        this.klijent = klijent;
        this.zaposleni = zaposleni;
    }

    public List<Soba> getSobe() {
        return sobe;
    }

    public void setSobe(List<Soba> sobe) {
        this.sobe = sobe;
    }

    public List<RezervacijaUsluge> getUsluge() {
        return usluge;
    }

    public void setUsluge(List<RezervacijaUsluge> usluge) {
        this.usluge = usluge;
    }

    public void addSoba(Soba soba){
        sobe.add(soba);
    }
    
    public void addUsluga(RezervacijaUsluge usluga){
        usluge.add(usluga);
    }
    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public long getRezervacijaId() {
        return rezervacijaId;
    }

    public void setRezervacijaId(long rezervacijaId) {
        this.rezervacijaId = rezervacijaId;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public Date getDatumRezervacije() {
        return datumRezervacije;
    }

    public void setDatumRezervacije(Date datumRezervacije) {
        this.datumRezervacije = datumRezervacije;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    @Override
    public String getAllQuery() {
        return "SELECT * FROM rezervacija INNER JOIN zaposleni ON rezervacija.zaposleniId=zaposleni.zaposleniId INNER JOIN klijent ON rezervacija.klijentId=klijent.klijentId INNER JOIN rezervacijausluge ON rezervacija.rezervacijaId=rezervacijausluge.rezervacijaId INNER JOIN usluga ON usluga.uslugaId=rezervacijausluge.uslugaId INNER JOIN rezervacijasobe ON rezervacija.rezervacijaId=rezervacijasobe.rezervacijaId INNER JOIN soba ON rezervacijasobe.sobaId=soba.sobaId INNER JOIN tipsobe ON tipsobe.tipSobeId=soba.tipSobeId";
    }

    @Override
    public PreparedStatement getQueryForInsert(Connection conn) throws SQLException {
        PreparedStatement ps=conn.prepareStatement("Insert into Rezervacija(datumOd,datumDo,datumRezervacije,ukupnaCena,klijentId,zaposleniId) values(?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1,new java.sql.Date(datumOd.getTime()));
            ps.setDate(2, new java.sql.Date(datumDo.getTime()));
            ps.setDate(3, new java.sql.Date(datumRezervacije.getTime()));
            ps.setDouble(4, ukupnaCena);
            ps.setLong(5, klijent.getKlijentId());
            ps.setLong(6, zaposleni.getZaposleniId());
            return ps;
    }

    @Override
    public PreparedStatement getQueryForEdit(Connection conn) throws SQLException {
        PreparedStatement ps=conn.prepareStatement("Update Rezervacija set datumOd = (?), datumDo = (?), datumRezervacije = (?), ukupnaCena = (?), klijentId = (?), zaposleniId = (?) where rezervacijaId = (?)");
            ps.setDate(1,new java.sql.Date(datumOd.getTime()));
            ps.setDate(2, new java.sql.Date(datumDo.getTime()));
            ps.setDate(3, new java.sql.Date(datumRezervacije.getTime()));
            ps.setDouble(4, ukupnaCena);
            ps.setLong(5, klijent.getKlijentId());
            ps.setLong(6, zaposleni.getZaposleniId());
            ps.setLong(7, rezervacijaId);
            return ps;
    }

    @Override
    public PreparedStatement getQueryForDelete(Connection conn) throws SQLException {
        PreparedStatement ps=conn.prepareStatement("Delete from Rezervacija where rezervacijaId = (?)");
            ps.setLong(1, rezervacijaId);
        return ps;
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        List<AbstractDomainObject> list=new LinkedList<>();
        while(rs.next()){
            long rezervacijaId=rs.getLong("rezervacijaId");
            Date datumOd=new java.util.Date(rs.getDate("datumOd").getTime());
            Date datumDo=new java.util.Date(rs.getDate("datumDo").getTime());
            Date datumRezervacije=rs.getDate("datumRezervacije");
            double ukupnaCena=rs.getDouble("ukupnaCena");
            
            long klijentId=rs.getLong("klijentId");
            String imeKlijent=rs.getString("imeKlijent");
            String prezimeKlijent=rs.getString("prezimeKlijent");
            String emailKlijent=rs.getString("emailKlijent");
            String brojPasosaKLijent=rs.getString("brojPasosaKlijent");
            String telefonKlijent=rs.getString("telefonKlijent");
            String adresaKlijent=rs.getString("adresaKlijent");
            Klijent k = new Klijent(klijentId, imeKlijent, prezimeKlijent, emailKlijent, telefonKlijent, brojPasosaKLijent, adresaKlijent);
            
            long zaposleniId = rs.getLong("zaposleniId");
            String imeZaposleni=rs.getString("imeZaposleni");
            String prezimeZaposleni=rs.getString("prezimeZaposleni");
            String emailZaposleni=rs.getString("emailZaposleni");
            String brojPasosaZaposleni=rs.getString("brojPasosaZaposleni");
            String telefonZaposleni=rs.getString("telefonZaposleni");
            String adresaZaposleni=rs.getString("adresaZaposleni");
            String username=rs.getString("username");
            String password=rs.getString("password");
            boolean radniStatus=rs.getBoolean("radniStatus");
            Zaposleni z = new Zaposleni(zaposleniId, imeZaposleni, prezimeZaposleni, emailZaposleni, brojPasosaZaposleni, telefonZaposleni, adresaZaposleni, username, password, radniStatus);
            
            long uslugaId=rs.getLong("uslugaId");
            double cenaPoDanu=rs.getDouble("cenaUslugePoDanu");
            String nazivUsluge=rs.getString("nazivUsluge");
            Usluga u=new Usluga(uslugaId, nazivUsluge, cenaPoDanu);
            int brojDanaUsluge = rs.getInt("brojDanaUsluge");
            
            
            
            long tipSobeId=rs.getLong("tipSobeId");
            String nazivTipaSobe=rs.getString("nazivTipaSobe");
            TipSobe tip=new TipSobe(tipSobeId, nazivTipaSobe);
            
            long sobaId=rs.getLong("sobaId");
            int sprat=rs.getInt("sprat");
            int brojSobe=rs.getInt("brojSobe");
            double cenaSobePoDanu=rs.getDouble("cenaSobePoDanu");
            int brojKreveta=rs.getInt("brojKreveta");
            Soba s=new Soba(sobaId, sprat, brojSobe, cenaSobePoDanu, brojKreveta, tip);
            
            Rezervacija r = new Rezervacija(rezervacijaId, datumOd, datumDo, datumRezervacije, ukupnaCena, k, z);
            RezervacijaUsluge ru = new RezervacijaUsluge(r, u, brojDanaUsluge);
            if(list.contains(r)){
                Rezervacija rez=(Rezervacija)list.get(list.indexOf(r));
                if(!rez.getUsluge().contains(ru)){
                    ru.setRezervacija(rez);
                    rez.addUsluga(ru);
                }
                if(!rez.getSobe().contains(s))
                    rez.addSoba(s);
            }else{
                list.add(r);
                r.addSoba(s);
                r.addUsluga(ru);
            }
            
        }
        rs.close();
        return list;
    }

    @Override
    public void setId(ResultSet rs) throws SQLException {
        rs.next();
        this.rezervacijaId = rs.getLong(1);
        rs.close();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Rezervacija)
            return ((Rezervacija)obj).getRezervacijaId()==this.rezervacijaId;
        return false;
    }
    
    
}
