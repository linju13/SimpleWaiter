
package beans;

import enums.EinheitenEnum;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Bestellung implements Serializable
{
    //Attribute
    private String bestellid; //"<KellnerNr>-Nr"
                              //hab mir gedacht, dass es dann beim Server 
                              //bei der Anzeige übersichtlicher ist, wenn die
                              //Id dieses Muster hat
    private Date bestellzeit;
    private Map<Getraenk, Integer> getraenke;
    private double gesamtSumme;
    private static int id;
    
    static
    {
        id = 0;
    }

    public Bestellung(Date bestellzeit, int kellnerId) 
    {
        this.bestellzeit = bestellzeit;
        getraenke = new HashMap();
        bestellid = String.format("%02d - %03d", kellnerId, ++id);
    }
    
    public void getraenkHinzufuegen(Getraenk g, int value){
        if(getraenke.containsKey(g)){
            int val = getraenke.get(g)+value;
            getraenke.put(g, val);
        } else {
            getraenke.put(g, value);
        }
        gesamtSumme += value*g.getPreis();
    } 
    
    public void getraenkStornieren(Getraenk g, int value){
        if(getraenke.containsKey(g)){
            int val = getraenke.get(g)-value;
            getraenke.put(g, val); 
            gesamtSumme -= value*g.getPreis();
        }
        
    }
   
    public Getraenk getGetraenk(String name, double menge, double preis, EinheitenEnum num ){
        Getraenk get = null;
        for(Getraenk g: getraenke.keySet()){
            if(g.getName()==name && g.getPreis()==preis && g.getMenge()==menge && g.getEinheit()==num){
                get = g;
            }
        }
        
        if(get==null){
            get = new Getraenk(name, menge, preis, num);
        }
        return get;
    }

    public String getBestellid() {
        return bestellid;
    }

    public void setBestellid(String bestellid) {
        this.bestellid = bestellid;
    }

    public Date getBestellzeit() {
        return bestellzeit;
    }

    public void setBestellzeit(Date bestellzeit) {
        this.bestellzeit = bestellzeit;
    }

    public Map<Getraenk, Integer> getGetraenke() {
        return getraenke;
    }

    public void setGetraenke(Map<Getraenk, Integer> getraenke) {
        this.getraenke = getraenke;
    }

    public double getGesamtSumme() {
        return gesamtSumme;
    }

    public void setGesamtSumme(double gesamtSumme) {
        this.gesamtSumme = gesamtSumme;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Bestellung.id = id;
    }
    
    
   
}
