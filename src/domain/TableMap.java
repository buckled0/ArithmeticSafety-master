package domain;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TableMap {

    String tableName;

    public TableMap(String tableName) {
        Map<String, String> tableMap = new HashMap<String, String>();

        tableMap.put("Premier League Current", "Premier League");
        tableMap.put("Premier League, 09/02/2014", "Premier League Feb 9 2014");
        tableMap.put("Premier League, 12/02/2014", "Premier League Feb 12 2014");
        tableMap.put("Premier League, 23/02/2014", "Premier League Feb 23 2014");
        tableMap.put("Premier League, 02/03/2014", "Premier League March 2 2014");
        tableMap.put("Premier League Half Way Point", "Premier League Half Way");
        tableMap.put("Premier League, 21/04/2013", "Premier League April 21 2013");
        tableMap.put("Premier League, 28/04/2013", "Premier League April 28 2013");
        tableMap.put("Premier League, 05/05/2013", "Premier League May 5 2013");
        tableMap.put("Premier League, 12/05/2013", "Premier League May 12 2013");
        tableMap.put("Premier League, 19/05/2013", "Premier League May 19 2013");
        tableMap.put("Premier League, 07/04/2012", "Premier League April 7 2012");
        tableMap.put("Premier League, 11/04/2012", "Premier League April 11 2012");
        tableMap.put("Premier League, 28/04/2012", "Premier League April 28 2012");
        tableMap.put("Premier League, 07/05/2012", "Premier League May 7 2012");
        tableMap.put("Premier League, 08/05/2012", "Premier League May 8 2012");
        tableMap.put("Premier League, 13/05/2012", "Premier League May 13 2012");

        Iterator tableMapIterator = tableMap.entrySet().iterator();

        while (tableMapIterator.hasNext()) {
            Map.Entry mapEntry = (Map.Entry) tableMapIterator.next();
            if (tableName == mapEntry.getKey())
                setTableName((String) mapEntry.getValue());
        }
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName(){
        return tableName;
    }
}
