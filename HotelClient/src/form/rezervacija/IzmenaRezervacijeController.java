/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.rezervacija;

import domain.Klijent;
import domain.Rezervacija;
import domain.RezervacijaUsluge;
import domain.Soba;
import domain.Usluga;
import domain.Zaposleni;
import help.Interface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.ModelTabeleSoba;
import model.ModelTabeleSobaIzabrane;
import model.ModelTabeleUsluga;
import model.ModelTabeleUslugaIzabrane;

/**
 *
 * @author Boris
 */
public class IzmenaRezervacijeController implements Interface {

    private IzmenaRezervacijeForm forma;
    private Rezervacija rezervacija;
    private SimpleDateFormat sdf;

    public IzmenaRezervacijeController(IzmenaRezervacijeForm forma, Rezervacija rezervacija) {
        this.forma = forma;
        this.rezervacija = rezervacija;
        sdf = new SimpleDateFormat("dd.MM.yyyy");

        ModelTabeleSoba mts = new ModelTabeleSoba(this);
        forma.getTabelaSobe().setModel(mts);
        Thread nitSobe = new Thread(mts);
        nitSobe.start();

        ModelTabeleUsluga mtu = new ModelTabeleUsluga(this);
        forma.getTabelaUsluge().setModel(mtu);
        Thread nitUsluge = new Thread(mtu);
        nitUsluge.start();

        ModelTabeleSobaIzabrane mtsi = new ModelTabeleSobaIzabrane();
        forma.getTabelaIzabraneSobe().setModel(mtsi);
        List<Soba> listaSoba = rezervacija.getSobe();
        ((ModelTabeleSobaIzabrane) forma.getTabelaIzabraneSobe().getModel()).setListaSoba(listaSoba);

        ModelTabeleUslugaIzabrane mtui = new ModelTabeleUslugaIzabrane();
        forma.getTabelaIzabraneUsluge().setModel(mtui);
        List<RezervacijaUsluge> listaUsluga = rezervacija.getUsluge();
        ((ModelTabeleUslugaIzabrane) forma.getTabelaIzabraneUsluge().getModel()).setListaUsluga(listaUsluga);

        forma.getTxtKlijent().setText(rezervacija.getKlijent().toString());
        forma.getTxtKlijent().setEditable(false);
        forma.getTxtZaposleni().setText(rezervacija.getZaposleni().toString());
        forma.getTxtZaposleni().setEditable(false);
        forma.getTxtDatumOd().setText(sdf.format(rezervacija.getDatumOd()));
        ((ModelTabeleSoba) forma.getTabelaSobe().getModel()).setDatumOd(rezervacija.getDatumOd());
        forma.getTxtDatumDo().setText(sdf.format(rezervacija.getDatumDo()));
        ((ModelTabeleSoba) forma.getTabelaSobe().getModel()).setDatumDo(rezervacija.getDatumDo());

        setListener();
    }

