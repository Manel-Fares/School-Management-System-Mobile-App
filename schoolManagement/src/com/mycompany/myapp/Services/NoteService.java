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
import com.mycompany.myapp.entities.Classe;
import com.mycompany.myapp.entities.Matiere;
import com.mycompany.myapp.entities.Note;
import com.mycompany.myapp.entities.User;
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
public class NoteService {

    public ArrayList<Note> notes;
    public ArrayList<Classe> classe;
    public ArrayList<Matiere> matiere;
    public ArrayList<User> user;
    public LinkedHashMap<String, Object> subject = new LinkedHashMap<>();
    public LinkedHashMap<String, Object> teacher = new LinkedHashMap<>();
    public LinkedHashMap<String, Object> student = new LinkedHashMap<>();
    public LinkedHashMap<String, Object> classest = new LinkedHashMap<>();

    public static NoteService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public NoteService() {
        req = new ConnectionRequest();
    }

    public static NoteService getInstance() {
        if (instance == null) {
            instance = new NoteService();
        }
        return instance;
    }

    public ArrayList<Note> parseNotes(String jsonText) {
        try {
            notes = new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String, Object> usersListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) usersListJson.get("root");

            for (Map<String, Object> obj : list) {

                Note n = new Note();
                subject = (LinkedHashMap) obj.get("matiere");

                for (Map.Entry<String, Object> entry : subject.entrySet()) {

                    if (entry.getKey().contains("nom")) {
                        n.getMatiere().setNomMatiere(entry.getValue().toString());
                    }

                    if (entry.getKey().contains("coef")) {
                        n.getMatiere().setCoefficient(entry.getValue().toString());
                    }

                    if (entry.getKey().contains("id")) {
                        n.getMatiere().setIdMatiere(entry.getValue().toString());
                    }

                }
                 student = (LinkedHashMap) obj.get("etudiant");

                for (Map.Entry<String, Object> entry : student.entrySet()) {

                    if (entry.getKey().contains("nomuser")) {
                        n.getEtudiant().setNomUser(entry.getValue().toString());
                                                System.out.println("yes" + n.getEtudiant().getNomUser());

                    }

                    if (entry.getKey().contains("prenomuser")) {
                       n.getEtudiant().setPrenomUser(entry.getValue().toString());
                    }

                   

                }

                float noteds = Float.parseFloat(obj.get("noteds").toString());
                n.setNoteDS(noteds);
                n.setNoteCC(Float.parseFloat(obj.get("notecc").toString()));
                
                n.setMoyenne((float)(Math.round(Float.parseFloat(obj.get("moyenne").toString())*1000)/1000));
                n.setNoteExam(Float.parseFloat(obj.get("noteexam").toString()));
                //  n.getMatiere().setNomMatiere(obj.get("matiere").toString());

                notes.add(n);
            }

        } catch (IOException ex) {

        }
        return notes;
    }

    public ArrayList<Classe> parseClasses(String jsonText) {
        try {
            classe = new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String, Object> usersListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) usersListJson.get("root");

            for (Map<String, Object> obj : list) {
                Classe c = new Classe();
                classest = (LinkedHashMap) obj.get("idClass");

                for (Map.Entry<String, Object> entry : classest.entrySet()) {

                    if (entry.getKey().contains("id")) {
                        float id = Float.parseFloat(entry.getValue().toString());
                        c.setId((int) id);
                        System.out.println("id = "+id);
                    }

                    if (entry.getKey().contains("name")) {
                        c.setName(entry.getValue().toString());
                    }

                }

                classe.add(c);
            }

        } catch (IOException ex) {

        }
        return classe;
    }

    //liste matiere enseignant
    public ArrayList<Matiere> parseMatieres(String jsonText) {
        try {
            matiere = new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String, Object> usersListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) usersListJson.get("root");

            for (Map<String, Object> obj : list) {
                Matiere m = new Matiere();
                subject = (LinkedHashMap) obj.get("idMatiere");

                for (Map.Entry<String, Object> entry : subject.entrySet()) {

                    if (entry.getKey().contains("id")) {
                        float id = Float.parseFloat(entry.getValue().toString());
                        int idM= (int) id;
                        m.setIdMatiere(String.valueOf(idM));
                    }
                    if (entry.getKey().contains("nom")) {
                        m.setNomMatiere(entry.getValue().toString());
                    }
                    if (entry.getKey().contains("coef")) {
                        m.setCoefficient(entry.getValue().toString());
                    }
                    


                }
              /*  int i = obj.get("idMatiere").toString().indexOf("=");
                int k = obj.get("idMatiere").toString().indexOf("nom");
                int c = obj.get("idMatiere").toString().indexOf("coef");
                float id = Float.parseFloat(obj.get("idMatiere").toString().substring(i + 1, k - 2));
                m.setIdMatiere(obj.get("idMatiere").toString());
                System.out.println(obj.get("idMatiere").toString());
                m.setNomMatiere(obj.get("idMatiere").toString().substring(k + 4, c - 2));*/

                matiere.add(m);
            }

        } catch (IOException ex) {

        }
        return matiere;
    }
    
    //les matiere d un enseignant
    public ArrayList<Matiere> getMatiere(String idC) {
        String url = Statics.BASE_URL + "user/getmatiere/" +User.getCurrentId()+ "/" + idC;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                matiere = parseMatieres(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return matiere;
    }

    
//les notes d un etudiant
    public ArrayList<Note> getNotes(String id) {
        String url = Statics.BASE_URL + "user/notesetudiant/" + id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                notes = parseNotes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return notes;
    }
//get Note enseignant

    public ArrayList<Note> getNotesEns(String idC, String idM, String idE) {
        String url = Statics.BASE_URL + "user/noteenseignant/" + idC + "/" + idM + "/" + idE;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                notes = parseNotes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return notes;
    }

    //les classe  d un enseignant
    public ArrayList<Classe> getClasses(String id) {
        String url = Statics.BASE_URL + "user/getclasse/" + id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                classe = parseClasses(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return classe;
    }
    public boolean addNote(String idC, String idM, String idE,String ncc,String nds,String nex) {

        String url = Statics.BASE_URL + "user/ajoutn/" +User.getCurrentId()+"/"+idE+"/"+idM+"/"+ncc+"/"+nds+"/"+nex ;
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


}
