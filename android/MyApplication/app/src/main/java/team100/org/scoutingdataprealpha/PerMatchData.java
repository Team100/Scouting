package team100.org.scoutingdataprealpha;

/**
 * Created by brendaporter on 2/14/15.
 */
public class PerMatchData {
    private boolean mLitter;
    private Match mMatch;
    private Team mTeam;

    public PerMatchData(Team team, Match match) {
        mLitter = false;
        mMatch = match;
        mTeam = team;
    }

    public Match getMatch() {
        return mMatch;
    }

    public Team getTeam() {
        return mTeam;
    }

    public boolean hasLitter() {
        return mLitter;
    }

    public void setGotNoodle(boolean litter) {
        this.mLitter = litter;
    }

    public String toString() {
        return "{TeamId: " + mTeam.getTeamID() + ", MatchId: " + mMatch.getMatchID() +
                ", Litter: " + mLitter + "}";
    }
}
