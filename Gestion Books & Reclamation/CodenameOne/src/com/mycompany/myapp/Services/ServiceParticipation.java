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
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.entities.Participation;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asus
 */
public class ServiceParticipation {

    public LinkedHashMap<String, Object> aa = new LinkedHashMap<>();
    public ArrayList<Evenement> Evenementt;
    public Map<Evenement, Integer> Evenemnts;
    public ArrayList<User> u;
    public static ServiceParticipation instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    private ConnectionRequest cr;

    private ServiceParticipation() {
        req = new ConnectionRequest();
    }

    public static ServiceParticipation getInstance() {
        if (instance == null) {
            instance = new ServiceParticipation();
        }
        return instance;
    }

    public Map<Evenement, Integer> parseEvenement(String jsonText) {
        Map<Evenement, Integer> hm = new HashMap();
        System.out.println("ggggggg");

        try {

            Evenementt = new ArrayList<>();
            u = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println("tasksListJson: " + tasksListJson);
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
              System.out.println("list: " + list);
            for (Map<String,Object> obj : list) {

                Evenement e = new Evenement();
                User u = new User();
                float idd = Float.parseFloat(obj.get("idevenement").toString());
                e.setIdEvenement((int) idd);
                e.setDateDebut(obj.get("datedebut").toString());
                e.setDateFin(obj.get("datefin").toString());
                e.setImage(obj.get("image").toString());
               // float id = Float.parseFloat(obj.get("idclub").toString());
              
                Evenementt.add(e);
                if(obj.get("x")==null)
                {  hm.put(e, 0);}
                else
                {
                int nbrParticipants=(int)Float.parseFloat(obj.get("x").toString());
                hm.put(e, nbrParticipants);
                System.out.println("hm" + hm);}
            }

        } catch (IOException ex) {

        }
        return hm;
    }

    public Map<Evenement, Integer> getEeventDetail(int id) {
       // System.out.println("idddd:   " + id);
         String url = Statics.BASE_URL + "user/event/alldetail/"+id ;
        cr = new ConnectionRequest(url);
        //   cr.setUrl(url);
        System.out.println("cr: " + cr.getUrl());

        cr.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("hello omaa jmai ");
                String res = new String(cr.getResponseData());

                System.out.println(res);
                Evenemnts = parseEvenement(res);
                //System.out.println("bbb :" + Evenemnts);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(cr);
        return Evenemnts;
    }
    /*************************************************/
     public Map<Evenement, Integer> parseEvenementNBrParticipation(String jsonText) {
        Map<Evenement, Integer> hm = new HashMap();
    

        try {

            Evenementt = new ArrayList<>();
            u = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println("tasksListJson: " + tasksListJson);
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
              System.out.println("list: " + list);
                  Club c=new Club();
            for (Map<String,Object> obj : list) {

                Evenement e = new Evenement();
                User u = new User();
                float idd = Float.parseFloat(obj.get("idevenement").toString());
                e.setIdEvenement((int) idd);
                String nomclub=obj.get("nomclub").toString();
                c.setNomClub(nomclub);
                e.setIdClub(c);
               
              
               // float id = Float.parseFloat(obj.get("idclub").toString());
              
                Evenementt.add(e);
                System.out.println("xx: "+(obj.get("x").toString()));
                int nbrParticipants=(int)Float.parseFloat(obj.get("x").toString());
                hm.put(e, nbrParticipants);
                //System.out.println("hm" + hm);
            }

        } catch (IOException ex) {

        }
        return hm;
    }

 public Map<Evenement, Integer> getEeventNbrParticipation() {
       // System.out.println("idddd:   " + id);
         String url = Statics.BASE_URL + "user/event/nbrPartEvent/";
        cr = new ConnectionRequest(url);
        //   cr.setUrl(url);
        System.out.println("cr: " + cr.getUrl());

        cr.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("hello omaa jmai ");
                String res = new String(cr.getResponseData());

                System.out.println(res);
                Evenemnts = parseEvenementNBrParticipation(res);
               System.out.println("bbb :" + Evenemnts);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(cr);
        return Evenemnts;
    }
/*****************************************************************/
  public boolean addParticipation(Participation p) {
        String url = Statics.BASE_URL + "user/event/add/" + p.getE().getIdEvenement() + "/" + p.getU().getId() ;
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
  boolean xx=false;
    public boolean getTestPart(int id,int idd) {
        
        String url = Statics.BASE_URL + "user/event/test/"+id+"/"+idd;
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String res = new String(req.getResponseData());
                System.out.println("resultats user Part: "+res);
                xx = parseTestPart(res);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return xx;
    }
    boolean yy=true;
 public boolean parseTestPart(String jsonText)  {
     System.out.println("jsonText parseTestPart: "+jsonText);
      if(jsonText.equals("[]"))
         {
                return false;
         }
     
      
  return true;
 }
}
