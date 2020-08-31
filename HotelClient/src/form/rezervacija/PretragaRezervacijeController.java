/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.rezervacija;

import domain.Rezervacija;
import help.Interface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.ModelTabeleRezervacije;

/**
 *
 * @author Boris
 */
public class PretragaRezervacijeController implements Interface{
    PretragaRezervacijeForm forma;

    public PretragaRezervacijeController(PretragaRezervacijeForm forma) {
        this.forma = forma;
        ModelTabeleRezervacije mtr = new ModelTabeleRezervacije(this);
        forma.getTabela().setModel(mtr);
        Thread nit = new Thread(mtr);
        nit.start();
        setListener();
    }

    private void cancel(){
        forma.dispose();
    }
    
    private void izmeni(){
        int i = forma.getTabela().getSelectedRow();
        if (i >= 0) {
            Rezervacija r = ((ModelTabeleRezervacije) forma.getTabela().getModel()).getSelectedRezervacija(i);
            IzmenaRezervacijeForm irf = new IzmenaRezervacijeForm(forma, true);
            IzmenaRezervacijeController irc = new IzmenaRezervacijeController(irf, r);
            irf.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(forma, "Niste izabrali rezervaciju za izmenu");
        }
    }
    
    private void filter(){
        ((ModelTabeleRezervacije) forma.getTabela().getModel()).setParametar(forma.getTxtPretraga().getText().trim());
    }
    
    private void delete(){
        int i = forma.getTabela().getSelectedRow();
        if(i>=0){
            try {
                Rezervacija r = ((ModelTabeleRezervacije)forma.getTabela().getModel()).getSelectedRezervacija(i);
                if(r.getDatumOd().before(new Date()) && r.getDatumDo().after(new Date())){
                    JOptionPane.showMessageDialog(forma, "Klijent koristi rezervaciju. Nije moguće obrisati izabranu rezervaciju.");
                    return;
                }
                controller.ClientController.getInstance().deleteRezervacija(r);
                JOptionPane.showMessageDialog(forma, "Uspesno ste obrisali rezervaciju.");
            } catch (Exception ex) {
                Logger.getLogger(PretragaRezervacijeController.class.getName()).log(Level.SEVERE, null, ex);
                endProgram();
            }
        }else{
            JOptionPane.showMessageDialog(forma, "Niste izabrali rezervaciju za brisanje");
        }
    }
    
    @Override
    public void endProgram() {
        JOptionPane.showMessageDialog(forma, "Server je prekinuo komunikaciju");
        System.exit(0);
    }

    private void setListener() {
        forma.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
        forma.getBtnIzmeni().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni();
            }
        });
        forma.getTxtPretraga().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                filter();
            }
        });
        forma.getBtnDelete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
            }
        });
    }
}
