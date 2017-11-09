package Model;

import java.util.Date;

public class Sentence {
    int iD;
    String content;
    int correctNo;
    int appearanceNo;
    float percentage;
    Date lastDate;
    boolean lastStatus;
    int parent;

    public Sentence(int iD, String content, int correctNo, int appearanceNo, float percentage, Date lastDate, boolean lastStatus, int parent) {
        this.iD = iD;
        this.content = content;
        this.correctNo = correctNo;
        this.appearanceNo = appearanceNo;
        calculatePercent();
        this.lastDate = lastDate;
        this.lastStatus = lastStatus;
        this.parent = parent;
    }

    public int getiD() {return iD;}

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCorrect() {
        return correctNo;
    }

    public void setCorrect(int correctNo) {
        this.correctNo = correctNo;
    }

    public int getAppear() {
        return appearanceNo;
    }

    public void setAppear(int appearanceNo) {
        this.appearanceNo = appearanceNo;
    }

    public float getPercent() {
        return percentage;
    }

    public void calculatePercent() {
        this.percentage = (getCorrect() / getAppear())*100;
    }

    public Date getDate() {
        return lastDate;
    }

    public void setDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public boolean getStatus() {
        return lastStatus;
    }

    public void setStatus(boolean lastStatus) {
        this.lastStatus = lastStatus;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String createBlanks() {
        String withBlanks = "";
        return withBlanks;
    }

    @Override
    public String toString() {
        return "Resource{" + content + " / " + parent + "}";
    }
}
