package Views;

import Model.DatabaseConnection;
import Model.Folder;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ProgressView {
    public static Scene view(DatabaseConnection database, ArrayList<Folder> topFolders) {
        int noCorrect = 0;
        int totalNo = 0;
        float pcntCorrect = 0;
        String avgTime = "0:00";

        if (totalNo == 0){
            noCorrect = 0;
            pcntCorrect = 0;
        }
        else{
            pcntCorrect = (noCorrect/totalNo)*100;
        }

        BorderPane root = LayoutGenerator.make("PROGRESS", false, false, true, database, topFolders);
        Scene scene = new Scene(root, 1024, 768);
        VBox centrePane = new VBox();
        HBox statsPane = new HBox();
        statsPane.setSpacing(100);
        statsPane.setPadding(new Insets(40));
        statsPane.setAlignment(Pos.CENTER);
        root.setCenter(centrePane);
        centrePane.getChildren().add(statsPane);

        Label pcntCorrectLbl = new Label(pcntCorrect+"%");
        VBox pcntCorrectBox = new VBox();
        pcntCorrectLbl.setFont(Font.font("Arial Black",105));
        Label pcntCorrectText = new Label("items correct");
        pcntCorrectText.setFont(Font.font("Arial Black", 33));
        pcntCorrectBox.getChildren().add(pcntCorrectLbl);
        pcntCorrectBox.getChildren().add(pcntCorrectText);
        pcntCorrectBox.setSpacing(-20);
        pcntCorrectBox.setAlignment(Pos.CENTER);
        statsPane.getChildren().add(pcntCorrectBox);

        Label avgTimeLbl = new Label(avgTime);
        VBox avgTimeBox = new VBox();
        avgTimeLbl.setFont(Font.font("Arial Black",105));
        Label avgTimeText = new Label("average time");
        avgTimeText.setFont(Font.font("Arial Black", 33));
        avgTimeBox.getChildren().add(avgTimeLbl);
        avgTimeBox.getChildren().add(avgTimeText);
        avgTimeBox.setSpacing(-20);
        avgTimeBox.setAlignment(Pos.CENTER);
        statsPane.getChildren().add(avgTimeBox);

        StackPane tablePane = new StackPane();
        TableView table = new TableView<>();
        tablePane.setAlignment(Pos.CENTER);
        tablePane.setPadding(new Insets(30));

        TableColumn itemColumn = new TableColumn<>("Item");
        //itemColumn.setCellValueFactory(new PropertyValueFactory<>("item"));
        itemColumn.setResizable(false);
        itemColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.4));
        table.getColumns().add(itemColumn);

        TableColumn noTestColumn = new TableColumn<>("No. Times Tested");
        //noTestColumn.setCellValueFactory(new PropertyValueFactory<>("times"));
        noTestColumn.setResizable(false);
        noTestColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        table.getColumns().add(noTestColumn);

        TableColumn correctColumn = new TableColumn<>("No. Correct");
        //correctColumn.setCellValueFactory(new PropertyValueFactory<>("correct"));
        correctColumn.setResizable(false);
        correctColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        table.getColumns().add(correctColumn);

        TableColumn avgTimeColumn = new TableColumn<>("Average Time");
        //avgTimeColumn.setCellValueFactory(new PropertyValueFactory<>("avgTime"));
        avgTimeColumn.setResizable(false);
        avgTimeColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        table.getColumns().add(avgTimeColumn);

        TableColumn statusColumn = new TableColumn<>("Status");
        //statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusColumn.setResizable(false);
        statusColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        table.getColumns().add(statusColumn);

        tablePane.getChildren().add(table);
        centrePane.getChildren().add(tablePane);


        return scene;
    }
}
