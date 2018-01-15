/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import beans.Bestellung;
import java.util.LinkedList;

/**
 *
 * @author jürgen
 */
public class ClientBL {
    private Kassa kassastand;
    private LinkedList<Bestellung> bestellungsliste;
    private Bestellung momentanBestellung;
    private int portNr;
    
    public ClientBL(){
        kassastand = new Kassa();
        bestellungsliste = new LinkedList<>();
    }
    
    public void bestellungHinzufuegen(Bestellung b){
        this.bestellungsliste.add(b);
    }
    
    public void bestellungStornieren(Bestellung b){
        this.bestellungsliste.remove(b);
    }
    
    public void bestellungAbschließen(Bestellung b){
        this.kassastand.bestellungZahlen(b.getGesamtSumme());
        kassastand.gewinnBerechnung();
        System.out.println(b.getGesamtSumme());
    }
}
