package Model;

public class ProgressEntry {
    String item;
    int timesTested;
    int correctNo;
    String type;
    String date;
    Boolean status;

    public ProgressEntry(String item, int timesTested, int correctNo, String type, String date, Boolean status) {
        this.item = item;
        this.timesTested = timesTested;
        this.correctNo = correctNo;
        this.type = type;
        this.date = date;
        this.status = status;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getTimesTested() {
        return timesTested;
    }

    public void setTimesTested(int timesTested) {
        this.timesTested = timesTested;
    }

    public int getCorrectNo() {
        return correctNo;
    }

    public void setCorrectNo(int correctNo) {
        this.correctNo = correctNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        if(status) {
            return "Correct";
        } else {
            return "False";
        }
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
