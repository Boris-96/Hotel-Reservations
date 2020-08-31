/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.rezervacija;

import model.ModelTabeleUslugaIzabrane;
import model.ModelTabeleUsluga;
import model.ModelTabeleSobaIzabrane;
import model.ModelTabeleSoba;
import domain.Klijent;
import domain.Rezervacija;
import domain.RezervacijaUsluge;
import domain.Soba;
import domain.Usluga;
import domain.Zaposleni;
import help.Interface;
import form.klijent.DodajKlijentaController;
import form.klijent.DodajKlijentaForm;
import form.klijent.PretragaKlijentaForm;
import help.KlijentEnum;
import form.klijent.PretragaKlijentaController;
import form.soba.DodajSobuController;
import form.soba.DodajTipSobeController;
import form.soba.DodajSobuForm;
import form.soba.DodajTipSobeForm;
import form.soba.PretragaSobaController;
import form.soba.PretragaSobaForm;
import form.usluga.DodajUsluguController;
import form.usluga.DodajUsluguForm;
import form.usluga.PretragaUslugeController;
import form.usluga.PretragaUslugeForm;
import form.zaposleni.DodajZaposlenogController;
import form.zaposleni.DodajZaposlenogForm;
import form.zaposleni.PretragaZaposlenogController;
import form.zaposleni.PretragaZaposlenogForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import nitSat.Sat;

/**
 *
 * @author Boris
 */
public class KreiranjeRezervacijeController implements Interface {

    private KreiranjeRezervacijeForm forma;
    private Thread nitSoba;
    private Thread nitUsluga;

    public KreiranjeRezervacijeController(KreiranjeRezervacijeForm forma) {
        this.forma = forma;
        Sat nitSat = new Sat(forma);
        nitSat.start();
        ModelTabeleSoba mts = new ModelTabeleSoba(this);
        nitSoba = new Thread(mts);
        forma.getTblSobe().setModel(mts);
        nitSoba.start();
        ModelTabeleUsluga mtu = new ModelTabeleUsluga(this);
        nitUsluga = new Thread(mtu);
        forma.getTblUsluge().setModel(mtu);
        nitUsluga.start();
        forma.getTblIzabraneSobe().setModel(new ModelTabeleSobaIzabrane());
        forma.getTblIzabraneUsluge().setModel(new ModelTabeleUslugaIzabrane());
        forma.getLblTrenutnoUlogovaniKorisnik().setText("Trenutno ulogovani zaposleni je: " + session.Session.getInstance().getTrenutnoUlogovaniZaposleni());
        setListener();
        forma.getTxtDatumDo().setEditable(false);
    }

    public void noviKlijent() {
        DodajKlijentaForm fdk = new DodajKlijentaForm(forma, true);
        DodajKlijentaController dodajKlijenta = new DodajKlijentaController(fdk);
        fdk.setVisible(true);
    }

    private void pretragaKlijenta() {
        PretragaKlijentaForm formPretraga = new PretragaKlijentaForm(forma, true);
        PretragaKlijentaController controlPret = new PretragaKlijentaController(formPretraga, KlijentEnum.view);
        formPretraga.setVisible(true);
    }

    public void noviZaposleni() {
        DodajZaposlenogForm fdz = new DodajZaposlenogForm(forma, true);
        DodajZaposlenogController dodajZaposlenog = new DodajZaposlenogController(fdz);
        fdz.setVisible(true);
    }

    public void pretragaZaposleni() {
        PretragaZaposlenogForm pzf = new PretragaZaposlenogForm(forma, true);
        PretragaZaposlenogController pzc = new PretragaZaposlenogController(pzf);
        pzf.setVisible(true);
    }

    public void pretragaRezervacija() {
        PretragaRezervacijeForm prf = new PretragaRezervacijeForm(forma, true);
        PretragaRezervacijeController prc = new PretragaRezervacijeController(prf);
        prf.setVisible(true);
    }

    public void novaSoba() {
        DodajSobuForm fds = new DodajSobuForm(forma, true);
        DodajSobuController dodajSobu = new DodajSobuController(fds);
        fds.setVisible(true);
    }

    public void pretragaSoba() {
        PretragaSobaForm psf = new PretragaSobaForm(forma, true);
        PretragaSobaController psc = new PretragaSobaController(psf);
        psf.setVisible(true);
    }

    public void novaUsluga() {
        DodajUsluguForm fdu = new DodajUsluguForm(forma, true);
        DodajUsluguController dodajUslugu = new DodajUsluguController(fdu);
        fdu.setVisible(true);
    }

    public void pretragaUsluga() {
        PretragaUslugeForm puf = new PretragaUslugeForm(forma, true);
        PretragaUslugeController puc = new PretragaUslugeController(puf);
        puf.setVisible(true);
    }

    public void noviTipSobe() {
        DodajTipSobeForm fdts = new DodajTipSobeForm(forma, true);
        DodajTipSobeController dodajTipSobe = new DodajTipSobeController(fdts);
        fdts.setVisible(true);
    }

