package xmlconnector;

import domain.Betting;
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

public class XMLBettingConnector {

    public ArrayList<Betting> bettingList = new ArrayList<Betting>();

    public XMLBettingConnector() throws ParserConfigurationException, IOException, SAXException {
        try {
            File file = new File("./src/xmlfiles/Premier League.xml");

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);

            document.getDocumentElement().normalize();

            NodeList teamList = document.getElementsByTagName("ROW");

            for(int i = 0; i < teamList.getLength(); i++){
                Node teamNode = teamList.item(i);

                if(teamNode.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) teamNode;
                    String teamName = element.getElementsByTagName("Team_Name").item(0).getTextContent();
                    int last_6_games = Integer.parseInt(element.getElementsByTagName("Last_6_games").item(0).getTextContent());
                    bettingList.add(new Betting(teamName, last_6_games));
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Betting> getBettingList(){
        return bettingList;
    }
}