/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.zaposleni;

import model.ModelTabeleZaposleni;
import domain.Zaposleni;
import help.Interface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Boris
 */
public class PretragaZaposlenogController implements Interface{
    PretragaZaposlenogForm forma;

    public PretragaZaposlenogController(PretragaZaposlenogForm forma) {
        this.forma = forma;
        ModelTabeleZaposleni mtz = new ModelTabeleZaposleni(this);
        Thread nit = new Thread(mtz);
        nit.start();
        forma.getTabela().setModel(mtz);
        setListener();
    }

    private void odustani(){
        forma.dispose();
    }
    
    private void izmeni(){
        int i = forma.getTabela().getSelectedRow();
        if (i >= 0) {
            Zaposleni z = ((ModelTabeleZaposleni) forma.getTabela().getModel()).getSelectedZaposleni(i);
            IzmeniZaposlenogForm izf = new IzmeniZaposlenogForm(forma, true);
            IzmeniZaposlenogController izc = new IzmeniZaposlenogController(izf,z);
            izf.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(forma, "Niste izabrali zaposlenog za izmenu");
        }
    }
    
    private void filter(){
        ((ModelTabeleZaposleni) forma.getTabela().getModel()).setParametar(forma.getTxtPretraga().getText().trim());
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
                odustani();
            }
        });
        forma.getBtnEdit().addActionListener(new ActionListener() {
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
    }

    
    
    
}
