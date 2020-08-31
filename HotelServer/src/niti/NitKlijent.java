/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import domain.Klijent;
import domain.Rezervacija;
import domain.Soba;
import domain.TipSobe;
import domain.Usluga;
import domain.Zaposleni;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.Request;
import transfer.Response;
import util.Operation;
import util.ResponseStatus;

/**
 *
 * @author Boris
 */
public class NitKlijent extends Thread{
    private Socket soket;
    
    NitKlijent(Socket soket) {
        this.soket = soket;
    }

    @Override
    public void run() {
        while(!isInterrupted()){
            try{
                ObjectInputStream ois = new ObjectInputStream(soket.getInputStream());
                Request req = (Request) ois.readObject();
                Response res = handlRequest(req);
                ObjectOutputStream oos = new ObjectOutputStream(soket.getOutputStream());
                oos.writeObject(res);
            }catch(Exception ex){
                ex.printStackTrace();
                if(ex instanceof SocketException)
                    break;
            }
        }
        try {
            soket.close();
        } catch (IOException ex) {
            Logger.getLogger(NitKlijent.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    private Response handlRequest(Request req) {
        Response res=new Response(null, ResponseStatus.Success, null);
        try{
            switch(req.getOperation()){
                case Operation.OPERATION_LOGIN:
                    res.setData(controler.ServerController.getInstance().login((Zaposleni)req.getData()));
                    break;
                case Operation.OPERATION_GET_ALL_ZAPOSLENI:
                    res.setData(controler.ServerController.getInstance().getAllZaposleni());
                    break;
                case Operation.OPERATION_GET_ALL_KLIJENTI:
                    res.setData(controler.ServerController.getInstance().getAllKlijenti());
                    break;
                case Operation.OPERATION_GET_ALL_REZERVACIJE:
                    res.setData(controler.ServerController.getInstance().getAllRezerrvacije());
                    break;
                case Operation.OPERATION_GET_ALL_SOBE:
                    res.setData(controler.ServerController.getInstance().getAllSobe());
                    break;
                case Operation.OPERATION_GET_ALL_TIPSOBE:
                    res.setData(controler.ServerController.getInstance().getAllTipSobe());
                    break;
                case Operation.OPERATION_GET_ALL_USLUGE:
                    res.setData(controler.ServerController.getInstance().getAllUsluge());
                    break;
                case Operation.OPERATION_INSERT_ZAPOSLENI:
                    controler.ServerController.getInstance().insertZaposleni((Zaposleni)req.getData());
                    break;
                case Operation.OPERATION_INSERT_KLIJENT:
                    controler.ServerController.getInstance().insertKlijent((Klijent)req.getData());
                    break;
                case Operation.OPERATION_INSERT_REZERVACIJA:
                    controler.ServerController.getInstance().insertRezervacija((Rezervacija)req.getData());
                    break;
                case Operation.OPERATION_INSERT_SOBA:
                    controler.ServerController.getInstance().insertSoba((Soba)req.getData());
                    break;
                case Operation.OPERATION_INSERT_TIPSOBE:
                    controler.ServerController.getInstance().insertTipSobe((TipSobe)req.getData());
                    break;
                case Operation.OPERATION_INSERT_USLUGA:
                    controler.ServerController.getInstance().insertUsluga((Usluga)req.getData());
                    break;
                case Operation.OPERATION_UPDATE_ZAPOSLENI:
                    controler.ServerController.getInstance().updateZaposleni((Zaposleni)req.getData());
                    break;
                case Operation.OPERATION_UPDATE_KLIJENT:
                    controler.ServerController.getInstance().updateKlijent((Klijent)req.getData());
                    break;
                case Operation.OPERATION_UPDATE_REZERVACIJA:
                    controler.ServerController.getInstance().updateRezervacija((Rezervacija)req.getData());
                    break;
                case Operation.OPERATION_UPDATE_SOBA:
                    controler.ServerController.getInstance().updateSoba((Soba)req.getData());
                    break;
                case Operation.OPERATION_UPDATE_TIPSOBE:
                    controler.ServerController.getInstance().updateTipSobe((TipSobe)req.getData());
                    break;
                case Operation.OPERATION_UPDATE_USLUGA:
                    controler.ServerController.getInstance().updateUsluga((Usluga)req.getData());
                    break;
                case Operation.OPERATION_DELETE_ZAPOSLENI:
                    controler.ServerController.getInstance().deleteZaposleni((Zaposleni)req.getData());
                    break;
                case Operation.OPERATION_DELETE_REZERVACIJA:
                    controler.ServerController.getInstance().deleteRezervacija((Rezervacija)req.getData());
                    break;
                case Operation.OPERATION_DELETE_SOBA:
                    controler.ServerController.getInstance().deleteSoba((Soba)req.getData());
                    break;
                case Operation.OPERATION_DELETE_TIPSOBE:
                    controler.ServerController.getInstance().deleteTipSobe((TipSobe)req.getData());
                    break;
                case Operation.OPERATION_DELETE_USLUGA:
                    controler.ServerController.getInstance().deleteUsluga((Usluga)req.getData());
                    break;
            }
        }catch(Exception e){
            res.setException(e);
            res.setResponseStatus(ResponseStatus.Error);
        }
        return res;
    }
    
    
}
