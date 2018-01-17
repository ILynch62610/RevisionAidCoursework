package Controller;

import Model.Folder;
import Model.Resource;
import Views.InnerFolderView;
import Views.ResourceView;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class InnerFolderController {
    public static void changeToInnerFolderView(MouseEvent event, Folder f) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(InnerFolderView.view(f));
    }
    public static void changeToResourceView(MouseEvent event, Resource r) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(ResourceView.view(r));
    }
}
