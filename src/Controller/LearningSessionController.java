package Controller;

import Model.*;
import Views.HomeView;
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
import java.util.Arrays;


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

    public static void selectItem(Rectangle rect, Text text, StackPane pane) {
        if (rect.getFill()== javafx.scene.paint.Color.GRAY){
            rect.setFill(javafx.scene.paint.Color.CORAL);
            LearningSessionView.numberSelected--;
            LearningSessionView.selectedItems.remove(text.getText());
            LearningSessionView.selectedPanes.remove(pane);
        }
        else if (LearningSessionView.numberSelected < 2) {
            rect.setFill(Color.GRAY);
            LearningSessionView.numberSelected++;
            LearningSessionView.selectedItems.add(text.getText());
            LearningSessionView.selectedPanes.add(pane);
        }
    }

    public static void checkMatch(Resource r) {
        Term term1 = null;
        Term term2 = null;
        Definition def1 = null;
        Definition def2 = null;
        ArrayList<Definition> definitions = new ArrayList<>();
        if (LearningSessionView.selectedItems.size() > 1) {
            for (Term t : LearningSession.sessionTerms) {
                for (int i = 0; i < t.getAnswers(Main.database).size(); i++) {
                    definitions.add(t.getAnswers(Main.database).get(i));
                }
                if (LearningSessionView.selectedItems.size() > 1) {
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
            }
            if ((term1 == null && term2 == null) || (def1 == null && def2 == null)) {
                if (term1 == null) {
                    def1.setAppear(def1.getAppear() + 1);
                    def2.setAppear(def2.getAppear() + 1);
                    def1.calculatePercent();
                    def2.calculatePercent();
                    def1.setDateToday();
                    def2.setDateToday();
                    def1.setStatus(false);
                    def2.setStatus(false);
                    DefinitionService.save(def1, Main.database);
                    DefinitionService.save(def2, Main.database);
                } else {
                    for (Definition d : term1.getAnswers(Main.database)) {
                        if (Arrays.asList(LearningSessionView.spaceFills).contains(d.getDesc())) {
                            d.setAppear(d.getAppear() + 1);
                            d.calculatePercent();
                            d.setDateToday();
                            d.setStatus(false);
                            DefinitionService.save(d, Main.database);
                        }
                    }
                    for (Definition d : term2.getAnswers(Main.database)) {
                        if (Arrays.asList(LearningSessionView.spaceFills).contains(d.getDesc())) {
                            d.setAppear(d.getAppear() + 1);
                            d.calculatePercent();
                            d.setDateToday();
                            d.setStatus(false);
                            DefinitionService.save(d, Main.database);
                        }
                    }
                }
                wrongAns();
            } else if (term1 == null) {
                if (def1.getParent() == term2.getiD()) {
                    LearningSession.correctAns++;
                    def1.setAppear(def1.getAppear() + 1);
                    def1.setCorrect(def1.getCorrect() + 1);
                    def1.calculatePercent();
                    def1.setDateToday();
                    def1.setStatus(true);
                    DefinitionService.save(def1, Main.database);
                    LearningSessionView.learningPane.getChildren().removeAll(LearningSessionView.selectedPanes);
                    LearningSessionView.selectedItems.clear();
                    LearningSessionView.selectedPanes.clear();
                    LearningSessionView.numberSelected = 0;
                    displayAttempts();
                    if (LearningSessionView.learningPane.getChildren().size() == 1) {
                        Main.stage.setScene(ResourceView.view(r));
                        LearningSessionView.timeline.stop();
                    }
                } else {
                    def1.setAppear(def1.getAppear() + 1);
                    def1.calculatePercent();
                    def1.setDateToday();
                    def1.setStatus(false);
                    DefinitionService.save(def1, Main.database);
                    for (Definition d : term2.getAnswers(Main.database)) {
                        if (Arrays.asList(LearningSessionView.spaceFills).contains(d.getDesc())) {
                            d.setAppear(d.getAppear() + 1);
                            d.calculatePercent();
                            d.setDateToday();
                            d.setStatus(false);
                            DefinitionService.save(d, Main.database);
                        }
                    }
                    wrongAns();
                }
            } else {
                if (def2.getParent() == term1.getiD()) {
                    LearningSession.correctAns++;
                    displayAttempts();
                    def2.setAppear(def2.getAppear() + 1);
                    def2.setCorrect(def2.getCorrect() + 1);
                    def2.calculatePercent();
                    def2.setDateToday();
                    def2.setStatus(true);
                    DefinitionService.save(def2, Main.database);
                    LearningSessionView.learningPane.getChildren().removeAll(LearningSessionView.selectedPanes);
                    LearningSessionView.selectedItems.clear();
                    LearningSessionView.selectedPanes.clear();
                    LearningSessionView.numberSelected = 0;
                    if (LearningSessionView.learningPane.getChildren().size() == 1) {
                        Main.stage.setScene(ResourceView.view(r));
                        LearningSessionView.timeline.stop();
                    }
                } else {
                    def2.setAppear(def2.getAppear() + 1);
                    def2.calculatePercent();
                    def2.setDateToday();
                    def2.setStatus(false);
                    DefinitionService.save(def2, Main.database);
                    for (Definition d : term1.getAnswers(Main.database)) {
                        if (Arrays.asList(LearningSessionView.spaceFills).contains(d.getDesc())) {
                            d.setAppear(d.getAppear() + 1);
                            d.calculatePercent();
                            d.setDateToday();
                            d.setStatus(false);
                            DefinitionService.save(d, Main.database);
                        }
                    }
                    wrongAns();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Something's Wrong");
            alert.setHeaderText("Please select 2 items and then click the match button.");
            alert.show();
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
        Sentence sentence = LearningSessionView.blankThing.getSentenceObject();

        if (answer.equals(blank.toLowerCase()) || answer.equals(blank) || (answer+".").equals(blank.toLowerCase()) || (answer+".").equals(blank) || (answer+",").equals(blank.toLowerCase()) || (answer+",").equals(blank)){
            LearningSession.correctAns +=1;
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Congratulations!");
            alert.setHeaderText("Well Done, that was the correct answer! Click ok to move on.");
            alert.showAndWait();
            sentence.setAppear(sentence.getAppear()+1);
            sentence.setCorrect(sentence.getCorrect()+1);
            sentence.setDateToday();
            sentence.setStatus(true);
            SentenceService.save(sentence,Main.database);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sorry");
            alert.setHeaderText("Unfortunately that wasn't the correct answer. The correct answer was " + blank + ". Better luck next time! Click ok to move on.");
            alert.showAndWait();
            sentence.setAppear(sentence.getAppear()+1);
            sentence.setDateToday();
            sentence.setStatus(false);
            SentenceService.save(sentence,Main.database);
        }

        number++;
        displayCorrect();
        LearningSessionView.blanksLabel.setText("Blank " + Integer.toString(number+1));

        if (number == LearningSessionView.blankThing.blanks.size()) {

            LearningSessionView.sentenceNumber++;

            if (LearningSessionView.sentenceNumber >= LearningSession.sessionSentences.size()) {
                Main.stage.setScene(ResourceView.view(r));
                LearningSessionView.sentenceNumber = 0;
                LearningSessionView.timeline.stop();
                return;
            }

            LearningSessionView.blankThing = LearningSession.sessionSentences.get(LearningSessionView.sentenceNumber).createBlanks();
            LearningSessionView.text.setText(LearningSessionView.blankThing.sentence);
            number = 0;
            LearningSessionView.blanksLabel.setText("Blank " + Integer.toString(number+1));
        }

    }
}
