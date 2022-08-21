package data.entity;

import java.sql.Time;

public class TimeTable {
    private int id;
    private String name;
    private int month;
    private int day;
    private Time onTime;
    private boolean morningOrAfternoon;
    private int type; //上班 0 早退 -1 迟到 1 旷工 2 休息 3 请假 4 未打卡 5 出差 6 其他 7

    public TimeTable(int id, String name, int month, int day, Time onTime, boolean morningOrAfternoon, int type) {
        this.id = id;
        this.name = name;
        this.month = month;
        this.day = day;
        this.onTime = onTime;
        this.morningOrAfternoon = morningOrAfternoon;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Time getOnTime() {
        return onTime;
    }

    public void setOnTime(Time onTime) {
        this.onTime = onTime;
    }

    public boolean isMorningOrAfternoon() {
        return morningOrAfternoon;
    }

    public void setMorningOrAfternoon(boolean morningOrAfternoon) {
        this.morningOrAfternoon = morningOrAfternoon;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
