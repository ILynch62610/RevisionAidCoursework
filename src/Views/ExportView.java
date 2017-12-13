package Views;

import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class ExportView {
    public static void view(Stage stage) {
        BorderPane root = LayoutGenerator.make("Export Resource", false, false);

        //ZIP Side
        GridPane zipPane = new GridPane();
        Button zipBtn = new Button("ZIP");
        Label fileNameL = new Label("File Name: ");
        TextField fileNameTF = new TextField();
        Label locationL = new Label("Location: ");
        Button locationBtn = new Button("FIND");
        zipPane.add(zipBtn,0,0,2,1);
        zipPane.add(fileNameL, 0,1);
        zipPane.add(fileNameTF,1,1);
        zipPane.add(locationL,0,3);
        zipPane.add(locationBtn,1,3);


        //Email Side
        GridPane emailPane = new GridPane();
        Button emailBtn = new Button("EMAIL");
        Label subjectL = new Label("Subject: ");
        TextField subjectTF = new TextField();
        emailPane.add(emailBtn,0,0,2,1);
        emailPane.add(subjectL,0,1);
        emailPane.add(subjectTF,1,1);


        //Centre Pane
        GridPane mainPane = new GridPane();
        ColumnConstraints half1 = new ColumnConstraints();
        half1.setPercentWidth(50);
        half1.setHalignment(HPos.CENTER);
        ColumnConstraints half2 = new ColumnConstraints();
        half2.setPercentWidth(50);
        half2.setHalignment(HPos.CENTER);
        mainPane.getColumnConstraints().addAll(half1,half2);
        mainPane.add(zipPane,0,0);
        mainPane.add(emailPane,1,0);
        root.setCenter(mainPane);

        Scene scene = new Scene(root, 1024, 768);

        stage.setTitle("Revision Prograammm");
        stage.setScene(scene);
        stage.show();
    }
}
