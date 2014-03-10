package test;

import domain.PremierLeague;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class PremierLeagueTest {

    @Test
    public void shouldReturnChelseaAbbreviation() throws ParserConfigurationException, SAXException, IOException {
        PremierLeague chelsea = createPremierLeague("Chelsea", 0, 0, 0, 0, 0, 0, 0);
        assertEquals("Should return Che as an abbreviation of Chelsea", "Che", chelsea.teamName);
    }

    private PremierLeague createPremierLeague(String name, int points, int goalDifference, int wins,
                            int loses, int draws, int gamesPlayed, int form) throws ParserConfigurationException, SAXException, IOException {
        return new PremierLeague(name, points, goalDifference, wins, loses, draws, gamesPlayed, form);
    }
}
