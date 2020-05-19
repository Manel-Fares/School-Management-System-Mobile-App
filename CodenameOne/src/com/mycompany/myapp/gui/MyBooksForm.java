/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Services.ServiceBooking;
import com.mycompany.myapp.entities.Booking;
import com.mycompany.myapp.entities.User;

import com.mycompany.myapp.utils.Statics;

/**
 *
 * @author Neifos
 */
public class MyBooksForm extends SideMenuEtudiantForm1 {

    public MyBooksForm(Resources res) {
         super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseX(
                FlowLayout.encloseIn(menuButton), FlowLayout.encloseIn(
                new Label("My Books ", "Title")
        )
        );

        tb.setTitleComponent(titleCmp);
        setupSideMenu(res);
     //   User u = new User("4", "14589647", "sofien", "sofien", "sofien@esprit.tn", "qddqfs", "Etudiant");
       // User.setCurrentuser(u);
  
                       Container C = new Container(BoxLayout.y());

        for (Booking b : ServiceBooking.getInstance().getAllBooking()) {
          int id = (int)Float.parseFloat(User.getCurrentuser().getId());
          int idd = (int)Float.parseFloat(b.getUser().getId());
           if (idd== id) {
                
              
                Label lbTitre = new Label();
                Button delete = new Button();
             FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE, 3);
                SpanLabel lbDescription = new SpanLabel();
  
                Container book = new Container(BoxLayout.x());
                Container desc = new Container(BoxLayout.y());
                
                ImageViewer picBook = new ImageViewer();

                int deviceWidth = Display.getInstance().getDisplayWidth();
                Image placeholder = Image.createImage(deviceWidth/10, deviceWidth /10, 0xbfc9d2); //square image set to 10% of screen width
                EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);

                picBook.setImage(URLImage.createToStorage(encImage, "Medium_" +  Statics.BOOK_IMG_URL + b.getBook().getPicbook(),  Statics.BOOK_IMG_URL + b.getBook().getPicbook(), URLImage.RESIZE_SCALE));
                
               lbTitre.setText(b.getBook().getTitrebook());
                lbDescription.setText(b.getBook().getDescriptionbook());
                lbTitre.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 0, 90));
                Font smallPlainMonospaceFont = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_SMALL);
                Font largeBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
                lbTitre.getUnselectedStyle().setFont(largeBoldSystemFont);
                lbDescription.getTextAllStyles().setFont(smallPlainMonospaceFont);
                desc.addComponent(lbTitre);
                desc.addComponent(lbDescription);
               book.addComponent(picBook);
                book.addComponent(desc);
                book.addComponent(delete);
                C.addComponent(book);
                delete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        ServiceBooking.getInstance().deleteBooking(b);
                        removeAll();
                        new MyBooksForm(res).show();
                    }
                });
             
            }

        }
        add(C);
    }

}
