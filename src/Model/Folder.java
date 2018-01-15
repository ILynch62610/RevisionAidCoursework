package Model;

import java.util.ArrayList;

public class Folder {
    int iD;
    String name;
    String icon;
    int parent;

    public Folder(int iD, String name, String icon, int parent) {
        this.iD = iD;
        this.name = name;
        this.icon = icon;
        this.parent = parent;
    }

    public int getiD() {return iD;}

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

    public ArrayList<ArrayList> getChildren(DatabaseConnection database) {
        ArrayList<Folder> childFolders = new ArrayList<Folder>();
        ArrayList<Folder> folders = new ArrayList<Folder>();
        ArrayList<Resource> childResources = new ArrayList<Resource>();
        ArrayList<Resource> resources = new ArrayList<Resource>();
        ArrayList<ArrayList> children = new ArrayList<ArrayList>();

        FolderService.selectAll(folders, database);
        for (Folder f : folders) {
            if (f.getParent() == this.getiD()) {
                childFolders.add(f);
            }
        }
        ResourceService.selectAll(resources, database);
        for (Resource r : resources) {
            if (r.getParent() == this.getiD()) {
                childResources.add(r);
            }
        }

        children.add(childFolders);
        children.add(childResources);

        return children;
    }
}
