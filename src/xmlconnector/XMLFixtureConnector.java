package xmlconnector;

import domain.fixture;
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

public class XMLFixtureConnector {
    public ArrayList<fixture> listOfFixtures = new ArrayList<fixture>();
    String homeTeam;
    String awayTeam;
    String score;

    public XMLFixtureConnector() throws ParserConfigurationException, IOException, SAXException {
        try {
            File file = new File("./src/xmlfiles/PremierLeagueFixtures.xml");

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);

            document.getDocumentElement().normalize();

            NodeList fixtureList = document.getElementsByTagName("match");

            for(int i = 0; i < fixtureList.getLength(); i++){
                Node fixtureNode = fixtureList.item(i);

                if(fixtureNode.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) fixtureNode;
                    homeTeam = element.getElementsByTagName("homeTeam").item(0).getTextContent();
                    awayTeam = element.getElementsByTagName("awayTeam").item(0).getTextContent();
                    if(fixtureList.getLength() == 3)
                        score = "Game not played";
                    else
                        score = (element.getElementsByTagName("homeTeamScore").item(0).getTextContent()) + "-" +
                                (element.getElementsByTagName("awayTeamScore").item(0).getTextContent());
                    listOfFixtures.add(new fixture(homeTeam, awayTeam, score));
                }

            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public ArrayList<fixture> getListOfFixtures(){
        return listOfFixtures;
    }
}
