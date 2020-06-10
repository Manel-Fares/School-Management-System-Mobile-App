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
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.Services.ServiceBooking;
import com.mycompany.myapp.Services.ServiceComment;
import com.mycompany.myapp.Services.ServiceLike;
import com.mycompany.myapp.Services.ServiceWishliste;
import com.mycompany.myapp.entities.Booking;
import com.mycompany.myapp.entities.Books;
import com.mycompany.myapp.entities.Comment;
import com.mycompany.myapp.entities.Likes;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.Wishliste;

import com.mycompany.myapp.utils.Statics;

/**
 *
 * @author Neifos
 */
public class CommentBookForm extends SideMenuEtudiantForm1 {

  
   public void AfficherComment(Container C,Books b,Resources theme){
            for (Comment c : ServiceComment.getInstance().getAllComments(b.getIdbook())) {

            Container commentaire = new Container(BoxLayout.y());
            Container cc = new Container(BoxLayout.x());

            Label lbBody = new Label();
            Label lbUser = new Label();
            Button delete = new Button();

            lbUser.setText(c.getAuthor().getUsername());

            lbBody.setText(c.getBody());

            FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE, 3);
            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ServiceComment.getInstance().deleteComment(c);
                    removeAll();
                    new CommentBookForm(theme, b).show();

                }
            });
            //lbBody.getUnselectedStyle().setFont(smallPlainMonospaceFont);
            commentaire.addComponent(lbUser);
            cc.addComponent(lbBody);
            cc.addComponent(delete);
            commentaire.addComponent(cc);

            C.addComponent(commentaire);
        }
        
    }
    public CommentBookForm(Resources theme, Books b) {
         super(BoxLayout.y());
        Toolbar tb = getToolbar();
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseX(
                FlowLayout.encloseIn(menuButton), FlowLayout.encloseIn(
                new Label(b.getTitrebook(), "Title")
        )
        );

        tb.setTitleComponent(titleCmp);
  setupSideMenu(theme);
        Container C = new Container(BoxLayout.y());

      

        Label lbTitre = new Label();
        SpanLabel lbDescription = new SpanLabel();
        Label label = new Label();
          Label lbNbrLike = new Label();
        ImageViewer picBook = new ImageViewer();
        Button comment = new Button();
        comment.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 0, 0));
        FontImage.setMaterialIcon(comment, FontImage.MATERIAL_COMMENT, 5);
        Button booking = new Button("Add To My Books");

        Container book = new Container(BoxLayout.y());
        Container bar = new Container(BoxLayout.x());
        int deviceWidth = Display.getInstance().getDisplayWidth();
        Image placeholder = Image.createImage(deviceWidth, deviceWidth+100, 0xbfc9d2); //square image set to 10% of screen width
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);

        picBook.setImage(URLImage.createToStorage(encImage, "Medium_" +  Statics.USER_IMG_URL + b.getPicbook(),  Statics.USER_IMG_URL + b.getPicbook(), URLImage.RESIZE_SCALE));

        lbTitre.setText(b.getTitrebook());
        lbDescription.setText(b.getDescriptionbook());
        lbTitre.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 0, 90));
        Font smallPlainMonospaceFont = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_SMALL);
        Font largeBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        lbTitre.getUnselectedStyle().setFont(largeBoldSystemFont);
        lbDescription.getTextAllStyles().setFont(smallPlainMonospaceFont);
        booking.getUnselectedStyle().setFont(smallPlainMonospaceFont);
        booking.getAllStyles().setBorder(Border.createLineBorder(CENTER));
  
   

        if (ServiceLike.getInstance().checkLike(b)==null) {

            Button btLike = new Button();
            btLike.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 0, 0));
            FontImage.setMaterialIcon(btLike, FontImage.MATERIAL_FAVORITE_BORDER, 5);

            bar.add(btLike);
            btLike.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    Likes ll = new Likes(b, User.getCurrentuser());
                    ServiceLike.getInstance().addLike(ll);
                    removeAll();
                    new CommentBookForm(theme, b).show();

                }
            });

        } else {
           

            Button btLike = new Button();
            btLike.getUnselectedStyle().setFgColor(ColorUtil.rgb(234, 60, 80));
            FontImage.setMaterialIcon(btLike, FontImage.MATERIAL_FAVORITE, 5);

            bar.add(btLike);
            btLike.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    ServiceLike.getInstance().deleteLike(ServiceLike.getInstance().checkLike(b));
                    removeAll();
                    new CommentBookForm(theme, b).show();

                }
            });

        }
        bar.addComponent(comment);

    

        if (ServiceWishliste.getInstance().checkWishliste(b)==null) {

            Button btwish = new Button();
            btwish.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 0, 0));
            FontImage.setMaterialIcon(btwish, FontImage.MATERIAL_BOOKMARK_BORDER, 5);

            bar.add(btwish);
            btwish.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    Wishliste ll = new Wishliste(b, User.getCurrentuser());
                    ServiceWishliste.getInstance().addWishliste(ll);
                    removeAll();
                    new CommentBookForm(theme, b).show();

                }
            });

        } else {

            Button btwish = new Button();
            btwish.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 0, 0));
            FontImage.setMaterialIcon(btwish, FontImage.MATERIAL_BOOKMARK, 5);

            bar.add(btwish);
            btwish.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    ServiceWishliste.getInstance().deleteWishliste(ServiceWishliste.getInstance().checkWishliste(b));
                    removeAll();
                    new CommentBookForm(theme, b).show();

                }
            });

        }
             if (ServiceBooking.getInstance().checkBooking(b) != null) {
                booking.setText("Calimed");
                booking.pressed();
            } else {

                booking.setText("Add To My Books");
                booking.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Booking res = new Booking(User.getCurrentuser(), b);

                        ServiceBooking.getInstance().addBooking(res);
                        removeAll();
                        new BooksForm(theme).show();
                    }
                });

            }
            lbNbrLike.setText(String.valueOf(b.getNbrLike() + " Likes"));
        book.addComponent(picBook);
        book.addComponent(bar);
        book.addComponent(lbNbrLike);
        book.addComponent(lbTitre);
        book.addComponent(lbDescription);
        book.addComponent(booking);
        C.addComponent(book);
    
        for (Comment c : ServiceComment.getInstance().getAllComments(b.getIdbook())) {

            Container commentaire = new Container(BoxLayout.y());
            Container cc = new Container(BoxLayout.x());

            Label lbBody = new Label();
            Label lbUser = new Label();
          ImageViewer picUser = new ImageViewer();
            Button delete = new Button();

            lbUser.setText(c.getAuthor().getUsername());
              int deviceWidthh = Display.getInstance().getDisplayWidth();
                Image placeholderr = Image.createImage(deviceWidthh/10, deviceWidthh /10, 0xbfc9d2); //square image set to 10% of screen width
                EncodedImage encImagee = EncodedImage.createFromImage(placeholderr, false);
                
                System.out.println(c.getAuthor().getPicUser());
                    picUser.setImage(URLImage.createToStorage(encImagee, "Medium_" + "c1.jpg" /*Statics.USER_IMG_URL + c.getAuthor().getPicUser()*/,  Statics.USER_IMG_URL + "c1.jpg"/*c.getAuthor().getPicUser()*/, URLImage.RESIZE_SCALE));

            

            lbBody.setText(c.getBody());

            FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE, 3);
            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ServiceComment.getInstance().deleteComment(c);
                    removeAll();
                    new CommentBookForm(theme, b).show();

                }
            });
            lbBody.getUnselectedStyle().setFont(smallPlainMonospaceFont);
            commentaire.addComponent(lbUser);
           cc.addComponent(picUser);
            cc.addComponent(lbBody);
            
            cc.addComponent(delete);
            commentaire.addComponent(cc);

            C.addComponent(commentaire);
        }

        Container write = new Container(BoxLayout.x());

        Button btn = new Button("Post");
        TextComponent cmmnt = (TextComponent) new TextComponent().label("Write Something..");
        Validator val = new Validator();
        val.addConstraint(cmmnt, new LengthConstraint(1));
        val.addSubmitButtons(btn);
        write.addComponent(cmmnt);
        // write.addComponent(btn);
        C.addComponent(write);
         C.addComponent(btn);
         btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Comment c = new Comment();
                c.setAuthor(User.getCurrentuser());
                c.setThread_id(b.getIdbook());
                c.setBody(cmmnt.getText());
                
                ServiceComment.getInstance().addComment(c);
                
           
                removeAll();
                
                
                 new CommentBookForm(theme, b).show();
               
           
            }
        });

        add(C);
    }

 

}
