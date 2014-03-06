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
    int gamesInSeasonDifference;
    int possibleAmountOfPoints;
    int valueToOvertake;

    public Team(String name,int points, int goalDifference, int wins, int loses, int draws, int gamesPlayed) {
        this.name = name;
        this.points = points;
        this.goalDifference = goalDifference;
        this.wins = wins;
        this.loses = loses;
        this.draws = draws;
        this.gamesPlayed = gamesPlayed;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public int getWins(){
        return wins;
    }

    public int getLoses(){
        return loses;
    }

    public int getDraws(){
        return draws;
    }

    public int getGamesPlayed(){
        return gamesPlayed;
    }

    public TeamStatus leagueStatus(Team team1, Team team2){

        if ((team1.getPoints() == team2.getPoints()) && (team1.getGoalDifference() == team2.getGoalDifference()))
            return TeamStatus.equalOnEverything;

        if ((team1.getPoints() == team2.getPoints()) && (team1.getGoalDifference() > team2.getGoalDifference()))
            return TeamStatus.equalButTop;

        if (team1.getPoints() - team2.points <= 2)
            return TeamStatus.atRisk;

        if (team1.getPoints() - team2.getPoints() == 3)
            return TeamStatus.fairlySafeForNow;

        if (team1.getPoints() - team2.getPoints() >= 4)
            return TeamStatus.definitelySafeForNow;

        return TeamStatus.couldBeRelegated;
    }

    public TeamStatus definitiveSafetyVerdictCheck(Team team1, ArrayList<Team> teamList, int i) {
        int totalGamesInSeason = 38;


        if(i == 0){
            for(int j = i + 1; j < teamList.size(); j++){
                valueToOvertake = getValueToOvertake(team1, teamList, totalGamesInSeason, j);
                if(teamList.get(j).getPoints() > valueToOvertake)
                    return TeamStatus.chanceOfChampion;
                else
                    return TeamStatus.champions;
            }

        }
        if(i > 0 && i < 4){
            for(int j = i + 1; j < teamList.size(); j++){
                valueToOvertake = getValueToOvertake(team1, teamList, totalGamesInSeason, j);
                if(teamList.get(j).getPoints() > valueToOvertake)
                    return TeamStatus.chanceOfChampionsLeague;
                else
                    return TeamStatus.championsLeague;
            }
        }
        if(i == 4){
            for(int j = i + 1; j < teamList.size(); j ++){
                valueToOvertake = getValueToOvertake(team1, teamList, totalGamesInSeason, j);
                if(teamList.get(j).getPoints() > valueToOvertake)
                    return TeamStatus.chanceOfEuropaLeague;
                else
                    return TeamStatus.europaLeague;
            }
        }
        if(i > 16){
            gamesInSeasonDifference = totalGamesInSeason - teamList.get(i).getGamesPlayed();
            possibleAmountOfPoints = gamesInSeasonDifference * 3;
            int teamAtRelegationPossibleEndOfSeason = teamList.get(16).getPoints();
            if(teamList.get(i).getPoints() + possibleAmountOfPoints > teamAtRelegationPossibleEndOfSeason)
                return TeamStatus.canGetOutOfRelegation;
            else
                return TeamStatus.definitelyRelegated;
        }
        if(i > 13){
            for(int j = i + 1; j < teamList.size(); j++){
                valueToOvertake = getValueToOvertake(team1, teamList, totalGamesInSeason, j);
                if(teamList.get(j).getPoints() + possibleAmountOfPoints > valueToOvertake)
                    return TeamStatus.highChanceOfRelegation;
                else
                    return TeamStatus.canGetOutOfRelegation;
            }
        }
        if(i > 4 && i <= 13){
            for(int j = 17; j < teamList.size(); j++){
                valueToOvertake = getValueToOvertake(team1, teamList, totalGamesInSeason, j);
                if(teamList.get(j).getPoints() > valueToOvertake)
                    return TeamStatus.couldBeRelegated;
                else
                    return TeamStatus.definitelySafe;
            }
        }
        return TeamStatus.definitelySafeForNow;
    }

    private int getValueToOvertake(Team team1, ArrayList<Team> teamList, int totalGamesInSeason, int j) {
        gamesInSeasonDifference = totalGamesInSeason - teamList.get(j).getGamesPlayed();
        possibleAmountOfPoints = gamesInSeasonDifference * 3;
        valueToOvertake = team1.getPoints() - possibleAmountOfPoints;
        return valueToOvertake;
    }

}