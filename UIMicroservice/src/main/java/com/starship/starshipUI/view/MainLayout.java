package com.starship.starshipUI.view;

import com.starship.starshipUI.services.SecurityService;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.*;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIcon;

@PageTitle("Main")
@Route(value = "main")
@PermitAll
public class MainLayout extends AppLayout {

    private SecurityService securityService;

    public MainLayout(@Autowired SecurityService securityService) {
        this.securityService = securityService;
        H1 logo = new H1("Vaadin CRM");
        logo.addClassName("logo");
        DrawerToggle toggle = new DrawerToggle();

        HorizontalLayout header=new HorizontalLayout();
        header.setWidthFull(); // Ensure the header takes up the full width

        if (securityService.getAuthenticatedUser() != null) {
            Button logout = new Button("Logout", click ->
                    securityService.logout());
            header.add(logout);
            header.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        }


        H1 title = new H1("Starship NBA");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        SideNav nav = getSideNav();

        Scroller scroller = new Scroller(nav);
        scroller.setClassName(LumoUtility.Padding.SMALL);
        addToDrawer(scroller);
        addToNavbar(toggle, title);
        addToNavbar(header);
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
