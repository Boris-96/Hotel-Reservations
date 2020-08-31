/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.zaposleni;

import domain.Zaposleni;
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
public class DodajZaposlenogController {

    private DodajZaposlenogForm forma;

    public DodajZaposlenogController(DodajZaposlenogForm forma) {
        this.forma = forma;
        setListener();
    }

    public void dodaj() {
        try {
            String ime = forma.getTxtImeZaposlenog().getText().trim();
            String prezime = forma.getTxtPrezimeZaposlenog().getText().trim();
            String email = forma.getTxtEmailZaposlenog().getText().trim();
            String telefon = forma.getTxtTelefonZaposlenog().getText().trim();
            String brPasosa = forma.getTxtBrojPasosaZaposleni().getText().trim();
            String adresa = forma.getTxtAdresaZaposleni().getText().trim();
            String user = forma.getTxtUsername().getText().trim();
            String password = new String(forma.getTxtPassword().getPassword()).trim();
            String passPotvrda = new String(forma.getTxtPasswordPotrvrda().getPassword()).trim();
            if (ime.isEmpty() || prezime.isEmpty() || email.isEmpty() || telefon.isEmpty() || brPasosa.isEmpty() || adresa.isEmpty() || user.isEmpty() || password.isEmpty() || passPotvrda.isEmpty()) {
                JOptionPane.showMessageDialog(forma, "Niste popunili sva polja");
                return;
            }
            if (!password.equals(passPotvrda)) {
                JOptionPane.showMessageDialog(forma, "Niste lepo uneli sifru");
                return;
            }

            Zaposleni z = new Zaposleni(-1, ime, prezime, email, brPasosa, telefon, adresa, user, password, true);
            List<Zaposleni> lista = controller.ClientController.getInstance().getAllZaposleni();
            for (Zaposleni zaposleni : lista) {
                if (zaposleni.equals(z)) {
                    JOptionPane.showMessageDialog(forma, "Korisničko ime je zauzeto. Molimo izaberite drugo korisničko ime");
                    return;
                }
            }
            controller.ClientController.getInstance().insertZaposleni(z);
            JOptionPane.showMessageDialog(forma, "Uspesno ste uneli novog zaposlenog");
        } catch (Exception ex) {
            Logger.getLogger(DodajZaposlenogForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cancel() {
        forma.dispose();
    }

    private void setListener() {
        forma.getBtnDodajZaposlenog().addActionListener(new ActionListener() {
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
