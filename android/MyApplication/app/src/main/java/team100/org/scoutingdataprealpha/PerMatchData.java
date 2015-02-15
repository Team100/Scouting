package team100.org.scoutingdataprealpha;

/**
 * Created by brendaporter on 2/14/15.
 */
public class PerMatchData {
    private boolean mGotNoodle;
    private int mMatchNum;
    private int mTeamNum;

    public PerMatchData(int teamNum, int matchNum) {

    }

    public int getmMatchNum() {
        return mMatchNum;
    }

    public int getmTeamNum() {
        return mTeamNum;
    }

    public boolean ismGotNoodle() {
        return mGotNoodle;
    }

    public void setmGotNoodle(boolean mGotNoodle) {
        this.mGotNoodle = mGotNoodle;
    }
}
