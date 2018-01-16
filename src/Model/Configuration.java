package Model;

public class Configuration {
    String timer;
    String name;
    String items;
    String background;

    public Configuration(String timer, String name, String items, String background) {
        this.timer = timer;
        this.name = name;
        this.items = items;
        this.background = background;
    }

    public String getTimer() {return timer;}

    public void setTimer(String timer) {this.timer = timer;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getItems() {return items;}

    public void setItems(String items) {this.items = items;}

    public String getBackground() {return background;}

    public void setBackground(String background) {this.background = background;}
}
