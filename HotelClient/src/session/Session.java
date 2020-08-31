/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import domain.Klijent;
import domain.Zaposleni;
import java.net.Socket;

/**
 *
 * @author Boris
 */
public class Session {
    private Zaposleni trenutnoUlogovaniZaposleni;
    private Socket socket;
    private static Session instance;
    private Klijent izabraniKlijent;
    
    public Session() {
    }

    public static Session getInstance() {
        if(instance==null)
            instance=new Session();
        return instance;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Zaposleni getTrenutnoUlogovaniZaposleni() {
        return trenutnoUlogovaniZaposleni;
    }

    public void setTrenutnoUlogovaniZaposleni(Zaposleni trenutnoUlogovaniZaposleni) {
        this.trenutnoUlogovaniZaposleni = trenutnoUlogovaniZaposleni;
    }

    public void setIzabraniKlijent(Klijent k) {
        izabraniKlijent = k;
    }

    public Klijent getIzabraniKlijent() {
        return izabraniKlijent;
    }
    
}
