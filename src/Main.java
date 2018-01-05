//Imports
import Views.ExportView;
import Views.HomeView;
import Views.InnerFolderView;
import Views.ResourceView;
import javafx.application.Application;
import javafx.stage.Stage;


//Main Class
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {


        HomeView.view(new Stage());
        //InnerFolderView.view(new Stage());
        //ResourceView.view(new Stage());
        ExportView.view(new Stage());



    }

    public static void main(String[] args) {
        launch(args);
    }
}