    public void datumOd() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            String textDatumOd = forma.getTxtDatumOd().getText();
            
            Date datumOd = sdf.parse(textDatumOd);
            if(textDatumOd.length()<10 || datumOd.before(new Date())){
                throw new Exception();
            }
                
            ((ModelTabeleSoba) forma.getTblSobe().getModel()).setDatumOd(datumOd);
            forma.getTxtDatumDo().setEditable(true);
            
        } catch (Exception e) {
            forma.getTxtDatumDo().setEditable(false);
            ((ModelTabeleSoba) forma.getTblSobe().getModel()).setDatumOd(null);
        }
    }

    public void datumDo() {
        try {
            SimpleDateFormat sfd = new SimpleDateFormat("dd.MM.yyyy");
            String text = forma.getTxtDatumDo().getText();
            Date datumDo = sfd.parse(text);
            String datumOdString = forma.getTxtDatumOd().getText();
            Date datumOd = sfd.parse(datumOdString);
            if (text.length()==10 && (datumDo.before(new Date()) || datumDo.before(datumOd) || datumDo.equals(datumOd))) {
                forma.getTxtDatumDo().setText("");
            } else {
                ((ModelTabeleSoba) forma.getTblSobe().getModel()).setDatumDo(datumDo);
            }
        } catch (Exception e) {
            ((ModelTabeleSoba) forma.getTblSobe().getModel()).setDatumDo(null);
        }
    }

    public void pretragaSobaFilter() {
        ((ModelTabeleSoba) forma.getTblSobe().getModel()).setParametar(forma.getTxtPretragaSoba().getText().trim());
        ((ModelTabeleSoba) forma.getTblSobe().getModel()).refresh();
    }

    public void izaberiSobu() {
        Soba s = ((ModelTabeleSoba) forma.getTblSobe().getModel()).getSelectedSoba(forma.getTblSobe().getSelectedRow());
        if (s != null) {
            ((ModelTabeleSobaIzabrane) forma.getTblIzabraneSobe().getModel()).addSoba(s);
        }
    }

    public void izbrisiIzabranuSobu() {
        ((ModelTabeleSobaIzabrane) forma.getTblIzabraneSobe().getModel()).removeSoba(forma.getTblIzabraneSobe().getSelectedRow());
    }

    public void cancel() {
        forma.dispose();
        System.exit(0);
    }

    public void izaberiKlijenta() {
        PretragaKlijentaForm formPretraga = new PretragaKlijentaForm(forma, true);
        PretragaKlijentaController pretraga = new PretragaKlijentaController(formPretraga, KlijentEnum.select);
        formPretraga.setVisible(true);
    }

    public void pretragaUslugeFilter() {
        ((ModelTabeleUsluga) forma.getTblUsluge().getModel()).setParametar(forma.getTxtPretragaUsluga().getText().trim());
        ((ModelTabeleUsluga) forma.getTblUsluge().getModel()).refresh();
    }

    public void izaberiUslugu() {
        String brojDana = forma.getTxtBrojKoriscenjaUsluge().getText().trim();
        if (brojDana.isEmpty()) {
            JOptionPane.showMessageDialog(forma, "Niste uneli broj dana korišćenja usluge.");
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            int brojDanaUsluge = Integer.parseInt(brojDana);
            Date datumDo = sdf.parse(forma.getTxtDatumDo().getText());
            Date datumOd = sdf.parse(forma.getTxtDatumOd().getText());
            int brDana = (int) ((datumDo.getTime() - datumOd.getTime()) / 86400000);
            if(brojDanaUsluge > brDana){
                JOptionPane.showMessageDialog(forma, "Broj dana korišćenja usluge ne sme biti veći od dana boravka u hotelu.");
                return;
            }
            int i = forma.getTblUsluge().getSelectedRow();
            if (i >= 0 && brojDanaUsluge >= 1) {
                Usluga u = ((ModelTabeleUsluga) forma.getTblUsluge().getModel()).getSelectedUsluga(i);
                if (u != null) {
                    ((ModelTabeleUslugaIzabrane) forma.getTblIzabraneUsluge().getModel()).addUsluga(u, brojDanaUsluge);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Unesite broj dana koriscenja usluge");
        }
    }

    public void izbrisiIzabranuUslugu() {
        ((ModelTabeleUslugaIzabrane) forma.getTblIzabraneUsluge().getModel()).removeUsluga(forma.getTblIzabraneUsluge().getSelectedRow());
    }

    public void izvrsiRezervaciju() {
        try {
            if(forma.getTxtDatumDo().getText().isEmpty() || forma.getTxtDatumOd().getText().isEmpty() || 
                ((ModelTabeleSobaIzabrane)forma.getTblIzabraneSobe().getModel()).getListaSoba().isEmpty()){
            throw new Exception("Niste popunili obavezna polja.");
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date datumOd = sdf.parse(forma.getTxtDatumOd().getText().trim());
            Date datumDo = sdf.parse(forma.getTxtDatumDo().getText().trim());
            Date datumRezervacije = new Date();
            Klijent klijent = session.Session.getInstance().getIzabraniKlijent();
            if (klijent == null) {
                throw new Exception("Niste izabrali klijenta.");
            }

            Zaposleni zaposleni = session.Session.getInstance().getTrenutnoUlogovaniZaposleni();
            List<Soba> listaSoba = ((ModelTabeleSobaIzabrane) forma.getTblIzabraneSobe().getModel()).getListaSoba();

            List<RezervacijaUsluge> listaIzabranih = ((ModelTabeleUslugaIzabrane) forma.getTblIzabraneUsluge().getModel()).getListaUsluga();

            Rezervacija r = new Rezervacija(-1, datumOd, datumDo, datumRezervacije, 0, klijent, zaposleni);
            for (RezervacijaUsluge izabrana : listaIzabranih) {
                izabrana.setRezervacija(r);
            }

            int brDana = (int) ((datumDo.getTime() - datumOd.getTime()) / 86400000);
            double cena = 0;
            for (RezervacijaUsluge rezervacijaUsluge : listaIzabranih) {
                cena += rezervacijaUsluge.getUsluga().getCenaUslugePoDanu() * rezervacijaUsluge.getBrojDanaUsluge();
            }
            for (Soba soba : listaSoba) {
                cena += soba.getCenaSobePoDanu() * brDana;
            }
            
            r.setUkupnaCena(cena);
            r.setUsluge(listaIzabranih);
            r.setSobe(listaSoba);

            if (datumDo.before(datumOd)) {
                throw new Exception("Datum do je pre datuma od!");
            }

            int izbor = JOptionPane.showConfirmDialog(forma, "Ukupna cena rezervacije je: " + cena + " evra.\n Da li zelite da potvrdite rezervaciju?");
            if (izbor == JOptionPane.YES_OPTION) {
                controller.ClientController.getInstance().insertRezervacija(r);
                JOptionPane.showMessageDialog(forma, "Uspesno ste sacuvali rezervaciju.");
                session.Session.getInstance().setIzabraniKlijent(null);
                forma.getLblKlijentIzabrani().setText("Niste izabrali klijenta.");
                ((ModelTabeleSobaIzabrane) forma.getTblIzabraneSobe().getModel()).reset();
                ((ModelTabeleUslugaIzabrane) forma.getTblIzabraneUsluge().getModel()).reset();
                forma.getTxtDatumDo().setText("");
                forma.getTxtDatumOd().setText("");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, ex.getMessage());
            ex.printStackTrace();
            Logger.getLogger(KreiranjeRezervacijeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setWindow() {
        Klijent k = session.Session.getInstance().getIzabraniKlijent();
        if (k != null) {
            forma.getLblKlijentIzabrani().setText("Izabrani klijent je: " + k.getImeKlijent() + " " + k.getPrezimeKlijent());
        }
    }

    private void setListener() {
        forma.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                setWindow();
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
            }
        });
        forma.getTxtDatumOd().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                datumOd();
            }
        });
        forma.getTxtDatumDo().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                datumDo();
            }
        });
        forma.getTxtPretragaSoba().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                pretragaSobaFilter();
            }
        });
        forma.getTxtPretragaUsluga().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                pretragaUslugeFilter();
            }
        });
        forma.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
        forma.getBtnIzaberiKlijenta().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izaberiKlijenta();
            }
        });
        forma.getBtnIzaberiSobu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izaberiSobu();
            }
        });
        forma.getBtnIzaberiUslugu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izaberiUslugu();
            }
        });
        forma.getBtnIzbrisiIzabranuSobu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izbrisiIzabranuSobu();
            }
        });
        forma.getBtnIzbrisiIzabranuUslugu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izbrisiIzabranuUslugu();
            }
        });
        forma.getBtnIzvrsirezervaciju().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izvrsiRezervaciju();
            }
        });
        forma.getMenuItemNovaSoba().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                novaSoba();
            }
        });
        forma.getMenuItemNovaUsluga().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                novaUsluga();
            }
        });
        forma.getMenuItemNoviKlijent().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                noviKlijent();
            }
        });
        forma.getMenuItemNoviTipSobe().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                noviTipSobe();
            }
        });
        forma.getMenuItemNoviZaposleni().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                noviZaposleni();
            }
        });
        forma.getMenuItemPretragaKlijenta().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pretragaKlijenta();
            }
        });
        forma.getMenuItemPretragaRezervacija().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pretragaRezervacija();
            }
        });
        forma.getMenuItemPretragaSoba().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pretragaSoba();
            }
        });
        forma.getMenuItemPretragaUsluga().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pretragaUsluga();
            }
        });
        forma.getMenuItemPretragaZaposleni().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pretragaZaposleni();
            }
        });
    }

    @Override
    public void endProgram() {
        JOptionPane.showMessageDialog(forma, "Server je prekinuo komunikaciju");
        System.exit(0);
    }

}
