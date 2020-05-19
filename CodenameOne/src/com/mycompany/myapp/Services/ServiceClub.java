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
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javafx.concurrent.Task;

/**
 *
 * @author bhk
 */
public class ServiceClub {

    public LinkedHashMap<String, Object> aa = new LinkedHashMap<>();

    public ArrayList<Task> tasks;
    public ArrayList<Club> clubb;
    public Map<Club, Float> clubs;
    public ArrayList<User> u;

    public static ServiceClub instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    private ConnectionRequest cr;

    private ServiceClub() {
        req = new ConnectionRequest();
    }

    public static ServiceClub getInstance() {
        if (instance == null) {
            instance = new ServiceClub();
        }
        return instance;
    }

 

    public ArrayList<Club> getClub() {
        String url = Statics.BASE_URL + "user/club/all";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                String res = new String(req.getResponseData());

                //System.out.println("resaa: " + res);
                clubb = parseTasks(res);
                //System.out.println("aaaa :" + clubs);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return clubb;
    }

    public ArrayList<Club> parseTasks(String jsonText) {
        try {
            clubb = new ArrayList<>();
            u = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            //System.out.println(tasksListJson);

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {

                Club t = new Club();
                // Users u =new Users();
                float id = Float.parseFloat(obj.get("idclub").toString());
                t.setIdClub((int) id);

                // Object o=
                /* List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("cc");
                        System.out.println( obj.get("idresponsable").toString().getBytes("username"));*/
                //   java.util.List<Integer> idresponsable = (java.util.List<Integer>)obj.get("idresponsable");
                // System.out.println("idresponsable;;;;;"+idresponsable);
                //Users uu= obj.get("idresponsable");
                aa = (LinkedHashMap) obj.get("idresponsable");

                System.out.println(aa);
                /* String bb=array.get(4).toString();
                System.out.println(""+bb);
                float f=Float.valueOf(bb);*/
                // int xx= Integer.parseInt(bb);
                User u = new User();

                // System.out.println("iiiiiiiiiiiiiiiii"+Integer.parseInt(array.get(4).toString()));
                for (Map.Entry<String, Object> entry : aa.entrySet()) {

                    if (entry.getKey().contains("username")) {
                        u.setUsername(entry.getValue().toString());
                    }
                    if (entry.getKey().contains("email")) {
                        u.setEmail(entry.getValue().toString());
                    }
                    if (entry.getKey().contains("id")) {
                        String pp = entry.getValue().toString();
                        System.out.println(pp);
                        float idd = Float.parseFloat(pp);
                        u.setId(pp);

                    }

                }
                // System.out.println("array: "+array);
                // System.out.println("iddd :"+obj.get("idresponsable"));
                t.setResponsable(u);
                t.setImage(obj.get("image").toString());
                t.setNomClub(obj.get("nomclub").toString());
                t.setDomaine(obj.get("domaine").toString());
                clubb.add(t);
            }

        } catch (IOException ex) {

        }
        return clubb;
    }
    public ArrayList<Club> getClubspcifique(int id) {
        String url = Statics.BASE_URL + "user/club/recuperer/"+id;
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


//    public Map<Club, Float> parseClub(String jsonText) {
//         Map<Club, Float> hm = new HashMap();
//         System.out.println("ggggggg");
//
//        try {
//             
//            clubb = new ArrayList<>();
//            u = new ArrayList<>();
//            JSONParser j = new JSONParser();
//            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//            System.out.println("jesontext: "+jsonText);
//            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
//            for (Map<String, Object> obj : list) {
//
//                Club t = new Club();
//                Users u = new Users();
//                System.out.println("hello nomclub "+obj);
//            /*    float idd = Float.parseFloat(obj.get("id").toString());
//                u.setId((int) idd);*/
//                u.setUsername(obj.get("username").toString());
//                u.setEmail(obj.get("email").toString());
//                float id = Float.parseFloat(obj.get("idclub").toString());
//                t.setIdClub((int) id);
//                t.setResponsable(u);
//                t.setImage(obj.get("image").toString());
//                t.setNomClub(obj.get("nomclub").toString());
//              t.setDomaine(obj.get("domaine").toString());
//                clubb.add(t);
//              
//                hm.put(t, Float.parseFloat(obj.get("rating").toString()));
//                System.out.println("hm" + hm);
//            }
//
//        } catch (IOException ex) {
//
//        }
//        return hm;
//    }
//
//    public Map<Club,Float>  getAllTasks(int id) {
//        System.out.println("iddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
//        String url = "http://localhost/GestionEcole/web/app_dev.php/club/al/"+id;
//        cr=new  ConnectionRequest(url);
//     //   cr.setUrl(url);
//        System.out.println("cr: "+cr.getUrl());
//
//        cr.addResponseListener(new ActionListener<NetworkEvent>() {
//
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//
//                String res = new String(req.getResponseData());
//
//                System.out.println("resultats: " + res);
//                clubs = parseClub(res);
//                System.out.println("bbb :" + clubs);
//
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(cr);
//        return clubs;
//    }

