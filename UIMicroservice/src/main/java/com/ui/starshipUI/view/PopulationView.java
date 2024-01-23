package com.ui.starshipUI.view;

import com.ui.starshipUI.Fetcher;
import com.ui.starshipUI.model.Country;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@PageTitle("Population")
@Route(value = "Population", layout = MainLayout.class)
@Uses(Icon.class)
@PermitAll
public class PopulationView extends Div {


    private final Map<String, String> alpha3ToAlpha2Map;
    private String apiGatewayHost;



    public PopulationView(@Value("${apiGateway.host}") String apiGatewayHost) {
        this.apiGatewayHost = apiGatewayHost;

        this.alpha3ToAlpha2Map = createAlpha3ToAlpha2Map();
        setSizeFull();
        addClassNames("population-view");


        VerticalLayout layout = new VerticalLayout(createGrid());
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        add(layout);
    }

    private Component createGrid() {
        Grid<Country> grid;
        grid = new Grid<>(Country.class, false);
        grid.addColumn("rank").setWidth("5px");
        grid.addColumn("CCA3").setWidth("9px");
        grid.addColumn(new ComponentRenderer<>(country -> {
            HorizontalLayout layout = new HorizontalLayout();

            // Image column
            if (!convertAlpha3ToAlpha2(country.getCCA3()).isEmpty()){
                Image image = new Image("https://flagpedia.net/data/flags/w580/"+ convertAlpha3ToAlpha2(country.getCCA3()).toLowerCase()+".png", "Image");
                image.setWidth("25px"); // Set the desired width
                image.setHeight("12px"); // Set the desired height
                layout.add(image);
            }
            // Country name column
            if (!country.getCountry().isEmpty()) {
                Span countrySpan = new Span(country.getCountry());
                layout.add(countrySpan);
            } else {
                layout.add(new Span()); // Empty span if country name is empty
            }

            return layout;
        })).setHeader("Country").setAutoWidth(true);
        grid.addColumn("capital").setAutoWidth(true);
        grid.addColumn("continent").setAutoWidth(true);
        // Use ComponentRenderer to format numbers with separators
        grid.addColumn(new ComponentRenderer<>(country -> {
            Div div = new Div();
            div.setText(String.format("%,d", country.getPopulation_2022()));
            return div;
        })).setHeader("Population 2022").setAutoWidth(true);

        grid.addColumn(new ComponentRenderer<>(country -> {
            Div div = new Div();
            div.setText(String.format("%,d", country.getPopulation_2020()));
            return div;
        })).setHeader("Population 2020").setAutoWidth(true);

        List<Country> countriesList= new Fetcher<Country>().fetch(apiGatewayHost + "/api/population/countries",
                Country.class, new HashMap<>(),
                (root) -> root);
        grid.setItems(countriesList);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);

        return grid;
    }

    private Map<String, String> createAlpha3ToAlpha2Map() {
        Map<String, String> map = new HashMap<>();
        Locale[] locales = Locale.getAvailableLocales();

        for (Locale locale : locales) {
            String alpha2 = locale.getCountry();
            String alpha3;
            try {
                alpha3 = locale.getISO3Country();
            } catch (Exception e) {
                continue;
            }

            if (!alpha3.isEmpty()) {
                map.put(alpha3, alpha2);
            }
        }
        return map;
    }

    public String convertAlpha3ToAlpha2(String alpha3Code) {
        return alpha3ToAlpha2Map.getOrDefault(alpha3Code,"");
    }

}