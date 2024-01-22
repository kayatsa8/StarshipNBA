package com.ui.starshipUI.view;

import com.ui.starshipUI.Fetcher;
import com.ui.starshipUI.filter.PlayerFilter;
import com.ui.starshipUI.filter.TeamFilter;
import com.ui.starshipUI.model.nba.players.Player;
import com.ui.starshipUI.model.nba.teams.Team;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@PageTitle("NBA")
@Route(value = "", layout = MainLayout.class)
@PermitAll
public class NBAView extends VerticalLayout {

    private String apiGatewayHost;

    public NBAView(@Value("${apiGateway.host}") String apiGatewayHost){
        this.apiGatewayHost = apiGatewayHost;
        makeTabNavigation();
    }

    private void makeTabNavigation(){
        TabSheet tabSheet = new TabSheet();
        tabSheet.setSizeFull();

        makeTeamTab(tabSheet);
        makePlayerTab(tabSheet);

        add(tabSheet);
    }

    private void makeTeamTab(TabSheet tabSheet){
        List<Team> teams = new Fetcher<Team>().fetch(apiGatewayHost + "/api/nba/teams",
                                                         Team.class, new HashMap<>(),
                                                         (root) -> root);
        Map<String, Grid.Column<Team>> columns = new HashMap<>();

        Div div = new Div();
        Grid<Team> grid = makeTeamGrid(columns);
        GridListDataView<Team> dataView = grid.setItems(teams);

        addTeamFilters(grid, dataView, columns);

        div.add(grid);

        tabSheet.add("Teams", div);
    }

    private Grid<Team> makeTeamGrid(Map<String, Grid.Column<Team>> columns){
        Grid<Team> grid = new Grid<>(Team.class, false);

        columns.put("ID", grid.addColumn(Team::getId).setHeader("ID").setTooltipGenerator(team -> "" + team.getId()));
        columns.put("Name", grid.addColumn(Team::getName).setHeader("Name").setTooltipGenerator(Team::getName));
        columns.put("Nickname",
                grid.addColumn(Team::getNickname).setHeader("Nickname").setTooltipGenerator(Team::getNickname));
        columns.put("Code", grid.addColumn(Team::getCode).setHeader("Code").setTooltipGenerator(Team::getCode));
        columns.put("City", grid.addColumn(Team::getCity).setHeader("City").setTooltipGenerator(Team::getCity));
        columns.put("Logo", grid.addColumn(new ComponentRenderer<>(this::logoImage)).setHeader("Logo")
                .setTooltipGenerator(Team::getLogo).setAutoWidth(false));
        columns.put("All Star", grid.addColumn(Team::isAllStar).setHeader("All Star")
                .setTooltipGenerator(team -> "" + team.isAllStar()));
        columns.put("NBA Franchise", grid.addColumn(Team::isNbaFranchise).setHeader("NBA Franchise")
                .setTooltipGenerator(team -> "" + team.isNbaFranchise()));
        columns.put("Leagues", grid.addColumn(Team::getLeaguesAsString).setHeader("Leagues")
                .setTooltipGenerator(Team::getLeaguesAsString));


        for(Grid.Column<Team> column : grid.getColumns()){
            column.setSortable(true);
        }

        grid.setMultiSort(true, Grid.MultiSortPriority.APPEND);

        grid.setHeight("580px");

        return grid;
    }

    private void addTeamFilters(Grid<Team> grid, GridListDataView<Team> dataView,
                                Map<String, Grid.Column<Team>> columns){
        TeamFilter filter = new TeamFilter(dataView);

//        HeaderRow headerRow = grid.appendHeaderRow();
        HeaderRow headerRow = grid.getHeaderRows().get(0);

        headerRow.getCell(columns.get("ID")).setComponent(
                createFilterHeader("ID", filter::setId));
        headerRow.getCell(columns.get("Name")).setComponent(
                createFilterHeader("Name", filter::setName));
        headerRow.getCell(columns.get("Nickname")).setComponent(
                createFilterHeader("Nickname", filter::setNickname));
        headerRow.getCell(columns.get("Code")).setComponent(
                createFilterHeader("Code", filter::setCode));
        headerRow.getCell(columns.get("City")).setComponent(
                createFilterHeader("City", filter::setCity));
        headerRow.getCell(columns.get("Logo")).setComponent(
                createFilterHeader("Logo", filter::setLogo));
        headerRow.getCell(columns.get("All Star")).setComponent(
                createFilterHeader("All Star", filter::setAllStar));
        headerRow.getCell(columns.get("NBA Franchise")).setComponent(
                createFilterHeader("NBA Franchise", filter::setNbaFranchise));
        headerRow.getCell(columns.get("Leagues")).setComponent(
                createFilterHeader("Leagues", filter::setLeagues));
    }



    private void makePlayerTab(TabSheet tabSheet){
        List<Player> players = new Fetcher<Player>().fetch(apiGatewayHost + "/api/nba/players",
                Player.class, new HashMap<>(),
                (root) -> root);

        Map<String, Grid.Column<Player>> columns = new HashMap<>();

        Div div = new Div();
        Grid<Player> grid = makePlayerGrid(columns);
        GridListDataView<Player> dataView = grid.setItems(players);

        addPlayerFilters(grid, dataView, columns);

        div.add(grid);

        tabSheet.add("Players", div);
    }

