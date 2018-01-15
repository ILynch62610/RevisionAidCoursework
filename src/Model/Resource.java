package Model;

import java.util.ArrayList;
import java.util.Date;

public class Resource {
    int iD;
    String name;
    String type;
    int parent;
    String lastUsed;

    public Resource(int iD, String name, String type, int parent, String lastUsed) {
        this.iD = iD;
        this.name = name;
        this.type = type;
        this.parent = parent;
        this.lastUsed = lastUsed;
    }

    public int getiD() {return iD;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(String lastUsed) {
        this.lastUsed = lastUsed;
    }

    @Override
    public String toString() {
        return "Resource{" + name + " / " + parent + " / " + lastUsed + "}";
    }

    public static ArrayList<String> getChildren() {
        ArrayList<String> children = new ArrayList<>();
        return children;
    }
}
