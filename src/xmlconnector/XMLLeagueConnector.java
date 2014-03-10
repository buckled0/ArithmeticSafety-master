package xmlconnector;

import domain.PremierLeague;
import domain.Team;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class XMLLeagueConnector {

    String tableName;
    public ArrayList<PremierLeague> currentPremLeagueList = new ArrayList<PremierLeague>();
    public ArrayList<Team> listOfTeams = new ArrayList<Team>();
    String leagueName;
    int numberOfGames;
    int relegationZoneAmount;

    public XMLLeagueConnector(String tableName) throws ParserConfigurationException, IOException, SAXException {
        this.tableName = tableName;
        try {
            File file = new File("./src/xmlfiles/" + tableName + ".xml");

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);

            document.getDocumentElement().normalize();

            NodeList teamList = document.getElementsByTagName("ROW");
            NodeList currentLeagueList = document.getElementsByTagName("PremierLeauge");
            NodeList leagueDetails = document.getElementsByTagName("League_Data");

            for(int i = 0; i < leagueDetails.getLength(); i++){
                Node leagueNode = leagueDetails.item(i);

                if(leagueNode.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) leagueNode;
                    leagueName = element.getElementsByTagName("League_Name").item(0).getTextContent();
                    numberOfGames = Integer.parseInt(element.getElementsByTagName("TotalGamesPlayed").item(0).getTextContent());
                    relegationZoneAmount = Integer.parseInt(element.getElementsByTagName("RelegationZoneAmount").item(0).getTextContent());
                }

            }

            for(int i = 0; i < currentLeagueList.getLength(); i++){
                Node teamNode = currentLeagueList.item(i);

                if(teamNode.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) teamNode;
                    String teamName = element.getElementsByTagName("Team_Name").item(0).getTextContent();
                    int points = Integer.parseInt(element.getElementsByTagName("Points").item(0).getTextContent());
                    int goalDifference = Integer.parseInt(element.getElementsByTagName("Goal_Difference").item(0).getTextContent());
                    int gamesPlayed = Integer.parseInt(element.getElementsByTagName("Games_Played").item(0).getTextContent());
                    int wins = Integer.parseInt(element.getElementsByTagName("Wins").item(0).getTextContent());
                    int loses = Integer.parseInt(element.getElementsByTagName("Loses").item(0).getTextContent());
                    int draws = Integer.parseInt(element.getElementsByTagName("Draws").item(0).getTextContent());
                    int form = Integer.parseInt(element.getElementsByTagName("Form").item(0).getTextContent());
                    currentPremLeagueList.add(new PremierLeague(teamName, points, goalDifference,
                            wins, loses, draws, gamesPlayed, form));
                }
            }

            for(int i = 0; i < teamList.getLength(); i++){
                Node teamNode = teamList.item(i);

                if(teamNode.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) teamNode;
                    String teamName = element.getElementsByTagName("Team_Name").item(0).getTextContent();
                    int points = Integer.parseInt(element.getElementsByTagName("Points").item(0).getTextContent());
                    int goalDifference = Integer.parseInt(element.getElementsByTagName("Goal_Difference").item(0).getTextContent());
                    int gamesPlayed = Integer.parseInt(element.getElementsByTagName("Games_Played").item(0).getTextContent());
                    int wins = Integer.parseInt(element.getElementsByTagName("Wins").item(0).getTextContent());
                    int loses = Integer.parseInt(element.getElementsByTagName("Loses").item(0).getTextContent());
                    int draws = Integer.parseInt(element.getElementsByTagName("Draws").item(0).getTextContent());
                    listOfTeams.add(new Team(teamName, points, goalDifference, wins, loses, draws, gamesPlayed));
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getLeagueName(){
        return leagueName;
    }

    public int getRelegationZoneAmount(){
        return relegationZoneAmount;
    }

    public int getNumberOfGames(){
        return numberOfGames;
    }

    public ArrayList<PremierLeague> getCurrentPremLeagueList(){
        return currentPremLeagueList;
    }

    public ArrayList<Team> getListOfTeams(){
        return listOfTeams;
    }
}
