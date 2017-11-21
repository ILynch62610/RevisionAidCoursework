package Views;

import Model.Folder;
import Model.FolderService;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.util.List;

public class HomeView {
    public static void view(Stage stage) {
        Image settingsImg = new Image("/images/settings.png",30,30,false,false);
        Button settingsBtn = new Button();
        settingsBtn.setGraphic(new ImageView(settingsImg));

        BorderPane root = LayoutGenerator.make("Welcome ...", settingsBtn);

        //Allows scrolling
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        root.setCenter(scrollPane);

        //Creates TilePane (for folder view)
        TilePane tilePane = new TilePane();
        scrollPane.setContent(tilePane);
        tilePane.prefWidthProperty().bind(root.widthProperty());
        tilePane.prefHeightProperty().bind(scrollPane.heightProperty());
        tilePane.setHgap(200);
        tilePane.setVgap(100);
        tilePane.setPadding(new Insets(20));


        //FolderService.selectAll();

        //Adding to FolderView
        for (int i=0; i<10; i++) {                                              //Change loop to make buttons for every folder (get from database)
            Button folder = new Button("Folder 1");
            folder.setGraphic(new ImageView("/images/settings.png"));
            folder.setContentDisplay(ContentDisplay.TOP);
            tilePane.getChildren().add(folder);
        }

        Scene scene = new Scene(root, 1024, 768);

        stage.setTitle("Revision Prograammm");
        stage.setScene(scene);
        stage.show();
    }

}
