package Controller;

import javafx.scene.control.Alert;

public class LearningSessionController {
    public static void outOfTime() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Out of Time!!");
        alert.setHeaderText("You're out of time. Click ok to move to the next question.");
        alert.show();
    }
}
