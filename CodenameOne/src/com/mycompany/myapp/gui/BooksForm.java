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
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Services.ServiceBook;
import com.mycompany.myapp.Services.ServiceBooking;
import com.mycompany.myapp.Services.ServiceLike;
import com.mycompany.myapp.Services.ServiceWishliste;
import com.mycompany.myapp.entities.Booking;
import com.mycompany.myapp.entities.Books;
import com.mycompany.myapp.entities.Likes;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.Wishliste;

import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author Neifos
 */
public class BooksForm extends SideMenuEtudiantForm1 {

    ArrayList<Likes> likes = new ArrayList<Likes>();
    ArrayList<Booking> bbking = new ArrayList<Booking>();
    ArrayList<Wishliste> Wish = new ArrayList<Wishliste>();
    Likes lik = new Likes();

    Wishliste wishe = new Wishliste();
  /*  public void Booooks(Resources theme ,Container C,User u ){
          for (Books b : ServiceBook.getInstance().getAllBooks()) {

            Label lbTitre = new Label();
            Label lbNbrLike = new Label();
            Label lbViews = new Label();

            SpanLabel lbDescription = new SpanLabel();
            Label label = new Label("_____________________________________________________________");
            Label label1 = new Label("_____________________________________________________________");
            label.getUnselectedStyle().setFgColor(ColorUtil.rgb(128, 128, 128));
            label1.getUnselectedStyle().setFgColor(ColorUtil.rgb(128, 128, 128));
            ImageViewer picBook = new ImageViewer();
            Button comment = new Button();
            comment.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 0, 0));

            FontImage.setMaterialIcon(comment, FontImage.MATERIAL_COMMENT, 5);

            Button booking = new Button();

            Container book = new Container(BoxLayout.y());

            int deviceWidth = Display.getInstance().getDisplayWidth();
            Image placeholder = Image.createImage(deviceWidth, deviceWidth + 100, 0xbfc9d2); //square image set to 10% of screen width
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);

            picBook.setImage(URLImage.createToStorage(encImage, "Medium_" + "http://localhost/GestionEcole/web/images/" + b.getPicbook(), "http://localhost/GestionEcole/web/images/" + b.getPicbook(), URLImage.RESIZE_SCALE));

            lbTitre.setText(b.getTitrebook());
            lbDescription.setText(b.getDescriptionbook());
            lbTitre.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 0, 90));
            Font smallPlainMonospaceFont = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_SMALL);
            Font largeBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
            lbTitre.getUnselectedStyle().setFont(largeBoldSystemFont);
            lbDescription.getTextAllStyles().setFont(smallPlainMonospaceFont);
            booking.getUnselectedStyle().setFont(smallPlainMonospaceFont);

            booking.getAllStyles().setBorder(Border.createLineBorder(CENTER));

            book.addComponent(picBook);

            Boolean sort = false, like = false;
          

            for (Likes l : likes) {

                if ((l.getBook().getIdbook() == b.getIdbook()) && !sort) {
                   

                    lik = l;

                    like = true;
                    sort = true;

                }
            }
            Container bar = new Container(BoxLayout.x());
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
                        new BooksForm(theme).show();

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
                        System.out.println(lik);
                        ServiceLike.getInstance().deleteLike(lik);
                        removeAll();
                        new BooksForm(theme).show();

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
                        new BooksForm(theme).show();

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
                        new BooksForm(theme).show();

                    }
                });

            }
            comment.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    removeAll();
                    new CommentBookForm(theme, b, u).show();
                }
            });
            //  book.addComponent(label1);
            book.add(bar);
            lbNbrLike.setText(String.valueOf(b.getNbrLike() + " Likes"));
            lbNbrLike.getAllStyles().setFont(smallPlainMonospaceFont);
            book.addComponent(lbNbrLike);
            book.addComponent(lbTitre);
            book.addComponent(lbDescription);

            if (!bbking.isEmpty()) {
                for (Booking k : bbking) {
                    if (k.getBook().getIdbook() == b.getIdbook()) {
                        booking.setText("Calimed");
                        booking.pressed();
                    } else {

                        booking.setText("Add To My Books");
                        booking.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                Booking res = new Booking(User.getCurrentuser(), b);
                                System.out.println(res);
                                ServiceBooking.getInstance().addBooking(res);
                                removeAll();
                        new BooksForm(theme).show();
                      
                            }
                        });
                    }

                }
            } else {
                booking.setText("Add To My Books");
                booking.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Booking res = new Booking(User.getCurrentuser(), b);
                        System.out.println(res);
                        ServiceBooking.getInstance().addBooking(res);
                      removeAll();
                       refreshTheme();
                        Booooks(theme, C, u);
                        
                       
                      
                        
                        
                     
                    }
                });

            }
          
            
            book.addComponent(booking);
            book.addComponent(label);
         

            C.addComponent(book);

        }
    }*/

