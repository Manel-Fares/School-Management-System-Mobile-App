/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;


import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Services.ServiceAbsence;
import com.mycompany.myapp.entities.Absence;
import com.mycompany.myapp.entities.Matiere;
import com.mycompany.myapp.entities.User;
import java.util.Calendar;

/**
 *
 * @author Pytrooooo
 */
public class EditAbsence extends SideMenuEnseignantForm1{
    
  
    Picker tfDate = new Picker();
    Picker tfTimedeb = new Picker();
    Picker tfTimefin = new Picker();
    ComboBox cbMat = new ComboBox();
    ComboBox cbUse = new ComboBox();
    Button btnAdd = new Button("Edit");
    Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));

    public EditAbsence(Resources theme,String id,String date,String timedeb,String timefin) {
         super(BoxLayout.y());
        Toolbar tb = getToolbar();
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseX(
                FlowLayout.encloseIn(menuButton), FlowLayout.encloseIn(
                new Label("Edit Absence", "Title")
        )
        );

        tb.setTitleComponent(titleCmp);
        setupSideMenu(theme);

        
        for (Matiere t : ServiceAbsence.getInstance().getAllMat()) {
            cbMat.addItem(t.getIdMatiere() + "-" + t.getNomMatiere());
        }
        for (User t : ServiceAbsence.getInstance().getAllUse()) {
            cbUse.addItem(t.getId() + "-" + t.getNomUser());
        }
        tfDate.setType(Display.PICKER_TYPE_DATE);
        tfTimedeb.setType(Display.PICKER_TYPE_TIME);
        tfTimefin.setType(Display.PICKER_TYPE_TIME);
        tfDate.setText(date);
        tfTimedeb.setText(timedeb);
        tfTimefin.setText(timefin);
        C1.add(tfDate);
        C1.add(tfTimedeb);
        C1.add(tfTimefin);
        C1.add(cbMat);
        C1.add(cbUse);
        C1.add(btnAdd);
        C1.add(C2);
        add(C1);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Calendar c = Calendar.getInstance();
                c.setTime(tfDate.getDate());
                String m1 = Integer.toString(c.get(Calendar.MONTH) + 1);
                String y1 = Integer.toString(c.get(Calendar.YEAR));
                String d1 = Integer.toString(c.get(Calendar.DAY_OF_MONTH));
                String Date = y1 + "-" + m1 + "-" + d1;
                String Timedeb = tfTimedeb.getText();
                String Timefin = tfTimefin.getText();
                Matiere m = new Matiere();
                User u = new User();
                String idmat = cbMat.getSelectedItem().toString().substring(0, cbMat.getSelectedItem().toString().indexOf('-'));
                String iduse = cbUse.getSelectedItem().toString().substring(0, cbUse.getSelectedItem().toString().indexOf('-'));
                //System.out.println();
                for (Matiere t : ServiceAbsence.getInstance().getAllMatid(idmat)) {
                    m.setIdMatiere(t.getIdMatiere());
                    m.setNomMatiere(t.getNomMatiere());
                }
                for (User t : ServiceAbsence.getInstance().getAllUseid(iduse)) {
                    u.setId(t.getId());
                    u.setNomUser(t.getNomUser());
                }
                Absence t = new Absence(u, m, Date, Timedeb, Timefin);
                System.out.println(t);
                ServiceAbsence.getInstance().editAbsence(id,t);
                Dialog.show("Edit Absence", "successfully edited", "ok", null);
                GetAbsence l = new GetAbsence(theme);
                 l.show();
            }
        });

    }
}
