///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.myapp.Services;
//
//import com.codename1.io.CharArrayReader;
//import com.codename1.io.ConnectionRequest;
//import com.codename1.io.JSONParser;
//import com.codename1.io.NetworkEvent;
//import com.codename1.io.NetworkManager;
//import com.codename1.ui.events.ActionListener;
//import com.mycompany.myapp.entities.Absence;
//import com.mycompany.myapp.entities.Matiere;
//import com.mycompany.myapp.entities.User;
//import com.mycompany.myapp.utils.Statics;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
//
//
//
//
///**
// *
// * @author Pytrooooo
// */
//public class ServiceAbsence {
//        public ArrayList<Absence> tasks;
//        public ArrayList<Matiere> tasksMat;
//        public ArrayList<User> tasksUse;
//        public LinkedHashMap<String, Object> mat = new LinkedHashMap<>();
//        public LinkedHashMap<String, Object> use = new LinkedHashMap<>();
//        public  int count=0;
//    
//    public static ServiceAbsence instance=null;
//    public boolean resultOK;
//    private ConnectionRequest req;
//        private final static String ACCOUNT_SID = "AC22a899de781eed33b898b5ba4e74dc7a";
//    private final static String AUTH_TOKEN = "c0803c607f311e28f655347866cd0bcb";
//
//    private ServiceAbsence() {
//         req = new ConnectionRequest();
//    }
//
//    public static ServiceAbsence getInstance() {
//        if (instance == null) {
//            instance = new ServiceAbsence();
//        }
//        return instance;
//    }
//    
//    public ArrayList<Absence> parseAbsence(String jsonText){
//        try {
//            tasks=new ArrayList<>();
//            JSONParser j = new JSONParser();
//            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//            
//            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
//            
//            for(Map<String,Object> obj : list){      
//                float id = Float.parseFloat(obj.get("id").toString());
//                String date = obj.get("date").toString() ;
//                String dateF = date.substring(0,date.indexOf('T') );
//                String timedeb = obj.get("timedeb").toString() ;
//                String timedebF =timedeb.substring(timedeb.indexOf('T')+1,timedeb.indexOf('+') );
//                String timefin = obj.get("timefin").toString() ;
//                String timefinF = timefin.substring(timefin.indexOf('T')+1,timefin.indexOf('+') );
//               
//              ////////////////Matiere//////////////
//                mat = (LinkedHashMap) obj.get("idMatiere");
//                Matiere m = new Matiere();
//                for (Map.Entry<String, Object> entry : mat.entrySet()) {
//                    if (entry.getKey().contains("nom")) {
//                        m.setNomMatiere(entry.getValue().toString());
//                    }
//                    if (entry.getKey().contains("coef")) {
//                        m.setCoefficient(entry.getValue().toString());
//                    }
//                    if (entry.getKey().contains("id")) {
//                        String pp = entry.getValue().toString();
//                  //      System.out.println(pp);
//                        float idd = Float.parseFloat(pp);
//                        m.setIdMatiere(String.valueOf(idd));
//                    }
//                }
//              ///////////////End Matiere///////////////
//              //////////////User////////////////
//                           use = (LinkedHashMap) obj.get("idUser");
//                User u = new User();
//                for (Map.Entry<String, Object> entry : use.entrySet()) {
//                    if (entry.getKey().contains("username")) {
//                        u.setNomUser(entry.getValue().toString());
//                    }
//                    if (entry.getKey().contains("emailCanonical")) {
//                        u.setEmail(entry.getValue().toString());
//                    }
//                    if (entry.getKey().contains("numteluser")) {
//                        String pp = entry.getValue().toString();
//                   //     System.out.println(pp);
//                        int idd =(int) Float.parseFloat(pp);
//                        u.setNumTelUser(idd);
//                    }
//                    if (entry.getKey().contains("id")) {
//                        String pp = entry.getValue().toString();
//                    //    System.out.println(pp);
//                        int idd =(int) Float.parseFloat(pp);
//                        u.setId(String.valueOf(idd));
//                    }
//
//                }
//              ///////////////End User///////////////
//           /*    System.out.println("id "+id);
//               System.out.println("date "+date);
//               System.out.println("timedeb "+timedeb);
//               System.out.println("timefin "+timefin);
//              */
//                tasks.add(new Absence((int)id,u,m,dateF,timedebF,timefinF) );
//               // System.out.println(tasks);
//            }
//            
//            
//        } catch (IOException ex) {
//            
//        }
//        return tasks;
//    }
//    
//        
//    public ArrayList<Absence> parseOnlAbsence(String jsonText){
//        try {
//            tasks=new ArrayList<>();
//            JSONParser j = new JSONParser();
//            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//            int k =0;
//            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
//            
//            for(Map<String,Object> obj : list){      
//                float id = Float.parseFloat(obj.get("id").toString());
//                String date = obj.get("date").toString() ;
//                String dateF = date.substring(0,date.indexOf('T') );
//                String timedeb = obj.get("timedeb").toString() ;
//                String timedebF =timedeb.substring(timedeb.indexOf('T')+1,timedeb.indexOf('+') );
//                String timefin = obj.get("timefin").toString() ;
//                String timefinF = timefin.substring(timefin.indexOf('T')+1,timefin.indexOf('+') );
//               
//              ////////////////Matiere//////////////
//                mat = (LinkedHashMap) obj.get("idMatiere");
//                Matiere m = new Matiere();
//                for (Map.Entry<String, Object> entry : mat.entrySet()) {
//                    
//                    if (entry.getKey().contains("nom")) {
//                        m.setNomMatiere(entry.getValue().toString());
//                    }
//                    if (entry.getKey().contains("coef")) {
//                        m.setCoefficient(entry.getValue().toString());
//                    }
//                    if (entry.getKey().contains("id")) {
//                        String pp = entry.getValue().toString();
//                     //   System.out.println(pp);
//                        float idd = Float.parseFloat(pp);
//                        m.setIdMatiere(String.valueOf(idd));
//                    }
//                }
//              ///////////////End Matiere///////////////
//              //////////////User////////////////
//                           use = (LinkedHashMap) obj.get("idUser");
//                User u = new User();
//                for (Map.Entry<String, Object> entry : use.entrySet()) {
//                    if (entry.getKey().contains("username")) {
//                        u.setNomUser(entry.getValue().toString());
//                    }
//                    if (entry.getKey().contains("emailCanonical")) {
//                        u.setEmail(entry.getValue().toString());
//                    }
//                    if (entry.getKey().contains("numteluser")) {
//                        String pp = entry.getValue().toString();
//               //         System.out.println(pp);
//                        int idd =(int) Float.parseFloat(pp);
//                        u.setNumTelUser(idd);
//                    }
//                    if (entry.getKey().contains("id")) {
//                        String pp = entry.getValue().toString();
//                        System.out.println(pp);
//                        int idd =(int) Float.parseFloat(pp);
//                        u.setId(String.valueOf(idd));
//                    }
//
//                }
//              ///////////////End User///////////////
//            /*   System.out.println("id "+id);
//               System.out.println("date "+date);
//               System.out.println("timedeb "+timedeb);
//               System.out.println("timefin "+timefin);*/
//              
//                tasks.add(new Absence((int)id,u,m,dateF,timedebF,timefinF) );
//         //       System.out.println(tasks);
//                
//                
//                k++;
//                System.out.println("aa" + k);
//                count=k;
//            }
//            
//            
//        } catch (IOException ex) {
//            
//        }
//        return tasks;
//    }
//    
//        
//        public ArrayList<Matiere> parseMat(String jsonText){
//        try {
//            tasksMat=new ArrayList<>();
//            JSONParser j = new JSONParser();
//            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//            
//            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
//            
//            for(Map<String,Object> obj : list){      
//                float id = Float.parseFloat(obj.get("id").toString());
//                String nom = obj.get("nom").toString() ;
//                tasksMat.add(new Matiere(String.valueOf(id),nom) );
//         //       System.out.println(tasksMat);
//            }
//            
//            
//        } catch (IOException ex) {
//            
//        }
//        return tasksMat;
//    }
//        
//        public ArrayList<User> parseUser(String jsonText){
//        try {
//            tasksUse=new ArrayList<>();
//            JSONParser j = new JSONParser();
//            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//            
//            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
//            
//            for(Map<String,Object> obj : list){      
//                int id = (int)Float.parseFloat(obj.get("id").toString());
//                String username = obj.get("username").toString() ;
//                String emailCanonical = obj.get("email").toString() ;
//                int numteluser = (int)Float.parseFloat(obj.get("numteluser").toString()) ;
//                User u = new User();
//                u.setId(String.valueOf(id));
//                u.setNomUser(username);
//                u.setEmail(emailCanonical);
//                u.setNumTelUser(numteluser);
//                tasksUse.add(u);
//           //     System.out.println(tasksUse);
//            }               
//        } catch (IOException ex) {
//            
//        }
//        return tasksUse;
//    }
//    
//    public ArrayList<Absence> getAllAbsence(){
//        String url = Statics.BASE_URL+"jsonAbsence";
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                tasks = parseAbsence(new String(req.getResponseData()));
//         //       System.out.println("Task : " + tasks);
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return tasks;
//    }
//    
//    
//        public ArrayList<Absence> getOnlineAbsence(int id){
//        String url = Statics.BASE_URL+"jsonOnlineAbsence/"+id;
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                tasks = parseOnlAbsence(new String(req.getResponseData()));
//           //     System.out.println("Task : " + tasks);
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return tasks;
//    }
//    
//    
//    public ArrayList<Matiere> getAllMat(){
//        String url = Statics.BASE_URL+"jsonMat";
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                tasksMat = parseMat(new String(req.getResponseData()));
//      //          System.out.println("Task : " + tasks);
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return tasksMat;
//    }
//    public ArrayList<Matiere> getAllMatid(String id){
//        String url = Statics.BASE_URL+"jsonMatid/"+id;
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                tasksMat = parseMat(new String(req.getResponseData()));
//                System.out.println("Task : " + tasks);
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return tasksMat;
//    }
//    public ArrayList<User> getAllUseid(String id){
//        String url = Statics.BASE_URL+"jsonUserid/"+id;
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                tasksUse = parseUser(new String(req.getResponseData()));
//                System.out.println("Task : " + tasks);
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return tasksUse;
//    }
//    public ArrayList<User> getAllUse(){
//        String url = Statics.BASE_URL+"jsonUser";
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                tasksUse = parseUser(new String(req.getResponseData()));
//                System.out.println("Task : " + tasks);
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return tasksUse;
//    }
//    
//    
//    
//    
//    
//        public boolean addAbsence(Absence t,String email,String m ,String d,String td,String tf) {
//        String url = Statics.BASE_URL+"jsonAddAbsence/"+t.getMatiere().getIdMatiere()+"/"+t.getUser().getId()+"/"+t.getDate()+"/"+t.getTimeDeb()+"/"+t.getTimeFin()+"/"+email+"/"+m+"/"+d+"/"+td+"/"+tf;
//        req.setUrl(url);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    }
//        
//            public boolean editAbsence(String id,Absence t) {
//        String url = Statics.BASE_URL+"jsonEditAbsence/"+id+"/"+t.getMatiere().getIdMatiere()+"/"+t.getUser().getId()+"/"+t.getDate()+"/"+t.getTimeDeb()+"/"+t.getTimeFin();
//        req.setUrl(url);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    }    
//        
//                public boolean DeleteAbsence(String t) {
//        String url = Statics.BASE_URL+"jsonDelete/"+t;
//        req.setUrl(url);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    }
//    public void sendsms(int str,int nbr){
//             Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//                    Message message = Message.creator(
//                            new PhoneNumber("+216"+str), // To number
//                            new PhoneNumber("+12057828595"), // From number
//                            "You got an extra number of absence take care , Total Absnece :"+nbr // SMS body
//                    ).create();
//                    System.out.println(message.getSid());
//    }
//
//}
