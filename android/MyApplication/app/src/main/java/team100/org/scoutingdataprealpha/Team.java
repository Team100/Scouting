package team100.org.scoutingdataprealpha;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brendaporter on 2/14/15.
 */
public class Team {
    Team(String teamID) {
        mTeamID = teamID;
        mMatchDataMap = new HashMap<String, PerMatchData>();
    }

    public String getTeamID() {
        return mTeamID;
    }

    public Map<String, PerMatchData> getMatchDataMap() {
        return mMatchDataMap;
    }

    public String toString() {
        return "{TeamId: " + mTeamID +
                ", MatchData: " + mMatchDataMap.toString() + "}";
    }

    private String mTeamID;
    private Map<String, PerMatchData> mMatchDataMap;
}
