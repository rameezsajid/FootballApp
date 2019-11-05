package com.rameezsajid.sportsapplication.Model;

public class OfficialsItem {
    private String mName;
    private String mType;


    public OfficialsItem(String name, String type) {
        mName = name;
        mType = type;
    }

    public String getName() {
        return mName;
    }

    public String getType() {
        return mType;
    }

}
