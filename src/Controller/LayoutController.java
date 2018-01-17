package Controller;

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
}
