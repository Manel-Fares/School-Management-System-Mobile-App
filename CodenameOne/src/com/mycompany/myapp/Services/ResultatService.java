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
import com.codename1.messaging.Message;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Note;
import com.mycompany.myapp.entities.Resultat;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.gui.ListResultatForm;
import com.mycompany.myapp.gui.ListUsersForm;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Aymen
 */
public class ResultatService {
    public ArrayList<Resultat> resultats;
    
    public LinkedHashMap<String, Object> subject = new LinkedHashMap<>();
    public LinkedHashMap<String, Object> teacher = new LinkedHashMap<>();
    public LinkedHashMap<String, Object> student = new LinkedHashMap<>();
    public LinkedHashMap<String, Object> classest = new LinkedHashMap<>();

    public static ResultatService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ResultatService() {
        req = new ConnectionRequest();
    }

    public static ResultatService getInstance() {
        if (instance == null) {
            instance = new ResultatService();
        }
        return instance;
    }

    public ArrayList<Resultat> parseResultat(String jsonText) {
        try {
            resultats = new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String, Object> usersListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) usersListJson.get("root");

            for (Map<String, Object> obj : list) {

                Resultat r = new Resultat();
             
                r.setResultat(Float.parseFloat(obj.get("resultat").toString()));
                student = (LinkedHashMap) obj.get("etudiant");
                   
                for (Map.Entry<String, Object> entry : student.entrySet()) {
                    User u = new User();
                    if (entry.getKey().contains("id")) {
                        String pp = entry.getValue().toString();
           
                        int idd =(int) Float.parseFloat(pp);
                        r.getEtudiant().setId(String.valueOf(idd));
                    }
                     if (entry.getKey().contains("prenomuser")) {

                        r.getEtudiant().setPrenomUser(entry.getValue().toString());
                    }
                      
                     if (entry.getKey().contains("nomuser")) {

                        r.getEtudiant().setNomUser(entry.getValue().toString());
                    }
                  //    System.out.println(r.getEtudiant().getNomUser());

                }
                
               

                resultats.add(r);
            }

        } catch (IOException ex) {

        }
        return resultats;
    }

    public ArrayList<Resultat> getResultat() {
        String url = Statics.BASE_URL + "user/getres";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultats = parseResultat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultats;
    }
     public void deleteResults () {
        String url = Statics.BASE_URL + "user/deleteres";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
                if (resultOK) {
                    Dialog.show("Success", "Results deleted", new Command("OK"));
                    //new interfacePersonnelForm().show();
                } else {
                    Dialog.show("ERROR", "Failed to Delete results. Try again!", new Command("OK"));
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
     public void calculResults () {
        String url = Statics.BASE_URL + "user/calculres";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
                if (resultOK) {
                    Message m = new Message("<html><body>Check out <a href=\"https://www.codenameone.com/\">Codename One</a></body></html>");
                    m.setMimeType(Message.MIME_HTML);

// notice that we provide a plain text alternative as well in the send method
                    boolean success = m.sendMessageViaCloudSync("Go Check your Resultat ", "manel.fares@esprit.tn", "Name Of User", "Message Subject",
                            "Check out Codename One at https://www.codenameone.com/");

                    Dialog.show("Success", "All results have been calculated", new Command("OK"));
                    //new ListResultatForm().show();
                } else {
                    Dialog.show("ERROR", "Failed to calculate the results. Results are already calculated!", new Command("OK"));
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

       public ArrayList<Resultat> getOneResultat(String id) {
             String url = Statics.BASE_URL + "user/resetudiant/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultats = parseResultat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultats;
    }
}
