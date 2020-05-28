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
import com.mycompany.myapp.entities.Booking;
import com.mycompany.myapp.entities.Books;
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
public class ServiceBooking {
         public ArrayList<Booking> booking;
     public LinkedHashMap<String, Object> user = new LinkedHashMap<>();
     public LinkedHashMap<String, Object> book = new LinkedHashMap<>();
    public static ServiceBooking instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceBooking() {
         req = new ConnectionRequest();
    }

    public static ServiceBooking getInstance() {
        if (instance == null) {
            instance = new ServiceBooking();
        }
        return instance;
    }
    public ArrayList<Booking> getBookingByUser(){
        ArrayList<Booking> bbking = new ArrayList<>();
           for (Booking bb : ServiceBooking.getInstance().getAllBooking()) {

            if (User.getCurrentId().equals(bb.getUser().getId().toString())) {
                bbking.add(bb);

            }

        }
           return bbking;
    }
    
       public Booking checkBooking(Books b) {
        for (Booking l : ServiceBooking.getInstance().getBookingByUser()) {

            if ((l.getBook().getIdbook() == b.getIdbook())) {

                return l;

            }

        }
        return null;
    }
    public boolean deleteBooking(Booking t) {
        System.out.println(t);
        String url = Statics.BASE_URL + "/books/deleteJsonBooking/"+ t.getIdBooking();
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
    public boolean addBooking(Booking t) {
        System.out.println(t);
        String url = Statics.BASE_URL + "/books/addJsonBooking/"+ t.getBook().getIdbook() + "/" + t.getUser().getId();
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

    public ArrayList<Booking> parseBooking(String jsonText){
        try {
            booking=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> bookingListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)bookingListJson.get("root");
            for(Map<String,Object> obj : list){
                
                Booking b = new Booking();
                float id = Float.parseFloat(obj.get("idreservation").toString());
                b.setIdBooking((int)id);
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
          
                booking.add(b);
                
             
            }
            
            
        } catch (IOException ex) {
            ex.getMessage();
            
        }
        return booking;
    }
    
    public ArrayList<Booking> getAllBooking(){
        String url = Statics.BASE_URL+"/books/books/allBooking";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                booking = parseBooking(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return booking;
    }
    
}
