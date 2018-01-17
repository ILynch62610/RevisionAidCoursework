package Model;

public class Configuration {
    String settingName;
    String settingValue;

    public Configuration(String settingName, String settingValue) {
        this.settingName = settingName;
        this.settingValue = settingValue;
    }

    public String getSettingName() {return settingName;}

    public void setSettingName(String settingName) {this.settingName = settingName;}

    public String getSettingValue() {return settingValue;}

    public void setSettingValue(String settingValue) {this.settingValue = settingValue;}
}
