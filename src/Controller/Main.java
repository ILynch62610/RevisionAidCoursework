package Controller;//Imports
import Model.*;
import Views.EditResourceView;
import Views.HomeView;
import Views.LearningSessionView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;


//Controller.Main Class
public class Main extends Application {

    public static Stage stage;
    public static Scene scene;
    public static DatabaseConnection database;
    public static ArrayList<Folder> topFolders;
    public static ArrayList<Configuration> configurations;

    @Override
    public void start(Stage initialStage) throws Exception {

        stage = initialStage;

        database = new DatabaseConnection("RevisionAidDatabase.db");
        ArrayList<Folder> folders = new ArrayList<Folder>();
        topFolders = new ArrayList<Folder>();
        configurations = ConfigurationService.getConfigurations(database);
        FolderService.selectAll(folders,database);
        for (Folder f : folders) {
            if(f.getParent() == 0) {
                topFolders.add(f);
            }
        }
        scene = HomeView.view();

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