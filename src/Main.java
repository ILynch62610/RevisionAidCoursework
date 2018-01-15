//Imports
import Model.*;
import javafx.application.Application;
import javafx.scene.text.FontWeight;
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
        ArrayList<Sentence> targetList = new ArrayList<Sentence>();
        ArrayList<Sentence> newList = new ArrayList<Sentence>();

        database = new DatabaseConnection("RevisionAidDatabase.db");
        //SentenceService.selectAll(targetList,database);
        //System.out.println(targetList);


        Sentence s = SentenceService.selectById(10,database);
        System.out.println(s.toString());
        SentenceService.delete(10,database);
        SentenceService.selectAll(targetList,database);
        System.out.println(targetList);
        SentenceService.save(s, database);
        SentenceService.selectAll(newList, database);
        System.out.println(newList);





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