package com.ui.starshipUI.view;

import com.ui.starshipUI.Fetcher;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@PageTitle("NBA")
@Route(value = "", layout = MainLayout.class)
public class NBAView extends VerticalLayout {

//    @Value("apiGateway.host")
    private String apiGatewayHost = "http://localhost:8080";

    public NBAView(){
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
        List<Player> players = new Fetcher<Player>().fetch("http://localhost:8080/api/nba/players",
                Player.class, new HashMap<>(),
                (root) -> root);

        Div div = new Div();
        Grid<Player> grid = makePlayerGrid();
        grid.setItems(players);

        div.add(grid);

        tabSheet.add("Players", div);
    }

    private Grid<Player> makePlayerGrid(){
        Grid<Player> grid = new Grid<>(Player.class, false);

        grid.addColumn(Player::getId).setHeader("ID").setTooltipGenerator((player) -> "" + player.getId());
        grid.addColumn(Player::getFirstName).setHeader("First Name").setTooltipGenerator(Player::getFirstName);
        grid.addColumn(Player::getLastName).setHeader("LastName").setTooltipGenerator(Player::getLastName);
        grid.addColumn(Player::getNbaStart).setHeader("NBA Start")
                .setTooltipGenerator((player) -> "" + player.getNbaStart());
        grid.addColumn(Player::getNbaPro).setHeader("NBA Pro").setTooltipGenerator((player) -> "" + player.getNbaPro());
        grid.addColumn(Player::getHeightInMeters).setHeader("Height (m)")
                .setTooltipGenerator(Player::getHeightInMeters);
        grid.addColumn(Player::getWeightInKilograms).setHeader("Weight (kg)")
                .setTooltipGenerator(Player::getWeightInKilograms);
        grid.addColumn(Player::getCollege).setHeader("College").setTooltipGenerator(Player::getCollege);
        grid.addColumn(Player::getAffiliation).setHeader("Affiliation").setTooltipGenerator(Player::getAffiliation);
        grid.addColumn(Player::getLeaguesAsString).setHeader("Leagues").setTooltipGenerator(Player::getLeaguesAsString);

        for(Grid.Column<Player> column : grid.getColumns()){
            column.setSortable(true);
        }

        grid.setMultiSort(true, Grid.MultiSortPriority.APPEND);

        grid.setHeight("580px");

        return grid;
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
