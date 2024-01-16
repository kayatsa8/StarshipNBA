package com.ui.rad_internship.view;


import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Dashboard")
@Route(value = "", layout = MainLayout.class)
public class Dashboard extends VerticalLayout {

    public Dashboard(){
        makeTabNavigation();
    }

    private void makeTabNavigation(){
        TabSheet tabSheet = new TabSheet();
        tabSheet.setSizeFull();

//        makeTeamTab(tabSheet);
//        makePlayerTab(tabSheet);

        add(tabSheet);
    }


}
