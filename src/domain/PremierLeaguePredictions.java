package domain;

import java.util.Random;

public class PremierLeaguePredictions {

    private int homeScore;
    private int awayScore;
    private int homePoints;
    private int awayPoints;

    public PremierLeaguePredictions(String awayTeamName, String homeTeamName) {
        int minGoals = 0;
        int maxGoals = 6;
        Random generator = new Random();
        int goals = generator.nextInt(minGoals + maxGoals) + 1;
        int homeTeamGoals = 0, awayTeamGoals = 0;
        StrengthMap strengthMapHome = new StrengthMap(homeTeamName);
        double homeTeamStrength = strengthMapHome.getTeamStrength();
        StrengthMap strengthMapAway = new StrengthMap(awayTeamName);
        double awayTeamStrength = strengthMapAway.getTeamStrength();
        int totalStrength = (int) Math.round(homeTeamStrength + awayTeamStrength);

        for (int i = 0; i < goals; i++) {
            if (generator.nextInt(totalStrength) + 1 < homeTeamStrength)
                homeTeamGoals += 1;
            else
                awayTeamGoals += 1;
        }

        setHomeScore(homeTeamGoals);
        setAwayScore(awayTeamGoals);

        calculatePointsEarned();

    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public int getHomeScore(){
        return homeScore;
    }

    public int getAwayScore(){
        return awayScore;
    }

    public void calculatePointsEarned() {
        int home = getHomeScore();
        int away = getAwayScore();
        int homePoints = 0;
        int awayPoints = 0;

        if (home > away) {
            homePoints += 3;
        } else if (away > home) {
            awayPoints += 3;
        } else {
            homePoints += 1;
            awayPoints += 1;
        }
        setHomePoints(homePoints);
        setAwayPoints(awayPoints);
    }

    public void setHomePoints(int homePoints) {
        this.homePoints = homePoints;
    }

    public void setAwayPoints(int awayPoints) {
        this.awayPoints = awayPoints;
    }

    public int getHomePoints(){
        return homePoints;
    }

    public int getAwayPoints(){
        return awayPoints;
    }
}
