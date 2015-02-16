package team100.org.scoutingdataprealpha;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by brendaporter on 2/15/15.
 */
public class MatchCSVParser {
    private Map<String, Team> mTeams;
    private Map<String, Match> mMatches;

    /**
     * Constructor for MatchCSVParser.
     */
    MatchCSVParser() {
        mTeams = new HashMap<String, Team>();
        mMatches = new HashMap<String, Match>();
    }

    /**
     * This function takes a csv string of match data with the format of match ID and a list of
     * Team IDs and creates a map of teams and a map of matches.
     * @param csvMatches The csv string of match data.
     */
    void parseMatches(String csvMatches) {
        Scanner linescanner = new Scanner(csvMatches).useDelimiter("\n");
        while (linescanner.hasNext()) {
            Scanner scanner = new Scanner(linescanner.next()).useDelimiter(",");
            String matchID = scanner.next();
            List<String> teamIDs = new ArrayList<>();
            while (scanner.hasNext()) {
                String teamID = scanner.next();
                teamIDs.add(teamID);
            }
            processMatchData(matchID, teamIDs);
            scanner.close();
        }
        linescanner.close();
    }

    private void processMatchData(String matchID, List<String> teamIDs) {
        Match m = new Match(matchID);
        for (String teamID : teamIDs) {
            Team t = mTeams.get(teamID);
            if (t == null) {
               t = new Team(teamID);
                mTeams.put(t.getTeamID(), t);
            }
            PerMatchData p = new PerMatchData(t, m);
            t.getMatchDataMap().put(m.getMatchID(), p);
            m.getTeamDataMap().put(t.getTeamID(), p);
        }
        mMatches.put(m.getMatchID(), m);
    }

    /**
     * Returns the teams.
     * @return The map of teams.
     */
    Map<String, Team> getTeams() {
        return mTeams;
    }

    /**
     * Returns the matches.
     * @return The map of matches.
     */
    Map<String, Match> getMatches() {
        return mMatches;
    }
}
