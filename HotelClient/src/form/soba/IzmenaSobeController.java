/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.soba;

import domain.Soba;
import domain.TipSobe;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Boris
 */
public class IzmenaSobeController {

    private IzmenaSobeForm forma;
    private Soba soba;

    public IzmenaSobeController(IzmenaSobeForm forma, Soba soba) {
        this.forma = forma;
        this.soba = soba;
        setListener();
        try {
            forma.getTxtBrojSobe().setText(soba.getBrojSobe() + "");
            forma.getTxtBrojSobe().setEditable(false);
            forma.getTxtBrojKreveta().setText(soba.getBrojKreveta() + "");
            forma.getTxtSprat().setText(soba.getSprat() + "");
            forma.getTxtSprat().setEditable(false);
            forma.getCmbTipSobe().removeAllItems();
            List<TipSobe> listaTipa = controller.ClientController.getInstance().getAllTipSobe();
            for (TipSobe tipSobe : listaTipa) {
                forma.getCmbTipSobe().addItem(tipSobe);
            }
            forma.getCmbTipSobe().setSelectedItem(soba.getTipSobe());
            forma.getTxtCenaPoDanu().setText(soba.getCenaSobePoDanu() + "");
        } catch (Exception ex) {
            Logger.getLogger(IzmenaSobeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cancel() {
        forma.dispose();
    }

    private void potvrdiIzmenu() {
        Soba s = new Soba();
        try {
            String brojSobe = forma.getTxtBrojSobe().getText().trim();
            int brojSobeInt = Integer.parseInt(brojSobe);
            String sprat = forma.getTxtSprat().getText().trim();
            int spratInt = Integer.parseInt(sprat);
            String brojKreveta = forma.getTxtBrojKreveta().getText().trim();
            int brojKrevetaInt = Integer.parseInt(brojKreveta);
            String cenaPoDanu = forma.getTxtCenaPoDanu().getText().trim();
            double cenaPoDanuDouble = Double.parseDouble(cenaPoDanu);
            TipSobe tipSobe = (TipSobe) forma.getCmbTipSobe().getSelectedItem();
            if (brojSobe.isEmpty() || brojKreveta.isEmpty() || sprat.isEmpty() || cenaPoDanu.isEmpty()) {
                JOptionPane.showMessageDialog(forma, "Niste popunili sva polja.");
                return;
            }
            s = new Soba(soba.getSobaId(), spratInt, brojSobeInt, cenaPoDanuDouble, brojKrevetaInt, tipSobe);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Niste uneli podatke u odgovarajucem formatu!");
            return;
        }
        try {
            controller.ClientController.getInstance().updateSoba(s);
            JOptionPane.showMessageDialog(forma, "Uspesno ste izvrsili izmenu podataka sobe");
            forma.dispose();
        } catch (Exception ex) {
            Logger.getLogger(IzmenaSobeController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(forma, "Greska! Izmena nije izvrsena!");
        }
    }

    private void setListener() {
        forma.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
        forma.getBtnPotvrdi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                potvrdiIzmenu();
            }
        });
    }

}
