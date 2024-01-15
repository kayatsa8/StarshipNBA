package com.ui.rad_internship.view;

import com.ui.rad_internship.Fetcher;
import com.ui.rad_internship.model.nba.players.Player;
import com.ui.rad_internship.model.nba.teams.Team;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.HashMap;
import java.util.List;

@PageTitle("NBA")
@Route(value = "nba", layout = MainLayout.class)
public class NBAView extends VerticalLayout {

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
        List<Team> teams = new Fetcher<Team>().fetch("http://localhost:8080/api/nba/teams",
                                                         Team.class, new HashMap<>(),
                                                         (root) -> root);
        Div div = new Div();
        Grid<Team> grid = makeTeamGrid();
        grid.setItems(teams);

        div.add(grid);

        tabSheet.add("Teams", div);
    }

    private Grid<Team> makeTeamGrid(){
        Grid<Team> grid = new Grid<>(Team.class, false);

        grid.addColumn(Team::getId).setHeader("ID").setTooltipGenerator(team -> "" + team.getId());
        grid.addColumn(Team::getName).setHeader("Name").setTooltipGenerator(Team::getName);
        grid.addColumn(Team::getNickname).setHeader("Nickname").setTooltipGenerator(Team::getNickname);
        grid.addColumn(Team::getCode).setHeader("Code").setTooltipGenerator(Team::getCode);
        grid.addColumn(Team::getCity).setHeader("City").setTooltipGenerator(Team::getCity);
        grid.addColumn(new ComponentRenderer<>(this::logoImage)).setHeader("Logo")
                .setTooltipGenerator(Team::getLogo).setAutoWidth(false);
        grid.addColumn(Team::isAllStar).setHeader("All Star").setTooltipGenerator(team -> "" + team.isAllStar());
        grid.addColumn(Team::isNbaFranchise).setHeader("NBA Franchise").setTooltipGenerator(team ->
                "" + team.isNbaFranchise());
        grid.addColumn(Team::getLeaguesAsString).setHeader("Leagues").setTooltipGenerator(Team::getLeaguesAsString);

        for(Grid.Column<Team> column : grid.getColumns()){
            column.setSortable(true);
        }

        grid.setMultiSort(true, Grid.MultiSortPriority.APPEND);

        grid.setHeight("580px");

        return grid;
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

        grid.addColumn(Player::getId).setHeader("ID");
        grid.addColumn(Player::getFirstName).setHeader("First Name");
        grid.addColumn(Player::getLastName).setHeader("LastName");
        grid.addColumn(Player::getNbaStart).setHeader("NBA Start");
        grid.addColumn(Player::getNbaPro).setHeader("NBA Pro");
        grid.addColumn(Player::getHeightInMeters).setHeader("Height (m)");
        grid.addColumn(Player::getWeightInKilograms).setHeader("Weight (kg)");
        grid.addColumn(Player::getCollege).setHeader("College");
        grid.addColumn(Player::getAffiliation).setHeader("Affiliation");
        grid.addColumn(Player::getLeaguesAsString).setHeader("Leagues");

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



}
