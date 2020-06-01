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
import com.mycompany.myapp.Services.ServiceParticipation;

import com.mycompany.myapp.entities.Club;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.entities.Participation;
import com.mycompany.myapp.entities.User;

import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author asus
 */
public class EvenementDetailForm extends SideMenuEtudiantForm1 {

    public EvenementDetailForm(Resources res, int id, String nomclub, int IdClub) {
        super(BoxLayout.y());
        ServiceParticipation aa = new ServiceParticipation();
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseX(
                FlowLayout.encloseIn(menuButton), FlowLayout.encloseIn(
                new Label("Event Detail", "Title")
        )
        );

        tb.setTitleComponent(titleCmp);
        setupSideMenu(res);
        //Form current;
        Image imgg = null;
        ImageViewer iv = null;
        EncodedImage ec;
        ImageViewer img;
        //   current = this;

        //   System.out.println("nom du club: " + nomclub);
        Map<Evenement, Integer> Evenements = new HashMap<>();
        Evenements = aa.getInstance().getEeventDetail(id);
        boolean test = aa.getInstance().getTestPart(Integer.parseInt(User.getCurrentId()), id);
        //    System.out.println("testttt: "+test);
        for (Map.Entry<Evenement, Integer> entry : Evenements.entrySet()) {

            try {
                Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                Button btnPartciper = new Button();
                String charr;

                Label l = new Label(entry.getKey().getImage());
                float idd = Float.parseFloat(entry.getValue().toString());
                int nbrParticipant = ((int) idd);
                Image dated = Image.createImage("/calendar1.png");
                Image datef = Image.createImage("/calendar2.png");
                Image group = Image.createImage("/group.png");
                Image organisant = Image.createImage("/organisant.png");

                Label l0 = new Label("number of participants: " + entry.getValue(), group);
                Label l1 = new Label("Start date: " + entry.getKey().getDateDebut(), dated);
                Label l2 = new Label("End date: " + entry.getKey().getDateFin(), datef);
                Label l3 = new Label("Organized by: " + nomclub, organisant);
                Label l4 = new Label("" + IdClub);
                //    setTitle("Event Organised by "+l3.getText());
                l4.setVisible(false);
                System.out.println("****************testt*****************:      " + test);
                if (!test) {
                    btnPartciper.setText("Join");
                    charr = "Welcome to our Event at" + l2.getText();
                } else {
                    btnPartciper.setText("Leave");
                    charr = "Next Time  :) ";
                }
                String url = "http://localhost/projet/schoolMgt/web/images/" + entry.getKey().getImage();
                ec = EncodedImage.create("/pic.jpeg");
                imgg = URLImage.createToStorage(ec, url, url, URLImage.RESIZE_SCALE);
                iv = new ImageViewer(imgg);
                c1.add(iv);

                c1.add(l1);
                c1.add(l2);
                c1.add(l3);
                c1.add(l0);
                c1.add(l4);

                Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                c2.add(c1);

                add(c2);
                add(btnPartciper);
                btnPartciper.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Club c = new Club(IdClub);
                        Evenement e = new Evenement(id, l1.getText(), l1.getText(), c, l.getText());
                        Participation p = new Participation(User.getCurrentuser(), e);
                        if (ServiceParticipation.getInstance().addParticipation(p)) {
                            Dialog.show("Success", charr, new Command("OK"));

                            new ListEvenementForm(res).show();

                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    }
                });
                //l3.addPointerPressedListener(e -> new ClubDetailForm(current, Integer.parseInt(l4.getText())).show());
            } catch (IOException ex) {
                ex.getMessage();
            }
        }
        //   getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

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
