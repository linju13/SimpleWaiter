/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JAXBTest;

import beans.Getraenk;
import beans.Getraenkelist;
import enums.EinheitenEnum;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import xml.XMLAccess;

/**
 *
 * @author Laura
 */
public class Test {
    
    private Getraenkelist getraenke;
    
    private void createGetraenke()
    {
        getraenke = new Getraenkelist();
        getraenke.addGeatraenk(new Getraenk("Bacardi Cola", 0.3, 3.5, EinheitenEnum.LITER));
        getraenke.addGeatraenk(new Getraenk("Red Bull", 0.25, 4, EinheitenEnum.LITER));
        getraenke.addGeatraenk(new Getraenk("Gin Tonic", 0.3, 4.1, EinheitenEnum.LITER));
        getraenke.addGeatraenk(new Getraenk("Tequila", 0.2, 2.2, EinheitenEnum.CL));

    }
    
    private void testXMLAccess()
    {
        XMLAccess xml = XMLAccess.getInstance();
        try {
            xml.marshall(getraenke);
        } catch (JAXBException ex) {
              ex.printStackTrace();
        } catch (MalformedURLException ex) {
             ex.printStackTrace();
        } catch (FileNotFoundException ex) {
             ex.printStackTrace();
         }
        
        Getraenkelist gr = null;
        try {
           gr =  xml.unmarshall();
        } catch (JAXBException ex) {
             ex.printStackTrace();
         } catch (MalformedURLException ex) {
           ex.printStackTrace();
        }
        
        gr.getGetraenke().stream().forEach(System.out::println);
    }
    
    public static void main(String[] args) {
      Test t = new Test();
      t.createGetraenke();
      t.testXMLAccess();
    }
}
