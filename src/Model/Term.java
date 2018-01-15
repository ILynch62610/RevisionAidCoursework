package Model;

import java.util.ArrayList;

public class Term {
    int iD;
    String content;
    int parent;

    public Term(int iD, String content, int parent) {
        this.iD = iD;
        this.content = content;
        this.parent = parent;
    }

    public int getiD() {return iD;}

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Term{" + content + " / " + parent + "}";
    }

    public ArrayList<Definition> getAnswers(DatabaseConnection database) {
        ArrayList<Definition> answers = new ArrayList<Definition>();
        ArrayList<Definition> definitions = new ArrayList<Definition>();

        DefinitionService.selectAll(definitions, database);
        for (Definition d : definitions) {
            if (d.getParent() == this.getiD()) {
                answers.add(d);
            }
        }

        return answers;
    }
}
