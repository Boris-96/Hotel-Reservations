/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.soba;

import domain.Soba;
import domain.TipSobe;
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
public class DodajSobuController{
    DodajSobuForm forma;

    public DodajSobuController(DodajSobuForm forma) {
        this.forma = forma;
        srediCombo();
        setListener();
    }

    public void cancel(){
        forma.dispose();
    }
    
    public void dodaj(){
        try {
            String spratt = forma.getTxtSprat().getText().trim();
            String brSobee = forma.getTxtBrojSobe().getText().trim();
            String cenaa = forma.getTxtCenaPoDanu().getText().trim();
            String brKrevetaa = forma.getTxtBrojKreveta().getText().trim();
            
            if(spratt.isEmpty() || brSobee.isEmpty() || cenaa.isEmpty() || brKrevetaa.isEmpty()){
                JOptionPane.showMessageDialog(forma, "Niste uneli sva polja.");
                return;
            }
            
            int sprat = Integer.parseInt(spratt);
            int brSobe = Integer.parseInt(brSobee);
            double cena = Double.parseDouble(cenaa);
            int brKreveta = Integer.parseInt(brKrevetaa);
            if(brKreveta > 6){
                JOptionPane.showMessageDialog(forma, "Soba ne može imati više od 6 kreveta");
                return;
            }
            TipSobe tip = (TipSobe) forma.getCmbTipSobe().getSelectedItem();
            List<Soba> lista = controller.ClientController.getInstance().getAllSobe();
            Soba s = new Soba(-1, sprat, brSobe, cena, brKreveta, tip);
            for (Soba soba : lista) {
                if(soba.getBrojSobe() == s.getBrojSobe() && soba.getSprat() == s.getSprat()){
                    JOptionPane.showMessageDialog(forma, "Soba vec postoji u bazi.");
                    return;
                }
            }
            controller.ClientController.getInstance().insertSoba(s);
            JOptionPane.showMessageDialog(forma, "Uspesno ste uneli sobu");
        } catch (Exception ex) {
            Logger.getLogger(DodajSobuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   }
    
    void srediCombo() {
        try {
            forma.getCmbTipSobe().removeAllItems();
            List<TipSobe> lista = controller.ClientController.getInstance().getAllTipSobe();
            for (TipSobe tipSobe : lista) {
                forma.getCmbTipSobe().addItem(tipSobe);
            }
        } catch (Exception ex){ 
            Logger.getLogger(DodajSobuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setListener() {
        forma.getBtnDodajSobu().addActionListener(new ActionListener() {
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
