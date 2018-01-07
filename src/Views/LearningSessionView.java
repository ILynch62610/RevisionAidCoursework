package Views;

import javafx.animation.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;


public class LearningSessionView {
    public static void view(Stage stage, String type) {
        int time = 20;
        int correctAns = 5;
        String term = "Hallo";
        String definition = "Hello";

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
                Text text = new Text(spaceFills[i]);
                StackPane stack = new StackPane();
                stack.getChildren().addAll(rect, text);
                learningPane.getChildren().add(stack);
            }
            root.setCenter(learningPane);
        }
        else if(type.equals("Cards")){

        }
        else if(type.equals("Blanks")){

        }
        else{

        }

        stage.setTitle("Revision Prograammm");
        stage.setScene(scene);
        stage.show();
    }
}
