/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.soba;

import domain.TipSobe;
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
public class DodajTipSobeController {

    DodajTipSobeForm forma;

    public DodajTipSobeController(DodajTipSobeForm forma) {
        this.forma = forma;
        setListener();
    }

    public void cancel() {
        forma.dispose();
    }

    public void dodaj() {
        try {
            String naziv = forma.getTxtNazivTipaSobe().getText().trim();
            if (naziv.isEmpty()) {
                JOptionPane.showMessageDialog(forma, "Niste uneli naziv.");
                return;
            }
            List<TipSobe> lista = controller.ClientController.getInstance().getAllTipSobe();
            for (TipSobe tipSobe : lista) {
                if (tipSobe.getNazivTipaSobe().equals(naziv)) {
                    JOptionPane.showMessageDialog(forma, "Uneti tip već postoji u bazi.");
                    return;
                }
            }
            TipSobe tip = new TipSobe(-1, naziv);
            controller.ClientController.getInstance().insertTipSobe(tip);
            JOptionPane.showMessageDialog(forma, "Uspešno ste uneli tip sobe.");
        } catch (Exception ex) {
            Logger.getLogger(DodajTipSobeController.class.getName()).log(Level.SEVERE, null, ex);
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
