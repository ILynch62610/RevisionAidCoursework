package Controller;

import Model.Configuration;
import Model.ConfigurationService;
import Views.LayoutGenerator;
import Views.SettingsView;

public class SettingsController {
    public static void saveBackground(String r, String g, String b) {
        if (r.isEmpty()){
            r = "255";
        }
        if (g.isEmpty()){
            g = "255";
        }
        if (b.isEmpty()) {
            b = "255";
        }
        for (Configuration c : Main.configurations) {
            if(c.getSettingName().equals("Background")) {
                String backgroundColour = r+g+b;
                c.setSettingValue(backgroundColour);
            }
        }

        saveAndClose();
    }

    public static void saveAndClose() {
        ConfigurationService.setConfigurations(Main.configurations,Main.database);
        LayoutGenerator.root.setEffect(null);
        SettingsView.settingsStage.close();
    }
    public static void saveName(String name) {
        for (Configuration c : Main.configurations) {
            if(c.getSettingName().equals("Name")) {
                c.setSettingValue(name);
            }
        }

        saveAndClose();
    }
    public static void saveTimers(Boolean learningValue, String learningTime, Boolean studyValue, String studyTime) {
        String time = "";
        if (learningValue == true) {
            time = learningTime;
        }
        else {
            time = "000";
        }
        if (studyValue == true) {
            time = time + studyValue;
        }
        else {
            time = time + "000";
        }

        for (Configuration c : Main.configurations) {
            if(c.getSettingName().equals("Timer")) {
                c.setSettingValue(time);
            }
        }

        saveAndClose();
    }
}
