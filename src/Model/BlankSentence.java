package Model;

import java.util.ArrayList;

public class BlankSentence {

    public String sentence;
    public ArrayList<String> blanks;
    public Sentence original;

    public BlankSentence() {
        blanks = new ArrayList<>();
    }
    public Sentence getSentenceObject() { return original; }
}
