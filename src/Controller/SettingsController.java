package Controller;

import Model.Configuration;
import Model.ConfigurationService;
import Views.LayoutGenerator;
import Views.SettingsView;

public class SettingsController {
    public static void saveBackground(String r, String g, String b) {
        for (Configuration c : Main.configurations) {
            if(c.getSettingName().equals("Background")) {
                System.out.println("C!");
                String backgroundColour = r+g+b;
                c.setSettingValue(backgroundColour);
            }
        }

        for (Configuration c : Main.configurations) {
            System.out.println(c);
        }

        saveAndClose();
    }

    public static void saveAndClose() {
        ConfigurationService.setConfigurations(Main.configurations,Main.database);
        LayoutGenerator.root.setEffect(null);
        SettingsView.settingsStage.close();
    }
}
