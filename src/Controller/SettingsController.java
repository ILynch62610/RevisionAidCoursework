package Controller;

import Model.ConfigurationService;
import Views.LayoutGenerator;
import Views.SettingsView;

public class SettingsController {

    public static void saveAndClose() {
        ConfigurationService.setConfigurations(Main.configurations,Main.database);
        LayoutGenerator.root.setEffect(null);
        SettingsView.settingsStage.close();
    }
}
