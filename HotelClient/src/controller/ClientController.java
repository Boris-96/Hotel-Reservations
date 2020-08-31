/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Klijent;
import domain.Rezervacija;
import domain.Soba;
import domain.TipSobe;
import domain.Usluga;
import domain.Zaposleni;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import transfer.Request;
import transfer.Response;
import util.Operation;
import util.ResponseStatus;

/**
 *
 * @author Boris
 */
public class ClientController {
    
    private static ClientController instance;

    public ClientController() {
    }

    
    public static ClientController getInstance() {
        if(instance==null)
            instance=new ClientController();
        return instance;
    }
    
    public synchronized void login(String username,String password) throws Exception{
        Zaposleni zaposleni=new Zaposleni();
        zaposleni.setUsername(username);
        zaposleni.setPassword(password);
        Request req=new Request(Operation.OPERATION_LOGIN, zaposleni);
        ObjectOutputStream oos=new ObjectOutputStream(session.Session.getInstance().getSocket().getOutputStream());
        oos.writeObject(req);
        ObjectInputStream ois=new ObjectInputStream(session.Session.getInstance().getSocket().getInputStream());
        Response res=(Response)ois.readObject();
        if(res.getResponseStatus()==ResponseStatus.Success){
            Zaposleni zap=(Zaposleni)res.getData();
            session.Session.getInstance().setTrenutnoUlogovaniZaposleni(zap);
        }else{
            throw res.getException();
        }
    }
    
       
    public synchronized List<Klijent> getAllKlijenti()throws Exception{
        Request req=new Request(Operation.OPERATION_GET_ALL_KLIJENTI, null);
        ObjectOutputStream oos=new ObjectOutputStream(session.Session.getInstance().getSocket().getOutputStream());
        oos.writeObject(req);
        ObjectInputStream ois=new ObjectInputStream(session.Session.getInstance().getSocket().getInputStream());
        Response res=(Response)ois.readObject();
        if(res.getResponseStatus()==ResponseStatus.Error)
            throw res.getException();
        return (List<Klijent>)res.getData();
    }
    
    public synchronized List<Zaposleni> getAllZaposleni()throws Exception{
        Request req=new Request(Operation.OPERATION_GET_ALL_ZAPOSLENI, null);
        ObjectOutputStream oos=new ObjectOutputStream(session.Session.getInstance().getSocket().getOutputStream());
        oos.writeObject(req);
        ObjectInputStream ois=new ObjectInputStream(session.Session.getInstance().getSocket().getInputStream());
        Response res=(Response)ois.readObject();
        if(res.getResponseStatus()==ResponseStatus.Error)
            throw res.getException();
        return (List<Zaposleni>)res.getData();
    }
    
    public synchronized List<Soba> getAllSobe()throws Exception{
        Request req=new Request(Operation.OPERATION_GET_ALL_SOBE, null);
        ObjectOutputStream oos=new ObjectOutputStream(session.Session.getInstance().getSocket().getOutputStream());
        oos.writeObject(req);
        ObjectInputStream ois=new ObjectInputStream(session.Session.getInstance().getSocket().getInputStream());
        Response res=(Response)ois.readObject();
        if(res.getResponseStatus()==ResponseStatus.Error)
            throw res.getException();
        return (List<Soba>)res.getData();
    }
    
    public synchronized List<TipSobe> getAllTipSobe()throws Exception{
        Request req=new Request(Operation.OPERATION_GET_ALL_TIPSOBE, null);
        ObjectOutputStream oos=new ObjectOutputStream(session.Session.getInstance().getSocket().getOutputStream());
        oos.writeObject(req);
        ObjectInputStream ois=new ObjectInputStream(session.Session.getInstance().getSocket().getInputStream());
        Response res=(Response)ois.readObject();
        if(res.getResponseStatus()==ResponseStatus.Error)
            throw res.getException();
        return (List<TipSobe>)res.getData();
    }
    
    public synchronized List<Usluga> getAllUsluge()throws Exception{
        Request req=new Request(Operation.OPERATION_GET_ALL_USLUGE, null);
        ObjectOutputStream oos=new ObjectOutputStream(session.Session.getInstance().getSocket().getOutputStream());
        oos.writeObject(req);
        ObjectInputStream ois=new ObjectInputStream(session.Session.getInstance().getSocket().getInputStream());
        Response res=(Response)ois.readObject();
        if(res.getResponseStatus()==ResponseStatus.Error)
            throw res.getException();
        return (List<Usluga>)res.getData();
    }
    
    public synchronized List<Rezervacija> getAllRezervacije()throws Exception{
        Request req=new Request(Operation.OPERATION_GET_ALL_REZERVACIJE, null);
        ObjectOutputStream oos=new ObjectOutputStream(session.Session.getInstance().getSocket().getOutputStream());
        oos.writeObject(req);
        ObjectInputStream ois=new ObjectInputStream(session.Session.getInstance().getSocket().getInputStream());
        Response res=(Response)ois.readObject();
        if(res.getResponseStatus()==ResponseStatus.Error)
            throw res.getException();
        return (List<Rezervacija>)res.getData();
    }
    
