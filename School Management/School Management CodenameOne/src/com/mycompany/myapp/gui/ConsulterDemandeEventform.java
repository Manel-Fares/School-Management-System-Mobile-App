/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Services.ServiceDemandeEvenement;
import com.mycompany.myapp.entities.DemandeEvenement;
import com.mycompany.myapp.entities.User;


import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class ConsulterDemandeEventform extends SideMenuEtudiantForm1 {

    Form current;
    Image imgg = null;
    ImageViewer iv = null;
    EncodedImage ec;
    ImageViewer img;

    public ConsulterDemandeEventform(Resources res) {
       super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseX(
                FlowLayout.encloseIn(menuButton), FlowLayout.encloseIn(
                new Label("Demande Consultation", "Title")
        )
        );

        tb.setTitleComponent(titleCmp);
        setupSideMenu(res);
        ArrayList<DemandeEvenement> Evenements;
        int id = Integer.parseInt(User.getCurrentId());
        Evenements = ServiceDemandeEvenement.getInstance().getDmndEeventDetail(id);
        for (DemandeEvenement event : Evenements) {
           
                Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                Label l = new Label("" + event.getIdDemandeEvenement());
                Button btnDelete = new Button("delete");
                Label l5 = new Label("" + event.getDescription());
                Label l1 = new Label(event.getDatedebut());
                Label l2 = new Label(event.getDatefin());
                Label l3 = new Label(event.getEtat());
                l.setVisible(false);
                String url = "http://localhost/projet/schoolMgt/web/images/" + event.getImage();
                   int deviceWidth = Display.getInstance().getDisplayWidth();
            Image placeholder = Image.createImage(deviceWidth, deviceWidth/2, 0xbfc9d2); //square image set to 10% of screen width
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
               // ec = EncodedImage.create("/pic.jpeg");
                imgg = URLImage.createToStorage(encImage, url, url, URLImage.RESIZE_SCALE);
                iv = new ImageViewer(imgg);
                 if (event.getEtat().compareTo("non Valider") == 0) {
                  l3.getUnselectedStyle().setFgColor(ColorUtil.rgb(50, 205, 50));
               

                } else{
                    
                      l3.getUnselectedStyle().setFgColor(ColorUtil.rgb(255, 69, 0));
                   
                }
                c1.add(l);
                c1.add(iv);
                c1.add(l5);
                c1.add(l1);
                c1.add(l2);
                c1.add(l3);
                c1.add(btnDelete);
                Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                c2.add(c1);

                add(c2);
                btnDelete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (ServiceDemandeEvenement.getInstance().deleteDemande(Integer.parseInt(l.getText()))) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                           
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    }
                });
                //c1.addPointerPressedListener(e -> new EvenementDetailForm(current, Integer.parseInt(l.getText()), l3.getText(), Integer.parseInt(l4.getText())).show());
          
        }
          setupSideMenu(res);
      //  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
 private Image colorCircle(int color) {
        int size = Display.getInstance().convertToPixels(3);
        Image i = Image.createImage(size, size, 0);
        Graphics g = i.getGraphics();
        g.setColor(color);
        g.fillArc(0, 0, size, size, 0, 360);
        return i;
    }

    

    
}
