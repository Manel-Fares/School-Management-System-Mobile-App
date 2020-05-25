package com.mycompany.myapp.gui;

import static com.codename1.charts.util.ColorUtil.BLACK;
import static com.codename1.charts.util.ColorUtil.WHITE;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.Services.UserService;
import java.io.IOException;
import java.io.InputStream;

/**
 * The Login form
 *
 * @author Shai Almog
 */
public class LoginForm extends Form {

    public LoginForm(Resources theme) {

        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Welcome", "WelcomeWhite")
        );
        // this.getAllStyles().setBgImage(theme.getImage("bk.jpg"));

        getTitleArea().setUIID("Container");

        Image profilePic = theme.getImage("logo.png");
        Image mask = theme.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePic");
        profilePicLabel.setMask(mask.createMask());

        TextField login = new TextField("", "username");
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        login.getAllStyles().setMargin(LEFT, 0);
        password.getAllStyles().setMargin(LEFT, 0);
        Label loginIcon = new Label("", "TextField");
        Label passwordIcon = new Label("", "TextField");
        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        passwordIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);

        Button loginButton = new Button("LOGIN");
        loginButton.setUIID("LoginButton");
        //   loginButton.getAllStyles().setBgColor(WHITE);
        //    loginButton.getAllStyles().setFgColor(BLACK);
        loginButton.setWidth(200);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                SpanLabel sp = new SpanLabel();

                sp.setText(UserService.getInstance().getConnectedUser(login.getText(), password.getText(), theme).toString());

            }
        });

        Button btn = new Button("FORGOT PASSWORD?");
        btn.getUnselectedStyle().setFgColor(WHITE);
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                if (login.getText().equals("")) {
                    Dialog.show("Error", "Please insert your username", "OK", null);

                } else {
                    String pw = UserService.getInstance().getPassword(login.getText());
                    if (!pw.equals("")) {
                        String myURL = "https://rest.nexmo.com/sms/json?api_key=9d374437&api_secret=Ddh9L5wmE3zNEmsA&to=21652478690" + "&from=SCHOOL&text=password " + pw + "             .     ";
                        ConnectionRequest cntRqst = new ConnectionRequest() {
                            @Override
                            protected void readResponse(InputStream in) throws IOException {
                            }

                            @Override
                            protected void postResponse() {
                                Dialog.show("SMS", "sms successfully sent", "OK", null);

                            }
                        };
                        cntRqst.setUrl(myURL);
                        NetworkManager.getInstance().addToQueue(cntRqst);
                    }
                }
            }
        });
      /*  
        Validator v = new Validator();
        v.addConstraint(login, new LengthConstraint(4));

        v.addSubmitButtons(loginButton);*/

        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if (!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }
        

        Container by = BoxLayout.encloseY(
                welcome,
                profilePicLabel,
                spaceLabel,
                BorderLayout.center(login).
                add(BorderLayout.WEST, loginIcon),
                BorderLayout.center(password).
                add(BorderLayout.WEST, passwordIcon),
                loginButton,
                btn
        );
        add(BorderLayout.CENTER, by);

        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
    }
}
