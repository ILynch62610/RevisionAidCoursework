//Imports
import Model.DatabaseConnection;
import Model.Definition;
import Model.DefinitionService;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;


//Main Class
public class Main extends Application {
    public static DatabaseConnection database;
    @Override
    public void start(Stage stage) throws Exception {


        //HomeView.view(new Stage());
        //InnerFolderView.view(new Stage());
        //ResourceView.view(new Stage());
        //ExportView.view(new Stage());
        //LearningSessionView.view(new Stage(),"Blanks");
        //EditResourceView.view();
        //ProgressView.view(new Stage());
        //EditResourceView.viewNotes(new Stage());
        //EditResourceView.viewTermDef(new Stage());
        ArrayList<Definition> targetList = new ArrayList<Definition>();

        database = new DatabaseConnection("RevisionAidDatabase.db");
        DefinitionService.selectAll(targetList, database);

        for (Definition d: targetList) {
            System.out.println(d);
        }





    }

    public static void main(String[] args) {
        launch(args);
    }
}