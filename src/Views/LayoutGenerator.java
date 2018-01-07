package Views;

import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class LayoutGenerator {

    public static BorderPane make(String title, Boolean settingsPane) {
        //Creates root pane and the top panel
        BorderPane root = new BorderPane();
        HBox topPane = new HBox(20);
        topPane.setPadding(new Insets(5));
        root.setTop(topPane);       //sets the HBox to be the top panel

        //Adding Text and Image to top pane
        Label topText = new Label();
        topText.setText(title);         //will display the text given as an argument
        topText.setStyle("-fx-font-family: Impact; -fx-font-size: 30pt; -fx-font-weight: bold;");

        topPane.getChildren().add(topText);         //adds text to the pane


        //if the view needs a settings button, then this section of code will be carried out
        if (settingsPane) {
            HBox buttons = new HBox(20);
            HBox.setHgrow(buttons, Priority.ALWAYS);
            buttons.setAlignment(Pos.CENTER_RIGHT);
            Image settingsImg = new Image("/images/settings.png",30,30,false,false);
            Button settingsBtn = new Button();
            settingsBtn.setGraphic(new ImageView(settingsImg));
            Button seeProgress = new Button("See Progress");
            buttons.getChildren().addAll(seeProgress, settingsBtn);
            topPane.getChildren().add(buttons);
        }

        return root;

    }

}
