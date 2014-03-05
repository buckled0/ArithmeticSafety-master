package domain;

import java.text.DecimalFormat;

public class Betting {

    public String name;
    public int goalDifferenceSixGames;

    private double pastHomeWinsPercentage = 0.4622;
    private double pastAwayWinsPercentage = 0.2694;
    public double homeTeamOdds;
    public double awayTeamOdds;

    public Betting(String name, int goalDifferenceSixGames) {
        this.name = name;
        this.goalDifferenceSixGames = goalDifferenceSixGames;
    }

    public String getName(){
        return name;
    }
    public int getGoalDifferenceSixGames(){
        return goalDifferenceSixGames;
    }

    public double homeOdds(Betting home, Betting away) {
        double dividedHomeOdds;
        this.homeTeamOdds = 0.0156 * (home.getGoalDifferenceSixGames() - away.getGoalDifferenceSixGames()) +
            pastHomeWinsPercentage;

        dividedHomeOdds = 1/homeTeamOdds;
        DecimalFormat finalHomeOdds = new DecimalFormat("#.##");

        return Double.parseDouble(finalHomeOdds.format(dividedHomeOdds));
    }

    public double awayOdds(Betting home, Betting away) {
        double dividedTeamOdds;
        this.awayTeamOdds = 0.0003 * (Math.pow(home.getGoalDifferenceSixGames() - away.getGoalDifferenceSixGames(), 2)) -
            (0.0127* (home.getGoalDifferenceSixGames() - away.getGoalDifferenceSixGames())) +
            pastAwayWinsPercentage;

        dividedTeamOdds = 1/awayTeamOdds;
        DecimalFormat finalAwayOdds = new DecimalFormat("#.##");

        return Double.parseDouble(finalAwayOdds.format(dividedTeamOdds));
    }

    public double drawingOdds(double homeOdds, double awayOdds){
        double drawingOdds = 10 - (homeOdds + awayOdds);
        DecimalFormat finalDrawOdds = new DecimalFormat("#.##");
        return Double.parseDouble(finalDrawOdds.format(drawingOdds));
    }

}
