package Controller;

import Model.Definition;
import Model.Resource;
import Model.Term;
import Views.LearningSessionView;
import Views.ResourceView;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;


public class LearningSessionController {

    public static int number;

    public static void outOfTime(Resource r) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Out of Time!!");
        alert.setHeaderText("You're out of time. Click ok to return to the resource menu.");
        alert.setOnCloseRequest(cr -> {
            Main.stage.setScene(ResourceView.view(r));
        });
        alert.show();
    }

    public static void displayCorrect() {
        LearningSessionView.correctPane.getChildren().clear();
        for(int i=0;i<=LearningSession.correctAns-1;i++){
            ImageView newPoint = new ImageView();
            newPoint.setImage(new Image("images/correcticon.png",40,40,true,true));
            LearningSessionView.correctPane.getChildren().add(newPoint);
        }
    }

    public static void displayAttempts() {
        displayCorrect();
        for(int i=0;i<=LearningSession.attempsNo-1;i++){
            ImageView newPoint = new ImageView();
            newPoint.setImage(new Image("images/wrong.png",40,40,true,true));
            LearningSessionView.correctPane.getChildren().add(newPoint);
        }
    }

    public static void selectItem(Rectangle rect, Text text) {
        if (rect.getFill()== javafx.scene.paint.Color.GRAY){
            rect.setFill(javafx.scene.paint.Color.CORAL);
            LearningSessionView.numberSelected--;
            LearningSessionView.selectedItems.remove(text.getText());
        }
        else if (LearningSessionView.numberSelected < 2) {
            rect.setFill(Color.GRAY);
            LearningSessionView.numberSelected++;
            LearningSessionView.selectedItems.add(text.getText());
        }
    }

    public static void checkMatch() {
        Term term1 = null;
        Term term2 = null;
        Definition def1 = null;
        Definition def2 = null;
        ArrayList<Definition> definitions = new ArrayList<>();
        for (Term t : LearningSession.sessionTerms) {
            for(int i=0; i<t.getAnswers(Main.database).size();i++) {
                definitions.add(t.getAnswers(Main.database).get(i));
            }
            if (LearningSessionView.selectedItems.get(0).equals(t.getContent())) {
                term1 = t;
            } else if (LearningSessionView.selectedItems.get(1).equals(t.getContent())) {
                term2 = t;
            }
        }
        for (Definition d : definitions) {
            if (LearningSessionView.selectedItems.get(0).equals(d.getDesc())) {
                def1 = d;
            } else if (LearningSessionView.selectedItems.get(1).equals(d.getDesc())) {
                def2 = d;
            }
        }
        if ((term1 == null && term2 == null) || (def1 == null && def2 == null)) {
            wrongAns();
        } else if(term1 == null) {
            System.out.println(def2.getParent());
            System.out.println(term1.getiD());
            if (def2.getParent() == term1.getiD()) {
                LearningSession.correctAns++;
                displayAttempts();
            } else {
                wrongAns();
            }
        } else {
            if (def1.getParent() == term2.getiD()) {
                LearningSession.correctAns++;
                displayAttempts();
            } else {
                wrongAns();
            }
        }
    }

    public static void wrongAns() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Wrong Answer");
        alert.setHeaderText("Sorry, that isn't quite right. You've used up one attempt.");
        alert.show();
        LearningSession.attempsNo++;
        displayAttempts();
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
