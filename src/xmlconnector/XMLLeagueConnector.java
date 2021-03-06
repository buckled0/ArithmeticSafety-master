package xmlconnector;

import domain.Team;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class XMLLeagueConnector {

    String tableName;
    public ArrayList<Team> listOfTeams = new ArrayList<Team>();
    String leagueName;
    int numberOfGames;
    int relegationZoneAmount;

    public XMLLeagueConnector(String tableName) throws ParserConfigurationException, IOException, SAXException {
        this.tableName = tableName;
        if(tableName.equals("Premier League")){
            try {
                URL url = new URL("http://www.footballwebpages.co.uk/league.xml?comp=1");
                URLConnection connection = url.openConnection();


                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(connection.getInputStream());

                TransformerFactory tfactory = TransformerFactory.newInstance();
                Transformer xform = tfactory.newTransformer();

                File xmlOutput = new File("./src/xmlfiles/CurrentPremierLeague.xml");
                xform.transform(new DOMSource(document), new StreamResult(xmlOutput));

                document.getDocumentElement().normalize();

                NodeList teamList = document.getElementsByTagName("team");

                NodeList leagueDetails = document.getElementsByTagName("leagueTable");
                for(int i = 0; i < leagueDetails.getLength(); i++){
                    Node leagueNode = leagueDetails.item(i);

                    if(leagueNode.getNodeType() == Node.ELEMENT_NODE){
                        Element element = (Element) leagueNode;
                        leagueName = element.getElementsByTagName("competition").item(0).getTextContent();
                        numberOfGames = 38;
                        relegationZoneAmount = 3;
                    }

                }
                for(int i = 0; i < teamList.getLength(); i++){
                    Node teamNode = teamList.item(i);
                    if(teamNode.getNodeType() == Node.ELEMENT_NODE){
                        Element element = (Element) teamNode;
                        String teamName = element.getElementsByTagName("name").item(0).getTextContent();
                        int points = Integer.parseInt(element.getElementsByTagName("points").item(0).getTextContent());
                        int goalDifference = Integer.parseInt(element.getElementsByTagName("goalDifference").item(0).getTextContent());
                        int gamesPlayed = Integer.parseInt(element.getElementsByTagName("played").item(0).getTextContent());
                        int wins = Integer.parseInt(element.getElementsByTagName("won").item(0).getTextContent());
                        int loses = Integer.parseInt(element.getElementsByTagName("lost").item(0).getTextContent());
                        int draws = Integer.parseInt(element.getElementsByTagName("drawn").item(0).getTextContent());
                        int form = 0;
                        listOfTeams.add(new Team(teamName, points, goalDifference, wins, loses, draws, gamesPlayed, form));
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        } else {
            try {
                File file = new File("./src/xmlfiles/" + tableName + ".xml");

                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(file);

                document.getDocumentElement().normalize();

                NodeList teamList = document.getElementsByTagName("ROW");

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
                        int form = Integer.parseInt(element.getElementsByTagName("Form").item(0).getTextContent());
                        listOfTeams.add(new Team(teamName, points, goalDifference, wins, loses, draws, gamesPlayed, form));
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }
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

    public ArrayList<Team> getListOfTeams(){
        return listOfTeams;
    }
}
