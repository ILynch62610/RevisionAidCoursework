package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Definition {
    int iD;
    String description;
    int correctNo;
    int appearanceNo;
    float percentage;
    String lastDate;
    boolean lastStatus;
    int parent;

    public Definition(int iD, String description, int correctNo, int appearanceNo, float percentage, String lastDate, boolean lastStatus, int parent) {
        checkDate();
        this.iD = iD;
        this.description = description;
        this.correctNo = correctNo;
        this.appearanceNo = appearanceNo;
        calculatePercent();
        this.lastDate = lastDate;
        this.lastStatus = lastStatus;
        this.parent = parent;
    }

    public int getiD() {return iD;}

    public String getDesc() {
        return description;
    }

    public void setDesc(String description) {
        this.description = description;
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

    public String getDate() {
        return lastDate;
    }

    public void setDate(String lastDate) {
        checkDate();
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

    private void checkDate() {
        if(lastDate != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date today = Calendar.getInstance().getTime();
            Date date = null;
            try {
                date = formatter.parse(lastDate);
                if (date.after(today)) {
                    lastDate = today.toString();
                }
            } catch (ParseException parp) {
                System.out.print("Can't convert date: " + parp.getMessage());
            }
        }
    }

    @Override
    public String toString() {
        return iD + ": " + description;
    }
}
