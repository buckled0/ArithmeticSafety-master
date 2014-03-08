package domain.teamtypes;

import domain.TeamStatus;

public class AtRiskTeam extends TeamType {

    public TeamStatus getTeamStatus(){
        return TeamStatus.atRisk;
    }
}
