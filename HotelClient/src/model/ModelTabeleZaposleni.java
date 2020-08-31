/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domain.Zaposleni;
import help.Interface;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Boris
 */
public class ModelTabeleZaposleni extends AbstractTableModel implements Runnable{
    private List<Zaposleni> lista;
    private String[] niz = new String[]{"Ime","Prezime","Broj pasosa","Telefon","Adresa"};
    private String parametar;
    private Interface end;

    public ModelTabeleZaposleni(Interface end) {
        try {
            this.end = end;
            lista = controller.ClientController.getInstance().getAllZaposleni();
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
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return niz.length;
    }

    public Zaposleni getSelectedZaposleni(int i){
        return lista.get(i);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Zaposleni z = lista.get(rowIndex);
        switch(columnIndex){
            case 0: return z.getImeZaposleni();
            case 1: return z.getPrezimeZaposleni();
            case 2: return z.getBrojPasosaZaposleni();
            case 3: return z.getTelefonZaposleni();
            case 4: return z.getAdresaZaposleni();
                
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return niz[column];
    }

    @Override
    public void run() {
        try{
            while(true){
                Thread.sleep(5000);
                lista = controller.ClientController.getInstance().getAllZaposleni();
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
        List<Zaposleni> nova = new LinkedList<>();
        for (Zaposleni zaposleni : lista) {
            if(zaposleni.getImeZaposleni().contains(parametar) || zaposleni.getPrezimeZaposleni().contains(parametar) || zaposleni.getBrojPasosaZaposleni().contains(parametar) || zaposleni.getAdresaZaposleni().contains(parametar) || zaposleni.getTelefonZaposleni().contains(parametar)){
                nova.add(zaposleni);
            }
        }
        lista = nova;
        fireTableDataChanged();
    }
    
}
