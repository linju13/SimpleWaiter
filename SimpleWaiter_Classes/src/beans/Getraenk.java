package beans;


import enums.EinheitenEnum;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"name","menge", "einheit", "preis"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Getraenk implements Serializable
{
    @XmlAttribute 
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

    public Getraenk()
    { }
    
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

    @Override
    public String toString() {
        return  name + ", " + menge + " " + einheit.getBezeichnung() + " - " +String.format("%.2f €", preis);
    }
    
    
}
