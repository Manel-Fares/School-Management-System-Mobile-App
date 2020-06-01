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
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author asus
 */
public class EvenementClubSpecifiqueForm extends SideMenuEtudiantForm1 {

    Form current;
    Image imgg = null;
    ImageViewer iv = null;
    EncodedImage ec;

    public EvenementClubSpecifiqueForm(Resources res) {

        super(BoxLayout.y());
        current=this;
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        System.out.println("voila:  " + ServiceEvenement.getInstance().getEeventClubNbrParticipation(Integer.parseInt(User.getCurrentId())));
        Container titleCmp = BoxLayout.encloseX(
                FlowLayout.encloseIn(menuButton), FlowLayout.encloseIn(
                new Label("Your Events List ", "Title")
        )
        );
        tb.setTitleComponent(titleCmp);
        setupSideMenu(res);

        Map<Evenement, Integer> Evenements = new HashMap<>();
        Evenements = ServiceEvenement.getInstance().getEeventClubNbrParticipation(Integer.parseInt(User.getCurrentId()));
        //   boolean test = aa.getInstance().getTestPart(Integer.parseInt(User.getCurrentId()), id);
        //    System.out.println("testttt: "+test);
        for (Map.Entry<Evenement, Integer> entry : Evenements.entrySet()) {
            Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            Label id = new Label("" + entry.getKey().getIdEvenement());
            Label dd = new Label(entry.getKey().getDateDebut());
            Label df = new Label(entry.getKey().getDateFin());
            Label image = new Label(entry.getKey().getImage());
            Label nbrPart = new Label("Patcipations: " + entry.getValue());
            Label nbr = new Label("" + entry.getValue());
            String url = "http://localhost/projet/schoolMgt/web/images/" + entry.getKey().getImage();
            int deviceWidth = Display.getInstance().getDisplayWidth();
            Image placeholder = Image.createImage(deviceWidth, deviceWidth / 2, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            imgg = URLImage.createToStorage(encImage, url, url, URLImage.RESIZE_SCALE);
            iv = new ImageViewer(imgg);
            c1.add(iv);
            c1.add(nbrPart);
            add(c1);
            Evenement ev=new Evenement(Integer.parseInt(id.getText()), dd.getText(), df.getText(), null, image.getText());
            iv.addPointerReleasedListener(e -> new EvenementClubSpecifiqueDetailForm(res,current,ev,Integer.parseInt(nbr.getText())).show());

        }

    }

}
