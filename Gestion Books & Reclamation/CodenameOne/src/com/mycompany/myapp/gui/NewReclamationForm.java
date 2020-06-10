/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Services.ServiceReclamation;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.entities.User;

/**
 *
 * @author Neifos
 */
public class NewReclamationForm extends SideMenuEtudiantForm1{
    
    public NewReclamationForm(Resources theme){
                  super(BoxLayout.y());
        Toolbar tb = getToolbar();
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseX(
                FlowLayout.encloseIn(menuButton), FlowLayout.encloseIn(
                new Label("My Reclam", "Title")
        )
        );
     
        tb.setTitleComponent(titleCmp);
  setupSideMenu(theme);
  
      Container newReclam = new Container(BoxLayout.y());
                Container type = new Container(BoxLayout.x());
                ComboBox chType = new ComboBox();
                Label tri = new Label("Type:");
                chType.addItem("All");
                chType.addItem("Enseignant");
                chType.addItem("Note");
                
                        TextArea cmmnt = new TextArea("");
                cmmnt.setHint("Description");
       
               
                Button send = new Button("Send");
                send.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (cmmnt.getText().compareTo("") != 0) {
               
                            Reclamation r = new Reclamation();
                            r.setDescriptionReclamation(cmmnt.getText());
                            r.setSujetReclamation(chType.getSelectedItem().toString());
                            r.setUser(User.getCurrentuser());
                            ServiceReclamation.getInstance().addreclamation(r);
                            removeAll();
                            new ReclamationForm(theme,chType.getSelectedItem().toString()).show();
           
      
      

                        }

                    }

                });
                type.addComponent(tri);
                type.addComponent(chType);
                newReclam.addComponent(type);
                newReclam.addComponent(cmmnt);
                newReclam.addComponent(send);
                add(newReclam);
    }
    
}
