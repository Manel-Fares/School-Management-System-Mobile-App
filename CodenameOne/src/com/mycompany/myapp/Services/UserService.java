/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Services;

import com.codename1.ui.util.Resources;
import com.codename1.charts.models.CategorySeries;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;

import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Absence;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.gui.AjoutNoteForm;
import com.mycompany.myapp.gui.GestionNoteForm;
import com.mycompany.myapp.gui.ListResultatForm;
import com.mycompany.myapp.gui.StaffInterfaceForm;
import com.mycompany.myapp.gui.StudentInterfaceForm;
import com.mycompany.myapp.gui.TeacherInterfaceForm;
import com.mycompany.myapp.gui.listeNoteEtudiants;
import com.mycompany.myapp.gui.statUserForm;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Aymen
 */
public class UserService {

    public ArrayList<User> users;

    public static UserService instance = null;
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

    public ArrayList<User> parseUsers(String jsonText) {
        try {
            users = new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String, Object> usersListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) usersListJson.get("root");

            for (Map<String, Object> obj : list) {

                User u = new User();
                float idE = Float.parseFloat(obj.get("id").toString());
                int id = (int) idE;
                u.setId(String.valueOf(id));
                u.setUsername(obj.get("username").toString());
                float cin = Float.parseFloat(obj.get("numteluser").toString());
                int numcin = (int) cin;
                u.setCinUser(numcin);
             //   System.out.println("*********************" + obj.get("cinuser").toString());
                u.setNomUser(obj.get("nomuser").toString());
                u.setPrenomUser(obj.get("prenomuser").toString());
                u.setPassword(obj.get("password").toString());
                u.setRoleUser(obj.get("roles").toString());
                u.setPicUser(obj.get("picuser").toString());
                float nt = Float.parseFloat(obj.get("numteluser").toString());

                int num = (int) nt;
                u.setNumTelUser(num);
                u.setEmail(obj.get("email").toString());
                users.add(u);
            }

        } catch (IOException ex) {

        }
        return users;
    }

    public ArrayList<User> getAllUsers() {
        String url = Statics.BASE_URL + "user/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }

    public boolean addUser(User user) {

        String url = Statics.BASE_URL + "school/" + user.getUsername() + "/" + user.getNomUser() + "/" + user.getPrenomUser()
                + "/" + user.getCinUser() + "/" + user.getEmail() + "/" + user.getSexeUser() + "/" + user.getPassword() + "/" + user.getRoleUser();
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
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

    public void deleteUser(String u) {
        String url = Statics.BASE_URL + "user/delete/" + u;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
                if (users.isEmpty()) {
                    Dialog.show("Success", "User deleted", new Command("OK"));
                    //    new ListUsersForm().show();
                } else {
                    Dialog.show("ERROR", "Failed to Delete the User. Try again!", new Command("OK"));
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public ArrayList<User> getConnectedUser(String u, String p, Resources res) {
        String url = Statics.BASE_URL + "user/login/" + u + "/" + p;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
                if (users.isEmpty()) {
                    Dialog.show("ERROR", "Bad Credentials. Try again", new Command("OK"));
                } else {
                    User connected = new User();
                    for (User obj : users) {
                        if (u.equals(obj.getUsername())) {
                            // System.out.println(obj.getId());
                            connected.setId(obj.getId());
                            User.setCurrentuser(connected);
                      //      System.out.println("this is the connected user" + User.getCurrentuser());

                            User.setCurrentId(obj.getId());
                            User.setCurrentEmail(obj.getEmail());
                            User.setCurrentPic(obj.getPicUser());
                            User.setCurrentNom(obj.getNomUser());
                            User.setCurrentPrenom(obj.getPrenomUser());
                         //   int num = String.valueOf();
                            User.setCurrentNumTelUser(obj.getNumTelUser());
                       //     System.out.println("num="+User.getCurrentNumTelUser());
                      //      User.setCurrentNu(obj.getNumTelUser()
                            
                        //    System.out.println("num " + obj.getNumTelUser());
                            if (obj.getRoleUser().equals("[ROLE_ENSEIGNANT]")) {
                                 new AjoutNoteForm(res).show();
                            } else if (obj.getRoleUser().equals("[ROLE_ETUDIANT]")) {

                                 new listeNoteEtudiants(res).show();

                                

                            } else if (obj.getRoleUser().equals("[ROLE_PERSONNEL]")) {
                                new ListResultatForm(res).show();
                            } else {
                                new statUserForm(res).show();
                            }

                        }
                    }

                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }

    public ArrayList<User> getEtudiants(String id) {
        String url = Statics.BASE_URL + "user/getEtud/" + id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }

    public String getPassword(String u) {
        String url = Statics.BASE_URL + "user/password/" + u;
        String pw = new String();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);

            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        if (users.isEmpty()) {
            Dialog.show("ERROR", "Invalid Username ", new Command("OK"));
        } else {
            for (User obj : users) {
                return obj.getPassword();
            }

        }
        return pw;
    }

    /**
     * Builds a category series using the provided values.
     *
     * @param titles the series titles
     * @param values the values
     * @return the category series
     */
    protected CategorySeries buildCategoryDataset(String title, double[] values) {
        CategorySeries series = new CategorySeries(title);

        series.add("Student ", values[0]);
        series.add("Teacher ", values[1]);
        series.add("Staff ", values[2]);
        series.add("Administrator ", values[3]);

        return series;
    }

}
