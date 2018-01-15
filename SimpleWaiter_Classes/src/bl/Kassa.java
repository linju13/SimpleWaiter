
package bl;


public class Kassa 
{
    private double kassastand;
    private double startSumme;
    
    public Kassa(){
        this.kassastand = 0;
    }
    
    public void start(double startSumme){
        this.startSumme = startSumme;
    }
    
    public double gewinnBerechnung(){
        return kassastand-startSumme;
    }
    
    public void bestellungZahlen(double summe){
        kassastand += summe;
    }

    public double getKassastand() {
        return kassastand;
    }

    public void setKassastand(double kassastand) {
        this.kassastand = kassastand;
    }

    public double getStartSumme() {
        return startSumme;
    }

    public void setStartSumme(double startSumme) {
        this.startSumme = startSumme;
    }
    
    
}
