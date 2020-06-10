/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.FloatingHint;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextComponent;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Services.ServiceReclamation;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.entities.User;


/**
 *
 * @author Neifos
 */
public class ReclamationForm extends SideMenuEtudiantForm1 {

    public ReclamationForm(Resources theme, String charr) {
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
      



       // Font smallPlainMonospaceFont = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
        Font smallPlainMonospaceFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        Font largeBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);

        Container recl = new Container(BoxLayout.y());
        Container bar = new Container(BoxLayout.x());
        Button btCree = new Button("New Reclam");

        ComboBox chType = new ComboBox();
        Label tri = new Label("Sort By :");
        chType.addItem("All");
        chType.addItem("Enseignant");
        chType.addItem("Note");
        btCree.getAllStyles().setBorder(Border.createLineBorder(CENTER));
        btCree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                removeAll();
              

//                Container newReclam = new Container(BoxLayout.y());
//                Container type = new Container(BoxLayout.x());
//                ComboBox chType = new ComboBox();
//                Label tri = new Label("Type:");
//                chType.addItem("All");
//                chType.addItem("Enseignant");
//                chType.addItem("Note");
//                
//                        TextArea cmmnt = new TextArea("");
//                cmmnt.setHint("Description");
//       
//               
//                Button send = new Button("Send");
//                send.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent evt) {
//                        if (cmmnt.getText().compareTo("") != 0) {
//               
//                            Reclamation r = new Reclamation();
//                            r.setDescriptionReclamation(cmmnt.getText());
//                            r.setSujetReclamation(chType.getSelectedItem().toString());
//                            r.setUser(User.getCurrentuser());
//                            ServiceReclamation.getInstance().addreclamation(r);
//                            removeAll();
//                            new ReclamationForm(theme,chType.getSelectedItem().toString()).show();
//           
//      
//      
//
//                        }
//
//                    }
//
//                });
//                type.addComponent(tri);
//                type.addComponent(chType);
//                newReclam.addComponent(type);
//                newReclam.addComponent(cmmnt);
//                newReclam.addComponent(send);
//                add(newReclam);
new NewReclamationForm(theme).show();

            }
        });
        chType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (chType.getSelectedItem().equals("Note")) {
                    removeAll();
                    new ReclamationForm(theme,"Note").show();
                } else if (chType.getSelectedItem().equals("Enseignant")) {
                    removeAll();
                    new ReclamationForm(theme,"Enseignant").show();
                } else {
                    removeAll();
                    new ReclamationForm(theme,"All").show();
                }

            }
        });
        bar.addComponent(tri);
        bar.addComponent(chType);

        recl.addComponent(bar);

        if (charr.compareTo("All") != 0) {
            for (Reclamation r : ServiceReclamation.getInstance().getAllReclamations()) {

                if ((r.getSujetReclamation().compareTo(charr) == 0)) {
                    Container reclamation = new Container(BoxLayout.x());
                    Container info = new Container(BoxLayout.y());
                    Container action = new Container(BoxLayout.y());

                    Label lbTitre = new Label();
  Label label = new Label("_____________________________________________________________");
  Label eccccc = new Label("        ");
                    Label lbStatus = new Label();

                    SpanLabel lbDescription = new SpanLabel();

                    Picker dtReclam = new Picker();
                    Button delete = new Button();
                    FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE, 3);
                    
                    lbStatus.setText(r.getStatutReclamation());
                    action.addComponent(lbStatus);
                  
   
                     action.addComponent(delete);
                    //info.addComponent(lbStatus);
         

                    lbTitre.setText(r.getSujetReclamation());
                    lbTitre.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 0, 90));
                    lbTitre.getUnselectedStyle().setFont(largeBoldSystemFont);
                    lbDescription.setText(r.getDescriptionReclamation());
                    lbDescription.getTextUnselectedStyle().setFont(smallPlainMonospaceFont);
                    // dtReclam.setDate(r.getDateCreation());
                    if (r.getStatutReclamation().compareTo("Pending") == 0) {
                        lbStatus.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 71, 189));
                        lbStatus.getUnselectedStyle().setFont(largeBoldSystemFont);

                    } else if (r.getStatutReclamation().compareTo("Traité") == 0) {
                        lbStatus.getUnselectedStyle().setFgColor(ColorUtil.rgb(50, 205, 50));
                        lbStatus.getUnselectedStyle().setFont(largeBoldSystemFont);

                    } else if (r.getStatutReclamation().compareTo("Non Traité") == 0) {
                        lbStatus.getUnselectedStyle().setFgColor(ColorUtil.rgb(255, 0, 0));
                        lbStatus.getUnselectedStyle().setFont(largeBoldSystemFont);

                    }
                  //  info.addComponent(lbTitre);
                    info.addComponent(lbDescription);
                    //info.addComponent(dtReclam);

                    reclamation.addComponent(info);
                    reclamation.addComponent(action);

                    recl.addComponent(reclamation);
                                    recl.addComponent(label);


                }

            }
            
        } else {
            for (Reclamation r : ServiceReclamation.getInstance().getAllReclamations()) {

                Container reclamation = new Container(BoxLayout.x());
                Container info = new Container(BoxLayout.y());
                Container action = new Container(BoxLayout.y());

                Label lbTitre = new Label();
            
  Label label = new Label("_____________________________________________________________");
                Label lbStatus = new Label();

                SpanLabel lbDescription = new SpanLabel();

                Picker dtReclam = new Picker();
                Button delete = new Button();
                FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE, 3);
                lbStatus.setText(r.getStatutReclamation());
                   action.addComponent(lbStatus);
                  
                    // action.addComponent(eccccc);
                

                lbTitre.setText(r.getSujetReclamation());
                lbTitre.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 0, 90));
                lbTitre.getUnselectedStyle().setFont(largeBoldSystemFont);
                lbDescription.setText(r.getDescriptionReclamation());
                lbDescription.getTextUnselectedStyle().setFont(smallPlainMonospaceFont);
                // dtReclam.setDate(r.getDateCreation());
                if (r.getStatutReclamation().compareTo("Pending") == 0) {
                               lbStatus.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 71, 189));
                    lbStatus.getUnselectedStyle().setFont(largeBoldSystemFont);

                } else if (r.getStatutReclamation().compareTo("Traité") == 0) {
                    lbStatus.getUnselectedStyle().setFgColor(ColorUtil.rgb(50, 205, 50));
                    lbStatus.getUnselectedStyle().setFont(largeBoldSystemFont);

                } else if (r.getStatutReclamation().compareTo("Non Traité") == 0) {
                    lbStatus.getUnselectedStyle().setFgColor(ColorUtil.rgb(255, 0, 0));
                    lbStatus.getUnselectedStyle().setFont(largeBoldSystemFont);

                }
                
                info.addComponent(delete);
                     action.addComponent(lbDescription);
                //info.addComponent(lbTitre);

                //info.addComponent(dtReclam);
               
                reclamation.addComponent(action);
                reclamation.addComponent(info);
                recl.addComponent(reclamation);
                recl.addComponent(label);
                
                delete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                    }
         
                });
            }

        }
        recl.addComponent(btCree);
        add(recl);
    }

}
