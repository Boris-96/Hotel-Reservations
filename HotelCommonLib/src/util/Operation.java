/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Boris
 */
public interface Operation {
    public static final int OPERATION_LOGIN=1;
        
    public static final int OPERATION_GET_ALL_KLIJENTI=2;
    public static final int OPERATION_GET_ALL_ZAPOSLENI=3;
    public static final int OPERATION_GET_ALL_SOBE=4;
    public static final int OPERATION_GET_ALL_USLUGE=5;
    public static final int OPERATION_GET_ALL_TIPSOBE=6;
    public static final int OPERATION_GET_ALL_REZERVACIJE=7;
    
    public static final int OPERATION_INSERT_KLIJENT=8;
    public static final int OPERATION_UPDATE_KLIJENT=9;
    public static final int OPERATION_DELETE_KLIJENT=10;
    
    public static final int OPERATION_INSERT_TIPSOBE=11;
    public static final int OPERATION_DELETE_TIPSOBE=12;
    public static final int OPERATION_UPDATE_TIPSOBE=13;
    
    public static final int OPERATION_INSERT_SOBA=14;
    public static final int OPERATION_UPDATE_SOBA=15;
    public static final int OPERATION_DELETE_SOBA=16;
    
    public static final int OPERATION_INSERT_ZAPOSLENI=17;
    public static final int OPERATION_UPDATE_ZAPOSLENI=18;
    public static final int OPERATION_DELETE_ZAPOSLENI=19;
    
    public static final int OPERATION_INSERT_USLUGA=20;
    public static final int OPERATION_UPDATE_USLUGA=21;
    public static final int OPERATION_DELETE_USLUGA=22;
    
    public static final int OPERATION_INSERT_REZERVACIJA=23;
    public static final int OPERATION_UPDATE_REZERVACIJA=24;
    public static final int OPERATION_DELETE_REZERVACIJA=25;
    
   
}
