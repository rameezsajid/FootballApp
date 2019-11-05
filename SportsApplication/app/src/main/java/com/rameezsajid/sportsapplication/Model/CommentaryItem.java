package com.rameezsajid.sportsapplication.Model;

public class CommentaryItem {
    private String mComment;
    private String mTime;


    public CommentaryItem(String comment, String time) {
        mComment = comment;
        mTime = time;
    }

    public String getComment() {
        return mComment;
    }

    public String getTime() {
        return mTime;
    }

}
