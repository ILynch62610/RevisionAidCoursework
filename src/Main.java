//Imports
import Model.*;
import Views.EditResourceView;
import Views.HomeView;
import Views.LearningSessionView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;


//Main Class
public class Main extends Application {
    public static DatabaseConnection database;
    @Override
    public void start(Stage stage) throws Exception {

        DatabaseConnection database = new DatabaseConnection("RevisionAidDatabase.db");
        ConfigurationService.getConfigurations(database);
        ArrayList<Folder> folders = new ArrayList<Folder>();
        ArrayList<Folder> topFolders = new ArrayList<Folder>();
        FolderService.selectAll(folders,database);
        for (Folder f : folders) {
            if(f.getParent() == 0) {
                topFolders.add(f);
            }
        }
        Scene scene = HomeView.view(database, topFolders);


        Configuration configuration = ConfigurationService.getConfigurations(database);
        System.out.println(configuration.getTimer());

        stage.setTitle("Revision Prograammm");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();


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


    }

    public static void main(String[] args) {
        launch(args);
    }
}