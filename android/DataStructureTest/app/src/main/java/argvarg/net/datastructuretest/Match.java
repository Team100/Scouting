package argvarg.net.datastructuretest;

/**
 * Created by Fredrik on 2/11/2015.
 */
public class Match {
    Team red1;
    Team red2;
    Team red3;
    Team blue1;
    Team blue2;
    Team blue3;
    Team[] redAlliance = {red1, red2, red3};
    Team[] blueAlliance = {blue1, blue2, blue3};

    public Match(int red1, int red2, int red3, int blue1, int blue2, int blue3) {
        this.red1 = new Team(red1);
        this.red2 = new Team(red2);
        this.red3 = new Team(red3);
        this.blue1 = new Team(blue1);
        this.blue2 = new Team(blue2);
        this.blue3 = new Team(blue3);
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
}
