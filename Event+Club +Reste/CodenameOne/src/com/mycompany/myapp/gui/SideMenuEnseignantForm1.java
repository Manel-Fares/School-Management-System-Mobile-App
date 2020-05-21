package com.mycompany.myapp.gui;

import com.codename1.components.ToastBar;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;

/**
 * Common code that can setup the side menu
 *
 * @author Shai Almog
 */
public abstract class SideMenuEnseignantForm1 extends Form {

    EncodedImage ec;

    public SideMenuEnseignantForm1(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuEnseignantForm1(String title) {
        super(title);
    }

    public SideMenuEnseignantForm1() {
    }

    public SideMenuEnseignantForm1(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public void setupSideMenu(Resources res) {
        String url = Statics.USER_IMG_URL + User.getCurrentPic();
        try {
            ec = EncodedImage.create("/pic.jpeg");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Image profilePic = URLImage.createToStorage(ec, url, url, URLImage.RESIZE_SCALE);
        Image mask = res.getImage("round-mask.png");
        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label("  " + User.getCurrentPrenom() + " " + User.getCurrentNom(), profilePic, "SideMenuTitle");
        profilePicLabel.setMask(mask.createMask());
        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");

        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu("  Students Grades", FontImage.MATERIAL_DASHBOARD, e -> new GestionNoteForm(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Add Grade", FontImage.MATERIAL_TRENDING_UP, e -> new AjoutNoteForm(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Classes Maganement", FontImage.MATERIAL_SETTINGS, e -> new LoginForm(res).show());
       // getToolbar().addMaterialCommandToSideMenu("  Abscences", FontImage.MATERIAL_ACCESS_TIME, e -> new GetAbsence(res).show());
        //getToolbar().addMaterialCommandToSideMenu("  Add Abscences", FontImage.MATERIAL_ACCESS_TIME, e -> new AddAbsence(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new LoginForm(res).show());
    }

}
