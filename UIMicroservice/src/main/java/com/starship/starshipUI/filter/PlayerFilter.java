package com.starship.starshipUI.filter;

import com.starship.starshipUI.model.nba.players.Player;
import com.vaadin.flow.component.grid.dataview.GridListDataView;

public class PlayerFilter {

    private final GridListDataView<Player> dataView;
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer nbaStart;
    private Integer nbaPro;
    private String height;
    private String weight;
    private String college;
    private String affiliation;
    private String leagues;


    public PlayerFilter(GridListDataView<Player> dataView){
        this.dataView = dataView;
        this.dataView.addFilter(this::test);


        id = null;
        firstName = null;
        lastName = null;
        nbaStart = null;
        nbaPro = null;
        height = null;
        weight = null;
        college = null;
        affiliation = null;
        leagues = null;
    }

    public void setId(Integer id) {
        this.id = id;
        this.dataView.refreshAll();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        this.dataView.refreshAll();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        this.dataView.refreshAll();
    }

    public void setNbaStart(Integer nbaStart) {
        this.nbaStart = nbaStart;
        this.dataView.refreshAll();
    }

    public void setNbaPro(Integer nbaPro) {
        this.nbaPro = nbaPro;
        this.dataView.refreshAll();
    }

    public void setHeight(String height) {
        this.height = height;
        this.dataView.refreshAll();
    }

    public void setWeight(String weight) {
        this.weight = weight;
        this.dataView.refreshAll();
    }

    public void setCollege(String college) {
        this.college = college;
        this.dataView.refreshAll();
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
        this.dataView.refreshAll();
    }

    public void setLeagues(String leagues) {
        this.leagues = leagues;
        this.dataView.refreshAll();
    }


    public boolean test(Player player){
        boolean matchesId = matches(player.getId(), id);
        boolean matchesFirstName = matches(player.getFirstName(), firstName);
        boolean matchesLastName = matches(player.getLastName(), lastName);
        boolean matchesNbaStart = matches(player.getNbaStart(), nbaStart);
        boolean matchesNbaPro = matches(player.getNbaPro(), nbaPro);
        boolean matchesHeight = matches(player.getHeightInMeters(), height);
        boolean matchesWeight = matches(player.getWeightInKilograms(), weight);
        boolean matchesCollege = matches(player.getCollege(), college);
        boolean matchesAffiliation = matches(player.getAffiliation(), affiliation);
        boolean matchesLeagues = matches(player.getLeaguesAsString(), leagues);

        return matchesId && matchesFirstName && matchesLastName && matchesNbaStart && matchesNbaPro
                && matchesHeight && matchesWeight && matchesCollege && matchesAffiliation
                && matchesLeagues;
    }

    private boolean matches(String value, String searchTerm) {
        return searchTerm == null || searchTerm.isEmpty()
                || (value!= null && value.toLowerCase().contains(searchTerm.toLowerCase()));
    }

    private boolean matches(Integer value, Integer searchTerm) {
        return searchTerm == null || (value!= null && value.equals(searchTerm));
    }

    public void setId(String s) {
        if(!s.isEmpty()){
            setId(Integer.parseInt(s));
        }
        else{
            setId((Integer) null);
        }
    }

    public void setNbaStart(String s) {
        if(!s.isEmpty()){
            setNbaStart(Integer.parseInt(s));
        }
        else{
            setNbaStart((Integer) null);
        }
    }

    public void setNbaPro(String s) {
        if(!s.isEmpty()){
            setNbaPro(Integer.parseInt(s));
        }
        else{
            setNbaPro((Integer) null);
        }
    }

}
