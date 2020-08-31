/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.ClientController;
import domain.Usluga;
import help.Interface;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Boris
 */
public class ModelTabeleUsluga extends AbstractTableModel implements Runnable{

    private List<Usluga> listaUsluga;
    private String[] niz = new String[]{"Naziv usluge", "Cena usluge po danu"};
    private String parametar;
    private Interface end;

    public ModelTabeleUsluga(Interface end) {
        try{
            this.end = end;
            listaUsluga = controller.ClientController.getInstance().getAllUsluge();
            parametar = "";
        }catch(Exception ex){
            if(ex instanceof SocketException)
                end.endProgram();
            ex.printStackTrace();
        }
    }

     public void refresh() {
        List<Usluga> listaU = new LinkedList<>();
        for (Usluga u : listaUsluga) {
            if (u.getNazivUsluge().contains(parametar) || String.valueOf(u.getCenaUslugePoDanu()).contains(parametar)) {
                listaU.add(u);
            }
        }
        listaUsluga = listaU;
        fireTableDataChanged();
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refresh();
    }
     
    public Usluga getSelectedUsluga(int i){
        if(i>=0)
            return listaUsluga.get(i);
        return null;
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
        Usluga u = listaUsluga.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return u.getNazivUsluge();
            case 1:
                return u.getCenaUslugePoDanu();

            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return niz[column];
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                listaUsluga = ClientController.getInstance().getAllUsluge();
                refresh();
                Thread.sleep(3000);
            }
        }catch(Exception ex){
            if(ex instanceof SocketException)
                end.endProgram();
            ex.printStackTrace();
        }
    }

    
 }
