/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.zaposleni;

import domain.Zaposleni;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Boris
 */
public class IzmeniZaposlenogController {
    private IzmeniZaposlenogForm forma;
    private Zaposleni z;

    public IzmeniZaposlenogController(IzmeniZaposlenogForm forma, Zaposleni z) {
        this.forma = forma;
        this.z = z;
        forma.getTxtIme().setText(z.getImeZaposleni());
        forma.getTxtPrezime().setText(z.getPrezimeZaposleni());
        forma.getTxtEmail().setText(z.getEmailZaposleni());
        forma.getTxtTelefon().setText(z.getTelefonZaposleni());
        forma.getTxtBrojPasosa().setText(z.getBrojPasosaZaposleni());
        forma.getTxtAdresa().setText(z.getAdresaZaposleni());
        forma.getTxtUsername().setText(z.getUsername());
        setListener();
    }

    private void cancel(){
        forma.dispose();
    }
    
    private void potvrdi(){
        String ime = forma.getTxtIme().getText();
        String prezime = forma.getTxtPrezime().getText();
        String email = forma.getTxtEmail().getText().trim();
        String telefon = forma.getTxtTelefon().getText().trim();
        String brojPasosa = forma.getTxtBrojPasosa().getText().trim();
        String adresa = forma.getTxtAdresa().getText();
        String username = forma.getTxtUsername().getText().trim();
        String password = String.copyValueOf(forma.getTxtPassword().getPassword()).trim();
        if(ime.isEmpty() || prezime.isEmpty() || email.isEmpty() || telefon.isEmpty() || brojPasosa.isEmpty()|| adresa.isEmpty() || username.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(forma, "Niste popunili sva polja.");
            return;
        }
        if(!String.copyValueOf(forma.getTxtPassword().getPassword()).equals(String.copyValueOf(forma.getTxtPotvrdaPassworda().getPassword()))){
            JOptionPane.showMessageDialog(forma, "Niste potvrdili šifru. Pokušajte ponovo.");
            return;
        }
        Zaposleni zaposleni = new Zaposleni(z.getZaposleniId(), ime, prezime, email, brojPasosa, telefon, adresa, username, password, true);
        try {
            controller.ClientController.getInstance().updateZaposleni(zaposleni);
            JOptionPane.showMessageDialog(forma, "Uspesno ste izvrsili izmenu podataka zaposlenog");
            forma.dispose();
        } catch (Exception ex) {
            Logger.getLogger(IzmeniZaposlenogController.class.getName()).log(Level.SEVERE, null, ex);
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
                potvrdi();
            }
        });
    }
    
    
}
