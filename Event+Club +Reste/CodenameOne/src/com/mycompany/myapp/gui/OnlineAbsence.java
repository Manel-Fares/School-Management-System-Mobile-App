///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.myapp.gui;
//
//
//import com.codename1.charts.util.ColorUtil;
//import com.codename1.components.SpanLabel;
//import com.codename1.ui.Button;
//import com.codename1.ui.Component;
//import com.codename1.ui.Container;
//import com.codename1.ui.FontImage;
//import com.codename1.ui.Form;
//import com.codename1.ui.Label;
//import com.codename1.ui.Toolbar;
//import com.codename1.ui.geom.Dimension;
//import com.codename1.ui.layouts.BorderLayout;
//import com.codename1.ui.layouts.BoxLayout;
//import com.codename1.ui.layouts.FlowLayout;
//import com.codename1.ui.plaf.RoundBorder;
//import com.codename1.ui.table.TableLayout;
//import com.codename1.ui.util.Resources;
//import com.mycompany.myapp.Services.ServiceAbsence;
//import com.mycompany.myapp.Services.UserService;
//import com.mycompany.myapp.entities.Absence;
//import com.mycompany.myapp.entities.User;
//import java.util.ArrayList;
//
///**
// *
// * @author Pytrooooo
// */
//public class OnlineAbsence extends SideMenuEtudiantForm1 {
//
//    Label lbTotal = new Label();
//    ArrayList<Absence> myList = new ArrayList();
//    Container C7 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//    public OnlineAbsence(Resources theme) {
//         super(BoxLayout.y());
//        Toolbar tb = getToolbar();
//        Button menuButton = new Button("");
//        menuButton.setUIID("Title");
//        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
//        menuButton.addActionListener(e -> getToolbar().openSideMenu());
//
//        Container titleCmp = BoxLayout.encloseX(
//                FlowLayout.encloseIn(menuButton), FlowLayout.encloseIn(
//                new Label("Current Absence", "Title")
//        )
//        );
//
//        tb.setTitleComponent(titleCmp);
//        setupSideMenu(theme);
//
//        myList = ServiceAbsence.getInstance().getOnlineAbsence(Integer.parseInt(User.getCurrentId()));
//        
//        lbTotal.setText("Total Absence : " + ServiceAbsence.getInstance().count);
//        lbTotal.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 51, 102));
//        C7.add(BorderLayout.centerAbsolute(lbTotal));
//  
//
//        for (Absence t : myList) {
//            Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//            TableLayout tl = new TableLayout(1, 2);
//            Container container = new Container(tl);
//            SpanLabel sp = new SpanLabel();           
//            SpanLabel spMatiere = new SpanLabel();
//            SpanLabel spDate = new SpanLabel();
//            SpanLabel spTimeDeb = new SpanLabel();
//            SpanLabel spTimeFin = new SpanLabel();
//            String id = String.valueOf(t.getId());
//            String nbr = String.valueOf(t.getId());
//            spMatiere.setText("Matiere : " + t.getMatiere().getNomMatiere());
//            spDate.setText("Date : " + t.getDate());
//            spTimeDeb.setText("TimeDeb : " + t.getTimeDeb());
//            spTimeFin.setText("TimeFin : " + t.getTimeFin());
//           // Dimension d = new Dimension(100, 100);
//           // Dimension d1 = new Dimension(600, 700);
//            // C2.setInlineAllStyles("border :2px solid black");
//            //Stroke borderStroke = new Stroke(2, Stroke.CAP_BUTT, Stroke.JOIN_BEVEL, 1);
//            C2.getUnselectedStyle().setBorder(RoundBorder.createGrooveBorder(4, 0x000000));
//            //createDashedBorder(3, 0xffffff)
//        //   C2.setPreferredSize(d1);
//            C2.addAll(BorderLayout.centerAbsolute( spMatiere), BorderLayout.centerAbsolute(spDate),
//                    BorderLayout.centerAbsolute( spTimeDeb), BorderLayout.centerAbsolute(spTimeFin));
//            C7.add(C2);
//        }
//        add(C7);
//    }
//
//}
