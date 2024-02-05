package com.starship.starshipUI.filter;

import com.starship.starshipUI.model.nba.teams.Team;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TeamFilter {

    private static Logger logger = LogManager.getLogger(TeamFilter.class);

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

        logger.info("creating team filter");
    }

    public void setId(Integer id) {
        this.id = id;
        this.dataView.refreshAll();

        logger.debug("team id for filter changed");
    }

    public void setName(String name) {
        this.name = name;
        this.dataView.refreshAll();

        logger.debug("team name for filter changed");
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
        this.dataView.refreshAll();

        logger.debug("team nickname for filter changed");
    }

    public void setCode(String code) {
        this.code = code;
        this.dataView.refreshAll();

        logger.debug("team code for filter changed");
    }

    public void setCity(String city) {
        this.city = city;
        this.dataView.refreshAll();

        logger.debug("team city for filter changed");
    }

    public void setLogo(String logo) {
        this.logo = logo;
        this.dataView.refreshAll();

        logger.debug("team logo for filter changed");
    }

    public void setAllStar(Boolean allStar) {
        this.allStar = allStar;
        this.dataView.refreshAll();

        logger.debug("team all-star for filter changed");
    }

    public void setNbaFranchise(Boolean nbaFranchise) {
        this.nbaFranchise = nbaFranchise;
        this.dataView.refreshAll();

        logger.debug("team nba franchise for filter changed");
    }

    public void setLeagues(String leagues) {
        this.leagues = leagues;
        this.dataView.refreshAll();

        logger.debug("team leagues for filter changed");
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
