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
public class ServiceEvenement {

    public LinkedHashMap<String, Object> aa = new LinkedHashMap<>();

    public ArrayList<Evenement> Evenementt;
    public Map<Evenement, Integer> Evenements;
    public ArrayList<User> u;

    public static ServiceEvenement instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    private ConnectionRequest cr;

    private ServiceEvenement() {
        req = new ConnectionRequest();
    }

    public static ServiceEvenement getInstance() {
        if (instance == null) {
            instance = new ServiceEvenement();
        }
        return instance;
    }

    public ArrayList<Evenement> getEvenement() {
        String url = Statics.BASE_URL + "user/event/all";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String res = new String(req.getResponseData());

                Evenementt = parseEvenements(res);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Evenementt;
    }

    public ArrayList<Evenement> parseEvenements(String jsonText) {
        try {
            Evenementt = new ArrayList<>();
            u = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            //System.out.println(tasksListJson);
            // System.out.println("jesontext parseEvenements: " + jsonText);
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            //System.out.println("List parseEvenements: " + list);
            for (Map<String, Object> obj : list) {
                Evenement e = new Evenement();
                float id = Float.parseFloat(obj.get("idevenement").toString());
                e.setIdEvenement((int) id);
                e.setDateDebut(obj.get("datedebut").toString());
                e.setDateFin(obj.get("datefin").toString());
                e.setImage(obj.get("image").toString());

                Club t = new Club();
                aa = (LinkedHashMap) obj.get("idclub");
                User u = new User();

                for (Map.Entry<String, Object> entry : aa.entrySet()) {

                    if (entry.getKey().contains("idclub")) {
                        String pp = entry.getValue().toString();
                        // System.out.println(pp);
                        float idd = Float.parseFloat(pp);
                        t.setIdClub((int) idd);
//                        u.setUsername(entry.getValue().toString());
                    }
                    if (entry.getKey().contains("nomclub")) {
                        t.setNomClub(entry.getValue().toString());
                    }
                    if (entry.getKey().contains("idresponsable")) {
                        //  System.out.println("Responsable: " + entry.getValue().getClass());
                        LinkedHashMap<String, Object> bb = (LinkedHashMap) entry.getValue();

                        for (Map.Entry<String, Object> entryy : bb.entrySet()) {
                            if (entryy.getKey().contains("username")) {
                                u.setUsername(entryy.getValue().toString());
                            }
                            if (entryy.getKey().contains("email")) {
                                u.setEmail(entryy.getValue().toString());
                            }
                            if (entryy.getKey().contains("id")) {
                                String pp = entryy.getValue().toString();
                                float idd = Float.parseFloat(pp);
                                u.setId(pp);

                            }
                        }
                        t.setResponsable(u);
                    }

                }
                e.setIdClub(t);

                Evenementt.add(e);
//                System.out.println("Evenementt: "+Evenementt);
            }

        } catch (IOException ex) {

        }
        return Evenementt;
    }

    public Map<Evenement, Integer> getEeventClubNbrParticipation(int id) {
        // System.out.println("idddd:   " + id);
        String url = Statics.BASE_URL + "user/eventcl/5";
        cr = new ConnectionRequest(url);
        //   cr.setUrl(url);
        //  System.out.println("cr: " + cr.getUrl());

        cr.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                //      System.out.println("hello omaa jmai ");
                String res = new String(cr.getResponseData());

                //    System.out.println(res);
                Evenements = parseEvenementNBrParticipation(res);
                //  System.out.println("bbb :" + Evenemnts);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(cr);
        return Evenements;
    }

    public Map<Evenement, Integer> parseEvenementNBrParticipation(String jsonText) {
        Map<Evenement, Integer> hm = new HashMap();

        try {

            Evenementt = new ArrayList<>();
            u = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            //  System.out.println("tasksListJson: " + tasksListJson);
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            //  System.out.println("list: " + list);
            Club c = new Club();
            for (Map<String, Object> obj : list) {

                Evenement e = new Evenement();
                User u = new User();
                float idd = Float.parseFloat(obj.get("idevenement").toString());
                e.setIdEvenement((int) idd);
                e.setDateDebut(obj.get("datedebut").toString());
                e.setDateFin(obj.get("datefin").toString());
                e.setImage(obj.get("image").toString());
                //   System.out.println("xx: "+(obj.get("x").toString()));
                int nbrParticipants = (int) Float.parseFloat(obj.get("x").toString());
                hm.put(e, nbrParticipants);
                //System.out.println("hm" + hm);
            }

        } catch (IOException ex) {

        }
        return hm;
    }
}
