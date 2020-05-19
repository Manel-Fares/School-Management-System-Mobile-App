/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import static com.codename1.charts.util.ColorUtil.BLACK;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;

import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.Services.UserService;
import com.mycompany.myapp.entities.User;
import java.util.Date;

public class RegisterForm extends SideMenuAdminForm {

    private Label prenomLabel, nomLabel, sexeLabel, dateNaissanceLabel, emailLabel,
            usernameLabel, passwordLabel, telephoneLabel, cinLabel, roleLabel;
    private TextField nom, prenom, email, username, password, telephone, cin;
    private Picker gender, role;
    private Container mainContainer;
    private Button addBtn;
    private Resources theme = UIManager.initFirstTheme("/theme");

    public RegisterForm(Resources res) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseX(
                FlowLayout.encloseIn(menuButton), FlowLayout.encloseIn(
                new Label("Registration", "Title")
        )
        );

        tb.setTitleComponent(titleCmp);
        setupSideMenu(res);
      

        username = new TextField();
        username.getUnselectedStyle().setFgColor(BLACK);
        nom = new TextField();
        nom.getUnselectedStyle().setFgColor(BLACK);
       // nom.setUIID("TextField");
        prenom = new TextField();
        prenom.getUnselectedStyle().setFgColor(BLACK);
       cin = new TextField();
        cin.getUnselectedStyle().setFgColor(BLACK);
        cin.setConstraint(com.codename1.ui.TextArea.NUMERIC);
        email = new TextField();
        email.getUnselectedStyle().setFgColor(BLACK);
        email.setConstraint(com.codename1.ui.TextArea.EMAILADDR);
        password = new TextField();
        password.getUnselectedStyle().setFgColor(BLACK);
        password.setConstraint(com.codename1.ui.TextArea.PASSWORD);
        telephone = new TextField();
        telephone.getUnselectedStyle().setFgColor(BLACK);
        telephone.setConstraint(com.codename1.ui.TextArea.NUMERIC);

        //l1.getUnselectedStyle().setAlignment(Component.CENTER);
        Font l1_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        usernameLabel = new Label("  Username: ");
        usernameLabel.getUnselectedStyle().setFont(l1_font);
        usernameLabel.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 76, 153));
        nomLabel = new Label("  Last Name: ");
        nomLabel.getUnselectedStyle().setFont(l1_font);
        nomLabel.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 76, 153));
        prenomLabel = new Label("  First Name: ");
        prenomLabel.getUnselectedStyle().setFont(l1_font);
        prenomLabel.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 76, 153));
        sexeLabel = new Label("  Gender: ");
        sexeLabel.getUnselectedStyle().setFont(l1_font);
        sexeLabel.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 76, 153));
        roleLabel = new Label("  Role: ");
        roleLabel.getUnselectedStyle().setFont(l1_font);
        roleLabel.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 76, 153));
        dateNaissanceLabel = new Label("  Birthdate: ");
        dateNaissanceLabel.getUnselectedStyle().setFont(l1_font);
        dateNaissanceLabel.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 76, 153));
        emailLabel = new Label("  Email: ");
        emailLabel.getUnselectedStyle().setFont(l1_font);
        emailLabel.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 76, 153));
        passwordLabel = new Label("  Password: ");
        passwordLabel.getUnselectedStyle().setFont(l1_font);
        passwordLabel.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 76, 153));
        telephoneLabel = new Label("  Telephone: ");
        telephoneLabel.getUnselectedStyle().setFont(l1_font);
        telephoneLabel.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 76, 153));
        cinLabel = new Label("  N°cin ");
        cinLabel.getUnselectedStyle().setFont(l1_font);
        cinLabel.getUnselectedStyle().setFgColor(ColorUtil.rgb(0, 76, 153));
        gender = new Picker();
        gender.getUnselectedStyle().setFgColor(BLACK);
        gender.setType(Display.PICKER_TYPE_STRINGS);
        gender.setStrings("Femme", "Homme");
        gender.setSelectedString("  Femme");
        role = new Picker();
        role.setType(Display.PICKER_TYPE_STRINGS);
        role.setStrings("ROLE_ADMINISTRATEUR", "ROLE_ETUDIANT", "ROLE_ENSEIGNANT", "ROLE_PERSONNEL");
        role.setSelectedString("ROLE_ETUDIANT");
        Picker dateBirth = new Picker();
        dateBirth.setType(Display.PICKER_TYPE_DATE);
        dateBirth.setDate(new Date(0));
        dateBirth.getUnselectedStyle().setFgColor(BLACK);
      
        addBtn = new Button("Register");
        addBtn.setUIID("LoginButton");
        // addBtn.getUnselectedStyle().setBgColor(0x0000A3);

        String phoneConstraint = "[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]";
        String cinConstraint = "[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]";
        Validator v = new Validator();
        v.addConstraint(nom, new LengthConstraint(4)).
                addConstraint(prenom, new LengthConstraint(4)).
                addConstraint(username, new LengthConstraint(4)).
                addConstraint(password, new LengthConstraint(5)).
                addConstraint(cin, new LengthConstraint(8)).
                addConstraint(telephone, new LengthConstraint(8)).
                addConstraint(email, RegexConstraint.validEmail()).
                addConstraint(telephone, new RegexConstraint(phoneConstraint, "Please enter a valid phone number")).
                addConstraint(cin, new RegexConstraint(cinConstraint, "Please enter a valid cin number"));

        v.addSubmitButtons(addBtn);
        Container mainContainer = GridLayout.encloseIn(2, BoxLayout.encloseY(
                usernameLabel, nomLabel, prenomLabel, cinLabel, sexeLabel, dateNaissanceLabel, emailLabel, passwordLabel, telephoneLabel, roleLabel),
                BoxLayout.encloseY(username, nom, prenom, cin, gender, dateBirth, email, password, telephone, role
                ));

        addBtn.addActionListener((ActionListener) (ActionEvent evt) -> {
            if ((username.getText().length() == 0) || (password.getText().length() == 0)) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {
                try {

                    User u = new User("", Integer.parseInt(cin.getText()), username.getText(), nom.getText(), prenom.getText(), email.getText(), Integer.parseInt(telephone.getText()), gender.getSelectedString(), password.getText(), role.getSelectedString());
                    if (UserService.getInstance().addUser(u)) {
                        Dialog.show("Success", "Account created", new Command("OK"));
                        new RegisterForm(res).show();
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                } catch (Exception e) {
                    Dialog.show("ERROR", "error", new Command("OK"));
                }

            }

        });
        Label nac = new Label("New Account ?");
        nac.getUnselectedStyle().setFgColor(ColorUtil.rgb(153, 0, 76));
        nac.getUnselectedStyle().setFont(l1_font);
        this.getAllStyles().setBgImage(theme.getImage("registerbg.jpg"));
                this.add(BorderLayout.centerAbsolute(nac));

        this.add(mainContainer);
        add(BorderLayout.centerAbsolute(addBtn));

        //   this.add(BorderLayout.CENTER, addBtn);
    }

}