    private Grid<Player> makePlayerGrid(Map<String, Grid.Column<Player>> columns){
        Grid<Player> grid = new Grid<>(Player.class, false);

        columns.put("ID", grid.addColumn(Player::getId).setHeader("ID")
                .setTooltipGenerator((player) -> "" + player.getId()));
        columns.put("First Name", grid.addColumn(Player::getFirstName).setHeader("First Name")
                .setTooltipGenerator(Player::getFirstName));
        columns.put("Last Name", grid.addColumn(Player::getLastName).setHeader("Last Name")
                .setTooltipGenerator(Player::getLastName));
        columns.put("NBA Start", grid.addColumn(Player::getNbaStart).setHeader("NBA Start")
                .setTooltipGenerator((player) -> "" + player.getNbaStart()));
        columns.put("NBA Pro", grid.addColumn(Player::getNbaPro).setHeader("NBA Pro")
                .setTooltipGenerator((player) -> "" + player.getNbaPro()));
        columns.put("Height (m)", grid.addColumn(Player::getHeightInMeters).setHeader("Height (m)")
                .setTooltipGenerator(Player::getHeightInMeters));
        columns.put("Weight (kg)", grid.addColumn(Player::getWeightInKilograms).setHeader("Weight (kg)")
                .setTooltipGenerator(Player::getWeightInKilograms));
        columns.put("College", grid.addColumn(Player::getCollege).setHeader("College")
                .setTooltipGenerator(Player::getCollege));
        columns.put("Affiliation", grid.addColumn(Player::getAffiliation).setHeader("Affiliation")
                .setTooltipGenerator(Player::getAffiliation));
        columns.put("Leagues", grid.addColumn(Player::getLeaguesAsString).setHeader("Leagues")
                .setTooltipGenerator(Player::getLeaguesAsString));


        for(Grid.Column<Player> column : grid.getColumns()){
            column.setSortable(true);
        }

        grid.setMultiSort(true, Grid.MultiSortPriority.APPEND);

        grid.setHeight("580px");

        return grid;
    }

    private void addPlayerFilters(Grid<Player> grid, GridListDataView<Player> dataView,
                                Map<String, Grid.Column<Player>> columns){
        PlayerFilter filter = new PlayerFilter(dataView);

//        HeaderRow headerRow = grid.appendHeaderRow();
        HeaderRow headerRow = grid.getHeaderRows().get(0);

        headerRow.getCell(columns.get("ID")).setComponent(
                createFilterHeader("ID", filter::setId));
        headerRow.getCell(columns.get("First Name")).setComponent(
                createFilterHeader("First Name", filter::setFirstName));
        headerRow.getCell(columns.get("Last Name")).setComponent(
                createFilterHeader("Lat Name", filter::setLastName));
        headerRow.getCell(columns.get("NBA Start")).setComponent(
                createFilterHeader("NBA Start", filter::setNbaStart));
        headerRow.getCell(columns.get("NBA Pro")).setComponent(
                createFilterHeader("NBA Pro", filter::setNbaPro));
        headerRow.getCell(columns.get("Height (m)")).setComponent(
                createFilterHeader("Height (m)", filter::setHeight));
        headerRow.getCell(columns.get("Weight (kg)")).setComponent(
                createFilterHeader("Weight (kg)", filter::setWeight));
        headerRow.getCell(columns.get("College")).setComponent(
                createFilterHeader("College", filter::setCollege));
        headerRow.getCell(columns.get("Affiliation")).setComponent(
                createFilterHeader("Affiliation", filter::setAffiliation));
        headerRow.getCell(columns.get("Leagues")).setComponent(
                createFilterHeader("Leagues", filter::setLeagues));
    }

    private Image logoImage(Team team){
        if(team.getLogo() != null && !team.getLogo().isEmpty()){
            Image image = new Image(team.getLogo(), "Image");
            image.setHeight("50px");
            image.setWidth("50px");
            return image;
        }
        else{
            return new Image();
        }
    }





    private static Component createFilterHeader(String labelText,
                                                Consumer<String> filterChangeConsumer) {
        NativeLabel label = new NativeLabel(labelText);
        label.getStyle().set("padding-top", "var(--lumo-space-m)")
                .set("font-size", "var(--lumo-font-size-xs)");
        TextField textField = new TextField();
        textField.setValueChangeMode(ValueChangeMode.EAGER);
        textField.setClearButtonVisible(true);
        textField.addThemeVariants(TextFieldVariant.LUMO_SMALL);
        textField.setWidthFull();
        textField.getStyle().set("max-width", "100%");
        textField.addValueChangeListener(
                e -> filterChangeConsumer.accept(e.getValue()));
        VerticalLayout layout = new VerticalLayout(label, textField);
        layout.getThemeList().clear();
        layout.getThemeList().add("spacing-xs");

        return layout;
    }



}
