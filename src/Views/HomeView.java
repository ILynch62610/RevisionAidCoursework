package Views;

import Controller.HomeController;
import Controller.Main;
import Model.DatabaseConnection;
import Model.Folder;
import Model.FolderService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class HomeView {
    public static Scene view() {
        BorderPane root = LayoutGenerator.make("Welcome ...", true, true, false);
        Scene scene = new Scene(root, 1024, 768);

        //Allows scrolling
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        root.setCenter(scrollPane);

        //Creates TilePane (for folder view)
        TilePane tilePane = new TilePane();
        scrollPane.setContent(tilePane);
        tilePane.prefWidthProperty().bind(root.widthProperty());
        tilePane.prefHeightProperty().bind(scrollPane.heightProperty());
        tilePane.setHgap(100);
        tilePane.setVgap(80);
        tilePane.setPadding(new Insets(20));


        //Adding to FolderView
        int noTopFolders = Main.topFolders.size();
        for (int i=0; i<noTopFolders; i++) {                                              //Change loop to make buttons for every folder (get from database)
            Button folder = new Button(Main.topFolders.get(i).getName());
            folder.setStyle("-fx-font-size: 22px");
            try {
                if(Main.topFolders.get(i).getIcon() == null) {
                    folder.setGraphic(new ImageView(new Image("/images/courseimage.png",200,200,true,true)));
                    folder.setPrefWidth(50);
                }
                else {
                    folder.setGraphic(new ImageView(new Image(Main.topFolders.get(i).getIcon(),200,200,true,true)));
                    folder.setPrefWidth(50);
                }
            } catch (IllegalArgumentException except){
                Main.topFolders.get(i).setIcon(null);
                folder.setGraphic(new ImageView(new Image("/images/courseimage.png",200,200,true,true)));
            }
            folder.setContentDisplay(ContentDisplay.TOP);
            final Folder f = Main.topFolders.get(i);
            folder.setOnAction(ae -> HomeController.changeToInnerFolderView(ae, f));
            tilePane.getChildren().add(folder);
        }


        return scene;
    }


}
