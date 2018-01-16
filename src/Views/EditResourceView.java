package Views;

import Model.DatabaseConnection;
import Model.Folder;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class EditResourceView {
    public static Scene viewTermDef(DatabaseConnection database, ArrayList<Folder> topFolders) {
        BorderPane root = LayoutGenerator.make("Resouce",false, false, false, database, topFolders);
        Scene scene = new Scene(root, 1024, 768);
        VBox centrePane = new VBox();
        root.setCenter(centrePane);

        TableView itemsTable = new TableView();

        TableColumn termColumn = new TableColumn<>("Term");
        //termColumn.setCellValueFactory(new PropertyValueFactory<>("term"));
        termColumn.setResizable(false);
        termColumn.prefWidthProperty().bind(itemsTable.widthProperty().multiply(0.45));
        itemsTable.getColumns().add(termColumn);

        TableColumn defColumn = new TableColumn<>("Definition");
        //defColumn.setCellValueFactory(new PropertyValueFactory<>("definition"));
        defColumn.setResizable(false);
        defColumn.prefWidthProperty().bind(itemsTable.widthProperty().multiply(0.45));
        itemsTable.getColumns().add(defColumn);

        TableColumn buttonsColumn = new TableColumn<>();
        buttonsColumn.setResizable(false);
        buttonsColumn.prefWidthProperty().bind(itemsTable.widthProperty().multiply(0.1));
        itemsTable.getColumns().add(buttonsColumn);

/*
        //Allows buttons to be added to column
        Callback<TableColumn<Data, Void>, TableCell<Data, Void>> cellFactory = new Callback<TableColumn<Data, Void>, TableCell<Data, Void>>() {
            @Override
            public TableCell<Data, Void> call(final TableColumn<Data, Void> param) {
                final TableCell<Data, Void> cell = new TableCell<Data, Void>() {

                    Button editBtn = new Button();
                    Button delBtn = new Button();

                    {
                        editBtn.setOnAction((ActionEvent event) -> {
                            //action here
                        });
                        delBtn.setOnAction((ActionEvent event) -> {
                            //action here
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(editBtn);
                        }
                    }
                };
                return cell;
            }
        };
*/


        Button addNewBtn = new Button("ADD NEW");
        addNewBtn.prefWidthProperty().bind(root.widthProperty().multiply(0.4));
        centrePane.setAlignment(Pos.CENTER);
        centrePane.setSpacing(50);
        centrePane.getChildren().add(itemsTable);
        centrePane.setPadding(new Insets(20));
        centrePane.getChildren().add(addNewBtn);


        return scene;
    }
    public static Scene viewNotes(DatabaseConnection database, ArrayList<Folder> topFolders) {
        BorderPane root = LayoutGenerator.make("RESOURCE",false, false, false, database, topFolders);
        Scene scene = new Scene(root, 1024, 768);
        GridPane centrePane = new GridPane();
        root.setCenter(centrePane);

        Label notesLbl = new Label("Notes File: ");
        Button notesBtn = new Button("FIND");
        String notesLocation = "please find file";
        Label notesLocLbl = new Label(notesLocation);
        centrePane.add(notesLbl,0,0);
        centrePane.add(notesBtn,1,0,2,1);
        centrePane.add(notesLocLbl,3,0,3,1);

        Label splitLbl = new Label("Split By: ");
        RadioButton bySentenceRad = new RadioButton();
        Label bySentenceLbl = new Label("By Sentence");
        RadioButton byParagraphRad = new RadioButton();
        Label byParagraphLbl = new Label("By Paragraph");
        RadioButton byTwoSentencesRad = new RadioButton();
        Label byTwoSentencesLbl = new Label("By Every 2 Sentences");
        RadioButton byBulletsRad = new RadioButton();
        Label byBulletsLbl = new Label("By Bullet Points");
        centrePane.add(splitLbl,0,1,1,2);
        centrePane.add(bySentenceRad,1,1);
        centrePane.add(bySentenceLbl,2,1);
        centrePane.add(byParagraphRad,4,1);
        centrePane.add(byParagraphLbl,5,1);
        centrePane.add(byTwoSentencesRad,1,2);
        centrePane.add(byTwoSentencesLbl,2,2);
        centrePane.add(byBulletsRad,4,2);
        centrePane.add(byBulletsLbl,5,2);

        return scene;

    }
}
