/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import Entity.Emplois;
import com.mycompany.myapp.utils.Statics;

/**
 *
 * @author Pytrooooo
 */
public class ServiceEmplois {
        public ArrayList<Emplois> tasks; 
        public LinkedHashMap<String, Object> empl = new LinkedHashMap<>();
        public  int count=0;
    
    public static ServiceEmplois instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceEmplois() {
         req = new ConnectionRequest();
    }

    public static ServiceEmplois getInstance() {
        if (instance == null) {
            instance = new ServiceEmplois();
        }
        return instance;
    }
    
    
     public ArrayList<Emplois> parseEmplois(String jsonText){
        try {
           tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
           
            for(Map<String,Object> obj : list){      
                float id = Float.parseFloat(obj.get("idemplois").toString());
                String date = obj.get("date").toString() ;
                String dateF = date.substring(0,date.indexOf('T') );
                String timedeb = obj.get("heure").toString() ;
                String heure =timedeb.substring(timedeb.indexOf('T')+1,timedeb.indexOf('+') );
                empl = (LinkedHashMap) obj.get("nameclas");
                String source = obj.get("source").toString() ;
                tasks.add(new Emplois((int)id,date,heure,source));
                System.out.println(tasks);
            }
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
     
             public ArrayList<Emplois> getEmplois(int id){
        String url = Statics.BASE_URL+"jsonEmplois/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseEmplois(new String(req.getResponseData()));
                System.out.println("Task : " + tasks);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
    
    
}
