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
import com.mycompany.myapp.utils.Statics;
import com.mycompany.myapp.entities.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Neifos
 */
public class ServiceBook {
        public ArrayList<Books> books;
    
    public static ServiceBook instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceBook() {
         req = new ConnectionRequest();
    }

    public static ServiceBook getInstance() {
        if (instance == null) {
            instance = new ServiceBook();
        }
        return instance;
    }

   /* public boolean addTask(Book t) {
        String url = Statics.BASE_URL + "/tasks/" + t.getName() + "/" + t.getStatus();
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
*/
    public ArrayList<Books> parseBooks(String jsonText){
        try {
            books=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> BooksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)BooksListJson.get("root");
            for(Map<String,Object> obj : list){
                Books b = new Books();
                float id = Float.parseFloat(obj.get("idbook").toString());
                b.setIdbook((int)id);
                b.setTitrebook(obj.get("titrebook").toString());
                b.setCategoriebook(obj.get("categoriebook").toString());
                b.setDescriptionbook(obj.get("descriptionbook").toString());
                b.setPicbook(obj.get("picbook").toString());
                float nbr = Float.parseFloat(obj.get("nbrLike").toString());
                b.setNbrLike((int)nbr);
                
          
                books.add(b);
                
             
            }
            
            
        } catch (IOException ex) {
            ex.getMessage();
            
        }
        return books;
    }
    
    public ArrayList<Books> getAllBooks(){
        String url = Statics.BASE_URL+"/books/books/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                books = parseBooks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return books;
    }
}
