package domain;

import java.util.ArrayList;

public class PremierLeagueRules implements TopLeagueRules{

    private int numberOfGames;
    private int relegationZoneAmount;
    private int gamesInSeasonDifference;
    private int possibleAmountOfPoints;
    private int valueToOvertake;

    public PremierLeagueRules(int numberOfGames, int relegationZoneAmount){
        setGamesPlayed(numberOfGames);
        setRelegationZoneAmount(relegationZoneAmount);
    }

    @Override
    public void setGamesPlayed(int numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    @Override
    public void setRelegationZoneAmount(int relegationZoneAmount) {
        this.relegationZoneAmount = relegationZoneAmount;
    }

    public TeamStatus setDefinitiveSafety(Team team, ArrayList<Team> teamList, int i) {
        final boolean firstTeamInLeague = i == 0;
        final boolean inChampionsLeagueQualificationPosition = i > 0 && i < 4;
        final boolean inEuropaLeagueQualificationPlace = i == 4;
        final boolean inBottomHalfOfLeague = i > 13;
        final boolean inRelegationZone = i > 16;
        final boolean inTopHalfOfLeagueAfterFifth = i > 4 && i <= 13;
        if(firstTeamInLeague){
            for(int j = i + 1; j < teamList.size(); j++){
                valueToOvertake = getValueToOvertake(team, teamList, numberOfGames, j);
                if(potentialPointsOvertakeCurrentPoints(teamList, j)) {
                    return TeamStatus.chanceOfChampion;
                }
                else {
                    return TeamStatus.champions;
                }
            }

        }
        if(inChampionsLeagueQualificationPosition){
            for(int j = i + 1; j < teamList.size(); j++){
                valueToOvertake = getValueToOvertake(team, teamList, numberOfGames, j);
                if(potentialPointsOvertakeCurrentPoints(teamList, j)) {
                    return TeamStatus.chanceOfChampionsLeague;
                }
                else {
                    return TeamStatus.championsLeague;
                }
            }
        }
        if(inEuropaLeagueQualificationPlace){
            for(int j = i + 1; j < teamList.size(); j ++){
                valueToOvertake = getValueToOvertake(team, teamList, numberOfGames, j);
                if(potentialPointsOvertakeCurrentPoints(teamList, j)) {
                    return TeamStatus.chanceOfEuropaLeague;
                }
                else {
                    return TeamStatus.europaLeague;
                }
            }
        }
        if(inRelegationZone){
            gamesInSeasonDifference = numberOfGames - teamList.get(i).gamesPlayed;
            possibleAmountOfPoints = gamesInSeasonDifference * 3;
            int teamAtRelegationPossibleEndOfSeason = teamList.get(16).points;
            if(teamList.get(i).points + possibleAmountOfPoints > teamAtRelegationPossibleEndOfSeason) {
                return TeamStatus.canGetOutOfRelegation;
            }
            else {
                return TeamStatus.definitelyRelegated;
            }
        }
        if(inBottomHalfOfLeague){
            for(int j = i + 1; j < teamList.size(); j++){
                valueToOvertake = getValueToOvertake(team, teamList, numberOfGames, j);
                if(teamList.get(j).points + possibleAmountOfPoints > valueToOvertake) {
                    return TeamStatus.highChanceOfRelegation;
                }
                else {
                    return TeamStatus.canGetOutOfRelegation;
                }
            }
        }
        if(inTopHalfOfLeagueAfterFifth){
            for(int j = 17; j < teamList.size(); j++){
                valueToOvertake = getValueToOvertake(team, teamList, numberOfGames, j);
                if(potentialPointsOvertakeCurrentPoints(teamList, j))
                    return TeamStatus.couldBeRelegated;
                else
                    return TeamStatus.definitelySafe;
            }
        }
        return TeamStatus.safe;
    }

    private boolean potentialPointsOvertakeCurrentPoints(ArrayList<Team> teamList, int j) {
        return teamList.get(j).points > valueToOvertake;
    }

    private int getValueToOvertake(Team team1, ArrayList<Team> teamList, int totalGamesInSeason, int j) {
        gamesInSeasonDifference = totalGamesInSeason - teamList.get(j).gamesPlayed;
        possibleAmountOfPoints = gamesInSeasonDifference * 3;
        valueToOvertake = team1.points - possibleAmountOfPoints;
        return valueToOvertake;
    }
}
