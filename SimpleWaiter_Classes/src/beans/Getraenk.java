package beans;


import enums.EinheitenEnum;
import java.io.Serializable;


public class Getraenk implements Serializable
{
    //Attribute
    private String name;
    private double menge;
    private double preis;
    private EinheitenEnum einheit;

    public Getraenk(String name, double menge, double preis, EinheitenEnum einheit) {
        this.name = name;
        this.menge = menge;
        this.preis = preis;
        this.einheit = einheit;
    }

    
    
    
    //getter
    public String getName() {
        return name;
    }

    public double getMenge() {
        return menge;
    }

    public double getPreis() {
        return preis;
    }

    public EinheitenEnum getEinheit() {
        return einheit;
    }
    
    
}
