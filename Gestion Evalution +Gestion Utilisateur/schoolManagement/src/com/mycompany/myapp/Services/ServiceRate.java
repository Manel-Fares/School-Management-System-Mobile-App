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
import com.mycompany.myapp.entities.Rating;
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
public class ServiceRate {

    public LinkedHashMap<String, Object> aa = new LinkedHashMap<>();

    public ArrayList<Club> clubb;
    public Map<Club, Float> clubs;
    public ArrayList<User> u;

    public static ServiceRate instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    private ConnectionRequest cr;

    private ServiceRate() {
        req = new ConnectionRequest();
    }

    public static ServiceRate getInstance() {
        if (instance == null) {
            instance = new ServiceRate();
        }
        return instance;
    }

    public Map<Club, Float> parseClub(String jsonText) {
        Map<Club, Float> hm = new HashMap();
        System.out.println("ggggggg");

        try {

            clubb = new ArrayList<>();
            u = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println("jesontext parseClub: " + jsonText);
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            System.out.println("List parseClub: " + list);
            
            for (Map<String, Object> obj : list) {

                Club t = new Club();
                User u = new User();
                System.out.println("hello nomclub " + obj);
                    float idd = Float.parseFloat(obj.get("id").toString());
                u.setId(obj.get("id").toString());
                u.setUsername(obj.get("username").toString());
                u.setEmail(obj.get("email").toString());
                float id = Float.parseFloat(obj.get("idclub").toString());
                t.setIdClub((int) id);
                t.setResponsable(u);
                t.setImage(obj.get("image").toString());
                t.setNomClub(obj.get("nomclub").toString());
                t.setDomaine(obj.get("domaine").toString());
                clubb.add(t);
                System.out.println(obj.get("rating"));
             if(obj.get("rating")==null){
                  hm.put(t,(float) 0); }
//                System.out.println("Rate: " + obj.get("rating").toString());
             else{
               hm.put(t, Float.parseFloat(obj.get("rating").toString()));
                System.out.println("hm" + hm);}
            }

        } catch (IOException ex) {

        }
        return hm;
    }

    public Map<Club, Float> getAllTasks(int id) {
        System.out.println("idddd:   " + id);
        String url = Statics.BASE_URL + "user/club/al/"+id;
        cr = new ConnectionRequest(url);
        //   cr.setUrl(url);
        System.out.println("cr: " + cr.getUrl());

        cr.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("hello omaa jmai ");
                String res = new String(cr.getResponseData());

                System.out.println(res);
                clubs = parseClub(res);
                System.out.println("bbb :" + clubs);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(cr);
        return clubs;
    }
    
       public boolean addRate(Rating r) {
         String url = Statics.BASE_URL + "user/club/add/"+r.getRating()+"/"+r.getC().getIdClub()+"/"+r.getEt().getId();
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

}
