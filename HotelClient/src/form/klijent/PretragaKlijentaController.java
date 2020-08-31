/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.klijent;

import help.KlijentEnum;
import model.ModelTabeleKlijenti;
import domain.Klijent;
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
public class PretragaKlijentaController implements Interface{

    private PretragaKlijentaForm forma;

    public PretragaKlijentaController(PretragaKlijentaForm forma, KlijentEnum en) {
        this.forma = forma;
        if (en.equals(KlijentEnum.view)) {
            forma.getBtnIzaberi().setVisible(false);
        } else if (en.equals(KlijentEnum.select)) {
            forma.getBtnIzmeni().setVisible(false);
        }
        ModelTabeleKlijenti mtk = new ModelTabeleKlijenti(this);
        Thread nitModela = new Thread(mtk);
        nitModela.start();
        forma.getTabela().setModel(mtk);
        setListener();
    }

    private void filter() {
        ((ModelTabeleKlijenti) forma.getTabela().getModel()).setParametar(forma.getTxtPretraga().getText().trim());
    }

    private void izaberiKlijenta() {
        int i = forma.getTabela().getSelectedRow();
        if (i >= 0) {
            Klijent k = ((ModelTabeleKlijenti) forma.getTabela().getModel()).getSelectedKlijent(i);
            session.Session.getInstance().setIzabraniKlijent(k);
            forma.dispose();
        }
    }

    public void izmeniKlijenta() {
        int i = forma.getTabela().getSelectedRow();
        if (i >= 0) {
            Klijent k = ((ModelTabeleKlijenti) forma.getTabela().getModel()).getSelectedKlijent(i);
            IzmeniKlijentaForm ikf = new IzmeniKlijentaForm(forma, true);
            IzmeniKlijentaController ikc = new IzmeniKlijentaController(ikf, k);
            ikf.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(forma, "Niste izabrali klijenta za izmenu");
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
                forma.dispose();
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
        forma.getBtnIzaberi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izaberiKlijenta();
            }
        });
        forma.getBtnIzmeni().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeniKlijenta();
            }
        });
    }

}
