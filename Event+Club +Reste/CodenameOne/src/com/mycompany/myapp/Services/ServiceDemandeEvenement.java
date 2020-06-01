/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Club;
import com.mycompany.myapp.entities.DemandeEvenement;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;


import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asus
 */
public class ServiceDemandeEvenement {
        public LinkedHashMap<String, Object> aa = new LinkedHashMap<>();

    public ArrayList<DemandeEvenement> DemandeEvenementt;
    public ArrayList<DemandeEvenement> DemandeEvenements;
    public ArrayList<User> u;
    public ArrayList<Club> clubb;
    public Map<Club, Float> clubs;
    public static ServiceDemandeEvenement instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    private ConnectionRequest cr;

    private ServiceDemandeEvenement() {
        req = new ConnectionRequest();
    }

    public static ServiceDemandeEvenement getInstance() {
        if (instance == null) {
            instance = new ServiceDemandeEvenement();
        }
        return instance;
    }
    
 public boolean addDemande(DemandeEvenement dev) {
        String url =Statics.BASE_URL + "user/demande/add/"+dev.getDescription()+"/"+dev.getDatedebut()+"/"+dev.getDatefin()
                +"/"+dev.getBudget()+"/"+dev.getIdclub().getNomClub()+"/"+dev.getImage();
        req.setUrl(url);
        //System.out.println("url: "+url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
 public boolean deleteDemande(int dev) {
        String url = Statics.BASE_URL + "user/demande/delete/"+dev;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
 public  ArrayList<DemandeEvenement> getDmndEeventDetail(int id) {
        //System.out.println("idddd:   " + id);
        String url = Statics.BASE_URL + "user/demande/cons/"+id ;
        cr = new ConnectionRequest(url);
        //   cr.setUrl(url);
        //System.out.println("cr: " + cr.getUrl());

        cr.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
               /// System.out.println("hello omaa jmai ");
                String res = new String(cr.getResponseData());

               // System.out.println(res);
                DemandeEvenements = parseDmndEvenement(res);
                //System.out.println("bbb :" + Evenemnts);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(cr);
        return DemandeEvenements;
    }
public  ArrayList<DemandeEvenement> parseDmndEvenement(String jsonText) {
      
     

        try {

            DemandeEvenementt = new ArrayList<>();
            u = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
         //   System.out.println("tasksListJson: " + tasksListJson);
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
             // System.out.println("list: " + list);
            for (Map<String,Object> obj : list) {

                DemandeEvenement e = new DemandeEvenement();
                User u = new User();
                float idd = Float.parseFloat(obj.get("iddemandeevenement").toString());
                e.setIdDemandeEvenement((int) idd);
                e.setDatedebut(obj.get("datedebut").toString());
                e.setDatefin(obj.get("datefin").toString());
                e.setImage(obj.get("image").toString());
                e.setDescription(obj.get("description").toString());
                e.setEtat(obj.get("etat").toString());
                e.setBudget(Float.parseFloat(obj.get("budget").toString()));
               // float id = Float.parseFloat(obj.get("idclub").toString());
              
                DemandeEvenementt.add(e);
               
            }

        } catch (IOException ex) {

        }
        return DemandeEvenementt;
    }
public ArrayList<Club> getClubspcifique(int id) {
        String url =Statics.BASE_URL + "user/club/recuperer/"+id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                String res = new String(req.getResponseData());

                //System.out.println("resaa: " + res);
                clubb = parseClubspecifique(res);
                //System.out.println("aaaa :" + clubs);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return clubb;
    }

public ArrayList<Club> parseClubspecifique(String jsonText) {
        
        System.out.println("ggggggg");

        try {

            clubb = new ArrayList<>();
            u = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println("jesontext parseClubspecifique: " + jsonText);
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            System.out.println("List parseClubspecifique: " + list);
            
            for (Map<String, Object> obj : list) {

                Club t = new Club();
                System.out.println("aaa: ");
                float id = Float.parseFloat(obj.get("idclub").toString());
                t.setIdClub((int) id);
                t.setNomClub(obj.get("nomclub").toString());
                clubb.add(t);
               
            }

        } catch (IOException ex) {

        }
        return clubb;
    }
}
