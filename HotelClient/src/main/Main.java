/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import form.login.LoginForm;
import form.login.LoginController;
import java.net.Socket;

/**
 *
 * @author Boris
 */
public class Main {
 
    public static void main(String[] args) {
        try{
            LoginForm forma = new LoginForm();
            LoginController control = new LoginController(forma);
            forma.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
