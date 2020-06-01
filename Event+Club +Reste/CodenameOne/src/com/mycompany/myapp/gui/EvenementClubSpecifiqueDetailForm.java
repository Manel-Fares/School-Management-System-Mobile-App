/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Services.ServiceEvenement;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.entities.User;
import java.io.IOException;

/**
 *
 * @author asus
 */
public class EvenementClubSpecifiqueDetailForm extends SideMenuEtudiantForm1 {

    Form current;
    Image imgg = null;
    ImageViewer iv = null;
    EncodedImage ec;

    public EvenementClubSpecifiqueDetailForm(Resources res, Form previous, Evenement ev, int nbr) {
        super(BoxLayout.y());
        try {
            Toolbar tb = getToolbar();
            tb.setTitleCentered(false);
            Button menuButton = new Button("");
            menuButton.setUIID("Title");
            FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
            menuButton.addActionListener(e -> getToolbar().openSideMenu());
            System.out.println("voila:  " + ServiceEvenement.getInstance().getEeventClubNbrParticipation(Integer.parseInt(User.getCurrentId())));
            Container titleCmp = BoxLayout.encloseX(
                    FlowLayout.encloseIn(menuButton), FlowLayout.encloseIn(
                            new Label("Club List ", "Title")
                    )
            );
            tb.setTitleComponent(titleCmp);
            setupSideMenu(res);
            Image dated = Image.createImage("/calendar1.png");
            Image datef = Image.createImage("/calendar2.png");
            Image group = Image.createImage("/group.png");
            Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label id = new Label("" + ev.getIdEvenement());
            Label dd = new Label("Start Date: "+ev.getDateDebut(),dated);
            Label df = new Label("End Date: "+ev.getDateFin(),datef);
            Label nbrPart = new Label("Patcipations: " + nbr,group);
            String url = "http://localhost/projet/schoolMgt/web/images/" + ev.getImage();
            int deviceWidth = Display.getInstance().getDisplayWidth();
            Image placeholder = Image.createImage(deviceWidth, deviceWidth / 2, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            imgg = URLImage.createToStorage(encImage, url, url, URLImage.RESIZE_SCALE);
            iv = new ImageViewer(imgg);
            c1.add(iv);
            c1.add(dd);
            c1.add(df);
            c1.add(nbrPart);
            add(c1);
            
        } catch (IOException ex) {
        }
            getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
