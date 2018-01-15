package Model;


import java.util.ArrayList;
import java.util.Random;

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

    public String getDate() {
        return lastDate;
    }

    public void setDate(String lastDate) {
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

    public ArrayList<String> createBlanks() {
        ArrayList<String> blanksAndAnswers = new ArrayList<String>();

        String[] nonBlanks = {"this", "the", "and", "or", "so", "because", "then", "a", "it", "on", "in", "was", "is", "that", "to", "than", "its", "it's", "from", "were", "with", "of", "at", "too", ".", ",", "!","&", "-"};
        ArrayList<String> nonBlanksList = new ArrayList<String>();
        for(String s : nonBlanks) {
            nonBlanksList.add(s);
        }

        String[] words = this.getContent().split(" ");
        int noWords = words.length;

        for (int i = 0; i < 2; i++) {
            Boolean found = false;
            while (!found) {
                Random rand = new Random();
                int pos = rand.nextInt(noWords);
                if (!nonBlanksList.contains(words[pos].toLowerCase())) {
                    blanksAndAnswers.add(words[pos]);
                    words[pos] = "_____";
                    found = true;
                }
            }
        }

        String withBlanks = String.join(" ",words);
        blanksAndAnswers.add(withBlanks);
        return blanksAndAnswers;
    }

    @Override
    public String toString() {
        return "Sentence{" + content + " / " + parent + "}";
    }
}
