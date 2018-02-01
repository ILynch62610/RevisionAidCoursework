package Views;

import Controller.LayoutController;
import Controller.Main;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProgressView {
    public static Scene view() {
        ObservableList<ProgressEntry> items = FXCollections.observableArrayList();
        ArrayList<Definition> definitions = new ArrayList<>();
        DefinitionService.selectAll(definitions, Main.database);
        ArrayList<Sentence> sentences = new ArrayList<>();
        SentenceService.selectAll(sentences,Main.database);

        for (Definition d : definitions) {
            ProgressEntry p = new ProgressEntry(d.getDesc(),d.getAppear(),d.getCorrect(),"Definition",d.getDate(),d.getStatus());
            items.add(p);
        }
        for (Sentence s : sentences) {
            ProgressEntry p = new ProgressEntry(s.getContent(),s.getAppear(),s.getCorrect(),"Sentence",s.getDate(),s.getStatus());
            items.add(p);
        }

        int totalNo = 0;
        int noCorrect = 0;
        double pcntCorrect;
        int daysAgo = 10000;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date today = Calendar.getInstance().getTime();

        for (ProgressEntry e : items) {
            totalNo += e.getTimesTested();
            noCorrect += e.getCorrectNo();
            try {
                Date date = formatter.parse(e.getDate());
                long difMillis = Math.abs(today.getTime() - date.getTime());
                long difference = TimeUnit.DAYS.convert(difMillis,TimeUnit.MILLISECONDS);
                if(difference < daysAgo) {
                    daysAgo = (int)difference;
                }
            } catch (ParseException parp) {
                System.out.print ("Can't convert date: " + parp.getMessage());
            }
        }

        if (totalNo == 0){
            pcntCorrect = 0;
        }
        else{
            pcntCorrect = ((double)noCorrect/(double)totalNo)*100;
        }

        BorderPane root = LayoutGenerator.make("PROGRESS", false, false, true, "Progress");
        Scene scene = new Scene(root, 1024, 768);
        VBox centrePane = new VBox();
        HBox statsPane = new HBox();
        statsPane.setSpacing(100);
        statsPane.setPadding(new Insets(40));
        statsPane.setAlignment(Pos.CENTER);
        centrePane.setStyle("-fx-background-color: rgb(" + LayoutController.getRed()+", "+LayoutController.getGreen()+", "+LayoutController.getBlue() + ")");
        root.setCenter(centrePane);
        centrePane.getChildren().add(statsPane);

        Label pcntCorrectLbl = new Label(String.valueOf(pcntCorrect)+"%");
        VBox pcntCorrectBox = new VBox();
        pcntCorrectLbl.setFont(Font.font("Arial Black",105));
        Label pcntCorrectText = new Label("items correct");
        pcntCorrectText.setFont(Font.font("Arial Black", 33));
        pcntCorrectBox.getChildren().add(pcntCorrectLbl);
        pcntCorrectBox.getChildren().add(pcntCorrectText);
        pcntCorrectBox.setSpacing(-20);
        pcntCorrectBox.setAlignment(Pos.CENTER);
        statsPane.getChildren().add(pcntCorrectBox);

        Label lastSessionTxt = new Label("last session was");
        lastSessionTxt.setFont(Font.font("Arial Black", 25));
        Label lastSessionLbl = new Label(String.valueOf(daysAgo));
        VBox lastSessionBox = new VBox();
        lastSessionLbl.setFont(Font.font("Arial Black",105));
        Label daysAgoTxt = new Label("days ago");
        daysAgoTxt.setFont(Font.font("Arial Black", 33));
        lastSessionBox.getChildren().add(lastSessionTxt);
        lastSessionBox.getChildren().add(lastSessionLbl);
        lastSessionBox.getChildren().add(daysAgoTxt);
        lastSessionBox.setSpacing(-35);
        lastSessionBox.setAlignment(Pos.CENTER);
        statsPane.getChildren().add(lastSessionBox);

        StackPane tablePane = new StackPane();
        TableView table = new TableView<>();
        tablePane.setAlignment(Pos.CENTER);
        tablePane.setPadding(new Insets(30));
        table.setItems(items);

        TableColumn itemColumn = new TableColumn<>("Item");
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("item"));
        itemColumn.setResizable(false);
        itemColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.48));
        table.getColumns().add(itemColumn);

        TableColumn typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        typeColumn.setResizable(false);
        typeColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
        table.getColumns().add(typeColumn);

        TableColumn noTestColumn = new TableColumn<>("No. Times Tested");
        noTestColumn.setCellValueFactory(new PropertyValueFactory<>("timesTested"));
        noTestColumn.setResizable(false);
        noTestColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.08));
        table.getColumns().add(noTestColumn);

        TableColumn correctColumn = new TableColumn<>("No. Correct");
        correctColumn.setCellValueFactory(new PropertyValueFactory<>("correctNo"));
        correctColumn.setResizable(false);
        correctColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.08));
        table.getColumns().add(correctColumn);

        TableColumn lastDateColumn = new TableColumn<>("Last Tested");
        lastDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        lastDateColumn.setResizable(false);
        lastDateColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.13));
        table.getColumns().add(lastDateColumn);

        TableColumn statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusColumn.setResizable(false);
        statusColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.11));
        table.getColumns().add(statusColumn);

        tablePane.getChildren().add(table);
        centrePane.getChildren().add(tablePane);


        return scene;
    }
}
