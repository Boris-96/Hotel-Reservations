/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import aso.AbstractSO;
import domain.Klijent;
import domain.Rezervacija;
import domain.Soba;
import domain.TipSobe;
import domain.Usluga;
import domain.Zaposleni;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import javafx.scene.paint.Color;
import so.klijent.SOZapamtiKlijenta;
import so.klijent.SOIzmeniKlijenta;
import so.klijent.SOUcitajKlijente;
import so.rezervacija.SOZapamtiRezervaciju;
import so.rezervacija.SOОbrisiRezervaciju;
import so.rezervacija.SOIzmeniRezevaciju;
import so.rezervacija.SOUcitajiRezervacije;
import so.soba.SOZapamtiSobu;
import so.soba.SOОbrisiSobu;
import so.soba.SOIzmeniSobu;
import so.soba.SOUcitajSobe;
import so.tipSobe.SOZapamtiTipSobe;
import so.tipSobe.SOОbrisiTipSobe;
import so.tipSobe.SOIzmeniTipSobe;
import so.tipSobe.SOUcitajTipoveSobe;
import so.usluga.SOZapamtiUslugu;
import so.usluga.SOОbrisiUslugu;
import so.usluga.SOIzmeniUslugu;
import so.usluga.SOUcitajUsluge;
import so.zaposleni.SOZapamtiZaposlenog;
import so.zaposleni.SOОbrisiZaposlenog;
import so.zaposleni.SOIzmeniZaposlenog;
import so.zaposleni.SOUcitajZaposlene;


/**
 *
 * @author Boris
 */
public class ServerController {
    private static ServerController instance;

    private ServerController() {
    }

    public static ServerController getInstance() {
        if(instance==null)
            instance=new ServerController();
        return instance;
    }
    
    public synchronized Zaposleni login(Zaposleni zaposleni)throws Exception{
        SOUcitajZaposlene so = new SOUcitajZaposlene();
        so.templateExecute(new Zaposleni());
        List<Zaposleni> list;
        list = so.getList();
        for (Zaposleni z : list) {
            if(z.getUsername().equals(zaposleni.getUsername()) && z.getPassword().equals(zaposleni.getPassword()))
                return z;
        }
        throw new Exception("Ne postoji takav korisnik");
    }
    
    public synchronized List<Klijent> getAllKlijenti()throws Exception{
        AbstractSO so=new SOUcitajKlijente();
        so.templateExecute(new Klijent());
        return ((SOUcitajKlijente)so).getList();
    }
    
    public synchronized List<Zaposleni> getAllZaposleni()throws Exception{
        AbstractSO so=new SOUcitajZaposlene();
        so.templateExecute(new Zaposleni());
        return ((SOUcitajZaposlene)so).getList();
    }
    
    public synchronized List<Soba> getAllSobe()throws Exception{
        AbstractSO so=new SOUcitajSobe();
        so.templateExecute(new Soba());
        return ((SOUcitajSobe)so).getList();
    }
    
    public synchronized List<TipSobe> getAllTipSobe()throws Exception{
        AbstractSO so=new SOUcitajTipoveSobe();
        so.templateExecute(new TipSobe());
        return ((SOUcitajTipoveSobe)so).getList();
    }
    
    public synchronized List<Usluga> getAllUsluge()throws Exception{
        AbstractSO so=new SOUcitajUsluge();
        so.templateExecute(new Usluga());
        return ((SOUcitajUsluge)so).getList();
    }
    
    public synchronized List<Rezervacija> getAllRezerrvacije()throws Exception{
        AbstractSO so=new SOUcitajiRezervacije();
        so.templateExecute(new Rezervacija());
        return ((SOUcitajiRezervacije)so).getList();
    }
    
    public synchronized void insertKlijent(Klijent klijent) throws Exception{
        AbstractSO so=new SOZapamtiKlijenta();
        so.templateExecute(klijent);
    }
    
    public synchronized void insertZaposleni(Zaposleni zaposleni) throws Exception{
        AbstractSO so=new SOZapamtiZaposlenog();
        so.templateExecute(zaposleni);
    }
    
    public synchronized void insertSoba(Soba soba) throws Exception{
        AbstractSO so=new SOZapamtiSobu();
        so.templateExecute(soba);
    }
    
    public synchronized void insertTipSobe(TipSobe tipSobe) throws Exception{
        AbstractSO so=new SOZapamtiTipSobe();
        so.templateExecute(tipSobe);
    }
    
    public synchronized void insertUsluga(Usluga usluga) throws Exception{
        AbstractSO so=new SOZapamtiUslugu();
        so.templateExecute(usluga);
    }
    
    public synchronized void insertRezervacija(Rezervacija rezervacija) throws Exception{
        AbstractSO so=new SOZapamtiRezervaciju();
        so.templateExecute(rezervacija);
    }
    
    public synchronized void updateKlijent(Klijent klijent) throws Exception{
        AbstractSO so=new SOIzmeniKlijenta();
        so.templateExecute(klijent);
    }
    
    public synchronized void updateZaposleni(Zaposleni zaposleni) throws Exception{
        AbstractSO so=new SOIzmeniZaposlenog();
        so.templateExecute(zaposleni);
    }
    
    public synchronized void updateSoba(Soba soba) throws Exception{
        AbstractSO so=new SOIzmeniSobu();
        so.templateExecute(soba);
    }
    
    public synchronized void updateTipSobe(TipSobe tipSobe) throws Exception{
        AbstractSO so=new SOIzmeniTipSobe();
        so.templateExecute(tipSobe);
    }
    
    public synchronized void updateUsluga(Usluga usluga) throws Exception{
        AbstractSO so=new SOIzmeniUslugu();
        so.templateExecute(usluga);
    }
    
    public synchronized void updateRezervacija(Rezervacija rezervacija) throws Exception{
        AbstractSO so=new SOIzmeniRezevaciju();
        so.templateExecute(rezervacija);
    }
    
     public synchronized void deleteZaposleni(Zaposleni zaposleni) throws Exception{
        AbstractSO so=new SOОbrisiZaposlenog();
        so.templateExecute(zaposleni);
    }
    
      public synchronized void deleteSoba(Soba soba) throws Exception{
        AbstractSO so=new SOОbrisiSobu();
        so.templateExecute(soba);
    }
    
    public synchronized void deleteTipSobe(TipSobe tipSobe) throws Exception{
        AbstractSO so=new SOОbrisiTipSobe();
        so.templateExecute(tipSobe);
    }
    
     public synchronized void deleteUsluga(Usluga usluga) throws Exception{
        AbstractSO so=new SOОbrisiUslugu();
        so.templateExecute(usluga);
    }
     
     public synchronized void deleteRezervacija(Rezervacija rezervacija) throws Exception{
        AbstractSO so=new SOОbrisiRezervaciju();
        so.templateExecute(rezervacija);
    } 
}
