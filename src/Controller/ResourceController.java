package Controller;

import Model.Resource;
import Views.LearningSessionView;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;


public class ResourceController {
    public static void changeToLearnView(ActionEvent ae, Resource r){
        Stage stage = (Stage) ((Node)ae.getSource()).getScene().getWindow();
        stage.setScene(LearningSessionView.view(r,"Learn"));
    }
    public static void changeToCardsView(ActionEvent ae, Resource r){
        Stage stage = (Stage) ((Node)ae.getSource()).getScene().getWindow();
        stage.setScene(LearningSessionView.view(r,"Cards"));
    }
    public static void changeToBlanksView(ActionEvent ae, Resource r){
        Stage stage = (Stage) ((Node)ae.getSource()).getScene().getWindow();
        stage.setScene(LearningSessionView.view(r,"Blanks"));
    }
    public static void changeToQuizView(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("COMING SOON");
        alert.setHeaderText("This function has not been finished yet but will be coming soon... watch this space!");
        alert.showAndWait();
    }
}
