/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.usluga;

import domain.Usluga;
import help.Interface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Boris
 */
public class DodajUsluguController {

    DodajUsluguForm forma;

    public DodajUsluguController(DodajUsluguForm forma) {
        this.forma = forma;
        setListener();
    }

    public void cancel() {
        forma.dispose();
    }

    public void dodaj() {
        try {
            String naziv = forma.getTxtNazivUsluge().getText().trim();
            String cena = forma.getTxtCenaUslugePoDanu().getText().trim();
            if (naziv.isEmpty() || cena.isEmpty()) {
                JOptionPane.showMessageDialog(forma, "Niste popunili sva polja");
                return;
            }
            double cenaD = Double.parseDouble(cena);
            List<Usluga> list = controller.ClientController.getInstance().getAllUsluge();
            Usluga u = new Usluga(-1, naziv, cenaD);
            for (Usluga usluga : list) {
                if (usluga.getNazivUsluge().equals(naziv)) {
                    JOptionPane.showMessageDialog(forma, "Uneta usluga vec postoji.");
                    return;
                }
            }
            controller.ClientController.getInstance().insertUsluga(u);
            System.out.println("Uneo uslugu");
            JOptionPane.showMessageDialog(forma, "Uspesno ste uneli uslugu");
        } catch (Exception ex) {
            Logger.getLogger(DodajUsluguController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setListener() {
        forma.getBtnDodaj().addActionListener(new ActionListener() {
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
