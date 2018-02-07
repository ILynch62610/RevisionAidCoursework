package Model;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Sentence {
    int iD;
    String content;
    int correctNo;
    int appearanceNo;
    float percentage;
    String lastDate;
    boolean lastStatus;
    int parent;

    public Sentence(int iD, String content, int correctNo, int appearanceNo, float percentage, String lastDate, boolean lastStatus, int parent) {
        checkDate();
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
        if (correctNo > appearanceNo) {
            correctNo = appearanceNo;
        }
        this.percentage = (getCorrect() / getAppear())*100;
    }

    public String getDate() {
        return lastDate;
    }

    public void setDate(String lastDate) {
        checkDate();
        this.lastDate = lastDate;
    }

    public void setDateToday() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date today = Calendar.getInstance().getTime();
        this.lastDate = formatter.format(today);
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
        if(lastDate != null && !lastDate.toLowerCase().equals("null")) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date today = Calendar.getInstance().getTime();
            Date date = null;
            try {
                date = formatter.parse(lastDate);
                if (date != null && date.after(today)) {
                    lastDate = today.toString();
                }
            } catch (ParseException parp) {
                System.out.println("Can't convert date: " + parp.getMessage());
            }
        }
    }

    public BlankSentence createBlanks() {

        BlankSentence result = new BlankSentence();

        String[] nonBlanks = {"this", "the", "and", "or", "so", "because", "then", "a", "it", "on", "in", "was", "is", "that", "to", "than", "its", "it's", "from", "were", "with", "of", "at", "too", ".", ",", "!","&", "-"};
        ArrayList<String> nonBlanksList = new ArrayList<>(Arrays.asList(nonBlanks));

        String[] words = this.getContent().split(" ");
        int noWords = words.length;
        int[] positions = new int[2];

        for (int i = 0; i < 2; i++) {
            Boolean found = false;
            while (!found) {
                Random rand = new Random();
                int pos = rand.nextInt(noWords);
                if (!nonBlanksList.contains(words[pos].toLowerCase())) {
                    positions[i] = pos;
                    found = true;
                }
            }
        }
        if (positions[0] > positions[1]) {
            int temp = positions[1];
            positions[1] = positions[0];
            positions[0] = temp;
        }
        for (int i=0; i<2; i++) {
            result.blanks.add(words[positions[i]]);
            words[positions[i]] = "_____";
        }

        String withBlanks = String.join(" ",words);
        result.sentence = withBlanks;
        result.original = this;
        return result;
    }

    @Override
    public String toString() {
        return "Sentence{" + content + " / " + parent + "}";
    }
}
