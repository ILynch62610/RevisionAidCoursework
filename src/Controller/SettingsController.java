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
        if (learningValue) {
            if (learningTime.length() < 3) {
                if(learningTime.length() < 2) {
                    time = "00" + learningTime;
                } else {
                    time = "0" + learningTime;
                }
            } else {
                time = learningTime;
            }
        }
        else {
            time = "000";
        }
        if (studyValue) {
            if (studyTime.length() < 3) {
                if (studyTime.length() < 2) {
                    time = time + "00" + studyTime;
                } else {
                    time = time + "0" + studyTime;
                }
            } else {
                time = time + studyTime;
            }
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
