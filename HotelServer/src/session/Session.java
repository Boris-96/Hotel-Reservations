/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.List;
import niti.NitKlijent;
import niti.NitServer;

/**
 *
 * @author Boris
 */
public class Session {
    private static Session instance;
    private Thread ns;
    private List<Thread> listaKlijenta;

    private Session() {
    }

    public static Session getInstance() {
        if(instance==null)
            instance=new Session();
        return instance;
    }

    public List<Thread> getListaKlijenta() {
        return listaKlijenta;
    }

    public void setListaKlijenta(List<Thread> listaKlijenta) {
        this.listaKlijenta = listaKlijenta;
    }

    public Thread getNs() {
        return ns;
    }

    public void setNs(Thread ns) {
        this.ns = ns;
    }

    

    
    
    
}
