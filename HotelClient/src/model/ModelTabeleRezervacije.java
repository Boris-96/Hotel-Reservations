/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domain.Rezervacija;
import domain.Soba;
import help.Interface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Boris
 */
public class ModelTabeleRezervacije extends AbstractTableModel implements Runnable {

    private List<Rezervacija> lista;
    private String[] niz = new String[]{"Klijent", "Zaposleni", "Datum od", "Datum do", "Rezervisane sobe"};
    private Interface end;
    private String parametar;
    
    public ModelTabeleRezervacije(Interface end) {
        try {
            this.end = end;
            lista = controller.ClientController.getInstance().getAllRezervacije();
            parametar = "";
        } catch (Exception ex) {
            if (ex instanceof SocketException) {
                end.endProgram();
            }
            ex.printStackTrace();
        }        
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }
    
    @Override
    public int getColumnCount() {
        return niz.length;
    }
    
    public void setParametar(String parametar) {
        this.parametar = parametar;
        refresh();
    }
    
    public Rezervacija getSelectedRezervacija(int i) {
        return lista.get(i);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Rezervacija r = lista.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        switch (columnIndex) {
            case 0:
                return r.getKlijent();
            case 1:
                return r.getZaposleni();
            case 2:
                return sdf.format(r.getDatumOd());
            case 3:
                return sdf.format(r.getDatumDo());
            case 4:
                String sobe = "";
                for (Soba s : r.getSobe()) {
                    sobe += "sprat: "+s.getSprat()+", broj: "+s.getBrojSobe()+";";
                }
                return sobe.substring(0, sobe.lastIndexOf(';'));
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return niz[column];
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(5000);
                lista = controller.ClientController.getInstance().getAllRezervacije();
                refresh();
            }
        } catch (Exception ex) {
            if (ex instanceof SocketException) {
                end.endProgram();
            }
            ex.printStackTrace();
        }
    }
    
    private void refresh() {
        List<Rezervacija> nova = new LinkedList<>();
        for (Rezervacija r : lista) {
            String klijent = r.getKlijent().toString();
            String zaposleni = r.getZaposleni().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            String datumOd = sdf.format(r.getDatumOd());
            String datumDo = sdf.format(r.getDatumDo());
            List<Soba> sobe = r.getSobe();
            String brojeviSoba = "";
            for (Soba soba : sobe) {
                brojeviSoba += soba.getBrojSobe();
            }
            if (klijent.contains(parametar) || zaposleni.contains(parametar) || datumOd.contains(parametar) || datumDo.contains(parametar) || brojeviSoba.contains(parametar)) {
                nova.add(r);
            }
        }
        lista = nova;
        fireTableDataChanged();
    }
}
