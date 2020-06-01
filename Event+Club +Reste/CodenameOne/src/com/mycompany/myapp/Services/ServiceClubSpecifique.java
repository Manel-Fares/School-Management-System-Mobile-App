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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asus
 */
public class ServiceClubSpecifique {
     Map<Club, Float> clubbs;
    public static ServiceClubSpecifique instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    private ConnectionRequest cr;

    private ServiceClubSpecifique() {
        req = new ConnectionRequest();
    }

    public static ServiceClubSpecifique getInstance() {
        if (instance == null) {
            instance = new ServiceClubSpecifique();
        }
        return instance;
    }
    public Map<Club, Float> getClubResponsable(int id) {
        
        String url = Statics.BASE_URL + "user/club/clubresp/"+id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                String res = new String(req.getResponseData());

                System.out.println("resaa: " + res);
                clubbs = parseClubResponsable(res);
                //System.out.println("aaaa :" + clubs);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return clubbs;
    }

    public Map<Club, Float> parseClubResponsable(String jsonText) {
        Map<Club, Float> hm = new HashMap();

        try {

            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println("tasksListJson 14785496577: " + tasksListJson);
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            //  System.out.println("list: " + list);
            for (Map<String, Object> obj : list) {

                Club e = new Club();
                User u = new User();
                float idd = Float.parseFloat(obj.get("idclub").toString());
                e.setIdClub((int) idd);
                e.setNomClub(obj.get("nomclub").toString());
                e.setDomaine(obj.get("domaine").toString());
                e.setImage(obj.get("image").toString());
                float rating = Float.parseFloat(obj.get("rating").toString());
                e.setRating(rating);

                if (obj.get("x") == null) {
                    hm.put(e, (float) 0);
                } else {
                    float nbrEvenements = Float.parseFloat(obj.get("x").toString()) * 100;
                    hm.put(e, nbrEvenements);
                    //   System.out.println("hm" + hm);
                }
            }

        } catch (IOException ex) {

        }

        return hm;
    }
   
}
