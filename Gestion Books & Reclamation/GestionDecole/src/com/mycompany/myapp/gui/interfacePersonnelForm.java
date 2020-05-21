/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Aymen
 */
public class interfacePersonnelForm extends Form{
     private Button addBookBtn,listBooksBtn,exitBtn;
            private Container mainContainer;
                private Resources theme;


            public interfacePersonnelForm (){
                this.setTitle("Espace Personnel");
 
                        mainContainer = new Container();
         
        TableLayout tl;
if(Display.getInstance().isTablet()) {
    tl = new TableLayout(7, 2);
} else {
    tl = new TableLayout(14, 1);
}
tl.setGrowHorizontally(true);
mainContainer.setLayout(tl);

exitBtn = new Button("Exit");
            addBookBtn = new Button("Add new complaint");
        addBookBtn.getUnselectedStyle().setFgColor(5542241);
        listBooksBtn = new Button("My complaints");
        listBooksBtn.getUnselectedStyle().setFgColor(5542241);
        this.setLayout(new BorderLayout());


            }
    
}
