package Model;

public class FolderItem {
    String name;
    String type;
    String date;
    int iD;

    public FolderItem(String name, String type, String date, int iD) {
        this.name = name;
        this.type = type;
        this.date = date;
        this.iD = iD;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getType() {return type;}

    public void setType(String type) {this.type = type;}

    public String getDate() {return date;}

    public void setDate(String date) {this.date = date;}

    public int getiD() {return iD;}

    public void setiD(int iD) {this.iD = iD;}
}
