/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;



/**
 *
 * @author Pytrooooo
 */
public class Emplois {
    private int IdEmplois;
    private String Date;
    private String Heure;
    private String Source;
    private Class nameclass;

    public Emplois(int IdEmplois, String Date, String Heure, String Source, Class nameclass) {
        this.IdEmplois = IdEmplois;
        this.Date = Date;
        this.Heure = Heure;
        this.Source = Source;
        this.nameclass = nameclass;
    }
    public Emplois(int IdEmplois, String Date, String Heure, String Source) {
        this.IdEmplois = IdEmplois;
        this.Date = Date;
        this.Heure = Heure;
        this.Source = Source;
       
    }

    public Emplois(String Date, String Heure, String Source, Class nameclass) {
        this.Date = Date;
        this.Heure = Heure;
        this.Source = Source;
        this.nameclass = nameclass;
    }
    public Emplois() {
    }

     public Emplois(String Date, String Heure, String Source) {
        this.Date = Date;
        this.Heure = Heure;
        this.Source = Source;
    }
    
    public int getIdEmplois() {
        return IdEmplois;
    }

    public void setIdEmplois(int IdEmplois) {
        this.IdEmplois = IdEmplois;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getHeure() {
        return Heure;
    }

    public void setHeure(String Heure) {
        this.Heure = Heure;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String Source) {
        this.Source = Source;
    }

    public Class getNameclass() {
        return nameclass;
    }

    public void setNameclass(Class nameclass) {
        this.nameclass = nameclass;
    }

  
    
    
}
