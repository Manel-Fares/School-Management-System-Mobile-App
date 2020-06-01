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

/**
 *
 * @author asus
 */
public class DemandespecifiqueService {
    
    
    
        public LinkedHashMap<String, Object> aa = new LinkedHashMap<>();

    public ArrayList<Club> clubb;
    public Map<Club, Float> clubs;
    public ArrayList<User> u;

    public static DemandespecifiqueService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    private ConnectionRequest cr;

    private DemandespecifiqueService() {
        req = new ConnectionRequest();
    }

    public static DemandespecifiqueService getInstance() {
        if (instance == null) {
            instance = new DemandespecifiqueService();
        }
        return instance;
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
boolean xx=false;
    public boolean getTestResp(int id) {
        System.out.println("************************id=:"+id);
        String url = "http://localhost/projet/schoolMgt/web/app_dev.php/user/c/"+id;
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String res = new String(req.getResponseData());
                System.out.println("resultats user tesr*************************************************************************: "+res);
                xx = parseTestPart(res);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return xx;
    }
    boolean yy=true;
 public boolean parseTestPart(String jsonText)  {
     System.out.println("jsonText parseTestPart: "+jsonText);
      if(jsonText.equals("[null]"))
         {
                return false;
         }
     
      
  return true;
 }
  public boolean editClub(Club c) {
        String url =Statics.BASE_URL +"user/club/clubedit/"+c.getIdClub()+"/"+c.getImage()+"/"+c.getDomaine();
        req.setUrl(url);
        System.out.println("et voilaaa:  "+url);
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
