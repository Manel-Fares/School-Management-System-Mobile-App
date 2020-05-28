/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import static com.codename1.charts.util.ColorUtil.BLACK;
import static com.codename1.charts.util.ColorUtil.BLUE;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Services.NoteService;
import com.mycompany.myapp.Services.UserService;
import com.mycompany.myapp.entities.Classe;
import com.mycompany.myapp.entities.Matiere;
import com.mycompany.myapp.entities.Note;
import com.mycompany.myapp.entities.User;
import java.util.ArrayList;

/**
 *
 * @author Aymen
 */
public class GestionNoteForm extends SideMenuEnseignantForm1 {

    String idC = "", idM = "";

    public GestionNoteForm(Resources res) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseX(
                FlowLayout.encloseIn(menuButton), FlowLayout.encloseIn(
                new Label("Users Statistics", "Title")
        )
        );

        tb.setTitleComponent(titleCmp);
        setupSideMenu(res);

        Label l0 = new Label("                                                                                               .");
        Label l6 = new Label("___________________________________________________");

        add(l0);

        Container c1 = new Container(BoxLayout.x());
        Container c2 = new Container(BoxLayout.x());
        Container c3 = new Container(BoxLayout.y());
        c1.setPreferredH(100);
        c2.setPreferredH(100);
        addAll(c1, c2, c3);

        Font lbl = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);

        Label lcls = new Label("  Select Class      ");
        lcls.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 51, 102));
        c1.add(lcls);

        ComboBox cb = new ComboBox();

        cb.getAllStyles().setFgColor(ColorUtil.rgb(153, 0, 76));
        cb.getAllStyles().setBgColor(ColorUtil.rgb(153, 0, 76));
        cb.setPreferredW(350);

        ArrayList<Classe> c = NoteService.getInstance().getClasses("2");
        for (Classe cl : c) {
            cb.addItem(cl.getName());
        }
        c1.add(cb);

        ComboBox cb2 = new ComboBox();
        cb2.getAllStyles().setBgColor(ColorUtil.rgb(153, 0, 76));
        cb2.getAllStyles().setFgColor(ColorUtil.rgb(153, 0, 76));

        cb2.setPreferredW(350);

        Label lmat = new Label("  Select Subject ");
        lmat.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 51, 102));

        c2.addAll(lmat, cb2);

        cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                c3.setVisible(false);

                ComboBox cb2 = new ComboBox();

                cb2.setPreferredW(350);

                c2.removeAll();
                Label lmat = new Label("  Select Subject ");
                lmat.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 51, 102));

                c2.addAll(lmat, cb2);
                idC = Integer.toString(c.get(cb.getSelectedIndex()).getId());
                ArrayList<Matiere> m = NoteService.getInstance().getMatiere(idC);
                for (Matiere matiere : m) {
                    System.out.println(matiere.getNomMatiere());
                    cb2.addItem(matiere.getNomMatiere());
                }

                idM = m.get(cb2.getSelectedIndex()).getIdMatiere();

                cb2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {

                        int idC = c.get(cb.getSelectedIndex()).getId();
                        String idM = m.get(cb2.getSelectedIndex()).getIdMatiere();

                        ArrayList<Note> notes = NoteService.getInstance().getNotesEns(idM, Integer.toString(idC), User.getCurrentId());
                        //     Font lbl = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
                        c3.removeAll();

                        for (Note note : notes) {

                            //   Container cnMat = new Container(BoxLayout.x());
                            Label nom = new Label("  " + note.getEtudiant().getNomUser() );
                            System.out.println(" nom " + note.getEtudiant().getNomUser());
                            System.out.println(" prenom " + note.getEtudiant().getPrenomUser());
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
                            Label nlcc = new Label("" + note.getNoteCC());
                            nlcc.getUnselectedStyle().setFgColor(BLACK);
                            Label nlex = new Label("" + note.getNoteExam());
                            nlex.getUnselectedStyle().setFgColor(BLACK);
                            Label l3 = new Label("  Score ");
                            l3.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 76, 153));
                            Label l4 = new Label("  " + Double.toString(note.getMoyenne()));
                            l4.getUnselectedStyle().setFgColor(BLACK);

                            l3.getUnselectedStyle().setFont(lbl);
                            Button details = new Button();
                            FontImage.setMaterialIcon(details, FontImage.MATERIAL_ARROW_DROP_DOWN);

                            Button nodetails = new Button();
                            FontImage.setMaterialIcon(nodetails, FontImage.MATERIAL_ARROW_DROP_UP);

                            Container nts = BoxLayout.encloseY(l3, l4);
                            Container ns = BoxLayout.encloseY(lcc, l1, lex);
                            Container not = GridLayout.encloseIn(3, ns, BoxLayout.encloseY(nlcc, l2, nlex), nts);
                            Container notess = BoxLayout.encloseY(
                                    nom, not
                            );
                            c3.add(notess);

                        }
                        c3.setVisible(true);
                    }
                });

            }
        });

    }
}
