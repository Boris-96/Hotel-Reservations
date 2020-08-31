/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.ClientController;
import domain.Rezervacija;
import domain.Soba;
import help.Interface;
import java.net.SocketException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Boris
 */
public class ModelTabeleSoba extends AbstractTableModel implements Runnable {

    private List<Soba> listaSoba;
    private String[] niz = new String[]{"Sprat", "Broj sobe", "Cena po danu", "Broj kreveta", "Tip sobe"};
    private Date datumOd;
    private Date datumDo;
    private String parametar;
    private Interface end;
    

    

    

    public ModelTabeleSoba(Interface end){
        try{
        this.end = end;
        listaSoba = new LinkedList<>();
        parametar = "";
        }catch(Exception ex){
            ex.printStackTrace();
            if(ex instanceof SocketException)
                end.endProgram();
        }
    }
    
    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
        refresh();
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
        refresh();
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refresh();
    }
    
    public void refresh() {
        try {
            List<Soba> listaS = new LinkedList<>();
            List<Rezervacija> listaR = ClientController.getInstance().getAllRezervacije();
            if (datumOd != null && datumDo != null) {
                for (Soba s : listaSoba) {
                    boolean postoji = false;
                    for (Rezervacija r : listaR) {
                        for (Soba so : r.getSobe()) {
                            if (so.equals(s)) {
                                if ((datumOd.before(r.getDatumDo()) && datumOd.after(r.getDatumOd())) || (datumOd.equals(r.getDatumOd()) || datumOd.equals(r.getDatumDo()))) {
                                    postoji = true;
                                }
                                if ((datumDo.before(r.getDatumDo()) && datumDo.after(r.getDatumOd())) || (datumDo.equals(r.getDatumOd()) || datumDo.equals(r.getDatumDo()))) {
                                    postoji = true;
                                }
                            }
                        }
                    }
                    if (!postoji) {
                        if (String.valueOf(s.getSprat()).contains(parametar) || String.valueOf(s.getBrojSobe()).contains(parametar) || String.valueOf(s.getCenaSobePoDanu()).contains(parametar) || String.valueOf(s.getBrojKreveta()).contains(parametar) || s.getTipSobe().getNazivTipaSobe().contains(parametar)) {
                            listaS.add(s);
                        }
                    }
                }
                listaSoba = listaS;
            }else
                listaSoba=new LinkedList<>();
            fireTableDataChanged();
        } catch (Exception ex) {
            ex.printStackTrace();
            if(ex instanceof SocketException)
                end.endProgram();
        }
    }

    

    @Override
    public int getRowCount() {
        return listaSoba.size();
    }

    public Soba getSelectedSoba(int i) {
        if (i >= 0) {
            return listaSoba.get(i);
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return niz[column];
    }

    @Override
    public int getColumnCount() {
        return niz.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Soba s = listaSoba.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return s.getSprat();
            case 1:
                return s.getBrojSobe();
            case 2:
                return s.getCenaSobePoDanu();
            case 3:
                return s.getBrojKreveta();
            case 4:
                return s.getTipSobe().getNazivTipaSobe();

            default:
                return null;
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Thread.sleep(3000);
                listaSoba = ClientController.getInstance().getAllSobe();
                refresh();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            if(ex instanceof SocketException)
                end.endProgram();
        }
    }

}
