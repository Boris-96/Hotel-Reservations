/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.soba;

import domain.Rezervacija;
import domain.Soba;
import help.Interface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.ModelTabeleSoba;

/**
 *
 * @author Boris
 */
public class PretragaSobaController implements Interface {

    PretragaSobaForm forma;

    public PretragaSobaController(PretragaSobaForm forma) {
        this.forma = forma;
        ModelTabeleSoba mts = new ModelTabeleSoba(this);
        forma.getTabela().setModel(mts);
        Thread nit = new Thread(mts);
        nit.start();
        setListener();
    }

    private void cancel() {
        forma.dispose();
    }

    private void izmeniSobu() {
        int i = forma.getTabela().getSelectedRow();
        if (i >= 0) {
            Soba s = ((ModelTabeleSoba) forma.getTabela().getModel()).getSelectedSoba(i);
            IzmenaSobeForm isf = new IzmenaSobeForm(forma, true);
            IzmenaSobeController isc = new IzmenaSobeController(isf, s);
            isf.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(forma, "Niste izabrali sobu za izmenu");
        }
    }

    private void filter() {
        ((ModelTabeleSoba) forma.getTabela().getModel()).setParametar(forma.getTxtPretraga().getText().trim());
    }

    private void delete() {
        try {
            int i = forma.getTabela().getSelectedRow();
            List<Rezervacija> listaRezervacija = controller.ClientController.getInstance().getAllRezervacije();
            if (i >= 0) {
                Soba s = ((ModelTabeleSoba) forma.getTabela().getModel()).getSelectedSoba(i);
                for (Rezervacija rezervacija : listaRezervacija) {
                    if(rezervacija.getSobe().contains(s) && rezervacija.getDatumDo().after(new Date())){
                        JOptionPane.showMessageDialog(forma, "Soba je rezervisana. Nije moguće izbrisati sobu.");
                        return;
                    }
                }
                controller.ClientController.getInstance().deleteSoba(s);
                JOptionPane.showMessageDialog(forma, "Uspešno ste obrisali sobu");

            } else {
                JOptionPane.showMessageDialog(forma, "Niste izabrali sobu za brisanje");
            }
        } catch (Exception ex) {
            Logger.getLogger(PretragaSobaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(forma, "Server je prekinuo komunikaciju!");
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
        forma.getBtnEdit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeniSobu();
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
