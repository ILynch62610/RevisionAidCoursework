//Imports
import Views.*;
import javafx.application.Application;
import javafx.stage.Stage;


//Main Class
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {


        //HomeView.view(new Stage());
        //InnerFolderView.view(new Stage());
        //ResourceView.view(new Stage());
        //ExportView.view(new Stage());
        LearningSessionView.view(new Stage(),"Blanks");
        //EditResourceView.view();



    }

    public static void main(String[] args) {
        launch(args);
    }
}