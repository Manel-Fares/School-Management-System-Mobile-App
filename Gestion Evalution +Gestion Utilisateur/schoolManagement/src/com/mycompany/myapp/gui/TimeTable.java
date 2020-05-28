/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.util.Resources;
import Entity.Emplois;
import Services.ServiceEmplois;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.entities.User;
import java.util.ArrayList;

/**
 *
 * @author Pytrooooo
 */
public class TimeTable extends SideMenuEtudiantForm1{
    
    public TimeTable(Resources theme) {
          super(BoxLayout.y());
        Toolbar tb = getToolbar();
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseX(
                FlowLayout.encloseIn(menuButton), FlowLayout.encloseIn(
                new Label("Time Table", "Title")
        )
        );

        tb.setTitleComponent(titleCmp);
        setupSideMenu(theme);

        
        
            FileSystemStorage fs = FileSystemStorage.getInstance();
            ArrayList<Emplois> e= ServiceEmplois.getInstance().getEmplois(Integer.parseInt(User.getCurrentId()));
            System.out.println(e);
               
         //          String PathTo= "C:\\wamp64\\www\\schoolMgt\\web\\Pdfs\\"+ServiceEmplois.getInstance().getEmplois(1); 
        //Paths.get("").toAbsolutePath().toString();
        String fileName = "http://localhost/projet/schoolMgt/web/Pdfs/" + e.get(0).getSource();
       // File org=new File(PathTo);
        //File news=new File(fileName);
       // Files.copy(org.toPath(), news.toPath(), StandardCopyOption.REPLACE_EXISTING);
         
            if (!fs.exists(fileName)) {
                Util.downloadUrlToFile("http://localhost/projet/schoolMgt/web/Pdfs/"+e.get(0).getSource(), fileName, true);
            }
            Display.getInstance().execute(fileName);
    }
    
}
