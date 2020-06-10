/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import static com.codename1.charts.util.ColorUtil.BLACK;
import static com.codename1.charts.util.ColorUtil.BLUE;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Services.NoteService;
import com.mycompany.myapp.Services.ServiceAbsence;
import com.mycompany.myapp.Services.UserService;
import com.mycompany.myapp.entities.Absence;
import com.mycompany.myapp.entities.Note;
import com.mycompany.myapp.entities.User;
import java.util.ArrayList;

/**
 *
 * @author Aymen
 */
public class listeNoteEtudiants extends SideMenuEtudiantForm1 {

    public listeNoteEtudiants(Resources res) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseX(
                FlowLayout.encloseIn(menuButton), FlowLayout.encloseIn(
                new Label("Subjects List", "Title")
        )
        );

        tb.setTitleComponent(titleCmp);
        setupSideMenu(res);

        ArrayList<Absence> myList = ServiceAbsence.getInstance().getOnlineAbsence(Integer.parseInt(User.getCurrentId()));
        int nbr = ServiceAbsence.getInstance().count;
        if (nbr > 0) {

          //  ServiceAbsence.getInstance().sendsms(52478690, nbr);
        }
        ArrayList<Note> n = NoteService.getInstance().getNotes(User.getCurrentId());

        Container cnAll = new Container(BoxLayout.y());
        Font lbl = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        Font lbl2 = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);

        for (Note note : n) {
            //   Container cnMat = new Container(BoxLayout.x());
            Label nom = new Label("  " + note.getMatiere().getNomMatiere());
            nom.getUnselectedStyle().setFgColor(ColorUtil.rgb(153, 0, 76));
            nom.getUnselectedStyle().setFont(lbl);

            Container notesCn = new Container(BoxLayout.y());
            Label l1 = new Label("   CD :");
            l1.getUnselectedStyle().setFont(lbl);
            l1.getAllStyles().setFgColor(ColorUtil.rgb(0, 76, 153));

            Label lcc = new Label("   CC :");
            lcc.getAllStyles().setFgColor(ColorUtil.rgb(0, 76, 153));
            lcc.getUnselectedStyle().setFont(lbl);

            Label lex = new Label("   Exam :");
            lex.getAllStyles().setFgColor(ColorUtil.rgb(0, 76, 153));
            lex.getUnselectedStyle().setFont(lbl);

            Label l2 = new Label("" + note.getNoteDS());
            l2.getUnselectedStyle().setFgColor(BLACK);
            Label nlcc = new Label(" " + note.getNoteCC());
            nlcc.getUnselectedStyle().setFgColor(BLACK);
            Label nlex = new Label("" + note.getNoteExam());
            nlex.getUnselectedStyle().setFgColor(BLACK);
            Label l3 = new Label("  Score ");
            l3.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 76, 153));
            Label l4 = new Label("  " + Double.toString(note.getMoyenne()));
            l4.getUnselectedStyle().setFgColor(BLACK);
            /*  Label ligne = new Label("____________________________________________________");
            ligne.getUnselectedStyle().setFgColor(ColorUtil.rgb(153, 0, 76));

            ligne.getUnselectedStyle().setFont(lbl);*/
            l3.getUnselectedStyle().setFont(lbl);
            Button details = new Button();
            FontImage.setMaterialIcon(details, FontImage.MATERIAL_ARROW_DROP_DOWN);

            Button nodetails = new Button();
            FontImage.setMaterialIcon(nodetails, FontImage.MATERIAL_ARROW_DROP_UP);

            Container nts = BoxLayout.encloseY(l3, l4);
            Container ns = BoxLayout.encloseY(lcc, l1, lex);
            Container not = GridLayout.encloseIn(3, ns, BoxLayout.encloseY(nlcc, l2, nlex), nts);
            Container notes = BoxLayout.encloseY(
                    nom, not
            );
            Label l = new Label("");

            cnAll.add(notes);

        }

        this.add(cnAll);

    }

}
