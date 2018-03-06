/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import beans.Getraenk;
import beans.Getraenkelist;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Laura
 */
public class XMLAccess {
    private static XMLAccess theInstance;
    
    public static XMLAccess getInstance(String fileName)
    {
        if(theInstance == null)
        {
            theInstance = new XMLAccess(fileName);
        }
        return theInstance;
    }
    
    private XMLAccess(String fileName)
    {
        if(!fileName.equals(""))
        {
            this.fileName = fileName;
        }
    }
    
    private String fileName = System.getProperty("user.dir") + File.separator + "src" + File.separator + "data" + File.separator + "getraenkeliste.xml";
    
     public void marshall(Getraenkelist g) throws JAXBException, MalformedURLException, FileNotFoundException 
    {
           JAXBContext context = JAXBContext.newInstance(Getraenkelist.class);
            
            Marshaller marsh = context.createMarshaller();
            marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
           
            marsh.marshal(g, System.out);
            marsh.marshal(g, new FileOutputStream(fileName));
            
    }
     
      public Getraenkelist unmarshall() throws JAXBException, MalformedURLException 
    {
         JAXBContext context = JAXBContext.newInstance(Getraenkelist.class);
        Unmarshaller unmarsh = context.createUnmarshaller();
        Getraenkelist list = (Getraenkelist) unmarsh.unmarshal(new File(fileName));
        return list;
    }
}
