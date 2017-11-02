package Model;

public class Term {
    String content;
    int parent;

    public Term(String content, int parent) {
        this.content = content;
        this.parent = parent;
    }

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
