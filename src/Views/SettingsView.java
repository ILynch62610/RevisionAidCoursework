package Views;

import Controller.SettingsController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SettingsView {

    public static Stage settingsStage;

    public static void generateSettingsView() {

        settingsStage = new Stage(StageStyle.UNDECORATED);

        Pane root = new Pane();

        Scene settingsScene = new Scene(root);

        Label exampleLabel = new Label("Settings!");

        root.getChildren().add(exampleLabel);

        for (int i = 0; i < 10; i++) {

            Button testButton = new Button("Click me");
            testButton.setOnAction(ae ->  SettingsController.closeSettings());
            testButton.setMinWidth(100);
            testButton.setMinHeight(40);
            testButton.setLayoutX(0);
            testButton.setLayoutY(40 + i * 60);
            root.getChildren().add(testButton);

        }


        settingsStage.setScene(settingsScene);

        LayoutGenerator.root.setEffect(new GaussianBlur());

        settingsStage.initModality(Modality.APPLICATION_MODAL);
        settingsStage.showAndWait();



    }

}
