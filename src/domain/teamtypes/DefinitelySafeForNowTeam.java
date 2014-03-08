package domain.teamtypes;

import domain.TeamStatus;

public class DefinitelySafeForNowTeam extends TeamType{

    public TeamStatus getTeamStatus(){
        return TeamStatus.definitelySafeForNow;
    }
}