    public synchronized void insertKlijent(Klijent klijent) throws Exception{
        Request req=new Request(Operation.OPERATION_INSERT_KLIJENT, klijent);
        ObjectOutputStream oos=new ObjectOutputStream(session.Session.getInstance().getSocket().getOutputStream());
        oos.writeObject(req);
        ObjectInputStream ois=new ObjectInputStream(session.Session.getInstance().getSocket().getInputStream());
        Response res=(Response)ois.readObject();
        if(res.getResponseStatus()==ResponseStatus.Error)
            throw res.getException();
    }
    
    public synchronized void insertZaposleni(Zaposleni zaposleni) throws Exception{
        Request req=new Request(Operation.OPERATION_INSERT_ZAPOSLENI, zaposleni);
        ObjectOutputStream oos=new ObjectOutputStream(session.Session.getInstance().getSocket().getOutputStream());
        oos.writeObject(req);
        ObjectInputStream ois=new ObjectInputStream(session.Session.getInstance().getSocket().getInputStream());
        Response res=(Response)ois.readObject();
        if(res.getResponseStatus()==ResponseStatus.Error)
            throw res.getException();
    }
    
    public synchronized void insertSoba(Soba soba) throws Exception{
        Request req=new Request(Operation.OPERATION_INSERT_SOBA, soba);
        ObjectOutputStream oos=new ObjectOutputStream(session.Session.getInstance().getSocket().getOutputStream());
        oos.writeObject(req);
        ObjectInputStream ois=new ObjectInputStream(session.Session.getInstance().getSocket().getInputStream());
        Response res=(Response)ois.readObject();
        if(res.getResponseStatus()==ResponseStatus.Error)
            throw res.getException();
    }
    
    public synchronized void insertTipSobe(TipSobe tipSobe) throws Exception{
        Request req=new Request(Operation.OPERATION_INSERT_TIPSOBE, tipSobe);
        ObjectOutputStream oos=new ObjectOutputStream(session.Session.getInstance().getSocket().getOutputStream());
        oos.writeObject(req);
        ObjectInputStream ois=new ObjectInputStream(session.Session.getInstance().getSocket().getInputStream());
        Response res=(Response)ois.readObject();
        if(res.getResponseStatus()==ResponseStatus.Error)
            throw res.getException();
    }
    
    public synchronized void insertUsluga(Usluga usluga) throws Exception{
        Request req=new Request(Operation.OPERATION_INSERT_USLUGA, usluga);
        ObjectOutputStream oos=new ObjectOutputStream(session.Session.getInstance().getSocket().getOutputStream());
        oos.writeObject(req);
        ObjectInputStream ois=new ObjectInputStream(session.Session.getInstance().getSocket().getInputStream());
        Response res=(Response)ois.readObject();
        if(res.getResponseStatus()==ResponseStatus.Error)
            throw res.getException();
    }
    
    public synchronized void insertRezervacija(Rezervacija rezervacija) throws Exception{
        Request req=new Request(Operation.OPERATION_INSERT_REZERVACIJA, rezervacija);
        ObjectOutputStream oos=new ObjectOutputStream(session.Session.getInstance().getSocket().getOutputStream());
        oos.writeObject(req);
        ObjectInputStream ois=new ObjectInputStream(session.Session.getInstance().getSocket().getInputStream());
        Response res=(Response)ois.readObject();
        if(res.getResponseStatus()==ResponseStatus.Error)
            throw res.getException();
    }
    
    public synchronized void updateKlijent(Klijent klijent) throws Exception{
        Request req=new Request(Operation.OPERATION_UPDATE_KLIJENT, klijent);
        ObjectOutputStream oos=new ObjectOutputStream(session.Session.getInstance().getSocket().getOutputStream());
        oos.writeObject(req);
        ObjectInputStream ois=new ObjectInputStream(session.Session.getInstance().getSocket().getInputStream());
        Response res=(Response)ois.readObject();
        if(res.getResponseStatus()==ResponseStatus.Error)
            throw res.getException();
    }
    
    public synchronized void updateZaposleni(Zaposleni zaposleni) throws Exception{
        Request req=new Request(Operation.OPERATION_UPDATE_ZAPOSLENI, zaposleni);
        ObjectOutputStream oos=new ObjectOutputStream(session.Session.getInstance().getSocket().getOutputStream());
        oos.writeObject(req);
        ObjectInputStream ois=new ObjectInputStream(session.Session.getInstance().getSocket().getInputStream());
        Response res=(Response)ois.readObject();
        if(res.getResponseStatus()==ResponseStatus.Error)
            throw res.getException();
    }
    
