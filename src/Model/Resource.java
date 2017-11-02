package Model;

import java.util.Date;

public class Resource {
    String name;
    String type;
    int parent;
    Date lastUsed;

    public Resource(String name, String type, int parent, Date lastUsed) {
        this.name = name;
        this.type = type;
        this.parent = parent;
        this.lastUsed = lastUsed;
    }

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

    public Date getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }

    @Override
    public String toString() {
        return "Resource{" + name + " / " + parent + " / " + lastUsed + "}";
    }
}