    public BooksForm(Resources theme) {
          super(BoxLayout.y());
        Toolbar tb = getToolbar();
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseX(
                FlowLayout.encloseIn(menuButton), FlowLayout.encloseIn(
                new Label("Books", "Title")
        )
        );
tb.setTitleComponent(titleCmp);
        setupSideMenu(theme);
       // User u = new User("4", "14589647", "sofien", "sofien", "sofien@esprit.tn", "qddqfs", "Etudiant");
        //User.setCurrentuser(u);

        getToolbar().addCommandToOverflowMenu("My Books", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new MyBooksForm(theme).show();

            }
        });
        getToolbar().addCommandToOverflowMenu("My WishListe", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new MyWishListeForm(theme).show();

            }
        });
        Container C = new Container(BoxLayout.y());
        for (Likes l : ServiceLike.getInstance().getAllLikes()) {

            if (User.getCurrentId().equals(l.getUser().getId().toString())) {
                likes.add(l);

            }

        }
        for (Booking bb : ServiceBooking.getInstance().getAllBooking()) {

            if (User.getCurrentId().equals(bb.getUser().getId().toString())) {
                bbking.add(bb);

            }

        }
        for (Wishliste l : ServiceWishliste.getInstance().getAllWishliste()) {

            if (User.getCurrentId().equals(l.getUser().getId().toString())) {
                Wish.add(l);

            }

        }
        //Booooks(theme, C,u);

       for (Books b : ServiceBook.getInstance().getAllBooks()) {

            Label lbTitre = new Label();
            Label lbNbrLike = new Label();
            Label lbViews = new Label();

            SpanLabel lbDescription = new SpanLabel();
            Label label = new Label("_____________________________________________________________");
            Label label1 = new Label("_____________________________________________________________");
            label.getUnselectedStyle().setFgColor(ColorUtil.rgb(128, 128, 128));
            label1.getUnselectedStyle().setFgColor(ColorUtil.rgb(128, 128, 128));
            ImageViewer picBook = new ImageViewer();
            Button comment = new Button();
            comment.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 0, 0));

            FontImage.setMaterialIcon(comment, FontImage.MATERIAL_COMMENT, 5);

            Button booking = new Button();

            Container book = new Container(BoxLayout.y());

            int deviceWidth = Display.getInstance().getDisplayWidth();
            Image placeholder = Image.createImage(deviceWidth, deviceWidth + 100, 0xbfc9d2); //square image set to 10% of screen width
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
           System.out.println(b.getPicbook());
            picBook.setImage(URLImage.createToStorage(encImage, "Medium_" + Statics.BOOK_IMG_URL + b.getPicbook(), Statics.BOOK_IMG_URL + b.getPicbook(), URLImage.RESIZE_SCALE));

            lbTitre.setText(b.getTitrebook());
            lbDescription.setText(b.getDescriptionbook());
            lbTitre.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 0, 90));
            Font smallPlainMonospaceFont = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_SMALL);
            Font largeBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
            lbTitre.getUnselectedStyle().setFont(largeBoldSystemFont);
            lbDescription.getTextAllStyles().setFont(smallPlainMonospaceFont);
            booking.getUnselectedStyle().setFont(smallPlainMonospaceFont);

            booking.getAllStyles().setBorder(Border.createLineBorder(CENTER));

            book.addComponent(picBook);

            Boolean sort = false, like = false;
          

            for (Likes l : likes) {

                if ((l.getBook().getIdbook() == b.getIdbook()) && !sort) {
                   

                    lik = l;

                    like = true;
                    sort = true;

                }
            }
            Container bar = new Container(BoxLayout.x());
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
                        new BooksForm(theme).show();

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
                        System.out.println(lik);
                        ServiceLike.getInstance().deleteLike(lik);
                        removeAll();
                        new BooksForm(theme).show();

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
                        new BooksForm(theme).show();

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
                        new BooksForm(theme).show();

                    }
                });

            }
            comment.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    removeAll();
                    new CommentBookForm(theme, b, User.getCurrentuser()).show();
                }
            });
            //  book.addComponent(label1);
            book.add(bar);
            lbNbrLike.setText(String.valueOf(b.getNbrLike() + " Likes"));
            lbNbrLike.getAllStyles().setFont(smallPlainMonospaceFont);
            book.addComponent(lbNbrLike);
            book.addComponent(lbTitre);
            book.addComponent(lbDescription);

            if (!bbking.isEmpty()) {
                for (Booking k : bbking) {
                    if (k.getBook().getIdbook() == b.getIdbook()) {
                        booking.setText("Calimed");
                        booking.pressed();
                    } else {

                        booking.setText("Add To My Books");
                        booking.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                Booking res = new Booking(User.getCurrentuser(), b);
                                System.out.println(res);
                                ServiceBooking.getInstance().addBooking(res);
                                removeAll();
                                new BooksForm(theme).show();
                            }
                        });
                    }

                }
            } else {
                booking.setText("Add To My Books");
                booking.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Booking res = new Booking(User.getCurrentuser(), b);
                        System.out.println(res);
                        ServiceBooking.getInstance().addBooking(res);
                      removeAll();
                        new BooksForm(theme).show();
                        
                     
                    }
                });

            }
          
            
            book.addComponent(booking);
            book.addComponent(label);
         

            C.addComponent(book);

        }
        add(C);

    }

}
