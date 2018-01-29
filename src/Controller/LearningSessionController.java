package Controller;

import javafx.scene.control.Alert;

public class LearningSessionController {
    public static void outOfTime() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Out of Time!!");
        alert.setHeaderText("You're out of time. Click ok to move to the next question.");
        alert.show();
    }

    public static void checkBlank(String blank, String answer, int no) {
        if (answer.equals(blank)){
            LearningSession.correctAns +=1;
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Congratulations!");
            alert.setHeaderText("Well Done, that was the correct answer! Click ok to move on.");
            alert.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sorry");
            alert.setHeaderText("Unfortunately that wasn't the correct answer. The correct answer was " + blank + ". Better luck next time! Click ok to move on.");
            alert.show();
        }
    }
}
