/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;


import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Services.ServiceAbsence;
import com.mycompany.myapp.entities.Absence;

/**
 *
 * @author Pytrooooo
 */
public class GetAbsence extends SideMenuEnseignantForm1{

    public GetAbsence(Resources theme) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseX(
                FlowLayout.encloseIn(menuButton), FlowLayout.encloseIn(
                new Label("Absences", "Title")
        )
        );

        tb.setTitleComponent(titleCmp);
        setupSideMenu(theme);


       
        for (Absence t : ServiceAbsence.getInstance().getAllAbsence()) {
            Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            TableLayout tl = new TableLayout(1, 2);
            Container container = new Container(tl);
            SpanLabel sp = new SpanLabel();
            SpanLabel spId = new SpanLabel();
            SpanLabel spUser = new SpanLabel();
            SpanLabel spMatiere = new SpanLabel();
            SpanLabel spDate = new SpanLabel();
            SpanLabel spTimeDeb = new SpanLabel();
            SpanLabel spTimeFin = new SpanLabel();
            Button btnEdit = new Button("Edit");
            Button btnDelete = new Button("Delete");
            String id = String.valueOf(t.getId());
            String nbr = String.valueOf(t.getId());
            spId.setText("Id : " + id);
            spUser.setText("User : " + t.getUser().getNomUser());
            spMatiere.setText("Matiere : " + t.getMatiere().getNomMatiere());
            spDate.setText("Date : " + t.getDate());
            spTimeDeb.setText("TimeDeb : " + t.getTimeDeb());
            spTimeFin.setText("TimeFin : " + t.getTimeFin());
           
            C1.addAll(BorderLayout.centerAbsolute(btnEdit),BorderLayout.centerAbsolute( btnDelete));
            // C2.setInlineAllStyles("border :2px solid black");
            //Stroke borderStroke = new Stroke(2, Stroke.CAP_BUTT, Stroke.JOIN_BEVEL, 1);
            C2.getUnselectedStyle().setBorder(RoundBorder.createGrooveBorder(4, 0x000000));
            //createDashedBorder(3, 0xffffff)
            C2.addAll(BorderLayout.centerAbsolute( spUser),
                    BorderLayout.centerAbsolute(spMatiere),BorderLayout.centerAbsolute( spDate), BorderLayout.centerAbsolute(spTimeDeb)
                    ,BorderLayout.centerAbsolute( spTimeFin), C1);

            btnDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ServiceAbsence.getInstance().DeleteAbsence(id);
                    Dialog.show("Delete Absence", "successfully removed", "ok", null);
                    GetAbsence l = new GetAbsence(theme);
                     l.show();
                }
            });
            
            btnEdit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    EditAbsence ea = new EditAbsence(theme, id, t.getDate(), t.getTimeDeb(), t.getTimeFin());
                    ea.show();
                }
            });
            add(C2);
        }

    }

}
