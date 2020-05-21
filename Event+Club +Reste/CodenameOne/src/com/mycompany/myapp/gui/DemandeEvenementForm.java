/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.capture.Capture;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Services.DemandespecifiqueService;
import com.mycompany.myapp.Services.ServiceClub;
import com.mycompany.myapp.Services.ServiceDemandeEvenement;
import com.mycompany.myapp.entities.Club;
import com.mycompany.myapp.entities.DemandeEvenement;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author asus
 */
public class DemandeEvenementForm extends SideMenuEtudiantForm1 {

    Form current;
    private FileUploader file;
    String fileNameInserver;
    private String imgPath;

    public DemandeEvenementForm(Resources res) {
       super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseX(
                FlowLayout.encloseIn(menuButton), FlowLayout.encloseIn(
                new Label("Demande", "Title")
        )
        );

        tb.setTitleComponent(titleCmp);
        setupSideMenu(res);
        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        TextArea description = new TextArea();
        description.setHint("description");
        Dimension d = new Dimension(500, 150);
        description.setSize(d);
        TextField budget = new TextField();
        budget.setHint("budget");
        Picker dd = new Picker();
        Picker df = new Picker();
        Button btnSend = new Button("send");
        ComboBox club = new ComboBox();
        Button picture = new Button("choose");
        picture.setMaterialIcon(FontImage.MATERIAL_CLOUD_UPLOAD);
        club.setX(CENTER);
        int x = Integer.parseInt(User.getCurrentId());
        DemandespecifiqueService sc = DemandespecifiqueService.getInstance();
        ArrayList<Club> clubs = sc.getClubspcifique(x);
        for (int i = 0; i < clubs.size(); i++) {

            club.addItem("" + clubs.get(i).getNomClub());
        }
        c1.add(club);
        c1.add(budget);
        c1.add(description);
        c1.add(dd);
        c1.add(df);
        c1.add(picture);
        c1.add(btnSend);
        add(c1);
        Club c = new Club();
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
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                c.setNomClub(club.getSelectedItem().toString());
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String ddd = dateFormat.format(dd.getDate());
                String ddf = dateFormat.format(df.getDate());
                  System.out.println(fileNameInserver);
                DemandeEvenement dev = new DemandeEvenement(c, description.getText(),
                        
                        ddd.toString(), ddf.toString(), "non valider ", Float.parseFloat(budget.getText()),fileNameInserver);
                
                if (ServiceDemandeEvenement.getInstance().addDemande(dev)) {
                    Dialog.show("Success", "Connection accepted", new Command("OK"));
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            }
        });
  setupSideMenu(res);        // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

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
