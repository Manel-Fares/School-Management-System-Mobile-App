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
public class ServiceLike {
        public ArrayList<Likes> likes;
     public LinkedHashMap<String, Object> user = new LinkedHashMap<>();
     public LinkedHashMap<String, Object> book = new LinkedHashMap<>();
    public static ServiceLike instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceLike() {
         req = new ConnectionRequest();
    }

    public static ServiceLike getInstance() {
        if (instance == null) {
            instance = new ServiceLike();
        }
        return instance;
    }

    public boolean addLike(Likes l) {
      
        String url = Statics.BASE_URL + "/books/newlike/" + l.getBook().getIdbook() + "/" + l.getUser().getId();
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
      public boolean deleteLike(Likes l) {
    
        String url = Statics.BASE_URL + "/books/deleteJsonlike/" + l.getIdLike();
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

    public ArrayList<Likes> parseLikes(String jsonText){
        try {
            likes=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> LikesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)LikesListJson.get("root");
            for(Map<String,Object> obj : list){
                
                Likes b = new Likes();
                float id = Float.parseFloat(obj.get("idlike").toString());
                b.setIdLike((int)id);
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
                       
                    } bk.setTitrebook(entry.getValue().toString());
                    if (entry.getKey().contains("descriptionbook")) {
                        bk.setDescriptionbook(entry.getValue().toString());
                    }

                }
             
               b.setUser(u);
               b.setBook(bk);
          
                likes.add(b);
                
             
            }
            
            
        } catch (IOException ex) {
            ex.getMessage();
            
        }
        return likes;
    }
    
    public ArrayList<Likes> getAllLikes(){
        String url = Statics.BASE_URL+"/books/books/allLike";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                likes = parseLikes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return likes;
    }
}
