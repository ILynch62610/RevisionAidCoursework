import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class LayoutGenerator {

    public static BorderPane make(String title, Node extra) {
        BorderPane root = new BorderPane();
        AnchorPane topPane = new AnchorPane();
        topPane.setPadding(new Insets(5));
        root.setTop(topPane);

        //Adding Text and Image to top pane
        Label topText = new Label();
        topText.setText(title);
        topText.setStyle("-fx-font-family: Impact; -fx-font-size: 30pt; -fx-font-weight: bold;");

        topPane.getChildren().add(topText);
        topPane.setLeftAnchor(topText,20.0);

        if (extra != null) {
            topPane.getChildren().add(extra);
            topPane.setRightAnchor(extra, 20.0);
        }


        return root;

    }

}
