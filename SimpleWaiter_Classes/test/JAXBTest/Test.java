/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JAXBTest;

import beans.Getraenk;
import beans.Getraenkelist;
import enums.EinheitenEnum;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
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
    
   /* private void testXMLAccess()
    {
        XMLAccess xml = XMLAccess.getInstance("");
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
    }*/
    
    private void testXMLAccess2() throws ParserConfigurationException, TransformerException, SAXException, IOException
    {
        String fileName = System.getProperty("user.dir") + File.separator + "src" + File.separator + "data" + File.separator + "getraenkeliste2.xml";
        
        XMLAccess xml = XMLAccess.getInstance();
        xml.marshall(getraenke, fileName);
        Getraenkelist getraenkelist= xml.unmarshall(fileName);
        
        for(Getraenk getraenk: getraenkelist.getGetraenke())
        {
            System.out.println(getraenk);
        }
    }
    
    public static void main(String[] args) {
      Test t = new Test();
      t.createGetraenke();
      //t.testXMLAccess();
        try {
            t.testXMLAccess2();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
