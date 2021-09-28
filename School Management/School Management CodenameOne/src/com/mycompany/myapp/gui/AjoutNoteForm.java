/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import static com.codename1.charts.util.ColorUtil.BLUE;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.Services.NoteService;
import com.mycompany.myapp.Services.UserService;
import com.mycompany.myapp.entities.Classe;
import com.mycompany.myapp.entities.Matiere;
import com.mycompany.myapp.entities.User;
import java.util.ArrayList;

/**
 *
 * @author Aymen
 */
public class AjoutNoteForm extends SideMenuEnseignantForm1 {

    String idC = "", idE = "", idM = "";

    public AjoutNoteForm(Resources res) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseX(
                FlowLayout.encloseIn(menuButton), FlowLayout.encloseIn(
                new Label("Add Grade", "Title")
        )
        );

        tb.setTitleComponent(titleCmp);
        setupSideMenu(res);

        Label l0 = new Label("                                                                                               .");
        Label l = new Label("___________________________________________________");
        Label l1 = new Label("___________________________________________________");
        Label l2 = new Label("___________________________________________________");
        Label l3 = new Label("___________________________________________________");
        Label l4 = new Label("___________________________________________________");
        Label l5 = new Label("___________________________________________________");
         add(l0);

        Container c1 = new Container(BoxLayout.x());
        Container c2 = new Container(BoxLayout.x());
        Container c3 = new Container(BoxLayout.x());
        Container c4 = new Container(BoxLayout.x());
        c1.setPreferredH(100);
        c3.setPreferredH(100);
        c2.setPreferredH(100);

        Container ccc = new Container(BoxLayout.x());
        ccc.setPreferredH(100);
        Container cds = new Container(BoxLayout.x());
        cds.setPreferredH(100);
        Container cex = new Container(BoxLayout.x());
        cex.setPreferredH(100);
        addAll(c1, l1, c2, l2, c3);

        Font lbl = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);

        Label lcls = new Label("  Select Class      ");
        lcls.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 51, 102));
        c1.add(lcls);

        ComboBox cb = new ComboBox();

        cb.getAllStyles().setFgColor(ColorUtil.rgb(153, 0, 76));
        cb.getAllStyles().setBgColor(ColorUtil.rgb(153, 0, 76));
        cb.setPreferredW(350);

        ArrayList<Classe> c = NoteService.getInstance().getClasses(User.getCurrentId());
        for (Classe cl : c) {
            cb.addItem(cl.getName());
        }
        c1.add(cb);

        ComboBox cb2 = new ComboBox();
        cb2.getAllStyles().setBgColor(ColorUtil.rgb(153, 0, 76));
        cb2.getAllStyles().setFgColor(ColorUtil.rgb(153, 0, 76));

        ComboBox cb3 = new ComboBox();
        cb2.setPreferredW(350);
        cb3.setPreferredW(350);

        Label lmat = new Label("  Select Subject ");
        lmat.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 51, 102));
        Label letd = new Label("  Select Student");
        letd.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 51, 102));

        c2.addAll(lmat, cb2);
        c3.addAll(letd, cb3);

        Label ds = new Label("  CD Grade    ");
        ds.getAllStyles().setFgColor(ColorUtil.rgb(0, 51, 102));
        Label cc = new Label("  CC Grade    ");
        cc.getAllStyles().setFgColor(ColorUtil.rgb(0, 51, 102));
        Label ex = new Label("  Exam Grade");
        ex.getAllStyles().setFgColor(ColorUtil.rgb(0, 51, 102));

        TextField dstf = new TextField();
        TextField cctf = new TextField();
        TextField extf = new TextField();
        cctf.setPreferredW(300);
        cctf.getUnselectedStyle().setBgColor(BLUE);
        dstf.setPreferredW(300);
        extf.setPreferredW(300);
        ccc.addAll(cc, cctf);
        cds.addAll(ds, dstf);
        cex.addAll(ex, extf);
        Container conteneurNote = BoxLayout.encloseY(l3, ccc, l4, cds, l5, cex);
conteneurNote.setVisible(false);
        add(conteneurNote);
        

        Button save = new Button("Add");
        add(BorderLayout.centerAbsolute(save));

        cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ComboBox cb2 = new ComboBox();
                ComboBox cb3 = new ComboBox();
                cb2.setPreferredW(350);
                cb3.setPreferredW(350);

                c2.removeAll();
                c3.removeAll();
                Label lmat = new Label("  Select Subject ");
                lmat.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 51, 102));
                Label letd = new Label("  Select Student");
                letd.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 51, 102));

                c2.addAll(lmat, cb2);
                c3.addAll(letd, cb3);
                idC = Integer.toString(c.get(cb.getSelectedIndex()).getId());
                ArrayList<Matiere> m = NoteService.getInstance().getMatiere(idC);
                for (Matiere matiere : m) {
                    System.out.println(matiere.getNomMatiere());
                    cb2.addItem(matiere.getNomMatiere());
                }
                ArrayList<User> u = UserService.getInstance().getEtudiants(Integer.toString(c.get(cb.getSelectedIndex()).getId()));
                for (User etud : u) {
                    cb3.addItem(etud.getPrenomUser() + " " + etud.getNomUser());
                }
                idM = m.get(cb2.getSelectedIndex()).getIdMatiere();

                idE = u.get(cb2.getSelectedIndex()).getId();

                cb2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        idM = m.get(cb2.getSelectedIndex()).getIdMatiere();

                    }
                });
                cb3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        idE = u.get(cb3.getSelectedIndex()).getId();
                        conteneurNote.setVisible(true);

                    }
                });

            }
        });

        String noteConstraint = "[0-9,.]";
        Validator v = new Validator();
              v.addConstraint(cctf, new RegexConstraint(noteConstraint, "Please enter a valid grade")).
                addConstraint(dstf, new RegexConstraint(noteConstraint, "Please enter a valid grade")).
                addConstraint(extf, new RegexConstraint(noteConstraint, "Please enter a valid grade"));

        v.addSubmitButtons(save);
        save.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 51, 102));
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (NoteService.getInstance().addNote(idC, idM, idE, cctf.getText(), dstf.getText(), extf.getText())) {
                    Dialog.show("Success", "Grade Added", new Command("OK"));
                    ComboBox cb2 = new ComboBox();
                    ComboBox cb3 = new ComboBox();
                    cb2.setPreferredW(350);
                    cb3.setPreferredW(350);

                    c2.removeAll();
                    c3.removeAll();
                    Label lmat = new Label("  Select Subject ");
                    lmat.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 51, 102));
                    Label letd = new Label("  Select Student");
                    letd.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 51, 102));

                    c2.addAll(lmat, cb2);
                    c3.addAll(letd, cb3);
                    cctf.setText("");
                    dstf.setText("");
                    extf.setText("");
                    conteneurNote.setVisible(false);

                } else {
                    Dialog.show("ERROR", "This Student already has a grade in this subject", new Command("OK"));
                }//System.out.println("classe "+idC+" matiere= "+idM+" etudiant= "+idE);
            }
        });

    }
}
