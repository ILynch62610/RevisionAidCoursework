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


        /*
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        Date testDate = null;
                        try {
                            testDate = formatter.parse(results.getString("DateLast"));
                            System.out.println(results.getString("DateLast")
                                         + " -> " + testDate.toString()
                                            + " -> " + formatter.format(testDate));


                        }
                        catch (ParseException parp) {
                            System.out.print ("Can't convert date: " + parp.getMessage());
                        }
         */


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

        Definition d = DefinitionService.selectById(1, database);
        System.out.println(d.toString());
        d.setDesc("testing");
        DefinitionService.save(d,database);
        System.out.println(DefinitionService.selectById(1,database).toString());


        /*
        if (d != null) {
            System.out.println(d.getDesc());
        }
        */




    }

    public static void main(String[] args) {
        launch(args);
    }
}