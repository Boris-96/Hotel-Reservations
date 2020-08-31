/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.login;

import com.sun.javafx.property.adapter.PropertyDescriptor;
import form.login.*;
import form.rezervacija.KreiranjeRezervacijeForm;
import form.rezervacija.KreiranjeRezervacijeController;
import model.ModelTabeleSoba;
import model.ModelTabeleSobaIzabrane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Boris
 */
public class LoginController {

    private LoginForm forma;

    public LoginController(JFrame forma) {
        this.forma = (LoginForm) forma;
        setListener();
    }

    void cancel() {
        forma.dispose();
        System.exit(0);
    }

    void login() {
        try {
            Socket socket = new Socket("127.0.0.1", 9000);
            session.Session.getInstance().setSocket(socket);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(forma, "Nije moguće povezivanje na server.");
            System.exit(0);
        }
        try {
            String korisnickoIme = forma.getTxtKorisnickoIme().getText().trim();
            String sifra = new String(forma.getTxtSifra().getPassword());
            if (korisnickoIme.isEmpty() || sifra.isEmpty()) {
                JOptionPane.showMessageDialog(forma, "Niste uneli korisničko ime ili šifru");
                return;
            }
            controller.ClientController.getInstance().login(korisnickoIme, sifra);
            forma.dispose();
            KreiranjeRezervacijeForm formRez = new KreiranjeRezervacijeForm();
            KreiranjeRezervacijeController controlRez = new KreiranjeRezervacijeController(formRez);
            formRez.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(forma, e.getMessage());
            e.printStackTrace();

        }
    }

    private void setListener() {
        forma.getBtnLogin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        forma.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
    }
}
