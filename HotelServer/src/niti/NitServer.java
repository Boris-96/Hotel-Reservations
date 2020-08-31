/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Boris
 */
public class NitServer extends Thread{
    
    private ServerSocket ss;
    private List<Thread> listaKlijenata;
    
    public NitServer(ServerSocket ss) {
        this.ss = ss;
        listaKlijenata=new ArrayList<>();
        session.Session.getInstance().setListaKlijenta(listaKlijenata);
    }

    @Override
    public void run() {
        while(!isInterrupted()){
            try{
                Socket soket = ss.accept();
                Thread klijent = new NitKlijent(soket);
                klijent.start();
                listaKlijenata.add(klijent);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        for(Thread nit: listaKlijenata){
            nit.interrupt();
        }
        try {
            ss.close();
        } catch (IOException ex) {
            Logger.getLogger(NitServer.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
    
}
