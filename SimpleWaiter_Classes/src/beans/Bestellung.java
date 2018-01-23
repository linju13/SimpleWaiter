
package beans;

import enums.EinheitenEnum;
import java.io.Serializable;
import java.util.Date;
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

    public Bestellung(Date bestellzeit, Map<Getraenk, Integer> getraenke, double gesamtSumme, int kellnerId) 
    {
        this.bestellzeit = bestellzeit;
        this.getraenke = getraenke;
        this.gesamtSumme = gesamtSumme;
        
        bestellid = String.format("%02d - %03d", kellnerId, ++id);
    }
    
    //getter
    public String getBestellid() {
        return bestellid;
    }

    public Date getBestellzeit() {
        return bestellzeit;
    }

    public Map<Getraenk, Integer> getGetraenke() {
        return getraenke;
    }

    public double getGesamtSumme() {
        return gesamtSumme;
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
    
}
