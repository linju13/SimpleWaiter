package TesteBeans;


import beans.Bestellung;
import beans.Getraenk;
import bl.ClientBL;
import enums.EinheitenEnum;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Corinna B
 */
public class Tester {
    public static void main(String[] args) {
        Bestellung b = new Bestellung(Date.from(Instant.now()), new HashMap<Getraenk, Integer>(), 0, 0);
        b.getraenkHinzufuegen(b.getGetraenk("Cola", 1, 1.5, EinheitenEnum.LITER), 2);
        b.getraenkHinzufuegen(b.getGetraenk("Fanta", 0.5, 1.5, EinheitenEnum.LITER), 3);
        b.getraenkHinzufuegen(b.getGetraenk("Sprite", 1.5, 1.5, EinheitenEnum.LITER), 1);
        b.getraenkHinzufuegen(b.getGetraenk("Wasser", 2, 1.5, EinheitenEnum.LITER), 4);
        b.getraenkHinzufuegen(b.getGetraenk("Fanta", 0.5, 1.5, EinheitenEnum.LITER), 3);

        b.getraenkStornieren(b.getGetraenk("Fanta", 0.5, 1.5, EinheitenEnum.LITER), 2);
        b.getraenkStornieren(b.getGetraenk("Wasser", 2, 1.5, EinheitenEnum.LITER), 2);
        
        for(Getraenk g : b.getGetraenke().keySet()){
            System.out.println(g.getName() + " - " + b.getGetraenke().get(g));
        }
        
        ClientBL bl = new ClientBL();
        bl.bestellungHinzufuegen(b);
        bl.bestellungAbschließen(b);
        
    }
}
