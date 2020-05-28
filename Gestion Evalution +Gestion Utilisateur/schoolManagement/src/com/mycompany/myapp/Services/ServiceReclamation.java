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
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Books;
import com.mycompany.myapp.entities.Etudiant;
import com.mycompany.myapp.entities.Reclamation;
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
public class ServiceReclamation {
    
        public ArrayList<Reclamation> Reclamation;
         public LinkedHashMap<String, Object> use = new LinkedHashMap<>();
    
    public static ServiceReclamation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceReclamation() {
         req = new ConnectionRequest();
    }

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }

    public boolean addreclamation(Reclamation r) {
        String url = Statics.BASE_URL + "reclamation/newmobile/" + r.getSujetReclamation()+ "/" + r.getDescriptionReclamation()+"/"+r.getUser().getId();
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

    public ArrayList<Reclamation> parseReclamations(String jsonText){
        try {
            Reclamation=new ArrayList<>();
            
            JSONParser j = new JSONParser();
            Map<String,Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
  

            List<Map<String,Object>> list = (List<Map<String,Object>>)ReclamationListJson.get("root");
         
            
              for(Map<String,Object> obj : list){
                  
               
                Reclamation b = new Reclamation();
     
                float id = Float.parseFloat(obj.get("idreclamation").toString());
     
                b.setIdReclamation((int)id);
                b.setSujetReclamation(obj.get("sujetreclamation").toString());
                b.setDescriptionReclamation(obj.get("descriptionreclamation").toString());
                b.setDateCreation(null);
                b.setStatutReclamation(obj.get("statutreclamation").toString());
                           use = (LinkedHashMap) obj.get("idetd");
                User u = new User();
                for (Map.Entry<String, Object> entry : use.entrySet()) {
                    if (entry.getKey().contains("username")) {
                        u.setNomUser(entry.getValue().toString());
                    }
                    if (entry.getKey().contains("email")) {
                        u.setEmail(entry.getValue().toString());
                    }
                    if (entry.getKey().contains("id")) {
                        String pp = entry.getValue().toString();
             
                        int idd =(int) Float.parseFloat(pp);
                        u.setId(String.valueOf(idd));
                    }

                }
          
         
               b.setUser(u);
            
                               
              
           
          
    
                
          
                Reclamation.add(b);
         
            }
            
            
        } catch (IOException ex) {
            ex.getMessage();
            
        }
        return Reclamation;
    }
    
    public ArrayList<Reclamation> getAllReclamations(){
        String url = Statics.BASE_URL+"reclamation/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Reclamation = parseReclamations(new String(req.getResponseData()));    
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
     
        return Reclamation;
      
    }
  
}

