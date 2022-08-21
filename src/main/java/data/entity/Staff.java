package data.entity;

public class Staff {
    private String name;
    private String depName;
    private int attDay;
    private int late;
    private int actAttDay;
    private int leaveEarly;
    private int onMiss;
    private int offMiss;
    private int noWork;

    public Staff(String name, String depName, int attDay, int late, int actAttDay, int leaveEarly, int onMiss, int offMiss, int noWork) {
        this.name = name;
        this.depName = depName;
        this.attDay = attDay;
        this.late = late;
        this.actAttDay = actAttDay;
        this.leaveEarly = leaveEarly;
        this.onMiss = onMiss;
        this.offMiss = offMiss;
        this.noWork = noWork;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public int getAttDay() {
        return attDay;
    }

    public void setAttDay(int attDay) {
        this.attDay = attDay;
    }

    public int getLate() {
        return late;
    }

    public void setLate(int late) {
        this.late = late;
    }

    public int getActAttDay() {
        return actAttDay;
    }

    public void setActAttDay(int actAttDay) {
        this.actAttDay = actAttDay;
    }

    public int getLeaveEarly() {
        return leaveEarly;
    }

    public void setLeaveEarly(int leaveEarly) {
        this.leaveEarly = leaveEarly;
    }

    public int getOnMiss() {
        return onMiss;
    }

    public void setOnMiss(int onMiss) {
        this.onMiss = onMiss;
    }

    public int getOffMiss() {
        return offMiss;
    }

    public void setOffMiss(int offMiss) {
        this.offMiss = offMiss;
    }

    public int getNoWork() {
        return noWork;
    }

    public void setNoWork(int noWork) {
        this.noWork = noWork;
    }
}
