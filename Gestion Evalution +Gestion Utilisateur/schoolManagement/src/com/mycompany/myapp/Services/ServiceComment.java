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
import com.mycompany.myapp.entities.Books;
import com.mycompany.myapp.entities.Comment;
import com.mycompany.myapp.entities.Likes;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Neifos
 */
public class ServiceComment {

    public ArrayList<Comment> comments;
    public LinkedHashMap<String, Object> user = new LinkedHashMap<>();
    public LinkedHashMap<String, Object> cmnt = new LinkedHashMap<>();

    public static ServiceComment instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceComment() {
        req = new ConnectionRequest();
    }

    public static ServiceComment getInstance() {
        if (instance == null) {
            instance = new ServiceComment();
        }
        return instance;
    }

     public boolean addComment(Comment l) {
      
        String url = Statics.BASE_URL + "books/CommentJson/"+l.getThread_id()+"/"+l.getAuthor().getId()+"/"+l.getBody();
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
     
    public boolean deleteComment(Comment l) {
    
        String url = Statics.BASE_URL + "books/deleteCommentJson/" + l.getId();
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
    public ArrayList<Comment> parseComment(String jsonText) {
        try {
            comments = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> CommentsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) CommentsListJson.get("root");
            for (Map<String, Object> obj : list) {
                User u = new User();

                cmnt = (LinkedHashMap) obj.get("comment");
                Comment b = new Comment();
                System.out.println(cmnt);
                for (Map.Entry<String, Object> entry : cmnt.entrySet()) {
                           if (entry.getKey().compareTo("author")==0) {
                          System.out.println("zzzzzzzzz");
                    System.out.println(entry.getValue());
                       LinkedHashMap<String, Object> bb = (LinkedHashMap) entry.getValue();
                          System.out.println(entry.getValue());

                        for (Map.Entry<String, Object> entryy : bb.entrySet()) {
                            if (entryy.getKey().contains("username")) {
                               
                                u.setUsername(entryy.getValue().toString());
                            }
                             if (entryy.getKey().contains("id")) {
                               
                                u.setId(entryy.getValue().toString());
                            }
                                if (entryy.getKey().contains("email")) {
                               
                                u.setEmail(entryy.getValue().toString());
                            }
                                     if (entryy.getKey().contains("picuser")) {
                               
                                u.setPicUser(entryy.getValue().toString());
                            }
                    }
                      }
                    if (entry.getKey().contains("body")) {
                        String pp = entry.getValue().toString();
                        System.out.println(pp);

                        b.setBody(pp);
                    }
                      if (entry.getKey().contains("id")) {
                     
                        b.setId(entry.getValue().toString());
                    }
                  /*  if (entry.getKey().contains("authorName")) {

                        b.setCreated_at(entry.getValue().toString());
                    }*/
               

                }

                b.setAuthor(u);
                comments.add(b);

            }
        } catch (IOException ex) {
            ex.getMessage();

        }
        return comments;
    }

    public ArrayList<Comment> getAllComments(int id) {
        String url = Statics.BASE_URL + "books/AllCommentJson/" + id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                comments = parseComment(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return comments;
    }

}
