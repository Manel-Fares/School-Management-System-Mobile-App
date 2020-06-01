/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.capture.Capture;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Services.DemandespecifiqueService;
import com.mycompany.myapp.Services.ServiceClubSpecifique;
import com.mycompany.myapp.Services.ServiceDemandeEvenement;
import com.mycompany.myapp.entities.Club;
import com.mycompany.myapp.utils.Statics;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author asus
 */
public class EditClubForm extends SideMenuEtudiantForm1 {

    private FileUploader file;
    String fileNameInserver;
    private String imgPath;

    public EditClubForm(Resources res,Form previous,Club c ) {

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

        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Button picture = new Button("choose");
        picture.setMaterialIcon(FontImage.MATERIAL_CLOUD_UPLOAD);

        TextField Domaine = new TextField();
        Domaine.setHint("New Domain");
        Button send=new Button("Send");
        picture.addPointerReleasedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    imgPath = Capture.capturePhoto();
                    System.out.println(imgPath);
                    String link = imgPath;
                    int pod = link.indexOf("/", 2);
                    System.out.println(pod);
                    String news = link.substring(pod + 2, link.length());
                    System.out.println(news);
                    FileUploader fu = new FileUploader(Statics.USER_IMG_URL);
                    System.out.println("aaaaaaaaa");
                    fileNameInserver = fu.upload(news);
                    System.out.println(fileNameInserver);
                } catch (Exception ex) {
                }

            }
        });
     
        c1.add(Domaine);
        c1.add(picture);
        c1.add(send);
        add(c1);
       
        
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 Club cc=new Club(c.getIdClub(), null, null, Domaine.getText(),fileNameInserver);
                  if (DemandespecifiqueService.getInstance().editClub(cc)) {
                    Dialog.show("Success", "demand sended", new Command("OK"));
                   previous.showBack();
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            }
        });
            getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
