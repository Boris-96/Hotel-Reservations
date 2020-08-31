/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.klijent;

import domain.Klijent;
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
public class IzmeniKlijentaController {

    IzmeniKlijentaForm forma;
    Klijent k;

    public IzmeniKlijentaController(IzmeniKlijentaForm forma, Klijent k) {
        this.forma = forma;
        this.k = k;
        forma.getTxtIme().setText(k.getImeKlijent());
        forma.getTxtPrezime().setText(k.getPrezimeKlijent());
        forma.getTxtEmail().setText(k.getEmailKlijent());
        forma.getTxtAdresa().setText(k.getAdresaKlijent());
        forma.getTxtBrojPasosa().setText(k.getBrojPasosaKlijent());
        forma.getTxtTelefon().setText(k.getTelefonKlijent());

        setListener();
    }

    public void izmeniKlijenta() {
        String ime = forma.getTxtIme().getText();
        String prezime = forma.getTxtPrezime().getText();
        String adresa = forma.getTxtAdresa().getText();
        String brojPasosa = forma.getTxtBrojPasosa().getText();
        String email = forma.getTxtEmail().getText().trim();
        String telefon = forma.getTxtTelefon().getText().trim();
        if (ime.isEmpty() || prezime.isEmpty() || email.isEmpty() || telefon.isEmpty() || brojPasosa.isEmpty() || adresa.isEmpty()) {
            JOptionPane.showMessageDialog(forma, "Niste popunili sva polja.");
            return;
        }
        Klijent klijent = new Klijent(k.getKlijentId(), ime, prezime, email, telefon, brojPasosa, adresa);
        try {

            List<Klijent> lista = controller.ClientController.getInstance().getAllKlijenti();

            for (Klijent kl : lista) {
                if (kl.getKlijentId() != klijent.getKlijentId()) {
                    if (kl.equals(klijent)) {
                        JOptionPane.showMessageDialog(forma, "Klijent sa takvim brojem pasoša već postoji u bazi.");
                        return;
                    }
                }
            }
            controller.ClientController.getInstance().updateKlijent(klijent);
            JOptionPane.showMessageDialog(forma, "Uspesno ste izvrsili izmenu podataka klijenta");
            forma.dispose();
        } catch (Exception ex) {
            Logger.getLogger(IzmeniKlijentaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(forma, "Greska! Izmena nije izvrsena!");
        }
    }

    private void setListener() {
        forma.getBtnPotvrdi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeniKlijenta();
            }
        });
        forma.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                forma.dispose();
            }
        });
    }

}
