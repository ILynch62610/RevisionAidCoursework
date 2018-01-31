package Views;

import Controller.LayoutController;
import Controller.LearningSession;
import Controller.LearningSessionController;
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
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

import static Controller.LearningSession.sessionTerms;


public class LearningSessionView {

    public static Label blanksLabel;
    public static BlankSentence blankThing;
    public static int sentenceNumber = 0;
    public static Text text;
    public static VBox correctPane;
    public static TilePane learningPane;
    public static ArrayList<String> selectedItems = new ArrayList<>();
    public static ArrayList<StackPane> selectedPanes = new ArrayList<>();
    public static int numberSelected=0;
    public static String[] spaceFills = new String[12];
    public static Timeline timeline = new Timeline();

    public static Scene view(Resource resource, String type) {
        Random rand = new Random();
        ArrayList<Term> terms = new ArrayList<>();
        String term = "";
        ArrayList<Definition> definitions = new ArrayList<>();
        String definition = "";
        //ArrayList<Sentence> sentences = new ArrayList<>();

        if(resource.getType().equals("TD")){
            terms = resource.getTChildren(Main.database);
            term = sessionTerms.get(0).getContent();
            definition = sessionTerms.get(0).getAnswers(Main.database).get(rand.nextInt(sessionTerms.get(0).getAnswers(Main.database).size())).getDesc();
            for (Term t  : resource.getTChildren(Main.database)) {
                for (int i=0;i<t.getAnswers(Main.database).size();i++){
                    definitions.add(t.getAnswers(Main.database).get(i));
                }
            }
        }else {
            //sentences = resource.getSChildren(Main.database);
            blankThing = LearningSession.sessionSentences.get(sentenceNumber).createBlanks();
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

        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(time+1),
                        new KeyValue(timeSecs, 0)));
        timeline.playFromStart();
        timeline.setOnFinished(ae -> LearningSessionController.outOfTime(resource));

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

        if(type.equals("Learn")||type.equals("Cards")||type.equals("Blanks")){
            correctPane = new VBox();
            correctPane.setAlignment(Pos.BOTTOM_CENTER);
            correctPane.setPadding(new Insets(15));
            correctPane.setSpacing(10);
            correctPane.setPrefWidth(75);
            root.setRight(correctPane);
            LearningSessionController.displayCorrect();
        }
        if(type.equals("Learn")){
            learningPane = new TilePane();
            learningPane.setHgap(90);
            learningPane.setVgap(120);
            learningPane.setPadding(new Insets(70,45,70,45));

            int[] spaces = {1,2,3,4,5,6,7,8,9,10,11,12};
            for(int i=0;i<sessionTerms.size();i++){
                Boolean foundDef = false;
                Boolean foundTerm = false;
                do {
                    int n = rand.nextInt(sessionTerms.size()*2);
                    if(spaces[n]!=0) {
                        foundDef = true;
                        spaces[n] = 0;
                        if (sessionTerms.get(i).getAnswers(Main.database).size() > 0) {
                            spaceFills[n] = sessionTerms.get(i).getAnswers(Main.database).get(rand.nextInt(sessionTerms.get(i).getAnswers(Main.database).size())).getDesc();
                        } else {
                            spaceFills[n] = sessionTerms.get(i).getAnswers(Main.database).get(0).getDesc();
                        }
                    }
                }while(!foundDef);
                do {
                    int n = rand.nextInt(sessionTerms.size()*2);
                    if(spaces[n]!=0) {
                        foundTerm = true;
                        spaces[n] = 0;
                        spaceFills[n] = sessionTerms.get(i).getContent();
                    }
                }while(!foundTerm);
            }
            for(int i=0;i<sessionTerms.size()*2;i++){
                Rectangle rect = new Rectangle();
                rect.setFill(Color.CORAL);
                rect.setHeight(100);
                rect.setWidth(150);
                Text text = new Text(spaceFills[i]);
                StackPane stack = new StackPane();
                rect.setOnMouseClicked(ae -> LearningSessionController.selectItem(rect,text,stack));
                text.setOnMouseClicked(ae -> LearningSessionController.selectItem(rect,text,stack));
                text.setWrappingWidth(130);
                text.setTextAlignment(TextAlignment.CENTER);
                stack.getChildren().addAll(rect, text);
                learningPane.getChildren().add(stack);
            }
            Button checkBtn = new Button("MATCH");
            checkBtn.setOnAction(ae -> LearningSessionController.checkMatch(resource));

            learningPane.getChildren().add(checkBtn);
            root.setCenter(learningPane);
        }
        else if(type.equals("Cards")){
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
                    Text text = new Text(definitions.get(i).getDesc());
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
                    Text text = new Text(resource.getTChildren(Main.database).get(i).getContent());
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
            text = new Text(blankThing.sentence);
            text.setTextAlignment(TextAlignment.CENTER);
            text.setFont(Font.font("Berlin Sans FB", 35));
            text.wrappingWidthProperty().bind(textBox.widthProperty());
            textPane.getChildren().add(text);

            //ArrayList<String> blanksFinal = blanks;
            //for(int i=0;i<2;i++) {
//                int number = i-1;
                GridPane answerPane = new GridPane();
                TextField answerBox = new TextField();
                answerBox.setFont(Font.font("Arial", 30));
                Button goBtn = new Button("GO");
                LearningSessionController.number = 0;   // RESET THE NUMBER
                goBtn.setOnAction(ae -> {
                    LearningSessionController.checkBlank(answerBox.getText(), resource);
                });
                goBtn.setFont(Font.font("Arial", 30));
                blanksLabel = new Label("Blank 1");
                answerPane.add(blanksLabel, 0, 0, 2, 1);
                answerPane.add(answerBox, 0, 1);
                answerPane.add(goBtn, 1, 1);
                answerPane.setAlignment(Pos.CENTER);
                answerPane.setPadding(new Insets(40, 100, 200, 100));
                root.setBottom(answerPane);
  //          }

            textPane.setStyle("-fx-background-color: rgb(" + LayoutController.getRed()+", "+LayoutController.getGreen()+", "+LayoutController.getBlue() + ")");
            root.setCenter(textPane);
        }
        else{

        }

        return scene;
    }
}
