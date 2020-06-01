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
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Services.DemandespecifiqueService;
import com.mycompany.myapp.Services.ServiceClub;
import com.mycompany.myapp.Services.ServiceRate;
import com.mycompany.myapp.entities.Club;
import com.mycompany.myapp.entities.Rating;
import com.mycompany.myapp.entities.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ListClubForm extends SideMenuEtudiantForm1 {

    Form current;
    Image imgg = null;
    ImageViewer iv = null;
    EncodedImage ec;

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    private Slider createStarRankSlider(int r) {
        Slider starRank = new Slider();
        starRank.setEditable(false);
        starRank.setMinValue(0);
        starRank.setMaxValue(5);
        starRank.setProgress(r);
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        // starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));

        return starRank;
    }

    public ListClubForm(Resources res) {
        super(BoxLayout.y());
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
 if (DemandespecifiqueService.getInstance().getTestResp(Integer.parseInt(User.getCurrentId()))) {
            tb.addCommandToOverflowMenu("Demande Event", null, e -> new DemandeEvenementForm(res).show());
            tb.addCommandToOverflowMenu("consult Demande Event", null, e -> new ConsulterDemandeEventform(res).show());
            tb.addCommandToOverflowMenu("your Club", null, e -> new ClubSpecifiqueForm(res).show());
            tb.addCommandToOverflowMenu("your Events", null, e -> new EvenementClubSpecifiqueForm(res).show());
        }
        
        ArrayList<Club> clubs;
        clubs = ServiceClub.getInstance().getClub();
        for (Club c : clubs) {
            try {
                Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Map<Club, Float> rate = new HashMap<>();
                rate = ServiceRate.getInstance().getAllTasks(c.getIdClub());
              //  System.out.println("imageeeeeeee ::::::");
               // System.out.println(c.getImage());
                Label l = new Label(c.getNomClub());
                Label l1 = new Label(c.getDomaine());
                Label l2 = new Label(c.getResponsable().getUsername());
                Label l3 = new Label("" + c.getIdClub());
                l3.setVisible(false);
                String url = "http://localhost/projet/schoolMgt/web/images/" + c.getImage();
                ec = EncodedImage.create("/pic.jpeg");
                imgg = URLImage.createToStorage(ec, url, url, URLImage.RESIZE_SCALE);
                iv = new ImageViewer(imgg);
                c1.add(l);

                //c1.add(iv);
                for (Map.Entry<Club, Float> entry : rate.entrySet()) {
                    {
                        String pp = entry.getValue().toString();
                        float nbrrate = Float.parseFloat(pp);
                        c1.add(FlowLayout.encloseCenter(createStarRankSlider((int) nbrrate)));
                    }
//;                     }
                }
                // createStarRankSlider
                //   c1.add(l);
//                c1.add(l1);
//                c1.add(l2);
//                c1.add(l3);
                c2.add(iv);
                c2.add(c1);

//                c2.getStyle().setBgColor(0xFFFFFF);
//                c2.getStyle().setBgTransparency(255);
                getStyle().setBgColor(0xD4F2F7);
                getStyle().setBgTransparency(255);
                Slider xx = new Slider();
                xx.setWidth(1);
                xx.setHeight(1);
                add(c2);
                add(xx);

                iv.addPointerReleasedListener(e -> new ClubDetailForm(res, Integer.parseInt(l3.getText())).show());
            } catch (IOException ex) {
                ex.getMessage();
            }
        }
        // setupSideMenu(res,current);
        //    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

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
