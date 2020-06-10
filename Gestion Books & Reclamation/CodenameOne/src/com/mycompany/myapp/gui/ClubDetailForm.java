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
import com.mycompany.myapp.Services.ServiceRate;
import com.mycompany.myapp.entities.Club;
import com.mycompany.myapp.entities.Rating;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author asus
 */
public class ClubDetailForm extends SideMenuEtudiantForm1 {

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

    private Slider createStarRankSlider(Resources res,int r, int idd) {
        Slider starRank = new Slider();
        starRank.setEditable(true);
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
        starRank.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Club c = new Club((int) idd);
                Rating r = new Rating((double) starRank.getProgress(), c, User.getCurrentuser());
           //     System.out.println("rate" + r);
             //   System.out.println(starRank.getProgress());
                if (ServiceRate.getInstance().addRate(r)) {
                    Dialog.show("Success", "Club has been rated", new Command("OK"));
                    new ListClubForm(res).show();

                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            }

        });
        return starRank;
    }

    public ClubDetailForm(Resources res, int id) {
        super(BoxLayout.y());
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

    
        
        //  Form current;

        //    current = this;
        //  System.out.println("azdzadza"+id);
        Map<Club, Float> clubs = new HashMap<>();
        //System.out.println("clubdddsmmm"+clubs.entrySet());
        clubs = ServiceRate.getInstance().getAllTasks(id);
        //  System.out.println("clubDetailform: "+clubs);
        for (Map.Entry<Club, Float> entry : clubs.entrySet()) {
            //System.out.println("clubsmmm"+clubs.entrySet());
            //System.out.println("club name "+entry.getKey().getNomClub()+"rate"+entry.getValue());
            try {
               Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                Label l = new Label(entry.getKey().getNomClub());
                setTitle(l.getText());
                float idd = Float.parseFloat(entry.getValue().toString());
                int rate = ((int) idd);
                Label l0 = new Label("" + entry.getValue());
                Label l1 = new Label("Domain :"+entry.getKey().getDomaine());
                Label l2 = new Label("Responsable :"+entry.getKey().getResponsable().getUsername());
                // Label l3 = new Label(""+entry.getKey().getResponsable().getId());
                //  l3.setVisible(false);
                String url = "http://localhost/projet/schoolMgt/web/images/" + entry.getKey().getImage();
                ec = EncodedImage.create("/pic.jpeg");
                imgg = URLImage.createToStorage(ec, url, url, URLImage.RESIZE_SCALE);
                iv = new ImageViewer(imgg);
                c1.add(iv);
                c1.add(FlowLayout.encloseCenter(createStarRankSlider(res,rate, id)));
                c1.add(l);
                c1.add(l1);
                c1.add(l2);
                // c1.add(l3);
                //
                Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));
                c.add(l0);
                Slider ra = new Slider();
                String pp = entry.getValue().toString();
                float nbrrate = Float.parseFloat(pp);
                ra.setProgress((int) nbrrate);
                ra.setMinValue(0);
                ra.setMaxValue(5);
                ra.setWidth(20);
                c.add(ra);
                Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                c1.add(c);
                c2.add(c1);
                add(c2);
                //l.addPointerPressedListener(e-> new ClubDetailForm(current,Integer.parseInt(l3.getText())).show());
            } catch (IOException ex) {
                ex.getMessage();
            }
        }
            tb.setTitleComponent(titleCmp);
        setupSideMenu(res);
        //      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
