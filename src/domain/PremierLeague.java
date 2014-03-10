package domain;

public class PremierLeague {

    public String teamName;
    public int points;
    public int goalDifference;
    public int wins;
    public int loses;
    public int draws;
    public int gamesPlayed;
    public int form;

    public PremierLeague(String teamName, int points, int goalDifference, int wins,
                         int loses, int draws, int gamesPlayed, int form) {
        this.teamName = teamName.substring(0, 3);
        this.points = points;
        this.goalDifference = goalDifference;
        this.wins = wins;
        this.loses = loses;
        this.draws = draws;
        this.gamesPlayed = gamesPlayed;
        this.form = form;
    }

}
