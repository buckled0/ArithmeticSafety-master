package domain;

import org.xml.sax.SAXException;
import xmlconnector.XMLLeagueConnector;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class TeamVerdict {

    public ArrayList<Team> teamList;
    public ArrayList<TeamStatus> verdictArray = new ArrayList<TeamStatus>();
    private XMLLeagueConnector xmlConnector;
    private String tableName;
    private int gamesRemaining;

    public TeamVerdict(String selectedValue) throws ParserConfigurationException, SAXException, IOException {
        this.tableName = selectedValue;

        TableMap tableMap = new TableMap(tableName);

        leagueVerdictConnector(tableMap.getTableName());
    }
    private void leagueVerdictConnector(String tableName) throws IOException, SAXException, ParserConfigurationException {
        xmlConnector = new XMLLeagueConnector(tableName);
        teamList = xmlConnector.getListOfTeams();
        verdictArray.clear();
        int checkGamesPlayed = teamList.get(0).gamesPlayed;

        String leagueName = xmlConnector.getLeagueName();
        int numberOfGames = xmlConnector.getNumberOfGames();
        int relegationZoneAmount = xmlConnector.getRelegationZoneAmount();
        if(checkGamesPlayed == numberOfGames)
            endOfSeasonPlacement();
        else if(checkGamesPlayed >= numberOfGames - 8){
            for(int i = 0; i < teamList.size() - 1; i++){
                Team team1 = teamList.get(i);
                TeamStatus teamStatus = team1.definitiveSafetyVerdictCheck(teamList, i, leagueName, numberOfGames,
                        relegationZoneAmount);
                verdictArray.add(i, teamStatus);
            }
            Team outOfRelegation = teamList.get(16);
            Team bottomTeam = teamList.get(19);

            int totalGamesInSeason = numberOfGames;
            int gamesInSeasonDifference = totalGamesInSeason - outOfRelegation.gamesPlayed;
            int possibleAmountOfPoints = gamesInSeasonDifference * 3;
            int possibleEndOfSeason = bottomTeam.points + possibleAmountOfPoints;
            setGamesRemaining(totalGamesInSeason - bottomTeam.gamesPlayed);

            if(possibleEndOfSeason > outOfRelegation.points)
                verdictArray.add(19, TeamStatus.highChanceOfRelegation);
            else
                verdictArray.add(19, TeamStatus.definitelyRelegated);
        }
        else if(checkGamesPlayed == 0)
            startOfSeason();
        else {
            for(int i = 0; i < teamList.size() - 1; i++){
                Team team1 = teamList.get(i);
                Team team2 = teamList.get(i + 1);
                TeamStatus teamStatus = team1.getTeamStatus(team2);
                verdictArray.add(i, teamStatus);
            }
            verdictArray.add(19, TeamStatus.couldBeRelegated);
        }

    }

    public void startOfSeason() {
        for(int i = 0; i < 20; i++)
            verdictArray.add(i, TeamStatus.startOfSeason);
    }

    public void endOfSeasonPlacement() {
        verdictArray.add(0, TeamStatus.champions);
        for(int i = 1; i < 4; i++)
            verdictArray.add(i, TeamStatus.championsLeague);

        verdictArray.add(4, TeamStatus.europaLeague);

        for(int i = 5; i < 17; i++){
            verdictArray.add(i, TeamStatus.finalPlace);
        }

        for(int i = 17; i < 20; i++)
            verdictArray.add(i, TeamStatus.definitelyRelegated);
    }

    public int getBottomDifference(){
        int bottomDifference = teamList.get(18).points - teamList.get(19).points + 1;
        return bottomDifference;
    }

    public ArrayList<Team> getTeamList(){ return teamList; }

    public ArrayList<TeamStatus> getVerdictArray(){
        return verdictArray;
    }

    private void setGamesRemaining(int gamesRemaining){
        this.gamesRemaining = gamesRemaining;
    }

    public int getGamesRemaining() {
        return gamesRemaining;
    }
}
