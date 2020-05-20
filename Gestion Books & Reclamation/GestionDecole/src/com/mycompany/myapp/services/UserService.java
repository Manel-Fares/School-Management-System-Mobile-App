/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.components.InteractionDialog;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.list.DefaultListModel;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.gui.interfaceAdministrateurForm;
import com.mycompany.myapp.gui.interfaceEnseignantForm;
import com.mycompany.myapp.gui.interfaceEtudiantForm;
import com.mycompany.myapp.gui.interfacePersonnelForm;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Aymen
 */
public class UserService {

  
    public ArrayList<User> users;
    
    public static UserService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public UserService() {
         req = new ConnectionRequest();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }
    
        public ArrayList<User> parseUsers(String jsonText){
        try {
            users=new ArrayList<>();
            JSONParser j = new JSONParser();
            
            Map<String,Object> usersListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           
            List<Map<String,Object>> list = (List<Map<String,Object>>)usersListJson.get("root");
            
            for(Map<String,Object> obj : list){
                
                User u = new User();
                String id = (obj.get("id").toString());
                u.setId(id);
                u.setUsername(obj.get("username").toString());
                u.setNomUser(obj.get("nomuser").toString());
                u.setPassword(obj.get("password").toString());
                u.setRoleUser(obj.get("roles").toString());
                users.add(u);
            }
          
        } catch (IOException ex) {
            
        }
        return users;
    }

     
     
      public ArrayList<User> getConnectedUser(String u, String p){
        String url = Statics.BASE_URL+"user/login/"+u+"/"+p;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
                 if (users.isEmpty()) {
                    InteractionDialog d = new InteractionDialog("Login");
                    TextArea popupBody = new TextArea("Bad Credentials. Try again!");
                    popupBody.setUIID("PopupBody");
                    popupBody.setEditable(false);
                    d.setLayout(new BorderLayout());
                    d.add(BorderLayout.CENTER, popupBody);
                    Button ok = new Button("OK");
                    ok.addActionListener((ee) -> d.dispose());
                    d.addComponent(BorderLayout.SOUTH, ok);
                    d.show(0, 0, 0, 0);
                
                 }else{
                 for (User obj : users) {
                        if (u.equals(obj.getUsername())) {
                            System.out.println(obj.getId());
                            User.setCurrentNom(obj.getNomUser());
                            User.setCurrentEmail(obj.getEmail());
                            User.setCurrentCin(obj.getCinUser());
                         
                            if(obj.getRoleUser().equals("[ROLE_ENSEIGNANT]")) {
                                  new interfaceEnseignantForm().show();
                            }
                            else if (obj.getRoleUser().equals("[ROLE_ETUDIANT]")) {
                                  new interfaceEtudiantForm().show();
                            }
                            else if (obj.getRoleUser().equals("[ROLE_PERSONNEL]")) {
                                  new interfacePersonnelForm().show();
                            }
                            else  {
                                  new interfaceAdministrateurForm().show();
                            }
                          
                        }
                    }
                     
                 }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }

   
}

 