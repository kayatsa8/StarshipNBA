package com.ui.rad_internship.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.*;
import com.vaadin.flow.theme.lumo.LumoIcon;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.vaadin.lineawesome.LineAwesomeIcon;

@PageTitle("Main")
@Route(value = "main")
public class MainLayout extends AppLayout {

    public MainLayout() {
        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("Starship NBA");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        SideNav nav = getSideNav();

        Scroller scroller = new Scroller(nav);
        scroller.setClassName(LumoUtility.Padding.SMALL);

        addToDrawer(scroller);
        addToNavbar(toggle, title);
    }

    private SideNav getSideNav(){
        SideNav nav = new SideNav();

        //hi mom
//        SideNavItem item = new SideNavItem("Hi Mom", HiMom.class);
//        nav.addItem(item);


        //nba
        addNBAScreen(nav);
        addNewsScreen(nav);
        addPopulationScreen(nav);

        return nav;
    }

    private void addNBAScreen(SideNav nav){
        SideNavItem nbaScreen = new SideNavItem("NBA", NBAView.class, LineAwesomeIcon.BASKETBALL_BALL_SOLID.create());
        nav.addItem(nbaScreen);
    }

    private void addNewsScreen(SideNav nav){
        SideNavItem newsScreen = new SideNavItem("News", NewsView.class, LineAwesomeIcon.NEWSPAPER.create());
        nav.addItem(newsScreen);
    }

    private void addPopulationScreen(SideNav nav){
        SideNavItem populationScreen = new SideNavItem("Population", PopulationView.class, LineAwesomeIcon.WAREHOUSE_SOLID.create());
        nav.addItem(populationScreen);
    }
}
