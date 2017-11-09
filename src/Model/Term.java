package Model;

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
}
