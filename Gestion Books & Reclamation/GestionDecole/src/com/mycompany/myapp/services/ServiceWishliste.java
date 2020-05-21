/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Books;
import com.mycompany.myapp.entities.Likes;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.Wishliste;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Neifos
 */
public class ServiceWishliste {
         public ArrayList<Wishliste> wishliste;
     public LinkedHashMap<String, Object> user = new LinkedHashMap<>();
     public LinkedHashMap<String, Object> book = new LinkedHashMap<>();
     
    public static ServiceWishliste instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceWishliste() {
         req = new ConnectionRequest();
    }

    public static ServiceWishliste getInstance() {
        if (instance == null) {
            instance = new ServiceWishliste();
        }
        return instance;
    }

    public boolean addWishliste(Wishliste t) {
        String url = Statics.BASE_URL + "/books/addWishlisteJson/"+t.getBook().getIdbook()+"/"+t.getUser().getId();
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
 public boolean deleteWishliste(Wishliste t) {
        String url = Statics.BASE_URL + "/books/deleteWishlisteJson/"+t.getIdwish();
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
    public ArrayList<Wishliste> parseWishliste(String jsonText){
        try {
            wishliste=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> WishlisteListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)WishlisteListJson.get("root");
            for(Map<String,Object> obj : list){
                
                Wishliste b = new Wishliste();
                float id = Float.parseFloat(obj.get("idlist").toString());
                b.setIdwish((int)id);
                user = (LinkedHashMap) obj.get("idetd");
                   User u = new User();
                for (Map.Entry<String, Object> entry : user.entrySet()) {
                    
                    if (entry.getKey().contains("id")) {
                        String pp = entry.getValue().toString();
           
                        int idd =(int) Float.parseFloat(pp);
                        u.setId(String.valueOf(idd));
                    }

                }
                               book = (LinkedHashMap) obj.get("idbook");
                Books bk = new Books();
                       for (Map.Entry<String, Object> entry : book.entrySet()) {
                    if (entry.getKey().contains("idbook")) {
                        bk.setIdbook((int)Float.parseFloat(entry.getValue().toString()));
                    }
                    if (entry.getKey().contains("titrebook")) {
                        bk.setTitrebook(entry.getValue().toString());
                       
                    } 
                    if (entry.getKey().contains("descriptionbook")) {
                        bk.setDescriptionbook(entry.getValue().toString());
                    }
                     if (entry.getKey().contains("picbook")) {
                        bk.setPicbook(entry.getValue().toString());
                    }

                }
             
               b.setUser(u);
               b.setBook(bk);
          
                wishliste.add(b);
                
             
            }
            
            
        } catch (IOException ex) {
            ex.getMessage();
            
        }
        return wishliste;
    }
    
    public ArrayList<Wishliste> getAllWishliste(){
        String url = Statics.BASE_URL+"/books/books/allWishliste";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                wishliste = parseWishliste(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return wishliste;
    }
    
}
