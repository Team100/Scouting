package com.example.brendaporter.filereadex;

/**
 * Created by brendaporter on 2/14/15.
 */
public class Team {private int teamNum;
    private int totalScore;
    private int totalTotesScore;
    private int totalContainerScore;
    private int totalNoodleScore;
    private int totalYellowCards;
    private int totalRedCards;
    private int totalPenalties;

    public Team (int teamNum) {
        this.teamNum = teamNum;
    }

    public void addToteScore(int score) {
        totalScore+=score;
        totalTotesScore+=score;
    }

    public void addContainerScore(int score) {
        totalScore+=score;
        totalContainerScore+=score;
    }

    public void addNoodleScore(int score) {
        totalScore+=score;
        totalNoodleScore+=score;
    }

    public void addPenalty(int deductedPoints) {
        totalScore-=deductedPoints;
        totalPenalties-=deductedPoints;
    }

    public void addYellowCard(int deductedPoints) {
        totalYellowCards++;
        totalScore-=deductedPoints;
        totalPenalties-=deductedPoints;
    }

    public void addRedCard(int deductedPoints) {
        totalRedCards++;
        totalScore-=deductedPoints;
        totalPenalties-=deductedPoints;
    }

    public int getTeamNum() {
        return teamNum;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public int getTotalTotesScore() {
        return totalTotesScore;
    }

    public int getTotalContainerScore() {
        return totalContainerScore;
    }

    public int getTotalNoodleScore() {
        return totalNoodleScore;
    }

    public int getTotalYellowCards() {
        return totalYellowCards;
    }

    public int getTotalRedCards() {
        return totalRedCards;
    }

    public int getTotalPenalties() {
        return totalPenalties;
    }
}
