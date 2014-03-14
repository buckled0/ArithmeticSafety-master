package domain;

import java.util.Random;

public class PremierLeaguePredictions {

    public int homeScore;
    public int awayScore;
    public int homePoints;
    public int awayPoints;
    public int homeGD;
    public int awayGD;

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

    public void calculatePointsEarned() {
        int home = homeScore;
        int away = awayScore;
        int homePoints = 0;
        int awayPoints = 0;
        int homeGD = 0;
        int awayGD = 0;

        if (home > away) {
            homePoints += 3;
            homeGD = home - away;
            awayGD = away - home;
        } else if (away > home) {
            awayPoints += 3;
            homeGD = away - home;
            awayGD = home - away;
        } else {
            homePoints += 1;
            awayPoints += 1;
        }
        setHomePoints(homePoints);
        setAwayPoints(awayPoints);
        setHomeGD(homeGD);
        setAwayGD(awayGD);
    }

    public void setHomePoints(int homePoints) {
        this.homePoints = homePoints;
    }

    public void setAwayPoints(int awayPoints) {
        this.awayPoints = awayPoints;
    }

    public void setHomeGD(int homeGD) {
        this.homeGD = homeGD;
    }

    public void setAwayGD(int awayGD) {
        this.awayGD = awayGD;
    }

}
