package xmlconnector;

import domain.Fixture;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class XMLFixtureConnector {
    public ArrayList<Fixture> listOfFixtures = new ArrayList<Fixture>();
    String homeTeam;
    String awayTeam;
    String score;

    public XMLFixtureConnector() throws ParserConfigurationException, IOException, SAXException {
        try {
            URL url = new URL("http://www.footballwebpages.co.uk/grid.xml?comp=1");
            URLConnection connection = url.openConnection();

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(connection.getInputStream());

            document.getDocumentElement().normalize();

            NodeList fixtureList = document.getElementsByTagName("match");

            for(int i = 0; i < fixtureList.getLength(); i++){
                Node fixtureNode = fixtureList.item(i);

                if(fixtureNode.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) fixtureNode;
                    homeTeam = element.getElementsByTagName("homeTeam").item(0).getTextContent();
                    awayTeam = element.getElementsByTagName("awayTeam").item(0).getTextContent();
                    if(fixtureNode.getChildNodes().getLength() == 7)
                        score = "GNP";
                    else
                        score = (element.getElementsByTagName("homeTeamScore").item(0).getTextContent()) + "-" +
                                (element.getElementsByTagName("awayTeamScore").item(0).getTextContent());
                    listOfFixtures.add(new Fixture(homeTeam, awayTeam, score));
                }

            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public ArrayList<Fixture> getListOfFixtures(){
        return listOfFixtures;
    }
}
