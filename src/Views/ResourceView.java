package Views;

import Controller.LayoutController;
import Controller.ResourceController;
import Model.DatabaseConnection;
import Model.Folder;
import Model.Resource;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ResourceView {
    public static Scene view(Resource resource) {
        BorderPane root = LayoutGenerator.make(resource.getName(), false, false, true, "Resource");
        Scene scene = new Scene(root, 1024, 768);

        //Creates Controller.Main Pane
        VBox basePane = new VBox();
        basePane.setStyle("-fx-background-color: rgb(" + LayoutController.getRed()+", "+LayoutController.getGreen()+", "+LayoutController.getBlue() + ")");
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
        learnSBtn.setGraphic(new ImageView(new Image("/images/learnicon.png",200,200,true,true)));
        learnSBtn.setOnAction(ae -> ResourceController.changeToLearnView(ae,resource));
        Button cardsSBtn = new Button("CARDS");
        cardsSBtn.setGraphic(new ImageView(new Image("/images/cardsicon.png",200,200,true,true)));
        cardsSBtn.setOnAction(ae -> ResourceController.changeToCardsView(ae, resource));
        Button blanksSBtn = new Button("BLANKS");
        blanksSBtn.setGraphic(new ImageView(new Image("/images/blanksicon.png",200,200,true,true)));
        blanksSBtn.setOnAction(ae -> ResourceController.changeToBlanksView(ae, resource));
        Button quizSBtn = new Button("QUIZ");
        quizSBtn.setGraphic(new ImageView(new Image("/images/quizicon.png",200,200,true,true)));
        quizSBtn.setOnAction(ae -> ResourceController.changeToQuizView());
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


        return scene;
    }
}
