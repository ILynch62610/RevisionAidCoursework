package Controller;

import Views.LayoutGenerator;
import Views.SettingsView;

public class SettingsController {

    public static void closeSettings() {

        System.out.println("Bye bye blur!");

        LayoutGenerator.root.setEffect(null);

        SettingsView.settingsStage.close();

    }
}
