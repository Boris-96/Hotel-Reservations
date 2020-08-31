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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.ModelTabeleUsluga;

/**
 *
 * @author Boris
 */
public class PretragaUslugeController implements Interface {

    PretragaUslugeForm forma;

    public PretragaUslugeController(PretragaUslugeForm forma) {
        this.forma = forma;
        ModelTabeleUsluga mtu = new ModelTabeleUsluga(this);
        forma.getTabela().setModel(mtu);
        Thread nit = new Thread(mtu);
        nit.start();
        setListener();
    }

    private void izmeniUslugu() {
        int i = forma.getTabela().getSelectedRow();
        if (i >= 0) {
            Usluga u = ((ModelTabeleUsluga) forma.getTabela().getModel()).getSelectedUsluga(i);
            IzmenaUslugeForm iuf = new IzmenaUslugeForm(forma, true);
            IzmenaUslugeController iuc = new IzmenaUslugeController(iuf, u);
            iuf.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(forma, "Niste izabrali uslugu za izmenu");
        }
    }

    private void obrisiUslugu() {
        int i = forma.getTabela().getSelectedRow();
        if(i>=0){
            try {
                Usluga u =((ModelTabeleUsluga) forma.getTabela().getModel()).getSelectedUsluga(i);
                controller.ClientController.getInstance().deleteUsluga(u);
            } catch (Exception ex) {
                Logger.getLogger(PretragaUslugeController.class.getName()).log(Level.SEVERE, null, ex);
                endProgram();
            }
        }else{
            JOptionPane.showMessageDialog(forma, "Niste izabrali uslugu za brisanje");    
                    }
    }

    private void odustani() {
        forma.dispose();
    }

    private void filter() {
        ((ModelTabeleUsluga) forma.getTabela().getModel()).setParametar(forma.getTxtNazivUsluge().getText().trim());
    }

    @Override
    public void endProgram() {
        JOptionPane.showMessageDialog(forma, "Server je prekinuo komunikaciju");
        System.exit(0);
    }

    private void setListener() {
        forma.getBtnEdit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeniUslugu();
            }
        });
        forma.getBtnDelete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisiUslugu();
            }
        });
        forma.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                odustani();
            }
        });
        forma.getTxtNazivUsluge().addKeyListener(new KeyListener() {
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
    }

}
