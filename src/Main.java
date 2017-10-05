//Imports
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.*;
import javafx.stage.Stage;


//Main Class
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        BorderPane root = new BorderPane();
        AnchorPane topPane = new AnchorPane();
        root.setTop(topPane);
        Label welcomeText = new Label();
        welcomeText.setText("Welcome ...");
        Image settingsImg = new Image("/images/settings.png",30,30,false,false);
        Button settingsBtn = new Button();
        settingsBtn.setGraphic(new ImageView(settingsImg));
        topPane.getChildren().addAll(welcomeText, settingsBtn);
        topPane.setRightAnchor(settingsBtn,10.0);
        topPane.setLeftAnchor(welcomeText,3.0);


        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        root.setCenter(scrollPane);

        TilePane tilePane = new TilePane();
        scrollPane.setContent(tilePane);
        tilePane.prefWidthProperty().bind(root.widthProperty());
        tilePane.prefHeightProperty().bind(scrollPane.heightProperty());
        tilePane.setHgap(30);
        tilePane.setVgap(20);
        tilePane.setPadding(new Insets(20));


        Scene scene = new Scene(root, 1024, 768);

        stage.setTitle("Hello World");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}