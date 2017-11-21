//Imports
import Views.HomeView;
import javafx.application.Application;
import javafx.stage.Stage;


//Main Class
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {


        HomeView.view(new Stage());



    }

    public static void main(String[] args) {
        launch(args);
    }
}