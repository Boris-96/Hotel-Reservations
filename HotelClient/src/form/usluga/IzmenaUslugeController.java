/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.usluga;

import domain.Usluga;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Boris
 */
public class IzmenaUslugeController {
    IzmenaUslugeForm forma;
    Usluga usluga;
    
    public IzmenaUslugeController(IzmenaUslugeForm forma, Usluga usluga) {
        this.forma = forma;
        this.usluga = usluga;
        forma.getTxtNazivUsluge().setText(usluga.getNazivUsluge());
        forma.getTxtNazivUsluge().setEditable(false);
        forma.getTxtCenaUsluge().setText(usluga.getCenaUslugePoDanu()+"");
        setListener();
    }

    private void odustani(){
        forma.dispose();
    }
    
    private void potvrdiIzmenu(){
        String naziv = forma.getTxtNazivUsluge().getText();
        String cena = forma.getTxtCenaUsluge().getText().trim();
        Usluga u = new Usluga();
        if(cena.isEmpty()){
            JOptionPane.showMessageDialog(forma, "Niste uneli cenu usluge po danu!");
            return;
        }
        try{
            double cenaD = Double.parseDouble(cena);
            u = new Usluga(usluga.getUslugaId(), naziv, cenaD);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(forma, "Niste uneli cenu u odgovarajućem formatu");
            return;
        }
        try {
            controller.ClientController.getInstance().updateUsluga(u);
        } catch (Exception ex) {
            Logger.getLogger(IzmenaUslugeController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(forma, "Greška! Usluga sa tim nazivom više ne postoji!");
        }
    }
    
    private void setListener() {
        forma.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                odustani();
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
