/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import beans.Getraenk;
import beans.Getraenkelist;
import enums.EinheitenEnum;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Laura
 */
public class XMLAccess {
    private static XMLAccess theInstance;
    
    public static XMLAccess getInstance()
    {
        if(theInstance == null)
        {
            theInstance = new XMLAccess();
        }
        return theInstance;
    }
    
    private XMLAccess()
    {

    }
    
    /*private String fileName = System.getProperty("user.dir") + File.separator + "src" + File.separator + "data" + File.separator + "getraenkeliste.xml";
    
    
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
    }*/
    
    public void marshall(Getraenkelist g, String fileName) throws ParserConfigurationException, TransformerConfigurationException, TransformerException 
    {
        DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
        
        Document xmlDoc = builder.newDocument();
        Element root = xmlDoc.createElement("getraenke");
        
        Element getraenkeElement;
        Element mengenElement;
        Element einheitenElement;
        Element preisElement;
        
        for(Getraenk getraenk: g.getGetraenke())
        {
            getraenkeElement = xmlDoc.createElement("getraenk");
            getraenkeElement.setAttribute("name", getraenk.getName());
            
            mengenElement = xmlDoc.createElement("menge");
            mengenElement.setTextContent(getraenk.getMenge()+"");
            
            einheitenElement = xmlDoc.createElement("einheit");
            einheitenElement.setTextContent(getraenk.getEinheit().name());
            
            preisElement = xmlDoc.createElement("preis");
            preisElement.setTextContent(getraenk.getPreis()+"");
            
            getraenkeElement.appendChild(mengenElement);
            getraenkeElement.appendChild(einheitenElement);
            getraenkeElement.appendChild(preisElement);
            root.appendChild(getraenkeElement);
        }

        xmlDoc.appendChild(root);
        
         TransformerFactory tFactory = TransformerFactory.newInstance();   
         Transformer transformer = tFactory.newTransformer();   
         transformer.setOutputProperty(OutputKeys.INDENT, "yes");   
         transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");   
         transformer.transform(new DOMSource(xmlDoc), new StreamResult(new File(fileName)));
    }
     
    public Getraenkelist unmarshall(String filename) throws ParserConfigurationException, SAXException, IOException 
    {
        Getraenkelist getraenke = new Getraenkelist();
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();    
        DocumentBuilder builder = factory.newDocumentBuilder();    
        Document doc = builder.parse(new File(filename)); 
        
        NodeList getraenkeNodes = doc.getElementsByTagName("getraenk");
        Element getraenk;
        String name = "";
        Double menge = 3.0;
        EinheitenEnum einheit = EinheitenEnum.CL;
        Double preis = 3.0;
        
        for(int i = 0; i < getraenkeNodes.getLength(); i++)
        {
            
                getraenk = (Element)getraenkeNodes.item(i);
           
                name = getraenk.getAttributes().item(0).getNodeValue();
                menge = Double.parseDouble(getraenk.getElementsByTagName("menge").item(0).getChildNodes().item(0).getNodeValue());
                einheit = EinheitenEnum.valueOf(getraenk.getElementsByTagName("einheit").item(0).getChildNodes().item(0).getNodeValue());
                preis = Double.parseDouble(getraenk.getElementsByTagName("preis").item(0).getChildNodes().item(0).getNodeValue());

            
            getraenke.addGeatraenk(new Getraenk(name, menge, preis, einheit));
        }
        
        return getraenke;
    }
}
