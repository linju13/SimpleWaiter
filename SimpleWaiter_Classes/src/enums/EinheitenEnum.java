package enums;


import java.io.Serializable;


public enum EinheitenEnum implements Serializable
{   
    LITER("Liter", "l");
    
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
}
