/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.klijent;

import form.klijent.*;
import domain.Klijent;
import help.Interface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import jdk.nashorn.internal.runtime.regexp.joni.EncodingHelper;

/**
 *
 * @author Boris
 */
public class DodajKlijentaController{

    private DodajKlijentaForm forma;

    public DodajKlijentaController(DodajKlijentaForm forma) {
        this.forma = forma;
        setListener();
    }

    public void cancel() {
        forma.dispose();
    }

    public void dodaj() {
        try {
            String ime = forma.getTxtImeKlijenta().getText();
            String prezime = forma.getTxtPrezimeKlijenta().getText().trim();
            String email = forma.getTxtEmailKlijenta().getText().trim();
            String telefon = forma.getTxtTelefonKlijenta().getText().trim();
            String brPasosa = forma.getTxtBrojPasosaKlijenta().getText().trim();
            String adresa = forma.getTxtAdresaKlijenta().getText().trim();
            if (ime.isEmpty() || prezime.isEmpty() || email.isEmpty() || telefon.isEmpty() || brPasosa.isEmpty() || adresa.isEmpty()) {
                JOptionPane.showMessageDialog(forma, "Niste popunili sva polja.");
                return;
            }
            Klijent k = new Klijent(-1, ime, prezime, email, telefon, brPasosa, adresa);
            List<Klijent> lista = controller.ClientController.getInstance().getAllKlijenti();
            for (Klijent klijent : lista) {
                if (klijent.equals(k)) {
                    JOptionPane.showMessageDialog(forma, "Klijent postoji u bazi.");
                    return;
                }
            }
            controller.ClientController.getInstance().insertKlijent(k);
            JOptionPane.showMessageDialog(forma, "Uspesno ste uneli novog klijenta.");
        } catch (Exception ex) {
            Logger.getLogger(DodajKlijentaForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    private void setListener() {
        forma.getBtnDodajKlijenta().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj();
            }
        });
        forma.getBtnOdustani().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
    }

}
