/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
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
import java.io.IOException;

/**
 *
 * @author asus
 */
public class DemandeDetailForm extends SideMenuEtudiantForm1 {

    Form current;
    Image imgg = null;
    ImageViewer iv = null;
    ImageViewer iv2 = null;
    EncodedImage ec;
    ImageViewer img;

    public DemandeDetailForm(Resources res,Form previous, DemandeEvenement dev) {
        super(BoxLayout.y());
        try {
            Toolbar tb = getToolbar();
            tb.setTitleCentered(false);
            Button menuButton = new Button("");
            menuButton.setUIID("Title");
            FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
            menuButton.addActionListener(e -> getToolbar().openSideMenu());

            Container titleCmp = BoxLayout.encloseX(
                    FlowLayout.encloseIn(menuButton), FlowLayout.encloseIn(
                    new Label("Club List ", "Title")
            )
            );
            tb.setTitleComponent(titleCmp);
            setupSideMenu(res);

            Image dated = Image.createImage("/calendar1.png");
            Image datef = Image.createImage("/calendar2.png");
            Image description = Image.createImage("/description.png");
            Image etat = Image.createImage("/etat.png");
            Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label l = new Label("" + dev.getIdDemandeEvenement());
            Button btnDelete = new Button("delete");
            Label l5 = new Label("Description: " + dev.getDescription());
            Label l1 = new Label("Start Date: " + dev.getDatedebut(), dated);
            Label l2 = new Label("End Date: " + dev.getDatefin(), datef);
            Label l3 = new Label("Etat: " + dev.getEtat(), etat);
            l.setVisible(false);
            String url = "http://localhost/projet/schoolMgt/web/images/" + dev.getImage();
            int deviceWidth = Display.getInstance().getDisplayWidth();
            Image placeholder = Image.createImage(deviceWidth, deviceWidth / 2, 0xbfc9d2); //square image set to 10% of screen width
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            // ec = EncodedImage.create("/pic.jpeg");
            imgg = URLImage.createToStorage(encImage, url, url, URLImage.RESIZE_SCALE);
            iv = new ImageViewer(imgg);
            c1.add(iv);
            c1.add(l5);
            c1.add(l1);
            c1.add(l2);
            c1.add(l3);
            c1.add(btnDelete);
            add(c1);
            btnDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    if (ServiceDemandeEvenement.getInstance().deleteDemande(Integer.parseInt(l.getText()))) {
                        Dialog.show("Success", "Demande has deleted", new Command("OK"));
                        previous.showBack();
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }

                }
            });
        } catch (IOException ex) {
        }
                    getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
