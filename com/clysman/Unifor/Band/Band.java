package com.clysman.Unifor.Band;

public class Band {
    private String name;

    private String type;

    private int members;

    private double gain;

    private int showsQty;

    public Band(String name, String type, int members, double gain, int showsQty) {
        this.name = name;
        this.type = type;
        this.members = members;
        this.gain = gain;
        this.showsQty = showsQty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMembers() {
        return members;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    public double getGain() {
        return gain;
    }

    public void setGain(double gain) {
        this.gain = gain;
    }

    public int getShowsQty() {
        return showsQty;
    }

    public void setShowsQty(int showsQty) {
        this.showsQty = showsQty;
    }
}
