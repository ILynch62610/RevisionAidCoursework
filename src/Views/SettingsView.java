package Views;

import Controller.Main;
import Controller.SettingsController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class SettingsView {

    public static Stage settingsStage;

    public static void generateSettingsView(String view) {

        settingsStage = new Stage(StageStyle.UNDECORATED);

        HBox root = new HBox();
        root.setStyle("-fx-background-color: transparent");
        HBox.setHgrow(root, Priority.ALWAYS);
        root.setAlignment(Pos.CENTER_RIGHT);
        root.prefWidthProperty().bind(Main.scene.widthProperty());

        VBox pane = new VBox();
        pane.setMaxWidth(350);
        pane.setPrefWidth(350);
        pane.setStyle("-fx-background-color: whitesmoke");
        root.getChildren().add(pane);

        Rectangle rect = new Rectangle();
        rect.setFill(Color.BISQUE);
        pane.getChildren().add(rect);

        if(view == "Home" || view == "Innerfolder") {
            Label generalLbl = new Label("General");
            generalLbl.setFont(Font.font("Arial", FontWeight.BOLD,30));
            pane.getChildren().add(generalLbl);
            Button backgroundBtn = new Button("Background");
            backgroundBtn.setOnAction(ae -> {
                pane.getChildren().clear();
                pane.getChildren().add(rect);
                Label backgroundLbl = new Label("Background");
                backgroundLbl.setFont(Font.font("Arial", FontWeight.BOLD,30));
                Label backgroundText = new Label("Want a different colour background? Enter the RGB values in the boxes below:");
                backgroundText.setWrapText(true);
                Label rLbl = new Label("R:");
                TextField rField = new TextField();
                Label gLbl = new Label("G:");
                TextField gField = new TextField();
                Label bLbl = new Label("B:");
                TextField bField = new TextField();
                Button confimBtn = new Button("Confirm");
                confimBtn.setOnAction(confirmAE -> {
                    SettingsController.saveBackground(rField.getText(),gField.getText(),bField.getText());
                });
                pane.getChildren().add(backgroundLbl);
                pane.getChildren().add(backgroundText);
                pane.getChildren().add(rLbl);
                pane.getChildren().add(rField);
                pane.getChildren().add(gLbl);
                pane.getChildren().add(gField);
                pane.getChildren().add(bLbl);
                pane.getChildren().add(bField);
                pane.getChildren().add(confimBtn);
            });
            pane.getChildren().add(backgroundBtn);
            Button nameBtn = new Button("Name");
            nameBtn.setOnAction(ae -> {
                pane.getChildren().clear();
                pane.getChildren().add(rect);
                Label nameLbl = new Label("Name");
                nameLbl.setFont(Font.font("Arial", FontWeight.BOLD,30));
                Label textFieldLbl = new Label("Enter Name below:");
                TextField nameField = new TextField();
                Button confirmBtn = new Button("Confirm");
                confirmBtn.setOnAction(confirmAE -> {
                    SettingsController.saveName(nameField.getText());
                });
                pane.getChildren().add(nameLbl);
                pane.getChildren().add(textFieldLbl);
                pane.getChildren().add(nameField);
                pane.getChildren().add(confirmBtn);
            });
            pane.getChildren().add(nameBtn);
            Button timerBtn = new Button("Timer");
            timerBtn.setOnAction(ae -> {
                pane.getChildren().clear();
                pane.getChildren().add(rect);
                Label timerLbl = new Label("Timer");
                timerLbl.setFont(Font.font("Arial", FontWeight.BOLD,30));
                Label timerText = new Label("The timer is used in your learning sessions.");
                timerText.setWrapText(true);

                Label learningLbl = new Label("Learning Sessions");
                ToggleGroup learningTimerGrp = new ToggleGroup();
                Label onLbl = new Label("On");
                RadioButton learningTimerOn = new RadioButton();
                learningTimerOn.setSelected(true);
                learningTimerOn.setToggleGroup(learningTimerGrp);
                Label offLbl = new Label("Off");
                RadioButton learningTimerOff = new RadioButton();
                learningTimerOff.setSelected(false);
                learningTimerOff.setToggleGroup(learningTimerGrp);

                Label perItemLbl = new Label("Time per Item");
                TextField learningField = new TextField();

                Label studyLbl = new Label("Study Sessions");
                ToggleGroup studyTimerGrp = new ToggleGroup();
                RadioButton studyTimerOn = new RadioButton();
                studyTimerOn.setSelected(true);
                studyTimerOn.setToggleGroup(studyTimerGrp);
                RadioButton studyTimerOff = new RadioButton();
                studyTimerOff.setSelected(false);
                studyTimerOff.setToggleGroup(studyTimerGrp);

                TextField studyField = new TextField();

                Button confirmBtn = new Button("Confirm");
                confirmBtn.setOnAction(confirmAE -> {
                    SettingsController.saveTimers(learningTimerOn.isSelected(),learningField.getText(),studyTimerOn.isSelected(), studyField.getText());
                });
                pane.getChildren().add(timerLbl);
                pane.getChildren().add(timerText);
                pane.getChildren().add(learningLbl);
                pane.getChildren().add(onLbl);
                pane.getChildren().add(learningTimerOn);
                pane.getChildren().add(offLbl);
                pane.getChildren().add(learningTimerOff);
                pane.getChildren().add(perItemLbl);
                pane.getChildren().add(learningField);
                pane.getChildren().add(studyLbl);
                pane.getChildren().add(onLbl);
                pane.getChildren().add(studyTimerOn);
                pane.getChildren().add(offLbl);
                pane.getChildren().add(studyTimerOff);
                pane.getChildren().add(perItemLbl);
                pane.getChildren().add(studyField);
                pane.getChildren().add(confirmBtn);
            });
            pane.getChildren().add(timerBtn);
            Button itemBtn = new Button("Items");
            pane.getChildren().add(itemBtn);
            Line breakLine = new Line(0,0,300,0);
            breakLine.setStrokeWidth(5);
            pane.getChildren().add(breakLine);
            if (view == "Home"){
                Label homeLbl = new Label("Home");
                homeLbl.setFont(Font.font("Arial", FontWeight.BOLD,30));
                Button createFolderBtn = new Button("Create");
                pane.getChildren().add(homeLbl);
                pane.getChildren().add(createFolderBtn);
            } else {
                Label folderLbl = new Label("Folder");
                Button createFolderBtn = new Button("Create Folder");
                Button createResourceBtn = new Button("Create Resource");
                Button renameBtn = new Button("Rename");
                Button deleteBtn = new Button("Delete");
                pane.getChildren().add(folderLbl);
                pane.getChildren().add(createFolderBtn);
                pane.getChildren().add(createResourceBtn);
                pane.getChildren().add(renameBtn);
                pane.getChildren().add(deleteBtn);
            }
        }

        Button saveBtn = new Button("Save Settings");
        saveBtn.setOnAction(ae -> SettingsController.saveAndClose());
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
