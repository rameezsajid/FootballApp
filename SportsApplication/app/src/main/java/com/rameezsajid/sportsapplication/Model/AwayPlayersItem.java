package com.rameezsajid.sportsapplication.Model;

public class AwayPlayersItem {
    private String mFirstName;
    private String mLastName;
    private int mShirt;
    private String mPosition;
    private Boolean mCaptain;
    private int mGoalsConceded;
    private int mGoalsScored;
    private int mAssists;
    private int mShotsOnGoal;
    private int mMinsPlayed;
    private int mSaves;
    private int mThrowIns;
    private int mGoalKicks;

    public AwayPlayersItem(String firstName, String lastName, int shirt, String position, Boolean captain, int goalsConceded, int goalsScored, int assists,
                           int shotsOnGoal, int minsPlayed, int saves, int throwIns, int goalKicks) {
        mFirstName = firstName;
        mLastName = lastName;
        mShirt = shirt;
        mPosition = position;
        mCaptain = captain;
        mGoalsConceded = goalsConceded;
        mGoalsScored = goalsScored;
        mAssists = assists;
        mShotsOnGoal = shotsOnGoal;
        mMinsPlayed = minsPlayed;
        mSaves = saves;
        mThrowIns = throwIns;
        mGoalKicks = goalKicks;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName(){
        return mLastName;
    }

    public int getShirt(){
        return mShirt;
    }

    public String getPosition(){
        return mPosition;
    }

    public Boolean getCaptain(){
        return mCaptain;
    }

    public int getGoalsConceded() {
        return mGoalsConceded;
    }

    public int getGoalsScored() {
        return mGoalsScored;
    }

    public int getAssists() {
        return mAssists;
    }

    public int getShotsOnGoal(){
        return mShotsOnGoal;
    }

    public int getMinsPlayed(){
        return mMinsPlayed;
    }

    public int getSaves(){
        return mSaves;
    }

    public int getThrowIns(){
        return mThrowIns;
    }

    public int getGoalKicks(){
        return mGoalKicks;
    }
}
