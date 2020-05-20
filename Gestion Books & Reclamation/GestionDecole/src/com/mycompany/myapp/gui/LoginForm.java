/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.table.TableLayout;
import com.mycompany.myapp.services.UserService;

/**
 *
 * @author Admin
 */
public class LoginForm extends Form {
    
    private final Label login,password;
    private final TextField loginTf,passwordTf;
    private final Container mainContainer;
    private final Button addBtn;
    
   public LoginForm(){
       
           this.setLayout(new BorderLayout());
        mainContainer = new Container();
         

        login = new Label("Username");
        loginTf= new TextField("");
        mainContainer.add(login);
        mainContainer.add(loginTf);
        
        password = new Label("Password:");
        
        passwordTf= new TextField();
        passwordTf.setConstraint(com.codename1.ui.TextArea.PASSWORD);
 
        mainContainer.add(password);
        mainContainer.add(passwordTf);
        
        
        //login.getUnselectedStyle().setAlignment(Component.CENTER);
        addBtn= new Button("Login");
        addBtn.addActionListener((ActionListener) (ActionEvent evt) -> {   
        SpanLabel sp = new SpanLabel();
             
             
        sp.setText(UserService.getInstance().getConnectedUser(loginTf.getText(),passwordTf.getText()).toString());
            System.out.println(sp.getText());
       
        });
     
        mainContainer.add(addBtn); 
        
        this.add(BorderLayout.NORTH, mainContainer);
   }
    
}
