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
import com.mycompany.myapp.Services.ResultatService;
import com.mycompany.myapp.Services.UserService;
import com.mycompany.myapp.entities.Note;
import com.mycompany.myapp.entities.Resultat;
import com.mycompany.myapp.entities.User;
import java.util.ArrayList;

/**
 *
 * @author Aymen
 */
public class ResultatEtudiant extends SideMenuEtudiantForm1 {

    public ResultatEtudiant(Resources res) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseX(
                FlowLayout.encloseIn(menuButton), FlowLayout.encloseIn(
                new Label("Result List", "Title")
        )
        );

        tb.setTitleComponent(titleCmp);
        setupSideMenu(res);

        ArrayList<Note> n = NoteService.getInstance().getNotes(User.getCurrentId());
        ArrayList<Resultat> r = ResultatService.getInstance().getOneResultat(User.getCurrentId());

        Container cnAll = new Container(BoxLayout.y());
        Font lbl = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        Font lbl2 = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
Label liste = new Label("Subjects List ");
liste.getAllStyles().setFgColor(ColorUtil.rgb(153, 0, 96));
liste.getUnselectedStyle().setFont(lbl);
cnAll.getUnselectedStyle().setMargin(2, 2, 4, 2);
cnAll.add( BorderLayout.centerAbsolute(liste));
        for (Note note : n) {
            //   Container cnMat = new Container(BoxLayout.x());
            Container notesCn = new Container(BoxLayout.y());

            Label l1 = new Label("   "+note.getMatiere().getNomMatiere());
            l1.getUnselectedStyle().setFont(lbl);
            l1.getAllStyles().setFgColor(ColorUtil.rgb(0, 51, 102));

            

            Label l4 = new Label("" + Double.toString(note.getMoyenne()));
            l4.getUnselectedStyle().setFgColor(BLACK);

            Container not = GridLayout.encloseIn(2, l1, l4);
            
            cnAll.add(not);

        }
        Label ligne = new Label ("_____________________________");
        ligne.getUnselectedStyle().setFgColor(ColorUtil.rgb(153, 0, 96));
        cnAll.add(BorderLayout.centerAbsolute(ligne));
        for (Resultat result : r) {
            Label resultatLabel = new Label(result.getResultat() + "");
            resultatLabel.getUnselectedStyle().setFont(lbl);
            resultatLabel.getAllStyles().setFgColor(BLACK);
            Label resultatLabel1 = new Label("  Resultat");
            resultatLabel1.getUnselectedStyle().setFont(lbl);
            resultatLabel1.getAllStyles().setFgColor(ColorUtil.rgb(0, 51, 102));

            Container resall = GridLayout.encloseIn(2, resultatLabel1, resultatLabel);
 cnAll.add(resall);
        }

        this.add(cnAll);

    }

}
