/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domain.Soba;
import help.Interface;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Boris
 */
public class ModelTabeleSobaIzabrane extends AbstractTableModel{
    private List<Soba> listaSoba;
    private String[] niz = new String[]{"Sprat","Broj sobe","Cena po danu","Broj kreveta","Tip sobe"};

    public ModelTabeleSobaIzabrane() {
        listaSoba = new LinkedList<>();
    }

    public List<Soba> getListaSoba() {
        return listaSoba;
    }

    public void setListaSoba(List<Soba> listaSoba) {
        this.listaSoba = listaSoba;
        fireTableDataChanged();
    }
        
    public void addSoba(Soba s){
        if(!listaSoba.contains(s)){
            listaSoba.add(s);
            fireTableDataChanged();
        }
    }
    
    public void removeSoba(int i){
        if(i>=0){
            listaSoba.remove(i);
            fireTableDataChanged();
        }
    }
    
    @Override
    public int getRowCount() {
        return listaSoba.size();
    }

    @Override
    public int getColumnCount() {
        return niz.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Soba s = listaSoba.get(rowIndex);
        
        switch(columnIndex){
            case 0: return s.getSprat();
            case 1: return s.getBrojSobe();
            case 2: return s.getCenaSobePoDanu();
            case 3: return s.getBrojKreveta();
            case 4: return s.getTipSobe().getNazivTipaSobe();
            
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return niz[column];
    }

    public void reset() {
        listaSoba = new LinkedList<>();
        fireTableDataChanged();
    }
    
}
