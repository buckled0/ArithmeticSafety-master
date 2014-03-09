package test;

import domain.Team;
import domain.TeamStatus;
import domain.TeamVerdict;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class definitiveSafetyVerdictCheckTest {

    private ArrayList<Team> teamList;

    @Test
    public void shouldCheckForDefiniteChampionsFrom8GamesLeft() {
        Team sunderland = createTeam("Sunderland", 111, 43, 0, 0, 0, 30);
        int i = 0;

        teamList = getTeamListFromVerdict("Premier League, 08/05/2012");


        assertEquals("Will return definitely safe as there is no way City can overtake", TeamStatus.champions,
                sunderland.definitiveSafetyVerdictCheck(teamList, i, "Premier League", 38, 3));
    }

    @Test
    public void shouldDefinitelyReturnDefinitelyReturnInChampionsLeagueFrom8GamesLeft() {
        Team sunderland = createTeam("Sunderland", 92, 43, 30, 0, 0, 30);
        int i = 1;

        teamList = getTeamListFromVerdict("Premier League, 08/05/2012");

        assertEquals("Points difference so great city can't overtake Sunderland, so definitely in champions league",
                TeamStatus.championsLeague, sunderland.definitiveSafetyVerdictCheck(teamList, i, "Premier League", 38, 3));
    }

    @Test
    public void shouldDefinitelyReturnInEuropaLeague(){
        Team sunderland = createTeam("Sunderland", 89, 43, 30, 0, 0, 30);
        int i = 4;

        teamList = getTeamListFromVerdict("Premier League, 08/05/2012");

        assertEquals("Points difference between two teams is so great, Sunderland defintiely in Europa",
                TeamStatus.europaLeague, sunderland.definitiveSafetyVerdictCheck(teamList, i, "Premier League", 38, 3));
    }

    @Test
    public void shouldBeDefinitelySafeIfTopOfRelegationIsAmountOfViablePointsAwayFromCurrentTeam(){
        Team sunderland = createTeam("Sunderland", 59, 43, 30, 0, 0, 30);
        int i = 5;

        teamList = getTeamListFromVerdict("Premier League, 08/05/2012");

        assertEquals("If Sunderland are anywhere in the league and City can't overtake," +
                " then Sunderland are definitely safe of relegation",
                TeamStatus.definitelySafe, sunderland.definitiveSafetyVerdictCheck(teamList, i, "Premier League", 38, 3));
    }

    @Test
    public void shouldReturnChanceOfChampionsIfTwoTeamsAreLockedForTitle(){
        Team manCity = createTeam("Man Utd", 86, 55, 27, 5, 5, 37);
        int i = 0;

        teamList = getTeamListFromVerdict("Premier League, 08/05/2012");

        assertEquals("Should return chance of champions",
                TeamStatus.chanceOfChampion, manCity.definitiveSafetyVerdictCheck(teamList, i, "Premier League", 38, 3));
    }

    @Test
    public void shouldBeUnsafeSinceBoltonCanOvertakeQPRSinceOneGameRemaining(){
        Team qpr = createTeam("QPR", 37, -22, 10, 20, 7, 37);
        int i = 16;

        teamList = getTeamListFromVerdict("Premier League, 08/05/2012");

        assertEquals("Should return unsafe as Bolton can overtake",
                TeamStatus.highChanceOfRelegation, qpr.definitiveSafetyVerdictCheck(teamList, i, "Premier League", 38, 3));
    }

    @Test
    public void shouldBeAbleToGetOutOfRelegationIfBoltonCanOvertakeQPR(){
        Team bolton = createTeam("Bolton", 35, -31, 10, 22, 5, 37);
        int i = 17;

        teamList = getTeamListFromVerdict("Premier League, 08/05/2012");

        assertEquals("Should return can get out of relegation if Bolton can win next game",
                TeamStatus.canGetOutOfRelegation, bolton.definitiveSafetyVerdictCheck(teamList, i, "Premier League", 38, 3));
    }
    
    @Test
    public void shouldDefinitelyBeRelegatedSinceBlackburnCantOvertakeQPR(){
        Team blackburn = createTeam("Blackburn", 31, -41, 8, 22, 7, 37);
        int i = 18;

        teamList = getTeamListFromVerdict("Premier League, 08/05/2012");

        assertEquals("Team should definitely be relegated if they can't get past team at top of relegation",
                TeamStatus.definitelyRelegated, blackburn.definitiveSafetyVerdictCheck(teamList, i, "Premier League", 38, 3));
    }
    
    @Test
    public void shouldBeUnsafeSinceBlackburnCouldPotentiallyGet12PointsAndOvertakeVilla(){
        Team astonVilla = createTeam("Aston Villa", 34, -9, 7, 13, 11, 31);
        int i = 14;

        teamList = getTeamListFromVerdict("Premier League, 07/04/2012");

        assertEquals("Aston Villa should be unsafe as Blackburn can overtake", TeamStatus.highChanceOfRelegation,
                astonVilla.definitiveSafetyVerdictCheck(teamList, i, "Premier League", 38, 3));
    }

    @Test
    public void shouldBeUnsafeSinceBlackburnCouldPotentiallyGet12PointsAndOvertakeBolton(){
        Team bolton = createTeam("Aston Villa", 29, -9, 7, 13, 11, 31);
        int i = 15;

        teamList = getTeamListFromVerdict("Premier League, 07/04/2012");

        assertEquals("Bolton should be unsafe as Blackburn can overtake", TeamStatus.highChanceOfRelegation,
                bolton.definitiveSafetyVerdictCheck(teamList, i, "Premier League", 38, 3));
    }

    public ArrayList<Team> getTeamListFromVerdict(String tableName) {
        try {
            TeamVerdict teamVerdict = new TeamVerdict(tableName);
            teamList = new ArrayList<Team>();
            for(int j = 0; j < teamVerdict.getTeamList().size(); j++)
                teamList.add(j, teamVerdict.getTeamList().get(j));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return teamList;
    }

    private Team createTeam(String name, int points, int goalDifference, int wins,
                            int loses, int draws, int gamesPlayed) {
        return new Team(name, points, goalDifference, wins, loses, draws, gamesPlayed);
    }
}
