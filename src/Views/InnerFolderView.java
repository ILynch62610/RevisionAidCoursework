package Views;

import Controller.InnerFolderController;
import Controller.LayoutController;
import Controller.Main;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class InnerFolderView {
    public static Scene view(Folder folder) {
        BorderPane root = LayoutGenerator.make(folder.getName(), true, true, true, "Innerfolder");
        Scene scene = new Scene(root, 1024, 768);

        //Creates FolderItems for contents of folder
        ObservableList<FolderItem> folderItems = FXCollections.observableArrayList();
        ArrayList<ArrayList> itemsArray = folder.getChildren(Main.database);
        ArrayList<Folder> foldersArray = itemsArray.get(0);
        for (int i=0; i<foldersArray.size(); i++) {
            FolderItem fI = new FolderItem(foldersArray.get(i).getName(),"Folder","",foldersArray.get(i).getiD());
            folderItems.add(fI);
        }
        ArrayList<Resource> resourcesArray = itemsArray.get(1);
        for (int i=0; i<resourcesArray.size(); i++) {
            FolderItem fI = new FolderItem(resourcesArray.get(i).getName(),"Resource", resourcesArray.get(i).getLastUsed(),resourcesArray.get(i).getiD());
            folderItems.add(fI);
        }


        //Creates Pane for Table
        StackPane middlePane = new StackPane();
        middlePane.setStyle("-fx-background-color: rgb(" + LayoutController.getRed()+", "+LayoutController.getGreen()+", "+LayoutController.getBlue() + ")");
        root.setCenter(middlePane);

        //Creates TableView for Folders and Resources List
        TableView table = new TableView<>();
        table.setPrefSize(400, 300);
        table.setItems(folderItems);
        table.setStyle("-fx-font-size: 18px");

        TableColumn nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setResizable(false);
        nameColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.5));
        table.getColumns().add(nameColumn);

        TableColumn typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        typeColumn.setResizable(false);
        typeColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        table.getColumns().add(typeColumn);

        TableColumn dateColumn = new TableColumn<>("Date last Modified");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateColumn.setResizable(false);
        dateColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.3));
        table.getColumns().add(dateColumn);

        table.setRowFactory(param -> {
            TableRow<FolderItem> row = new TableRow<FolderItem>();
            row.setOnMouseClicked(event -> {
                if(!row.isEmpty() && event.getButton()== MouseButton.PRIMARY && event.getClickCount() == 2){
                    FolderItem clickedItem = row.getItem();
                    if(clickedItem.getType()=="Folder"){
                        Folder f = FolderService.selectById(clickedItem.getiD(),Main.database);
                        InnerFolderController.changeToInnerFolderView(event,f);
                    }
                    else {
                        Resource r = ResourceService.selectById(clickedItem.getiD(),Main.database);
                        InnerFolderController.changeToResourceView(event,r);
                    }
                }
            });
            return row;
        });

        middlePane.getChildren().add(table);

        return scene;
    }

}
