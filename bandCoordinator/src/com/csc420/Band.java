package com.csc420;

public class Band implements Comparable<Band>{

    String bandName;
    Float setTime;

    public Band(String name, float time){
        bandName = name;
        setTime = time;
    }
    public Band(String name){
        bandName = name;
    }

    public void setBandName(String name){
        bandName = name;
    }
    public void setSetTime(float time){
        setTime = time;
    }
    public String getBandName(){
        return bandName;
    }
    public Float getSetTime(){
        return setTime;
    }

    @Override
    public int compareTo(Band o) {
        return 0;
    }
    @Override
    public String toString(){
        return bandName + " has a set time of " + setTime + " minutes";
    }
}
