package domain;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class StrengthMap {

    public double teamStrength;

    public StrengthMap(String teamName) {
        Map<String, Double> strengthMap = new HashMap<String, Double>();

        strengthMap.put("Liverpool", 7.19);
        strengthMap.put("Chelsea", 7.03);
        strengthMap.put("Man City", 7.10);
        strengthMap.put("West Ham", 6.81);
        strengthMap.put("Man Utd", 6.94);
        strengthMap.put("Tottenham", 6.83);
        strengthMap.put("Southampton", 6.91);
        strengthMap.put("Sunderland", 6.64);
        strengthMap.put("Stoke", 6.77);
        strengthMap.put("Swansea", 6.77);
        strengthMap.put("Arsenal", 7.07);
        strengthMap.put("Newcastle", 6.87);
        strengthMap.put("Aston Villa", 6.79);
        strengthMap.put("Everton", 6.97);
        strengthMap.put("Cardiff", 6.66);
        strengthMap.put("Hull", 6.74);
        strengthMap.put("Crystal Palace", 6.76);
        strengthMap.put("Norwich", 6.67);
        strengthMap.put("West Brom", 6.75);
        strengthMap.put("Fulham", 6.65);

        Iterator strengthMapIterator = strengthMap.entrySet().iterator();

        while (strengthMapIterator.hasNext()) {
            Map.Entry mapEntry = (Map.Entry) strengthMapIterator.next();
            if (teamName == mapEntry.getKey())
                System.out.println(teamName);
                setTeamStrength((Double) mapEntry.getValue());
        }
    }

    public void setTeamStrength(double teamStrength) {
        this.teamStrength = teamStrength;
    }

    public double getTeamStrength(){
        return teamStrength;
    }
}
