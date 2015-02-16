package argvarg.net.datastructuretest;

/**
 * Created by Fredrik on 2/11/2015.
 */
public class Match {
    private Team red1;
    private Team red2;
    private Team red3;
    private Team blue1;
    private Team blue2;
    private Team blue3;
    private Team[] redAlliance = {red1, red2, red3};
    private Team[] blueAlliance = {blue1, blue2, blue3};
    private int redScore;
    private int blueScore;

    public Match(int red1, int red2, int red3, int blue1, int blue2, int blue3) {
        this.red1 = new Team(red1);
        this.red2 = new Team(red2);
        this.red3 = new Team(red3);
        this.blue1 = new Team(blue1);
        this.blue2 = new Team(blue2);
        this.blue3 = new Team(blue3);
    }

    /**
     *
     * @param team the alliance team # (1, 2, or 3) that scored the points
     * @param points the amount of points scored
     */
    public void addRedToteScore(int team, int points) {
        redScore+=points;
        redAlliance[team].addToteScore(points);
    }

    /**
     *
     * @param team the alliance team # (1, 2, or 3) that scored the points
     * @param points the amount of points scored
     */
    public void addRedContainerScore(int team, int points) {
        redScore+=points;
        redAlliance[team].addContainerScore(points);
    }

    /**
     *
     * @param team the alliance team # (1, 2, or 3) that scored the points
     * @param points the amount of points scored
     */
    public void addRedNoodleScore(int team, int points) {
        redScore+=points;
        redAlliance[team].addNoodleScore(points);
    }

    /**
     *
     * @param team the alliance team # (1, 2, or 3) that got the penalty
     * @param deductedPoints the amount of points to deduct
     */
    public void addRedPenalty(int team, int deductedPoints) {
        redScore-=deductedPoints;
        redAlliance[team].addPenalty(deductedPoints);
    }

    /**
     *
     * @param team the alliance team # (1, 2, or 3) that got the penalty
     * @param deductedPoints the amount of points to deduct
     */
    public void addRedYellowCard(int team, int deductedPoints) {
        redScore-=deductedPoints;
        redAlliance[team].addToteScore(deductedPoints);
    }

    /**
     *
     * @param team the alliance team # (1, 2, or 3) that got the penalty
     * @param deductedPoints the amount of points to deduct
     */
    public void addRedRedCard(int team, int deductedPoints) {
        redScore-=deductedPoints;
        redAlliance[team].addRedCard(deductedPoints);
    }

    /**
     *
     * @param team the alliance team # (1, 2, or 3) that got the penalty
     * @param deductedPoints the amount of points to deduct
     */
    public void addBluePenalty(int team, int deductedPoints) {
        blueScore-=deductedPoints;
        blueAlliance[team].addPenalty(deductedPoints);
    }

    /**
     *
     * @param team the alliance team # (1, 2, or 3) that got the penalty
     * @param deductedPoints the amount of points to deduct
     */
    public void addBlueYellowCard(int team, int deductedPoints) {
        blueScore-=deductedPoints;
        blueAlliance[team].addToteScore(deductedPoints);
    }

    /**
     *
     * @param team the alliance team # (1, 2, or 3) that got the penalty
     * @param deductedPoints the amount of points to deduct
     */
    public void addBlueRedCard(int team, int deductedPoints) {
        blueScore -= deductedPoints;
        blueAlliance[team].addRedCard(deductedPoints);
    }

    /**
     *
     * @param team the alliance team # (1, 2, or 3) that scored the points
     * @param points the amount of points scored
     */
    public void addBlueToteScore(int team, int points) {
        blueScore+=points;
        blueAlliance[team].addToteScore(points);
    }

    /**
     *
     * @param team the alliance team # (1, 2, or 3) that scored the points
     * @param points the amount of points scored
     */
    public void addBlueContainerScore(int team, int points) {
        blueScore+=points;
        blueAlliance[team].addContainerScore(points);
    }

    /**
     *
     * @param team the alliance team # (1, 2, or 3) that scored the points
     * @param points the amount of points scored
     */
    public void addBlueNoodleScore(int team, int points) {
        blueScore+=points;
        blueAlliance[team].addNoodleScore(points);
    }

    public Team getRed1() {
        return red1;
    }

    public Team getRed2() {
        return red2;
    }

    public Team getRed3() {
        return red3;
    }

    public Team getBlue1() {
        return blue1;
    }

    public Team getBlue2() {
        return blue2;
    }

    public Team getBlue3() {
        return blue3;
    }

    public Team[] getRedAlliance() {
        return redAlliance;
    }

    public Team[] getBlueAlliance() {
        return blueAlliance;
    }

    public int getRedScore() {
        return redScore;
    }

    public int getBlueScore() {
        return blueScore;
    }
}
