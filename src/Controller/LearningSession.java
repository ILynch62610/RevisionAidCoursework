package Controller;

import Model.Configuration;
import Model.ConfigurationService;
import Model.Resource;
import Model.Term;
import Views.InnerFolderView;
import Views.LearningSessionView;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class LearningSession {

    public static ArrayList<Term> sessionTerms;

    public static int getItemsPerSessionConfig(String type) {
        int learnItems = 5;
        int studyItems = 15;
        for (Configuration c : Main.configurations) {
            if(c.getSettingName().equals("Items")) {
                learnItems = Integer.parseInt(c.getSettingValue().substring(0,2));
                studyItems = Integer.parseInt(c.getSettingValue().substring(2,4));
            }
        }
        if (learnItems < 5) {
            learnItems = 5;
        }
        if (studyItems < 5) {
            studyItems = 5;
        }
        if(type.equals("Learn")){
            return learnItems;
        }
        else {
            return studyItems;
        }
    }
    public static void learn(Resource r, ActionEvent ae) {
        int correctAns = 0;
        if (r.getType().equals("TD")){
            ArrayList<Term> resourceTerms = r.getTChildren(Main.database);
            System.out.println(resourceTerms);
            sessionTerms = new ArrayList<>();
            int learnItems = getItemsPerSessionConfig("Learn");
            for (int i=0; i<Math.round(learnItems/4);i++) {
                Random rand = new Random();
                int n = rand.nextInt(resourceTerms.size());
                sessionTerms.add(resourceTerms.get(n));
                resourceTerms.remove(n);
            }
            for (int i=0; i<Math.round(learnItems/3);i++) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Term leastRecent = resourceTerms.get(0);
                Date earliestDate = Calendar.getInstance().getTime();
                for (Term t : resourceTerms) {
                    Date date = null;
                    try {
                        date = formatter.parse(t.getAnswers(Main.database).get(0).getDate());
                        if (date.before(earliestDate)) {
                            earliestDate = date;
                            leastRecent = t;
                        }
                    }
                    catch (ParseException parp) {
                        System.out.print ("Can't convert date: " + parp.getMessage());
                    }
                }
                sessionTerms.add(leastRecent);
                resourceTerms.remove(leastRecent);
            }
            for (int i=0; i<Math.round(learnItems/4);i++){
                float lowest = 100;
                Term lowestTerm = resourceTerms.get(0);
                for (Term t : resourceTerms) {
                    if (t.getAnswers(Main.database).get(0).getPercent() < lowest) {
                        lowest = t.getAnswers(Main.database).get(0).getPercent();
                        lowestTerm = t;
                    }
                }
                sessionTerms.add(lowestTerm);
                resourceTerms.remove(lowestTerm);
            }
            for (int i=0; i<Math.round(learnItems/6);i++){
                Random rand = new Random();
                Boolean found = false;
                while (found == false){
                    int n = rand.nextInt(resourceTerms.size());
                    if (resourceTerms.get(n).getAnswers(Main.database).get(0).getStatus() == false) {
                        sessionTerms.add(resourceTerms.get(n));
                        resourceTerms.remove(n);
                        found = true;
                    }
                }
            }
            while(sessionTerms.size() > learnItems) {
                sessionTerms.remove(-1);
            }


            while(sessionTerms.size()>0) {
                Stage stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
                stage.setScene(LearningSessionView.view(r, "Learn", correctAns));
            }
        }
        else if(r.getType().equals("NS")) {

        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Resource Error");
            alert.setHeaderText("There has been an issue with retrieving the content of this resource. Please choose another one.");
            alert.showAndWait();
        }
    }
    public static void cards(Resource r, ActionEvent ae) {

    }
    public static void blanks(Resource r, ActionEvent ae) {

    }
}
