/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nitSat;

import form.rezervacija.KreiranjeRezervacijeForm;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Boris
 */
public class Sat extends Thread {

    private KreiranjeRezervacijeForm forma;

    public Sat(KreiranjeRezervacijeForm forma) {
        this.forma = forma;
    }

    @Override
    public void run() {
        try {
            while (true) {
                forma.getLblSat().setText(String.valueOf(new Date()));
                sleep(1000);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Sat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
