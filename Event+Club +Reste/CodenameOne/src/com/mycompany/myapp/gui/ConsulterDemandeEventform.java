/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
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
import com.mycompany.myapp.Services.DemandespecifiqueService;
import com.mycompany.myapp.Services.ServiceClub;
import com.mycompany.myapp.Services.ServiceDemandeEvenement;
import com.mycompany.myapp.entities.DemandeEvenement;
import com.mycompany.myapp.entities.User;

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
    ImageViewer iv2 = null;
    EncodedImage ec;
    ImageViewer img;

    public ConsulterDemandeEventform(Resources res) {
        super(BoxLayout.y());
        current = this;
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
            try {
                Image dated = Image.createImage("/calendar1.png");
                Image datef = Image.createImage("/calendar2.png");
                Image description = Image.createImage("/description.png");
                Image etat = Image.createImage("/etat.png");
                Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                Label l = new Label("" + event.getIdDemandeEvenement());
                Button btnDelete = new Button("delete");
                Button btnDelete2 = new Button("delete");
                Label l5 = new Label("Description: " + event.getDescription(), description);
                Label desc = new Label("Description: " + event.getDescription(), description);
                Label dd = new Label("" + event.getDatedebut(), dated);
                Label df = new Label("" + event.getDatefin(), datef);
                Label et = new Label("" + event.getEtat(), etat);
                Label budget = new Label("" + event.getBudget());
//                Label idclub = new Label("" + event.getIdclub());

                l.setVisible(false);
                String image = event.getImage();
                String url = "http://localhost/projet/schoolMgt/web/images/" + event.getImage();
                int deviceWidth = Display.getInstance().getDisplayWidth();
                Image placeholder = Image.createImage(deviceWidth, deviceWidth / 2, 0xbfc9d2); //square image set to 10% of screen width
                EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
                // ec = EncodedImage.create("/pic.jpeg");
                imgg = URLImage.createToStorage(encImage, url, url, URLImage.RESIZE_SCALE);
                iv = new ImageViewer(imgg);
                iv2 = new ImageViewer(imgg);

                /* if (event.getEtat().compareTo("non valider") == 0) {

                    l3.getUnselectedStyle().setFgColor(ColorUtil.rgb(255, 69, 0));

                } else {
                    l3.getUnselectedStyle().setFgColor(ColorUtil.rgb(50, 205, 50));

                }*/
                Container c22 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                // c1.add(l);
                c1.add(iv);
                c1.add(l5);
//                c1.add(l1);
//                c1.add(l2);
//                c1.add(l3);
//                c22.add(btnDelete);
//                c1.add(c22);
                Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                c2.add(c1);

                add(c2);

                DemandeEvenement dev = new DemandeEvenement(null, Integer.parseInt(l.getText()), desc.getText(), dd.getText(), df.getText(), et.getText(), Float.parseFloat(budget.getText()), image);
                iv.addPointerReleasedListener(e -> new DemandeDetailForm(res,current, dev).show());

                //c1.addPointerPressedListener(e -> new EvenementDetailForm(current, Integer.parseInt(l.getText()), l3.getText(), Integer.parseInt(l4.getText())).show());
            } catch (IOException ex) {
            }

        }
        if (DemandespecifiqueService.getInstance()
                .getTestResp(Integer.parseInt(User.getCurrentId()))) {
            tb.addCommandToOverflowMenu("Demande Event", null, e -> new DemandeEvenementForm(res).show());
            tb.addCommandToOverflowMenu("consult Demande Event", null, e -> new ConsulterDemandeEventform(res).show());
        }

        setupSideMenu(res);
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
/*   btnDelete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (ServiceDemandeEvenement.getInstance().deleteDemande(Integer.parseInt(l.getText()))) {
                            Dialog.show("Success", "Demande has deleted", new Command("OK"));
                                     c1.removeAll();
                            c2.removeAll();
                            removeAll();
                             getContentPane().addPullToRefresh(new Runnable() {
                                    @Override
                                    public void run() {
                               
                                        Container c3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                                           ArrayList<DemandeEvenement> Evenementss;
                            Evenementss = ServiceDemandeEvenement.getInstance().getDmndEeventDetail(id);
                            for (DemandeEvenement event : Evenementss) {
                                Label l = new Label("" + event.getIdDemandeEvenement());
                                Button btnDelete = new Button("delete");
                                Button btnDelete2 = new Button("delete");
                                Label l5 = new Label("" + event.getDescription());
                                Label l1 = new Label(event.getDatedebut());
                                Label l2 = new Label(event.getDatefin());
                                Label l3 = new Label(event.getEtat());
                                l.setVisible(false);
                                String url = "http://localhost/projet/schoolMgt/web/images/" + event.getImage();
                                int deviceWidth = Display.getInstance().getDisplayWidth();
                                Image placeholder = Image.createImage(deviceWidth, deviceWidth / 2, 0xbfc9d2); //square image set to 10% of screen width
                                EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
                                // ec = EncodedImage.create("/pic.jpeg");
                                imgg = URLImage.createToStorage(encImage, url, url, URLImage.RESIZE_SCALE);

                                iv2 = new ImageViewer(imgg);

                                if (event.getEtat().compareTo("non Valider") == 0) {
                                    l3.getUnselectedStyle().setFgColor(ColorUtil.rgb(50, 205, 50));

                                } else {

                                    l3.getUnselectedStyle().setFgColor(ColorUtil.rgb(255, 69, 0));

                                }

                               
                           }
                                       
                c1.add(l);
                c1.add(iv2);
                c1.add(l5);
                c1.add(l1);
                c1.add(l2);
                c1.add(l3);
                c22.add(btnDelete);
                c1.add(c22);
                Container c4 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                c4.add(c1);
                                        c3.add(c4);
                                        addComponent(c3);
                                        revalidate();
                                    }
                                }); 
                         
                            } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }

                        }
                    }      );*/
