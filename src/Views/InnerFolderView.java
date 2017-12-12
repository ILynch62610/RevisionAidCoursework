package Views;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class InnerFolderView {
    public static void view(Stage stage) {
        BorderPane root = LayoutGenerator.make("Welcome ...", false, true);

        //Creates Pane for Table
        StackPane middlePane = new StackPane();
        root.setCenter(middlePane);

        //Creates TableView for Folders and Resources List
        TableView table = new TableView<>();
        table.setPrefSize(400, 300);
        //table.setItems();

        TableColumn nameColumn = new TableColumn<>("Name");
        //nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setResizable(false);
        nameColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.5));
        table.getColumns().add(nameColumn);

        TableColumn typeColumn = new TableColumn<>("Type");
        //typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        typeColumn.setResizable(false);
        typeColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        table.getColumns().add(typeColumn);

        TableColumn dateColumn = new TableColumn<>("Date last Modified");
        //dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateColumn.setResizable(false);
        dateColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.3));
        table.getColumns().add(dateColumn);


        middlePane.getChildren().add(table);

        Scene scene = new Scene(root, 1024, 768);

        stage.setTitle("Revision Prograammm");
        stage.setScene(scene);
        stage.show();
    }

}
