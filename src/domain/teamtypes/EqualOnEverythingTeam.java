package domain.teamtypes;

import domain.TeamStatus;

public class EqualOnEverythingTeam extends TeamType {

    public TeamStatus getTeamStatus(){
        return TeamStatus.equalOnEverything;
    }
}
