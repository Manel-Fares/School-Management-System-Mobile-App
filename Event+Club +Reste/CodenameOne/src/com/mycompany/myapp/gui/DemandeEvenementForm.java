/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.capture.Capture;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
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
import com.codename1.ui.validation.Constraint;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.Services.DemandespecifiqueService;

import com.mycompany.myapp.Services.ServiceDemandeEvenement;
import com.mycompany.myapp.entities.Club;
import com.mycompany.myapp.entities.DemandeEvenement;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.Date;

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
    Validator val = new Validator();
    boolean xx = false;
    boolean val1 = true;
    boolean val2 = true;
    boolean val3 = true;
    boolean val4 = true;
    boolean val5 = true;
    boolean val11 = false;
    boolean val22 = false;
    boolean val33 = false;
    boolean val44 = false;
    boolean val55 = false;

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
        Date date = new Date();
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
        val.addConstraint(budget, new Constraint() {
            public boolean isValid(Object value) {
                String v = (String) value;

                if (v.length() != 0) {
                    for (int i = 0; i < v.length(); i++) {
                        char c = v.charAt(i);
                        if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9') {
                            val11 = true;

                            return true;
                        }
                        val1 = false;
                        return false;
                    }
                }
                val1 = false;
                return false;
            }

            @Override
            public String getDefaultFailMessage() {
                return "Must be valid phone number";
            }
        });

        val.addConstraint(dd, new Constraint() {
            public boolean isValid(Object value) {

                DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                String datee = dateFormat.format(date);
                int datecurrent = Integer.parseInt(datee);
                String dated = dateFormat.format(dd.getDate());
                int datedeb = Integer.parseInt(dated);
                String datef = dateFormat.format(df.getDate());
                int datefin = Integer.parseInt(datef);

                if (datedeb - datecurrent >= 0 && datefin - datedeb > 0) {
                    val33 = true;

                    return true;

                } else {
                    val3 = false;
                    return xx;
                }
            }

            public String getDefaultFailMessage() {
                return "Must be valid date ";
            }
        });

        val.addConstraint(df, new Constraint() {
            public boolean isValid(Object value) {

                DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                String datee = dateFormat.format(date);
                int datecurrent = Integer.parseInt(datee);
                String dated = dateFormat.format(dd.getDate());
                int datedeb = Integer.parseInt(dated);
                String datef = dateFormat.format(df.getDate());
                int datefin = Integer.parseInt(datef);

                if (datefin - datecurrent > 0 && datefin - datedeb > 0) {

                    val44 = true;
                    System.out.println("val3: " + val4);
                    return true;
                } else {

                    val4 = false;
                    System.out.println("val3: " + val4);
                    return xx;
                }
            }
        

            public String getDefaultFailMessage() {
                return "Must be valid date ";
            }
        });
       
         val.addConstraint(description, new LengthConstraint(1));
         if (DemandespecifiqueService.getInstance().getTestResp(Integer.parseInt(User.getCurrentId()))) {
            tb.addCommandToOverflowMenu("consult Demande Event", null, e -> new ConsulterDemandeEventform(res).show());
        }
        
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
        if (fileNameInserver == null) {
            val55 = false;
        } else {
            val5 = true;
        }

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
      if(!val.isValid() ||fileNameInserver==null)
      { Dialog.show("error", "verifie your champs", new Command("OK"));}
      else 
      {

                c.setNomClub(club.getSelectedItem().toString());
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String ddd = dateFormat.format(dd.getDate());
                String ddf = dateFormat.format(df.getDate());
                System.out.println(fileNameInserver);
                DemandeEvenement dev = new DemandeEvenement(c, description.getText(),
                        ddd.toString(), ddf.toString(), "non valider ", Float.parseFloat(budget.getText()), fileNameInserver);

                if (ServiceDemandeEvenement.getInstance().addDemande(dev)) {
                    Dialog.show("Success", "demand sended", new Command("OK"));
                    new ConsulterDemandeEventform(res).show();
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            }
            }});
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
