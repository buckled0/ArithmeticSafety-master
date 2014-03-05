package domain;

import org.xml.sax.SAXException;
import xmlconnector.XMLBettingConnector;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class BettingVerdict {
    public Betting home;
    public Betting away;
    public double homeOdds;
    public double awayOdds;
    public double drawingOdds;
    public ArrayList<Double> bettingVerdictList = new ArrayList<Double>();

    public BettingVerdict(String homeTeam, String awayTeam){
        try {
            XMLBettingConnector xmlBettingConnector = new XMLBettingConnector();

            bettingVerdictList.clear();
            for(int i = 0; i < xmlBettingConnector.getBettingList().size(); i++){
                if(xmlBettingConnector.getBettingList().get(i).getName().equals(homeTeam))
                    home = xmlBettingConnector.getBettingList().get(i);
                if(xmlBettingConnector.getBettingList().get(i).getName().equals(awayTeam))
                    away = xmlBettingConnector.getBettingList().get(i);
            }

            homeOdds = home.homeOdds(home, away);
            awayOdds = away.awayOdds(home, away);
            drawingOdds = home.drawingOdds(homeOdds, awayOdds);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }

    public double getHomeOdds(){
        return homeOdds;
    }

    public double getAwayOdds(){
        return awayOdds;
    }

    public double getDrawingOdds(){
        return drawingOdds;
    }

}
