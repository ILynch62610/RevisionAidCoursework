package Views;

import Controller.Main;
import Controller.SettingsController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class SettingsView {

    public static Stage settingsStage;

    public static void generateSettingsView() {

        settingsStage = new Stage(StageStyle.UNDECORATED);

        HBox root = new HBox();
        root.setStyle("-fx-background-color: transparent");
        HBox.setHgrow(root, Priority.ALWAYS);
        root.setAlignment(Pos.CENTER_RIGHT);
        root.prefWidthProperty().bind(Main.scene.widthProperty());

        VBox pane = new VBox();
        pane.setStyle("-fx-background-color: whitesmoke");
        root.getChildren().add(pane);


        Button saveBtn = new Button("Save Settings");
        pane.getChildren().add(saveBtn);

        Scene settingsScene = new Scene(root);
        settingsScene.setFill(Color.TRANSPARENT);




        settingsStage.setScene(settingsScene);
        settingsStage.initStyle(StageStyle.TRANSPARENT);

        LayoutGenerator.root.setEffect(new GaussianBlur());

        settingsStage.initModality(Modality.APPLICATION_MODAL);
        settingsStage.showAndWait();



    }

}
