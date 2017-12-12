package Views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ResourceView {
    public static void view(Stage stage) {
        String resourceName = "Some name";
        BorderPane root = LayoutGenerator.make(resourceName, false, false);

        //Creates Main Pane
        GridPane basePane = new GridPane();

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
        basePane.add(options,7,0);

        //Creates Session Buttons
        Button learnSBtn = new Button("LEARN");
        Button cardsSBtn = new Button("CARDS");
        Button blanksSBtn = new Button("BLANKS");
        Button quizSBtn = new Button("QUIZ");
        TilePane sessionBtns = new TilePane();
        sessionBtns.prefWidthProperty().bind(root.widthProperty());
        sessionBtns.setHgap(200);
        sessionBtns.setVgap(100);
        sessionBtns.getChildren().addAll(learnSBtn,cardsSBtn,blanksSBtn,quizSBtn);
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
