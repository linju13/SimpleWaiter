
package beans;

import java.util.Date;
import java.util.Map;

public class Bestellung 
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
    
}
