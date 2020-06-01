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
import com.mycompany.myapp.entities.Club;

import java.io.IOException;


/**
 *
 * @author asus
 */
public class ClubSpecifiqueDetailForm extends SideMenuEtudiantForm1 {
   Form current;
    Image imgg = null;
    ImageViewer iv = null;
    EncodedImage ec;
    ImageViewer img;

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    private Slider createStarRankSlider(Resources res, int r) {
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

    public ClubSpecifiqueDetailForm(Resources res,Form previous,Club c, String pr) {
        super(BoxLayout.y());
        current=this;
        try {
            Toolbar tb = getToolbar();
            tb.setTitleCentered(false);
            Button menuButton = new Button("");
            menuButton.setUIID("Title");
            FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
            menuButton.addActionListener(e -> getToolbar().openSideMenu());
            
            Container titleCmp = BoxLayout.encloseX(
                    FlowLayout.encloseIn(menuButton), FlowLayout.encloseIn(
                            new Label("Club Details ", "Title")
                    )
            );
            
            
            Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
            Image respo = Image.createImage("/management.png");
            Image d = Image.createImage("/domaine.png");
            Image name = Image.createImage("/name.png");
            Image edit = Image.createImage("/edit.png");
            Label nomclub = new Label("Club Name:  " + c.getNomClub(), name);
            Label Stat = new Label(" Statistique Event :  " + pr+"%", name);
            Button edite=new Button(edit,"Edit");
            Label ratte = new Label("Rate:");
            Label domaine = new Label("Domain :  " + c.getDomaine(), d);
            // Label l3 = new Label(""+entry.getKey().getResponsable().getId());
            //  l3.setVisible(false);
            String url = "http://localhost/projet/schoolMgt/web/images/" + c.getImage();
            ec = EncodedImage.create("/pic.jpeg");
            imgg = URLImage.createToStorage(ec, url, url, URLImage.RESIZE_SCALE);
            iv = new ImageViewer(imgg);
            c1.add(iv);
            float vv=c.getRating();
           c1.add(FlowLayout.encloseCenter(createStarRankSlider(res,(int)vv )));
            c1.add(nomclub);
            c1.add(domaine);
            Container cc = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Slider ra = new Slider();
            //String pp = c.getRating().toString();
            float nbrrate = c.getRating();
            ra.setProgress((int) nbrrate);
            cc.add(ratte);
            cc.add(ra);
            Label r=new Label(""+c.getRating());
            cc.add(r);
            c1.add(cc);
            Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            
            c1.add(Stat);
            c1.add(edite);
            add(c1);
            getStyle().setBgColor(0xFFFFFF);
            getStyle().setBgTransparency(255);
            //l.addPointerPressedListener(e-> new ClubDetailForm(current,Integer.parseInt(l3.getText())).show());
            edite.addActionListener(e -> new EditClubForm(res,current,c).show());
            tb.setTitleComponent(titleCmp);
            setupSideMenu(res);
            getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        } catch (IOException ex) {
        }

    }

}
