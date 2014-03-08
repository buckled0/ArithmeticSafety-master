package domain;

public class DefinitelySafeForNowTeam extends TeamType {

    public TeamStatus getTeamStatus(){
        return TeamStatus.definitelySafeForNow;
    }
}
