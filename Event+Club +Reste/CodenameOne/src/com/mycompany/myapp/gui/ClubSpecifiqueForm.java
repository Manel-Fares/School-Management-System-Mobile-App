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
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Services.ServiceClub;
import com.mycompany.myapp.Services.ServiceClubSpecifique;
import com.mycompany.myapp.entities.Club;
import com.mycompany.myapp.entities.User;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author asus
 */
public class ClubSpecifiqueForm extends SideMenuEtudiantForm1 {

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

    public ClubSpecifiqueForm(Resources res) {

        super(BoxLayout.y());
        current=this;
        //      System.out.println("************************voila votre resultats" + ServiceClub.getInstance().getClubResponsable());
        getStyle().setBgColor(0xC7EEF5);
        getStyle().setBgTransparency(255);
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Map<Club, Float> Clubs = new HashMap<>();
        ServiceClubSpecifique inst = ServiceClubSpecifique.getInstance();
        Clubs = inst.getClubResponsable(Integer.parseInt(User.getCurrentId()));
        for (Map.Entry<Club, Float> entry : Clubs.entrySet()) {

            try {
                Label idClub = new Label("" + entry.getKey().getIdClub());
                Label nomClub = new Label("Club Name: " + entry.getKey().getNomClub());
                Label DomaineClub = new Label("Domaine: " + entry.getKey().getDomaine());
                Label n = new Label("" + entry.getKey().getNomClub());
                Label d = new Label("" + entry.getKey().getDomaine());
                Label RateClub = new Label("" + entry.getKey().getRating());

                Label PourcentageClub = new Label("" + entry.getValue());
                // Label PourcentageClub=new Label();
                Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                String url = "http://localhost/projet/schoolMgt/web/images/" + entry.getKey().getImage();
                ec = EncodedImage.create("/pic.jpeg");
                imgg = URLImage.createToStorage(ec, url, url, URLImage.RESIZE_SCALE);
                iv = new ImageViewer(imgg);
                c1.add(nomClub);
                String pp = entry.getKey().getRating().toString();
                float nbrrate = Float.parseFloat(pp);
                c1.add(FlowLayout.encloseCenter(createStarRankSlider((int) nbrrate)));

                c2.add(iv);
                c2.add(c1);
//                c1.add(DomaineClub);
//                c1.add(RateClub);
//                c1.add(PourcentageClub);
                add(c2);
                Label imgggg = new Label(entry.getKey().getImage());
                Club c = new Club(Integer.parseInt(idClub.getText()), null, n.getText(), d.getText(), imgggg.getText(), Float.parseFloat(RateClub.getText()));
                iv.addPointerReleasedListener(e -> new ClubSpecifiqueDetailForm(res,current, c, PourcentageClub.getText()).show());
            } catch (IOException ex) {
            }
        }

        Container titleCmp = BoxLayout.encloseX(
                FlowLayout.encloseIn(menuButton), FlowLayout.encloseIn(
                new Label("Event Detail", "Title")
        )
        );
        tb.setTitleComponent(titleCmp);
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
