package domain;

abstract class TeamType {

    abstract TeamStatus getTeamStatus();

    public static TeamType newTeamType(Team team1, Team team2){

        boolean equalOnEverything = (team1.getPoints() == team2.getPoints()) && (team1.getGoalDifference() == team2.getGoalDifference());
        boolean equalButTopTeam = (team1.getPoints() == team2.getPoints()) && (team1.getGoalDifference() > team2.getGoalDifference());
        boolean atRiskTeam = team1.getPoints() - team2.getPoints() <= 2;
        boolean fairlySafeForNowTeam = team1.getPoints() - team2.getPoints() == 3;
        boolean definitelySafeForNowTeam = team1.getPoints() - team2.getPoints() >= 4;

        if (equalOnEverything)
            return new EqualOnEverythingTeam();

        if (equalButTopTeam)
            return new EqualButTopTeam();

        if (atRiskTeam)
            return new AtRiskTeam();

        if (fairlySafeForNowTeam)
            return new FairlySafeForNowTeam();

        if (definitelySafeForNowTeam)
            return new DefinitelySafeForNowTeam();

        return new DefinitelySafeForNowTeam();
    }
}
