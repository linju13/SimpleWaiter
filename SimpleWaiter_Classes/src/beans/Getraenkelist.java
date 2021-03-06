/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author Laura
 */
/*@XmlRootElement (name="getraenke")
@XmlType(propOrder = {"getraenke"})
@XmlAccessorType(XmlAccessType.FIELD)*/
public class Getraenkelist implements Serializable
{
    
    //@XmlElement (name="getraenk")
    private LinkedList<Getraenk> getraenke;
    
    private String name;
    
    public Getraenkelist(String n)
    {
        this.name = n;
        getraenke = new LinkedList<>();
    }
    
    public void addGeatraenk(Getraenk g)
    {
        getraenke.add(g);
    }

    public LinkedList<Getraenk> getGetraenke() {
        return getraenke;
    } 

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
