package enums;


import java.io.Serializable;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

//@XmlType(propOrder = {"bezeichnung", "kurzzeichen"})
@XmlEnum
public enum EinheitenEnum implements Serializable
{   
    LITER("Liter", "l"),
    CL("Centiliter", "cl");
    
    //Attribute
    private String bezeichnung;
    private String kurzzeichen;

    private EinheitenEnum(String bezeichnung, String kurzzeichen) 
    {
        this.bezeichnung = bezeichnung;
        this.kurzzeichen = kurzzeichen;
    }

    
    //getter
    public static EinheitenEnum getLITER() {
        return LITER;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public String getKurzzeichen() {
        return kurzzeichen;
    } 

    @Override
    public String toString() {
        return bezeichnung + ", " + kurzzeichen;
    }
    
}
