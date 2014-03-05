package test;

import domain.Betting;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class BettingTest {

    @Test
    public void shouldPassToHomeOddsMethodForSunderland(){
        Betting home = createTeam("Sunderland", 6);
        Betting away = createTeam("Hull", 1);

        double expected = 1.83;

        assertEquals("Should give the home odds", expected, home.homeOdds(home, away));

    }

    @Test
    public void shouldPassToHomeOddsMethodForChelseaIfOneValueIsZero(){
        Betting home = createTeam("Chelsea", 9);
        Betting away = createTeam("Newcastle", -5);

        double expected = 0.41;

        assertEquals("Should give the home odds", expected, home.homeOdds(home, away));

    }

    @Test
    public void shouldPassToAwayOddsMethodForHull(){
        Betting away = createTeam("Hull", 1);
        Betting home = createTeam("Sunderland", 6);

        double expected = 3.30;

        assertEquals("Should give away odds", expected, away.awayOdds(home, away));
    }

    @Test
    public void shouldPassToAwayOddsMethodForChelseaAndNewcastleIfAwayValueIsZero(){
        Betting away = createTeam("Chelsea", 9);
        Betting home = createTeam("Newcastle", -5);

        double expected = 0.55;

        assertEquals("Should give the home odds", expected, away.awayOdds(home, away));

    }

    @Test
    public void shouldPassToAwayOddsMethodForChelseaAndNewcastleIfHomeValueIsZero(){
        Betting home = createTeam("Chelsea", 9);
        Betting away = createTeam("Newcastle", -5);

        double expected = 0.48;

        assertEquals("Should give the home odds", expected, away.awayOdds(home, away));

    }

    private Betting createTeam(String name, int goalDifferenceSixGames) {
        return new Betting(name, goalDifferenceSixGames);
    }

}
