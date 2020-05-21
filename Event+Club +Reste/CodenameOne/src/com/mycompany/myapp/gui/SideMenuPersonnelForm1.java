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
import com.mycompany.myapp.Services.ResultatService;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;

/**
 * Common code that can setup the side menu
 *
 * @author Shai Almog
 */
public abstract class SideMenuPersonnelForm1 extends Form {

    EncodedImage ec;

    public SideMenuPersonnelForm1(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuPersonnelForm1(String title) {
        super(title);
    }

    public SideMenuPersonnelForm1() {
    }

    public SideMenuPersonnelForm1(Layout contentPaneLayout) {
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
        getToolbar().addMaterialCommandToSideMenu("  Calculate School Result", FontImage.MATERIAL_DASHBOARD, e -> {
            ResultatService.getInstance().calculResults();
            new ListResultatForm(res).show();
        });
        getToolbar().addMaterialCommandToSideMenu("  Delete All Result", FontImage.MATERIAL_TRENDING_UP, e -> {
            ResultatService.getInstance().deleteResults();
            new ListResultatForm(res).show();
        });
        getToolbar().addMaterialCommandToSideMenu("  Display Result", FontImage.MATERIAL_ACCESS_TIME, e -> new ListResultatForm(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new LoginForm(res).show());

    }

}
