
package beans;

import java.time.LocalDateTime;
import java.util.Map;

public class Bestellung 
{
    //Attribute
    private long bestellid;
    private LocalDateTime bestellzeit;
    private Map<Getraenk, Integer> getraenke;
    private double gesamtSumme;

    public Bestellung(LocalDateTime bestellzeit, Map<Getraenk, Integer> getraenke, double gesamtSumme) {
        this.bestellzeit = bestellzeit;
        this.getraenke = getraenke;
        this.gesamtSumme = gesamtSumme;
    }
    
    //getter
    public long getBestellid() {
        return bestellid;
    }

    public LocalDateTime getBestellzeit() {
        return bestellzeit;
    }

    public Map<Getraenk, Integer> getGetraenke() {
        return getraenke;
    }

    public double getGesamtSumme() {
        return gesamtSumme;
    }
    
}
