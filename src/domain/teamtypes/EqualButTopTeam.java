package domain.teamtypes;

import domain.TeamStatus;

public class EqualButTopTeam extends TeamType {

    public TeamStatus getTeamStatus(){
        return TeamStatus.equalButTop;
    }
}
