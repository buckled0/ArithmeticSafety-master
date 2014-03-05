package domain;

import java.util.Map;

public class VerdictDictionary {

    Map<TeamStatus, String> verdictMap;

    public void VerdictDictionary(){

        verdictMap.put(TeamStatus.startOfSeason, "Start of Season, no verdict yet");
        verdictMap.put(TeamStatus.equalOnEverything, "Drawing on points and Goal Difference");
        verdictMap.put(TeamStatus.equalButTop, "Drawing on points but has a higher Goal Difference");
        verdictMap.put(TeamStatus.atRisk, "Team is at Risk this week");
        verdictMap.put(TeamStatus.fairlySafeForNow, "Team is safe, but if team below wins, risk of drawing");
        verdictMap.put(TeamStatus.definitelySafeForNow, "Team is definitely safe this week");
        verdictMap.put(TeamStatus.champions, "Champions of the League!!!");
        verdictMap.put(TeamStatus.drawingForTitle, "Racing for the Title!!");
        verdictMap.put(TeamStatus.chanceOfChampion, "High possibility of winning the League");
        verdictMap.put(TeamStatus.chanceOfChampionsLeague, "High chance of entering the Champions League");
        verdictMap.put(TeamStatus.chanceOfEuropaLeague, "High chance of entering the Europa League");
        verdictMap.put(TeamStatus.highChanceOfRelegation, "High chance of being relegated");
        verdictMap.put(TeamStatus.championsLeague, "In the Champions League!");
        verdictMap.put(TeamStatus.europaLeague, "In the Europa League!");
        verdictMap.put(TeamStatus.definitelyRelegated, "Team is definitely moving to Championship");

    }

}
