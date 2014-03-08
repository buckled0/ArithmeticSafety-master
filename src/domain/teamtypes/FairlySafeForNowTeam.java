package domain.teamtypes;

import domain.TeamStatus;

public class FairlySafeForNowTeam extends TeamType{

    public TeamStatus getTeamStatus(){
        return TeamStatus.fairlySafeForNow;
    }
}
