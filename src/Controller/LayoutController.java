package Controller;

import Model.Configuration;
import Views.HomeView;
import Views.ProgressView;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class LayoutController {
    public static void changeToHomeView(ActionEvent ae) {
        Stage stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
        stage.setScene(HomeView.view());
    }
    public static void changeToProgressView(ActionEvent ae) {
        Stage stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
        stage.setScene(ProgressView.view());
    }
    public static String getBackgroundColour() {
        String background = "220220220";
        for (Configuration c : Main.configurations) {
            if (c.getSettingName().equals("Background")) {
                System.out.println("FOUND COLOUR: " + c.getSettingValue());
                background = c.getSettingValue();
            }
        }
        return background;
    }
    public static String getRed() {
        return getBackgroundColour().substring(0,3);
    }
    public static String getGreen() {
        return getBackgroundColour().substring(3,6);
    }
    public static String getBlue() {
        return getBackgroundColour().substring(6,9);
    }
}
