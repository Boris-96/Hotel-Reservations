/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domain.Klijent;
import help.Interface;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Boris
 */
public class ModelTabeleKlijenti extends AbstractTableModel implements Runnable{
    private List<Klijent> lista;
    private String[] niz = new String[]{"Ime","Prezime","Broj pasosa","Telefon","Adresa"};
    private Interface end;
    private String parametar;
    

    public ModelTabeleKlijenti(Interface end) {
        try {
            this.end = end;
            lista = controller.ClientController.getInstance().getAllKlijenti();
            parametar = "";
        } catch (Exception ex) {
            if(ex instanceof SocketException)
                end.endProgram();
            ex.printStackTrace();
            }      
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refresh();
    }

    public Klijent getSelectedKlijent(int i){
        return lista.get(i);
    }
    
    @Override
    public String getColumnName(int column) {
        return niz[column];
    }
    
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return niz.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Klijent k = lista.get(rowIndex);
        
        switch(columnIndex){
            case 0: return k.getImeKlijent();
            case 1: return k.getPrezimeKlijent();
            case 2: return k.getBrojPasosaKlijent();
            case 3: return k.getTelefonKlijent();
            case 4: return k.getAdresaKlijent();
            
            default: return  null;
        }
    }

    @Override
    public void run() {
        try{
            while(true){
                Thread.sleep(5000);
                lista = controller.ClientController.getInstance().getAllKlijenti();
                refresh();
            }
        }catch(Exception ex){
            if(ex instanceof SocketException){
                end.endProgram();
            }
            ex.printStackTrace();
        }
    }

    private void refresh() {
        List<Klijent> nova = new LinkedList<>();
        for (Klijent klijent : lista) {
            if(klijent.getImeKlijent().contains(parametar) || klijent.getPrezimeKlijent().contains(parametar) || klijent.getBrojPasosaKlijent().contains(parametar) || klijent.getAdresaKlijent().contains(parametar) || klijent.getTelefonKlijent().contains(parametar)){
                nova.add(klijent);
            }
        }
        lista = nova;
        fireTableDataChanged();
    }
    
}
