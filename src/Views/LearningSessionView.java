package Views;

import Controller.LayoutController;
import Controller.Main;
import Model.*;
import javafx.animation.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class LearningSessionView {
    public static Scene view(Resource resource, String type) {
        if(resource.getType()=="TD"){
            ArrayList<Term> terms = resource.getTChildren(Main.database);
        }else {
            ArrayList<Sentence> terms = resource.getSChildren(Main.database);
        }

        int time = 20;
        for (int i=0; i<Main.configurations.size();i++) {
            if(Main.configurations.get(i).getSettingName().equals("Timer")) {
                int studyTime = Integer.parseInt(Main.configurations.get(i).getSettingValue().substring(0,3));
                int reviseTime = Integer.parseInt(Main.configurations.get(i).getSettingValue().substring(3,6));
                if (type=="Learn") {
                    time = studyTime;
                }
                else {
                    time = reviseTime;
                }
            }
        }

        int correctAns = 5;
        String term = "Hallo";
        String[] terms = {"Hallo", "Bonjour", "Konnichiwa", "Ni Hau", "Yooo", "Hola", "Hi", "Hey"};
        String definition = "Hello";
        String[] definitions = {"Hello", "Sup", "Def 3", "Def 4", "Def 5", "Def 6", "Def 7", "Def 8"};
        String blanksSentence = "On May 27, 97 men from the Royal Norfolk Regiment ran out of ammunition and surrendered at the village of Le Paradis.";
        int blanksNo = 2;

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1024, 768);

        HBox topPane = new HBox(20);
        topPane.setPadding(new Insets(5,0,5,5));
        root.setTop(topPane);       //sets the HBox to be the top panel

        //Adding Text and Image to top pane
        Label topText = new Label();
        topText.setText(type);         //will display the text given as an argument
        topText.setStyle("-fx-font-family: Impact; -fx-font-size: 30pt; -fx-font-weight: bold;");

        topPane.getChildren().add(topText);         //adds text to the pane

        //Creating Timer
        HBox timerPane = new HBox(20);
        HBox.setHgrow(timerPane, Priority.ALWAYS);
        timerPane.setAlignment(Pos.CENTER_RIGHT);

        Label timerLabel = new Label();
        timerLabel.setStyle("-fx-font-size: 25px; -fx-font-weight: bold");

        IntegerProperty timeSecs = new SimpleIntegerProperty(time);
        timerLabel.textProperty().bind(timeSecs.asString());

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(time+1),
                        new KeyValue(timeSecs, 0)));
        timeline.playFromStart();

        timerPane.getChildren().add(timerLabel);

        Rectangle timerbar = new Rectangle();
        timerbar.setFill(Color.DARKBLUE);
        timerbar.setHeight(50);
        timerbar.widthProperty().bind(root.widthProperty().multiply(0.65));
        timerbar.setX(0);
        timerbar.setY(0);
        timerPane.getChildren().add(timerbar);
        topPane.getChildren().add(timerPane);

        TranslateTransition tt = new TranslateTransition(Duration.seconds(time), timerbar);
        tt.setToX((root.getWidth()*0.65));

        tt.play();

        if(type.equals("Learn")||type.equals("Cards")){
            VBox correctPane = new VBox();
            correctPane.setAlignment(Pos.BOTTOM_CENTER);
            correctPane.setPadding(new Insets(15));
            correctPane.setSpacing(10);
            root.setRight(correctPane);
            for(int i=0;i<=correctAns-1;i++){
                ImageView newPoint = new ImageView();
                newPoint.setImage(new Image("images/settings.png"));
                correctPane.getChildren().add(newPoint);
            }
        }
        if(type.equals("Learn")){
            TilePane learningPane = new TilePane();
            learningPane.setHgap(90);
            learningPane.setVgap(120);
            learningPane.setPadding(new Insets(70,45,70,45));

            int[] spaces = {1,2,3,4,5,6,7,8,9,10,11,12};
            String[] spaceFills = new String[12];
            for(int i=0;i<6;i++){
                Boolean foundDef = false;
                Boolean foundTerm = false;
                do {
                    Random rand = new Random();
                    int n = rand.nextInt(12);
                    if(spaces[n]!=0) {
                        foundDef = true;
                        spaces[n] = 0;
                        spaceFills[n] = definition;
                    }
                }while(!foundDef);
                do {
                    Random rand = new Random();
                    int n = rand.nextInt(12);
                    if(spaces[n]!=0) {
                        foundTerm = true;
                        spaces[n] = 0;
                        spaceFills[n] = term;
                    }
                }while(!foundTerm);
            }
            for(int i=0;i<12;i++){
                Rectangle rect = new Rectangle();
                rect.setFill(Color.CORAL);
                rect.setHeight(100);
                rect.setWidth(150);
                Text text = new Text(spaceFills[i]);
                StackPane stack = new StackPane();
                stack.getChildren().addAll(rect, text);
                learningPane.getChildren().add(stack);
            }
            root.setCenter(learningPane);
        }
        else if(type.equals("Cards")){
            Random rand = new Random();
            int choice = rand.nextInt(2);
            VBox centerPane  = new VBox();
            centerPane.setPadding(new Insets(70));
            centerPane.setSpacing(60);
            StackPane keyPane = new StackPane();
            keyPane.setPadding(new Insets(30,0,50,0));
            TilePane cardsPane = new TilePane();
            root.setCenter(centerPane);
            cardsPane.setVgap(50);
            cardsPane.setHgap(70);
            centerPane.getChildren().addAll(keyPane, cardsPane);
            if(choice==0){
                Rectangle termBox = new Rectangle();
                termBox.setFill(Color.FLORALWHITE);
                termBox.setWidth(300);
                termBox.setHeight(140);
                Text termText = new Text(term);
                keyPane.getChildren().addAll(termBox, termText);
                for(int i=0;i<8;i++){
                    Rectangle rect = new Rectangle();
                    rect.setFill(Color.LAVENDER);
                    rect.setWidth(155);
                    rect.setHeight(100);
                    Text text = new Text(definitions[i]);
                    StackPane stack = new StackPane();
                    stack.getChildren().addAll(rect, text);
                    cardsPane.getChildren().add(stack);
                }
            }
            else{
                Rectangle defBox = new Rectangle();
                defBox.setFill(Color.FLORALWHITE);
                defBox.setWidth(300);
                defBox.setHeight(140);
                Text defText = new Text(definition);
                keyPane.getChildren().addAll(defBox, defText);
                for(int i=0;i<8;i++) {
                    Rectangle rect = new Rectangle();
                    rect.setFill(Color.LAVENDER);
                    rect.setWidth(155);
                    rect.setHeight(100);
                    Text text = new Text(terms[i]);
                    StackPane stack = new StackPane();
                    stack.getChildren().addAll(rect, text);
                    cardsPane.getChildren().add(stack);
                }
            }
        }
        else if(type.equals("Blanks")){
            StackPane textPane = new StackPane();
            Rectangle textBox = new Rectangle();
            textBox.setWidth(500);
            textPane.setPadding(new Insets(150,150,100,150));
            GridPane answerPane = new GridPane();
            Text text = new Text(blanksSentence);
            text.setTextAlignment(TextAlignment.CENTER);
            text.setFont(Font.font("Berlin Sans FB", 35));
            text.wrappingWidthProperty().bind(textBox.widthProperty());
            textPane.getChildren().add(text);

            TextField answerBox = new TextField();
            answerBox.setFont(Font.font("Arial", 30));
            Button goBtn = new Button("GO");
            goBtn.setFont(Font.font("Arial",30));
            Label blanksLabel = new Label("Blank " + blanksNo);
            answerPane.add(blanksLabel,0,0,2,1);
            answerPane.add(answerBox,0,1);
            answerPane.add(goBtn,1,1);
            answerPane.setAlignment(Pos.CENTER);
            answerPane.setPadding(new Insets(40,100,200,100));
            root.setBottom(answerPane);
            textPane.setStyle("-fx-background-color: " + LayoutController.getRed()+","+LayoutController.getGreen()+","+LayoutController.getBlue());
            root.setCenter(textPane);
        }
        else{

        }

        return scene;
    }
}
