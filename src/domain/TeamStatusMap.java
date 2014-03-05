package domain;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TeamStatusMap {

    String teamVerdict;

    public TeamStatusMap(TeamStatus teamStatus) {
        HashMap<TeamStatus, String> teamStatusMap = new HashMap<TeamStatus, String>();
        teamStatusMap.put(TeamStatus.startOfSeason, "Start of Season, no verdict yet");
        teamStatusMap.put(TeamStatus.equalOnEverything, "Drawing on points and Goal Difference");
        teamStatusMap.put(TeamStatus.equalButTop, "Drawing on points but has a higher Goal Difference");
        teamStatusMap.put(TeamStatus.atRisk, "Team is at Risk this week");
        teamStatusMap.put(TeamStatus.fairlySafeForNow, "Team is safe, but if team below wins, risk of drawing");
        teamStatusMap.put(TeamStatus.champions, "Champions of the Premier League!!!");
        teamStatusMap.put(TeamStatus.drawingForTitle, "Racing for the Title!!");
        teamStatusMap.put(TeamStatus.chanceOfChampion, "High possibility of winning the League");
        teamStatusMap.put(TeamStatus.chanceOfChampionsLeague, "High chance of entering the Champions League");
        teamStatusMap.put(TeamStatus.chanceOfEuropaLeague, "High chance of entering the Europa League");
        teamStatusMap.put(TeamStatus.highChanceOfRelegation, "High chance of being relegated");
        teamStatusMap.put(TeamStatus.championsLeague, "In the Champions League!");
        teamStatusMap.put(TeamStatus.europaLeague, "In the Europa League!");
        teamStatusMap.put(TeamStatus.definitelySafeOfRelegation, "Team is definitely safe of Relegation");
        teamStatusMap.put(TeamStatus.definitelySafe, "Team is safe where they are");
        teamStatusMap.put(TeamStatus.definitelySafeForNow, "Team could be overtaken soon, but safe this week");
        teamStatusMap.put(TeamStatus.definitelyRelegated, "Team is definitely moving to Championship");
        teamStatusMap.put(TeamStatus.couldBeRelegated, "Team still aren't completely safe!");

        Iterator teamStatusIterator = teamStatusMap.entrySet().iterator();

        while(teamStatusIterator.hasNext()){
            Map.Entry mapEntry = (Map.Entry) teamStatusIterator.next();
            if(teamStatus == mapEntry.getKey())
                setTeamVerdict((String) mapEntry.getValue());
        }

    }

    public void setTeamVerdict(String teamVerdict){
        this.teamVerdict = teamVerdict;
    }

    public String getTeamVerdict(){
        return teamVerdict;
    }

}
