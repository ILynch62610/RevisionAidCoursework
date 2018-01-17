package Controller;

import Model.Folder;
import Views.InnerFolderView;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;



public class HomeController {
    public static void changeToInnerFolderView(ActionEvent ae, Folder f) {
        Stage stage = (Stage) ((Node)ae.getSource()).getScene().getWindow();
        stage.setScene(InnerFolderView.view(f));
    }
}
