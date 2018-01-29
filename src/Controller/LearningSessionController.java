package Controller;

import Model.Resource;
import Views.LearningSessionView;
import Views.ResourceView;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class LearningSessionController {

    public static int number;

    public static void outOfTime(Resource r) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Out of Time!!");
        alert.setHeaderText("You're out of time. Click ok to move to return to the resource menu.");
        alert.setOnCloseRequest(cr -> {
            Main.stage.setScene(ResourceView.view(r));
        });
        alert.show();
    }

    public static void displayCorrect() {
        LearningSessionView.correctPane.getChildren().clear();
        for(int i=0;i<=LearningSession.correctAns-1;i++){
            ImageView newPoint = new ImageView();
            newPoint.setImage(new Image("images/settings.png"));
            LearningSessionView.correctPane.getChildren().add(newPoint);
        }
    }

    public static void checkBlank(String answer, Resource r) {

        String blank = LearningSessionView.blankThing.blanks.get(number);

        if (answer.equals(blank)){
            LearningSession.correctAns +=1;
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Congratulations!");
            alert.setHeaderText("Well Done, that was the correct answer! Click ok to move on.");
            alert.showAndWait();

        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sorry");
            alert.setHeaderText("Unfortunately that wasn't the correct answer. The correct answer was " + blank + ". Better luck next time! Click ok to move on.");
            alert.showAndWait();
        }

        number++;
        displayCorrect();
        LearningSessionView.blanksLabel.setText("Blank " + Integer.toString(number+1));

        if (number == LearningSessionView.blankThing.blanks.size()) {

            LearningSessionView.sentenceNumber++;

            if (LearningSessionView.sentenceNumber >= LearningSession.sessionSentences.size()) {
                Main.stage.setScene(ResourceView.view(r));
                return;
            }

            LearningSessionView.blankThing = LearningSession.sessionSentences.get(LearningSessionView.sentenceNumber).createBlanks();
            LearningSessionView.text.setText(LearningSessionView.blankThing.sentence);
            number = 0;
            LearningSessionView.blanksLabel.setText("Blank " + Integer.toString(number+1));
        }

    }
}
