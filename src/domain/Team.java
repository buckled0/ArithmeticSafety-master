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
    private int gamesInSeasonDifference;
    private int possibleAmountOfPoints;
    private int valueToOvertake;

    public Team(String name,int points, int goalDifference, int wins, int loses, int draws, int gamesPlayed) {
        this.name = name;
        this.points = points;
        this.goalDifference = goalDifference;
        this.wins = wins;
        this.loses = loses;
        this.draws = draws;
        this.gamesPlayed = gamesPlayed;
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


    public TeamStatus definitiveSafetyVerdictCheck(Team team1, ArrayList<Team> teamList, int i, int numberOfGames) {
        int totalGamesInSeason = numberOfGames;

        if(i == 0){
            for(int j = i + 1; j < teamList.size(); j++){
                valueToOvertake = getValueToOvertake(team1, teamList, totalGamesInSeason, j);
                if(teamList.get(j).points > valueToOvertake)
                    return TeamStatus.chanceOfChampion;
                else
                    return TeamStatus.champions;
            }

        }
        if(i > 0 && i < 4){
            for(int j = i + 1; j < teamList.size(); j++){
                valueToOvertake = getValueToOvertake(team1, teamList, totalGamesInSeason, j);
                if(teamList.get(j).points > valueToOvertake)
                    return TeamStatus.chanceOfChampionsLeague;
                else
                    return TeamStatus.championsLeague;
            }
        }
        if(i == 4){
            for(int j = i + 1; j < teamList.size(); j ++){
                valueToOvertake = getValueToOvertake(team1, teamList, totalGamesInSeason, j);
                if(teamList.get(j).points > valueToOvertake)
                    return TeamStatus.chanceOfEuropaLeague;
                else
                    return TeamStatus.europaLeague;
            }
        }
        if(i > 16){
            gamesInSeasonDifference = totalGamesInSeason - teamList.get(i).gamesPlayed;
            possibleAmountOfPoints = gamesInSeasonDifference * 3;
            int teamAtRelegationPossibleEndOfSeason = teamList.get(16).points;
            if(teamList.get(i).points + possibleAmountOfPoints > teamAtRelegationPossibleEndOfSeason)
                return TeamStatus.canGetOutOfRelegation;
            else
                return TeamStatus.definitelyRelegated;
        }
        if(i > 13){
            for(int j = i + 1; j < teamList.size(); j++){
                valueToOvertake = getValueToOvertake(team1, teamList, totalGamesInSeason, j);
                if(teamList.get(j).points + possibleAmountOfPoints > valueToOvertake)
                    return TeamStatus.highChanceOfRelegation;
                else
                    return TeamStatus.canGetOutOfRelegation;
            }
        }
        if(i > 4 && i <= 13){
            for(int j = 17; j < teamList.size(); j++){
                valueToOvertake = getValueToOvertake(team1, teamList, totalGamesInSeason, j);
                if(teamList.get(j).points > valueToOvertake)
                    return TeamStatus.couldBeRelegated;
                else
                    return TeamStatus.definitelySafe;
            }
        }
        return TeamStatus.safe;
    }

    private int getValueToOvertake(Team team1, ArrayList<Team> teamList, int totalGamesInSeason, int j) {
        gamesInSeasonDifference = totalGamesInSeason - teamList.get(j).gamesPlayed;
        possibleAmountOfPoints = gamesInSeasonDifference * 3;
        valueToOvertake = team1.points - possibleAmountOfPoints;
        return valueToOvertake;
    }

}
