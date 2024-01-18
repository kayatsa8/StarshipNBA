package com.ui.starshipUI.filter;

import com.ui.starshipUI.model.nba.teams.Team;
import com.vaadin.flow.component.grid.dataview.GridListDataView;

public class TeamFilter {

    private final GridListDataView<Team> dataView;
    private Integer id;
    private String name;
    private String nickname;
    private String code;
    private String city;
    private String logo;
    private Boolean allStar;
    private Boolean nbaFranchise;
    private String leagues;


    public TeamFilter(GridListDataView<Team> dataView){
        this.dataView = dataView;
        this.dataView.addFilter(this::test);

        id = null;
        name = null;
        nickname = null;
        code = null;
        city = null;
        logo = null;
        allStar = null;
        nbaFranchise = null;
        leagues = null;
    }

    public void setId(Integer id) {
        this.id = id;
        this.dataView.refreshAll();
    }

    public void setName(String name) {
        this.name = name;
        this.dataView.refreshAll();
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
        this.dataView.refreshAll();
    }

    public void setCode(String code) {
        this.code = code;
        this.dataView.refreshAll();
    }

    public void setCity(String city) {
        this.city = city;
        this.dataView.refreshAll();
    }

    public void setLogo(String logo) {
        this.logo = logo;
        this.dataView.refreshAll();
    }

    public void setAllStar(Boolean allStar) {
        this.allStar = allStar;
        this.dataView.refreshAll();
    }

    public void setNbaFranchise(Boolean nbaFranchise) {
        this.nbaFranchise = nbaFranchise;
        this.dataView.refreshAll();
    }

    public void setLeagues(String leagues) {
        this.leagues = leagues;
        this.dataView.refreshAll();
    }

    public boolean test(Team team) {
        boolean matchesId = matches(team.getId(), id);
        boolean matchesName = matches(team.getName(), name);
        boolean matchesNickname = matches(team.getNickname(), nickname);
        boolean matchesCode = matches(team.getCode(), code);
        boolean matchesCity = matches(team.getCity(), city);
        boolean matchesLogo = matches(team.getLogo(), logo);
        boolean matchesAllStar = matches(team.isAllStar(), allStar);
        boolean matchesNbaFranchise = matches(team.isNbaFranchise(), nbaFranchise);
        boolean matchesLeagues = matches(team.getLeaguesAsString(), leagues);

        return matchesId && matchesName && matchesNickname && matchesCode && matchesCity
                && matchesLogo && matchesAllStar && matchesNbaFranchise && matchesLeagues;
    }

    private boolean matches(String value, String searchTerm) {
        return searchTerm == null || searchTerm.isEmpty()
                || (value!= null && value.toLowerCase().contains(searchTerm.toLowerCase()));
    }

    private boolean matches(Integer value, Integer searchTerm) {
        return searchTerm == null || (value!= null && value.equals(searchTerm));
    }

    private boolean matches(Boolean value, Boolean searchTerm) {
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

    public void setAllStar(String s){
        if(!s.isEmpty()){
            setAllStar(Boolean.parseBoolean(s));
        }
        else{
            setAllStar((Boolean) null);
        }
    }

    public void setNbaFranchise(String s){
        if(!s.isEmpty()){
            setNbaFranchise(Boolean.parseBoolean(s));
        }
        else{
            setNbaFranchise((Boolean) null);
        }
    }
}
