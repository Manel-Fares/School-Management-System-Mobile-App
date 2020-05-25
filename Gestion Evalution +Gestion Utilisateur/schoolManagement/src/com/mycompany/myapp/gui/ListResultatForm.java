/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import static com.codename1.charts.util.ColorUtil.BLACK;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Services.ResultatService;
import com.mycompany.myapp.entities.Resultat;
import com.mycompany.myapp.entities.User;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Aymen
 */
public class ListResultatForm extends SideMenuPersonnelForm1 {

    public ListResultatForm(Resources res) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseX(
                FlowLayout.encloseIn(menuButton), FlowLayout.encloseIn(
                new Label("Results List", "Title")
        )
        );

        tb.setTitleComponent(titleCmp);
        setupSideMenu(res);

        Container cnAll = new Container(BoxLayout.y());
        add(cnAll);

        Font lbl = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);

        ArrayList<Resultat> r = ResultatService.getInstance().getResultat();
        {

            Container cn = new Container(BoxLayout.y());
            tb.addCommandToOverflowMenu("Calculate Result", null, e -> {
                 ResultatService.getInstance().calculResults();
               cnAll.removeAll();
               cn.removeAll();

                for (Resultat result : r) {
                    Label nomprenom = new Label( "     " + result.getEtudiant().getNomUser());
                    nomprenom.getAllStyles().setFgColor(ColorUtil.rgb(0, 76, 153));
                    nomprenom.getUnselectedStyle().setFont(lbl);

                    Label resultat = new Label(String.valueOf(result.getResultat()));
                    resultat.getUnselectedStyle().setFgColor(BLACK);
                    resultat.getUnselectedStyle().setFont(lbl);
                    Container affichage = GridLayout.encloseIn(2, nomprenom, resultat);

                    cn.add(affichage);

                }                cnAll.add(cn);


            });

        }
        
         Container cn = new Container(BoxLayout.y());

         tb.addCommandToOverflowMenu("Delete Result", null, e -> {
             ResultatService.getInstance().deleteResults();
               cnAll.removeAll();
               cn.removeAll();

               
               

            });

        
        
    }

}
