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
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.entities.Books;
import com.mycompany.myapp.entities.Comment;
import com.mycompany.myapp.entities.Likes;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.Wishliste;
import com.mycompany.myapp.services.ServiceComment;
import com.mycompany.myapp.services.ServiceLike;
import com.mycompany.myapp.services.ServiceWishliste;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author Neifos
 */
public class CommentBookForm extends Form {

    ArrayList<Likes> likes = new ArrayList<Likes>();
    ArrayList<Wishliste> Wish = new ArrayList<Wishliste>();
    Likes lik = new Likes();
    Wishliste wishe = new Wishliste();
   public void AfficherComment(Container C,Books b,User u,Resources theme){
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
                    new CommentBookForm(theme, b, u).show();

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
    public CommentBookForm(Resources theme, Books b, User u) {
        Container C = new Container(BoxLayout.y());

        for (Likes l : ServiceLike.getInstance().getAllLikes()) {

            if (User.getCurrentId().equals(l.getUser().getId().toString())) {
                likes.add(l);

            }

        }
        for (Wishliste l : ServiceWishliste.getInstance().getAllWishliste()) {

            if (User.getCurrentId().equals(l.getUser().getId().toString())) {
                Wish.add(l);

            }

        }

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

        //label.setIcon(URLImage.createToStorage(encImage, "Large_" + "http://localhost/GestionEcole/web/images/"+b.getPicbook(), "http://localhost/GestionEcole/web/images/"+b.getPicbook(), URLImage.RESIZE_SCALE));
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
        Boolean sort = false, like = false;
        for (Likes l : likes) {

            if ((l.getBook().getIdbook() == b.getIdbook()) && !sort) {

                lik = l;

                like = true;
                sort = true;

            }
        }

        if (!like) {

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
                    new CommentBookForm(theme, b, u).show();

                }
            });

        } else {
            System.out.println(lik);

            Button btLike = new Button();
            btLike.getUnselectedStyle().setFgColor(ColorUtil.rgb(234, 60, 80));
            FontImage.setMaterialIcon(btLike, FontImage.MATERIAL_FAVORITE, 5);

            bar.add(btLike);
            btLike.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    ServiceLike.getInstance().deleteLike(lik);
                    removeAll();
                    new CommentBookForm(theme, b, u).show();

                }
            });

        }
        bar.addComponent(comment);

        Boolean sortwish = false, wish = false;

        for (Wishliste l : Wish) {

            if ((l.getBook().getIdbook() == b.getIdbook()) && !sortwish) {

                wishe = l;

                wish = true;
                sortwish = true;

            }
        }

        if (!wish) {

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
                    new CommentBookForm(theme, b, u).show();

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

                    ServiceWishliste.getInstance().deleteWishliste(wishe);
                    removeAll();
                    new CommentBookForm(theme, b, u).show();

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
            Button delete = new Button();

            lbUser.setText(c.getAuthor().getUsername());

            lbBody.setText(c.getBody());

            FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE, 3);
            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ServiceComment.getInstance().deleteComment(c);
                    removeAll();
                    new CommentBookForm(theme, b, u).show();

                }
            });
            lbBody.getUnselectedStyle().setFont(smallPlainMonospaceFont);
            commentaire.addComponent(lbUser);
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
                
                
                 new CommentBookForm(theme, b, u).show();
               
           
            }
        });

        add(C);
    }

}