    private void datumOd() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            
            if (!forma.getTxtDatumOd().getText().isEmpty()) {
                Date datumOd = sdf.parse(forma.getTxtDatumOd().getText());
                if (!forma.getTxtDatumDo().getText().isEmpty()) {
                    if (datumOd.before(new Date())) {
                        forma.getTxtDatumOd().setText("");
                        ((ModelTabeleSoba) forma.getTabelaSobe().getModel()).setDatumOd(null);
                    } else {
                        ((ModelTabeleSoba) forma.getTabelaSobe().getModel()).setDatumOd(datumOd);
                    }
                } else {
                    ((ModelTabeleSoba) forma.getTabelaSobe().getModel()).setDatumOd(datumOd);
                }
            } else {
                ((ModelTabeleSoba) forma.getTabelaSobe().getModel()).setDatumOd(null);
            }
        } catch (Exception e) {

        }
    }

    private void datumDo() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            
            if (!forma.getTxtDatumDo().getText().isEmpty()) {
                Date datumDo = sdf.parse(forma.getTxtDatumDo().getText());
                if (!forma.getTxtDatumOd().getText().isEmpty()) {
                    Date datumOd = sdf.parse(forma.getTxtDatumOd().getText());
                    if (datumDo.before(datumOd)) {
                        forma.getTxtDatumDo().setText("");
                        ((ModelTabeleSoba) forma.getTabelaSobe().getModel()).setDatumDo(null);
                    } else {
                        ((ModelTabeleSoba) forma.getTabelaSobe().getModel()).setDatumDo(datumDo);
                    }
                } else {
                    ((ModelTabeleSoba) forma.getTabelaSobe().getModel()).setDatumDo(datumDo);
                }
            }else{
                ((ModelTabeleSoba) forma.getTabelaSobe().getModel()).setDatumDo(null);
            }
        } catch (Exception e) {

        }
    }

    private void cancel() {
        forma.dispose();
    }

    private void pretragaSobaFilter() {
        ((ModelTabeleSoba) forma.getTabelaSobe().getModel()).setParametar(forma.getTxtPretragaSobe().getText().trim());
        ((ModelTabeleSoba) forma.getTabelaSobe().getModel()).refresh();
    }

    private void pretragaUslugeFilter() {
        ((ModelTabeleUsluga) forma.getTabelaUsluge().getModel()).setParametar(forma.getTxtPretragaUsluge().getText().trim());
        ((ModelTabeleUsluga) forma.getTabelaUsluge().getModel()).refresh();
    }

    private void izaberiSobu() {
        Soba s = ((ModelTabeleSoba) forma.getTabelaSobe().getModel()).getSelectedSoba(forma.getTabelaSobe().getSelectedRow());
        if (s != null) {
            ((ModelTabeleSobaIzabrane) forma.getTabelaIzabraneSobe().getModel()).addSoba(s);
        }
    }

    private void izaberiUslugu() {
        String brojDana = forma.getTxtBrojDanaUsluge().getText().trim();
        if (brojDana.isEmpty()) {
            JOptionPane.showMessageDialog(forma, "Niste uneli broj dana korišćenja usluge.");
            return;
        }
        try {
            int brojDanaUsluge = Integer.parseInt(brojDana);
            int i = forma.getTabelaUsluge().getSelectedRow();
            if (i >= 0 && brojDanaUsluge >= 1) {
                Usluga u = ((ModelTabeleUsluga) forma.getTabelaUsluge().getModel()).getSelectedUsluga(i);
                if (u != null) {
                    ((ModelTabeleUslugaIzabrane) forma.getTabelaIzabraneUsluge().getModel()).addUsluga(u, brojDanaUsluge);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Unesite broj dana koriscenja usluge");
        }
    }

    private void izbrisiIzabranuSobu() {
        ((ModelTabeleSobaIzabrane) forma.getTabelaIzabraneSobe().getModel()).removeSoba(forma.getTabelaIzabraneSobe().getSelectedRow());
    }

    private void izbrisiIzabranuUslugu() {
        ((ModelTabeleUslugaIzabrane) forma.getTabelaIzabraneUsluge().getModel()).removeUsluga(forma.getTabelaIzabraneUsluge().getSelectedRow());
    }

    private void potvrdiIzmenu() {
try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date datumOd = sdf.parse(forma.getTxtDatumOd().getText().trim());
            Date datumDo = sdf.parse(forma.getTxtDatumDo().getText().trim());
            Date datumRezervacije = new Date();
            Klijent klijent = rezervacija.getKlijent();
            

            Zaposleni zaposleni = session.Session.getInstance().getTrenutnoUlogovaniZaposleni();
            List<Soba> listaSoba = ((ModelTabeleSobaIzabrane) forma.getTabelaIzabraneSobe().getModel()).getListaSoba();

            List<RezervacijaUsluge> listaIzabranih = ((ModelTabeleUslugaIzabrane) forma.getTabelaIzabraneUsluge().getModel()).getListaUsluga();

            Rezervacija r = new Rezervacija(rezervacija.getRezervacijaId(), datumOd, datumDo, datumRezervacije, 0, klijent, zaposleni);
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

            int izbor = JOptionPane.showConfirmDialog(forma, "Ukupna cena rezervacije je: " + cena + " evra.\n Da li zelite da potvrdite izmenu rezervacije?");
            if (izbor == JOptionPane.YES_OPTION) {
                controller.ClientController.getInstance().insertRezervacija(r);
                JOptionPane.showMessageDialog(forma, "Uspesno ste izmenili rezervaciju.");
                forma.dispose();
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, ex.getMessage());
            ex.printStackTrace();
            Logger.getLogger(KreiranjeRezervacijeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void endProgram() {
        JOptionPane.showMessageDialog(forma, "Server je prekinuo komunikaciju");
        System.exit(0);
    }

    private void setListener() {
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
        forma.getTxtPretragaSobe().addKeyListener(new KeyListener() {
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
        forma.getTxtPretragaUsluge().addKeyListener(new KeyListener() {
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
        forma.getBtnIzbrisiSobu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izbrisiIzabranuSobu();
            }
        });
        forma.getBtnIzbrisiUslugu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izbrisiIzabranuUslugu();
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
