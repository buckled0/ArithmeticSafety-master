package domain;

public class Game {

    public String homeTeam;
    public String awayTeam;
    public String score;

    public Game(String homeTeam, String awayTeam, String score){
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.score = score;
    }

}
