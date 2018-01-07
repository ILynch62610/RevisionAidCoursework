package Views;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ResourceView {
    public static void view(Stage stage) {
        String resourceName = "Some name";
        BorderPane root = LayoutGenerator.make(resourceName, false);

        //Creates Main Pane
        VBox basePane = new VBox();

        root.setCenter(basePane);

        //Creates Options Buttons
        HBox options = new HBox(20);
        HBox.setHgrow(options, Priority.ALWAYS);
        options.setAlignment(Pos.CENTER_RIGHT);
        Button exportBtn = new Button();
        exportBtn.setGraphic(new ImageView("/images/settings.png"));
        Button editBtn = new Button();
        editBtn.setGraphic(new ImageView("/images/settings.png"));
        Button deleteBtn = new Button();
        deleteBtn.setGraphic(new ImageView("/images/settings.png"));
        options.getChildren().addAll(exportBtn,editBtn,deleteBtn);
        basePane.getChildren().add(options);

        //Creates Session Buttons
        Button learnSBtn = new Button("LEARN");
        Button cardsSBtn = new Button("CARDS");
        Button blanksSBtn = new Button("BLANKS");
        Button quizSBtn = new Button("QUIZ");
        GridPane sessionBtns = new GridPane();
        ColumnConstraints buf1 = new ColumnConstraints();
        buf1.setPercentWidth(14);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(29);
        col1.setHalignment(HPos.CENTER);
        ColumnConstraints buf2 = new ColumnConstraints();
        buf2.setPercentWidth(14);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(29);
        col2.setHalignment(HPos.CENTER);
        ColumnConstraints buf3 = new ColumnConstraints();
        buf3.setPercentWidth(14);
        sessionBtns.getColumnConstraints().addAll(buf1,col1,buf2,col2,buf3);
        RowConstraints bufr1 = new RowConstraints();
        bufr1.setPercentHeight(14);
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(29);
        RowConstraints bufr2 = new RowConstraints();
        bufr2.setPercentHeight(14);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(29);
        RowConstraints bufr3 = new RowConstraints();
        bufr3.setPercentHeight(14);
        sessionBtns.getRowConstraints().addAll(bufr1,row1,bufr2,row2,bufr3);
        sessionBtns.prefWidthProperty().bind(root.widthProperty());
        sessionBtns.add(learnSBtn,1,1);
        sessionBtns.add(cardsSBtn,3,1);
        sessionBtns.add(blanksSBtn,1,3);
        sessionBtns.add(quizSBtn,3,3);
        basePane.getChildren().add(sessionBtns);
/*
        basePane.add(learnSBtn,2,2,2,3);
        basePane.add(cardsSBtn,5,2,2,3);
        basePane.add(blanksSBtn,2,6,2,3);
        basePane.add(quizSBtn,5,6,2,3);

        for (int i = 0; i < 7; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(10);
            basePane.getColumnConstraints().add(column);
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(10);
            basePane.getRowConstraints().add(row);
        }

*/
        Scene scene = new Scene(root, 1024, 768);

        stage.setTitle("Revision Prograammm");
        stage.setScene(scene);
        stage.show();
    }
}
