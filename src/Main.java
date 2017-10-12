//Imports
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.stage.Stage;


//Main Class
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        BorderPane root = LayoutGenerator.make("Welcome ...", null);


        Scene scene = new Scene(root, 1024, 768);

        stage.setTitle("Revision Prograammm");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}