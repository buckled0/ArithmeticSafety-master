package domain;

import java.util.Random;

public class PremierLeaguePredictions {

    private String finalScore;

    public PremierLeaguePredictions(String homeTeam, String awayTeam) {
        int minGoals = 0;
        int maxGoals = 6;
        Random generator = new Random();
        int goals = generator.nextInt(minGoals + maxGoals) + 1;
        int homeTeamGoals = 0, awayTeamGoals = 0;
        StrengthMap strengthMapHome = new StrengthMap(homeTeam);
        double homeTeamStrength = strengthMapHome.getTeamStrength();
        StrengthMap strengthMapAway = new StrengthMap(awayTeam);
        double awayTeamStrength = strengthMapAway.getTeamStrength();
        int totalStrength = (int) Math.round(homeTeamStrength + awayTeamStrength);

        for (int i = 0; i < goals; i++) {
            if (generator.nextInt(totalStrength) + 1 < homeTeamStrength)
                homeTeamGoals+=1;
            else
                awayTeamGoals+=1;
        }

        String finalScore = homeTeamGoals + "-" + awayTeamGoals;

        setFinalScore(finalScore);
    }

    public void setFinalScore(String finalScore) {
        this.finalScore = finalScore;
    }

    public String getFinalScore(){
        return finalScore;
    }
}
