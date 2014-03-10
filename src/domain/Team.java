package domain;

import java.util.ArrayList;

public class Team {

    public String name;
    public int points;
    public int goalDifference;
    public int wins;
    public int loses;
    public int draws;
    public int gamesPlayed;
    public int form;

    public Team(String name, int points, int goalDifference, int wins, int loses, int draws, int gamesPlayed, int form) {
        this.name = name;
        this.points = points;
        this.goalDifference = goalDifference;
        this.wins = wins;
        this.loses = loses;
        this.draws = draws;
        this.gamesPlayed = gamesPlayed;
        this.form = form;
    }

    public TeamStatus getTeamStatus(Team team2){
        boolean equalOnEverythingTeam = isEqualOnPointsAndGoalDifference(team2);
        boolean equalButTopTeam = equalButTop(team2);
        boolean atRiskTeam = atRisk(team2);
        boolean fairlySafeForNowTeam = fairlySafeForNow(team2);
        boolean definitelySafeForNowTeam = definitelySafeForNow(team2);

        if (equalOnEverythingTeam)
            return new EqualOnEverythingTeam().getTeamStatus();

        if (equalButTopTeam)
            return new EqualButTopTeam().getTeamStatus();

        if (atRiskTeam)
            return new AtRiskTeam().getTeamStatus();

        if (fairlySafeForNowTeam)
            return new FairlySafeForNowTeam().getTeamStatus();

        if (definitelySafeForNowTeam)
            return new DefinitelySafeForNowTeam().getTeamStatus();

        return new DefinitelySafeForNowTeam().getTeamStatus();
    }

    private boolean definitelySafeForNow(Team team2) {
        return teamsPointDifference(team2) >= 4;
    }

    private boolean fairlySafeForNow(Team team2) {
        return teamsPointDifference(team2) == 3;
    }

    private boolean atRisk(Team team2) {
        return teamsPointDifference(team2) <= 2;
    }

    private boolean equalButTop(Team team2) {
        return equalTeams(team2) && (this.goalDifference > team2.goalDifference);
    }

    private boolean isEqualOnPointsAndGoalDifference(Team team2) {
        return equalTeams(team2) && (this.goalDifference == team2.goalDifference);
    }

    private int teamsPointDifference(Team team2) {
        return this.points - team2.points;
    }

    private boolean equalTeams(Team team2) {
        return this.points == team2.points;
    }


    public TeamStatus definitiveSafetyVerdictCheck(ArrayList<Team> teamList, int i,
                                                   String leagueName, int numberOfGames, int relegationZoneAmount) {
        if (leagueName.equals("Premier League")) {
            PremierLeagueRules premierLeagueRules = new PremierLeagueRules(numberOfGames, relegationZoneAmount);
            return premierLeagueRules.setDefinitiveSafety(this, teamList, i);
        }
        return TeamStatus.safe;
    }

}
