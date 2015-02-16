package team100.org.scoutingdataprealpha;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brendaporter on 2/14/15.
 */
public class Match {

    Match(String matchID) {
        mMatchID = matchID;
        mTeamDataMap = new HashMap<String, PerMatchData>();
    }

    public String getMatchID() {
        return mMatchID;
    }

    public Map<String, PerMatchData> getTeamDataMap() {
        return mTeamDataMap;
    }

    public String toString() {
        return "{MatchId: " + mMatchID +
                ", Teams: " + mTeamDataMap.keySet().toString() + "}";
    }

    private String mMatchID;
    private Map<String, PerMatchData> mTeamDataMap;
}
