package test;

import domain.Team;
import domain.TeamStatus;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class TeamTest {
    @Test
    public void giveTeamName() {
        assertTeamName("Sunderland", createTeam("Sunderland", 32, 0, 0, 0, 0, 0));
        assertTeamName("QPR", createTeam("QPR", 15, 0, 0, 0, 0, 0));
    }

    @Test
    public void giveTeamPoints() {
        assertEquals("Team Points", 32, createTeam("Sunderland", 0, 32, 0, 0, 0, 0).getPoints());
        assertEquals("Team Points", 31, createTeam("QPR", 0, 31, 0, 0, 0, 0).getPoints());
    }

    @Test
    public void giveTeamGoalDifference() {
        assertEquals("Team Goal Difference", -13, createTeam("Sunderland", -13, 32, 0, 0, 0, 0).getGoalDifference());
        assertEquals("Team Goal Difference", 10, createTeam("Sunderland", 10, 10, 0, 0, 0, 0).getGoalDifference());
    }

    @Test
    public void shouldHaveBothTeamsEqual(){
        Team sunderland = createTeam("Sunderland", 10, 32, 0, 0, 0, 0);
        Team manCity = createTeam("Man City", 10, 32, 0, 0, 0, 0);

        assertEquals("Team Equal Determination", TeamStatus.equalOnEverything, sunderland.leagueStatus(sunderland, manCity));
    }

    @Test
    public void shouldBeEqualOnPointsButHigherGoalDifference(){
        Team sunderland = createTeam("Sunderland", 15, 32, 0, 0, 0, 0);
        Team manCity = createTeam("Man City", 10, 32, 0, 0, 0, 0);

        assertEquals("Team equal but higher Goal Difference", TeamStatus.equalButTop,
                sunderland.leagueStatus(sunderland, manCity));
    }

    @Test
    public void shouldBeAtRiskIfPointsDifferenceIsTwoOrLess(){
        Team sunderland = createTeam("Sunderland", 15, 32, 0, 0, 0, 0);
        Team manCity = createTeam("Man City", 15, 30, 0, 0, 0, 0);

        assertEquals("Team is at risk as points difference is less than 2", TeamStatus.atRisk,
                sunderland.leagueStatus(sunderland, manCity));
    }

    @Test
    public void shouldBeFairlySafeIfPointsDifferenceIsThree(){
        Team sunderland = createTeam("Sunderland", 15, 33, 0, 0, 0, 0);
        Team manCity = createTeam("Man City", 15, 30, 0, 0, 0, 0);

        assertEquals("Team is farely safe if points difference is three", TeamStatus.fairlySafeForNow,
                sunderland.leagueStatus(sunderland, manCity));
    }


    @Test
    public void shouldBeDefinitelySafeForNowIfPointsDifferenceIsGreaterThanFour(){
        Team sunderland = createTeam("Sunderland", 15, 34, 0, 0, 0, 0);
        Team manCity = createTeam("Man City", 15, 30, 0, 0, 0, 0);

        assertEquals("Team is definitely safe that week if points difference is greater than four",
                TeamStatus.definitelySafeForNow, sunderland.leagueStatus(sunderland, manCity));
    }


    private Team createTeam(String name, int points, int goalDifference, int wins,
                            int loses, int draws, int gamesPlayed) {
        return new Team(name, points, goalDifference, wins, loses, draws, gamesPlayed);
    }


    private void assertTeamName(String teamName, Team team) {
        assertEquals("Team Name", teamName, team.getName());
    }

}
