/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import static com.codename1.charts.util.ColorUtil.BLACK;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Services.UserService;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Aymen
 */
public class ListUsersForm extends SideMenuAdminForm {

    EncodedImage ec;
    String url;
    Image profilePic;
    Image mask;

    public ListUsersForm(Resources res) {

        super(BoxLayout.y());

        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseX(
                FlowLayout.encloseIn(menuButton), FlowLayout.encloseIn(
                new Label("Users List", "Title")
        )
        );

        tb.setTitleComponent(titleCmp);
        setupSideMenu(res);

        try {
            ec = EncodedImage.create("/pic.jpeg");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        Container cnAll = new Container(BoxLayout.y());
        Font lbl = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        ArrayList<User> u = UserService.getInstance().getAllUsers();
        for (User user : u) {
            Container cnone = new Container(BoxLayout.x());
            url = Statics.USER_IMG_URL + user.getPicUser();
            profilePic = URLImage.createToStorage(ec, url, url, URLImage.RESIZE_SCALE);
            mask = res.getImage("round-mask.png");
            profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
            Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
            profilePicLabel.setMask(mask.createMask());

            Container cnUser = new Container(BoxLayout.y());

            Label prenom = new Label("  "+user.getPrenomUser() + " " + user.getNomUser());
            Label email = new Label(user.getEmail());
            Label numtel = new Label("  "+user.getCinUser()+"");
                        Button delete = new Button();


            email.getUnselectedStyle().setFgColor(BLACK);
            prenom.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 0, 90));
            numtel.getUnselectedStyle().setFgColor(BLACK);
            delete.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 0, 90));

            prenom.getUnselectedStyle().setFont(lbl);

            FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE_SWEEP);
            FontImage.setMaterialIcon(email, FontImage.MATERIAL_EMAIL);
            FontImage.setMaterialIcon(numtel, FontImage.MATERIAL_PHONE_IPHONE);

            cnUser.addAll(prenom, email, numtel);
            cnone.addAll(profilePicLabel, cnUser, delete);
         
            cnAll.add(cnone);

            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    UserService.getInstance().deleteUser(user.getUsername());

                }
            });

            System.out.println(user.getPrenomUser() + " " + user.getNomUser());

        }
        this.add(cnAll);

    }

}
