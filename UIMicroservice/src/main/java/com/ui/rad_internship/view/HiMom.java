package com.ui.rad_internship.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Hi Mom")
@Route(value = "HiMom", layout = MainLayout.class)
public class HiMom extends VerticalLayout {

    public HiMom(){
        Button button = new Button("Hi");

        button.addClickListener(click->{
            Notification.show("Hi Mom!");
        });

        add(
                button
        );
    }


}
