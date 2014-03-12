package domain;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class StrengthMap {

    public double teamStrength;

    public StrengthMap(String teamName) {
        Map<String, Double> strengthMap = new HashMap<String, Double>();

        strengthMap.put("Liverpool", 7.18);
        strengthMap.put("Chelsea", 7.05);
        strengthMap.put("Man City", 7.11);
        strengthMap.put("West Ham", 6.82);
        strengthMap.put("Man Utd", 6.96);
        strengthMap.put("Tottenham", 6.85);
        strengthMap.put("Southampton", 6.9);
        strengthMap.put("Sunderland", 6.64);
        strengthMap.put("Stoke", 6.75);
        strengthMap.put("Swansea", 6.77);
        strengthMap.put("Arsenal", 7.06);
        strengthMap.put("Newcastle", 6.88);
        strengthMap.put("Aston Villa", 6.77);
        strengthMap.put("Everton", 6.97);
        strengthMap.put("Cardiff", 6.66);
        strengthMap.put("Hull", 6.75);
        strengthMap.put("Crystal Palace", 6.75);
        strengthMap.put("Norwich", 6.68);
        strengthMap.put("West Brom", 6.74);
        strengthMap.put("Fulham", 6.64);

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
