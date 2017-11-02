package Model;

import java.util.ArrayList;

public class Folder {
    String name;
    String icon;
    int parent;

    public Folder(String name, String icon, int parent) {
        this.name = name;
        this.icon = icon;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Folder{" + name + " / " + parent + "}";
    }
}