    public synchronized void updateSoba(Soba soba) throws Exception{
        Request req=new Request(Operation.OPERATION_UPDATE_SOBA, soba);
        ObjectOutputStream oos=new ObjectOutputStream(session.Session.getInstance().getSocket().getOutputStream());
        oos.writeObject(req);
        ObjectInputStream ois=new ObjectInputStream(session.Session.getInstance().getSocket().getInputStream());
        Response res=(Response)ois.readObject();
        if(res.getResponseStatus()==ResponseStatus.Error)
            throw res.getException();
    }
    
    public synchronized void updateTipSobe(TipSobe tipSobe) throws Exception{
        Request req=new Request(Operation.OPERATION_UPDATE_TIPSOBE, tipSobe);
        ObjectOutputStream oos=new ObjectOutputStream(session.Session.getInstance().getSocket().getOutputStream());
        oos.writeObject(req);
        ObjectInputStream ois=new ObjectInputStream(session.Session.getInstance().getSocket().getInputStream());
        Response res=(Response)ois.readObject();
        if(res.getResponseStatus()==ResponseStatus.Error)
            throw res.getException();
    }
    
    public synchronized void updateUsluga(Usluga usluga) throws Exception{
        Request req=new Request(Operation.OPERATION_UPDATE_USLUGA, usluga);
        ObjectOutputStream oos=new ObjectOutputStream(session.Session.getInstance().getSocket().getOutputStream());
        oos.writeObject(req);
        ObjectInputStream ois=new ObjectInputStream(session.Session.getInstance().getSocket().getInputStream());
        Response res=(Response)ois.readObject();
        if(res.getResponseStatus()==ResponseStatus.Error)
            throw res.getException();
    }
    
    public synchronized void updateRezervacija(Rezervacija rezervacija) throws Exception{
        Request req=new Request(Operation.OPERATION_UPDATE_REZERVACIJA, rezervacija);
        ObjectOutputStream oos=new ObjectOutputStream(session.Session.getInstance().getSocket().getOutputStream());
        oos.writeObject(req);
        ObjectInputStream ois=new ObjectInputStream(session.Session.getInstance().getSocket().getInputStream());
        Response res=(Response)ois.readObject();
        if(res.getResponseStatus()==ResponseStatus.Error)
            throw res.getException();
    }
    
     public synchronized void deleteZaposleni(Zaposleni zaposleni) throws Exception{
        Request req=new Request(Operation.OPERATION_DELETE_ZAPOSLENI, zaposleni);
        ObjectOutputStream oos=new ObjectOutputStream(session.Session.getInstance().getSocket().getOutputStream());
        oos.writeObject(req);
        ObjectInputStream ois=new ObjectInputStream(session.Session.getInstance().getSocket().getInputStream());
        Response res=(Response)ois.readObject();
        if(res.getResponseStatus()==ResponseStatus.Error)
            throw res.getException();
    }
    
      public synchronized void deleteSoba(Soba soba) throws Exception{
        Request req=new Request(Operation.OPERATION_DELETE_SOBA, soba);
        ObjectOutputStream oos=new ObjectOutputStream(session.Session.getInstance().getSocket().getOutputStream());
        oos.writeObject(req);
        ObjectInputStream ois=new ObjectInputStream(session.Session.getInstance().getSocket().getInputStream());
        Response res=(Response)ois.readObject();
        if(res.getResponseStatus()==ResponseStatus.Error)
            throw res.getException();
    }
    
    public synchronized void deleteTipSobe(TipSobe tipSobe) throws Exception{
        Request req=new Request(Operation.OPERATION_DELETE_TIPSOBE, tipSobe);
        ObjectOutputStream oos=new ObjectOutputStream(session.Session.getInstance().getSocket().getOutputStream());
        oos.writeObject(req);
        ObjectInputStream ois=new ObjectInputStream(session.Session.getInstance().getSocket().getInputStream());
        Response res=(Response)ois.readObject();
        if(res.getResponseStatus()==ResponseStatus.Error)
            throw res.getException();
    }
    
     public synchronized void deleteUsluga(Usluga usluga) throws Exception{
        Request req=new Request(Operation.OPERATION_DELETE_USLUGA, usluga);
        ObjectOutputStream oos=new ObjectOutputStream(session.Session.getInstance().getSocket().getOutputStream());
        oos.writeObject(req);
        ObjectInputStream ois=new ObjectInputStream(session.Session.getInstance().getSocket().getInputStream());
        Response res=(Response)ois.readObject();
        if(res.getResponseStatus()==ResponseStatus.Error)
            throw res.getException();
    }
     
     public synchronized void deleteRezervacija(Rezervacija rezervacija) throws Exception{
        Request req=new Request(Operation.OPERATION_DELETE_REZERVACIJA, rezervacija);
        ObjectOutputStream oos=new ObjectOutputStream(session.Session.getInstance().getSocket().getOutputStream());
        oos.writeObject(req);
        ObjectInputStream ois=new ObjectInputStream(session.Session.getInstance().getSocket().getInputStream());
        Response res=(Response)ois.readObject();
        if(res.getResponseStatus()==ResponseStatus.Error)
            throw res.getException();
    } 

    
}
