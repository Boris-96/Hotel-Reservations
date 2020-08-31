/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domain.RezervacijaUsluge;
import domain.Usluga;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Boris
 */
public class ModelTabeleUslugaIzabrane extends AbstractTableModel{
    private List<RezervacijaUsluge> listaUsluga;
    private String[] niz = new String[]{"Naziv usluge","Cena usluge po danu","Broj dana korišćenja"};

    public ModelTabeleUslugaIzabrane() {
        listaUsluga = new LinkedList<>();
    }

    public List<RezervacijaUsluge> getListaUsluga() {
        return listaUsluga;
    }

    public void setListaUsluga(List<RezervacijaUsluge> listaUsluga) {
        this.listaUsluga = listaUsluga;
        fireTableDataChanged();
    }

    
    public void addUsluga(Usluga u, int brojDana){
        RezervacijaUsluge rezUsl = new RezervacijaUsluge(null, u, brojDana);
        if(!listaUsluga.contains(u)){
            listaUsluga.add(rezUsl);
            fireTableDataChanged();
        }
    }
    
    public void removeUsluga(int i){
        if(i>=0){
            listaUsluga.remove(i);
            fireTableDataChanged();
        }
    }
    
    @Override
    public int getRowCount() {
        return listaUsluga.size();
    }

    @Override
    public int getColumnCount() {
        return niz.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RezervacijaUsluge u = listaUsluga.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return u.getUsluga().getNazivUsluge();
            case 1:
                return u.getUsluga().getCenaUslugePoDanu();
            case 2:
                return u.getBrojDanaUsluge();

            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return niz[column];
    }

    public void reset() {
        listaUsluga = new LinkedList<>();
        fireTableDataChanged();
    }
    
}
