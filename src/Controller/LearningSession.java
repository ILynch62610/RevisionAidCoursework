package Controller;

import Model.*;
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
    public static ArrayList<Sentence> sessionSentences;
    public static int correctAns;
    public static int attempsNo;

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
    public static void getSessionItems(Resource r) {
        //if the resource consists of terms and definitions, the following code will run
        if (r.getType().equals("TD")){
            //gets all the terms in the resource
            ArrayList<Term> resourceTerms = r.getTChildren(Main.database);
            sessionTerms = new ArrayList<>();
            //this method returns the number of items that should appear in a learning session from the settings
            int learnItems = getItemsPerSessionConfig("Learn");

            int resourceSize = resourceTerms.size();
            //this says how many of the items should be picked based on different factors
            int noOfRandomPicks = Math.round(learnItems/4); //a quarter of items
            int noOfOlderPicks = Math.round(learnItems/3);  //a third of items
            int noOfLowPercentage = Math.round(learnItems/4);   //a quarter of items
            int noOfRecentlyWrong = Math.round(learnItems/6);   //a sixth of items

            for (int i=0; i<noOfRandomPicks;i++) {
                //this will randomly choose terms and add them to the list of items to appear in the session
                Random rand = new Random();
                int n = rand.nextInt(resourceTerms.size());
                sessionTerms.add(resourceTerms.get(n));
                //it will then remove the term from the list of terms so that it can't be picked twice
                resourceTerms.remove(n);
            }   //this runs until the predefined number of items have been found

            for (int i=0; i<noOfOlderPicks;i++) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                //sets the least recent term to be the first term in the list
                Term leastRecent = resourceTerms.get(0);
                //sets the earliest date to be today's date
                Date earliestDate = Calendar.getInstance().getTime();
                for (Term t : resourceTerms) {
                    //goes through every term in the list of terms in the resource
                    Date date = null;
                    try {
                        //then gets the date that the term was last in a session
                        String theDate = t.getAnswers(Main.database).get(0).getDate();
                        //checks that the date isn't null
                        if (theDate != null && !theDate.toLowerCase().equals("null")) {
                            date = formatter.parse(theDate);
                            //compares the date to the least recent date (originally set to today)
                            if (date.before(earliestDate)) {
                                earliestDate = date;
                                //if it is less recent, then that term is set to the less recent
                                leastRecent = t;
                            }
                        }
                    }
                    catch (ParseException parp) {
                        System.out.print ("Can't convert date: " + parp.getMessage());
                    }
                }
                //if the date was null, the term is automatically added to the list, otherwise the least recent is added
                sessionTerms.add(leastRecent);
                resourceTerms.remove(leastRecent);
            }

            for (int i=0; i<noOfLowPercentage;i++){
                //lowest percentage is set to 100%
                float lowest = 100;
                Term lowestTerm = resourceTerms.get(0);
                for (Term t : resourceTerms) {
                    //compares if the term's percentage is less than the lowest so far
                    if (t.getAnswers(Main.database).get(0).getPercent() < lowest) {
                        lowest = t.getAnswers(Main.database).get(0).getPercent();
                        //if it is lower, then the lowest term is changed to this one
                        lowestTerm = t;
                    }
                }
                //the term with the lowest percentage is then added to the list of session terms
                sessionTerms.add(lowestTerm);
                resourceTerms.remove(lowestTerm);
            }

            for (int i=0; i<noOfRecentlyWrong;i++){
                Random rand = new Random();
                Boolean found = false;
                //will run until a term that was answered incorrectly last is found
                while (found == false){
                    int n = rand.nextInt(resourceTerms.size());
                    //if the term's answer was wrong the last time, then it will be added to the list of session terms
                    if (resourceTerms.get(n).getAnswers(Main.database).get(0).getStatus() == false) {
                        sessionTerms.add(resourceTerms.get(n));
                        resourceTerms.remove(n);
                        found = true;
                    }
                }

            }
            //if the list of session terms is bigger than the number of items that are supposed to appear, the last item on the list will be removed until the sizes match
            while(sessionTerms.size() > learnItems) {
                sessionTerms.remove(-1);
            }
            //if the list of session terms is smaller than the number of items that are supposed to appear, and smaller than the number of terms in the resource, another term will randomly be added to the list
            while(sessionTerms.size() < learnItems && sessionTerms.size() < resourceSize) {
                Random rand = new Random();
                int n = rand.nextInt(resourceTerms.size());
                sessionTerms.add(resourceTerms.get(n));
                resourceTerms.remove(n);
            }

        }
        else if(r.getType().equals("NS")) {
            ArrayList<Sentence> resourceSentences = r.getSChildren(Main.database);
            sessionSentences = new ArrayList<>();
            int studyItems = getItemsPerSessionConfig("Study");

            int resourceSize = resourceSentences.size();
            int noOfRandomPicks;
            int noOfOlderPicks;
            int noOfLowPercentage;
            int noOfRecentlyWrong;
            if(resourceSize < studyItems) {
                noOfRandomPicks = Math.round(studyItems / 4/resourceSize);
                noOfOlderPicks = Math.round(studyItems / 3/resourceSize);
                noOfLowPercentage = Math.round(studyItems / 4/resourceSize);
                noOfRecentlyWrong = Math.round(studyItems / 6/resourceSize);
            } else {
                noOfRandomPicks = Math.round(studyItems / 4);
                noOfOlderPicks = Math.round(studyItems / 3);
                noOfLowPercentage = Math.round(studyItems / 4);
                noOfRecentlyWrong = Math.round(studyItems / 6);
            }

            for (int i=0; i<noOfRandomPicks;i++) {
                Random rand = new Random();
                int n = rand.nextInt(resourceSentences.size());
                sessionSentences.add(resourceSentences.get(n));
                resourceSentences.remove(n);
            }

            for (int i=0; i<noOfOlderPicks;i++) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Sentence leastRecent = resourceSentences.get(0);
                Date earliestDate = Calendar.getInstance().getTime();
                for (Sentence s : resourceSentences) {
                    if (s.getDate() == null) {
                        break;
                    } else {
                        Date date = null;
                        try {
                            date = formatter.parse(s.getDate());
                            if (date.before(earliestDate)) {
                                earliestDate = date;
                                leastRecent = s;
                            }
                        } catch (ParseException parp) {
                            System.out.print("Can't convert date: " + parp.getMessage());
                        }
                    }
                }
                sessionSentences.add(leastRecent);
                resourceSentences.remove(leastRecent);
            }

            for (int i=0; i<noOfLowPercentage;i++){
                float lowest = 100;
                Sentence lowestTerm = resourceSentences.get(0);
                for (Sentence s : resourceSentences) {
                    if (s.getPercent() < lowest) {
                        lowest = s.getPercent();
                        lowestTerm = s;
                    }
                }
                sessionSentences.add(lowestTerm);
                resourceSentences.remove(lowestTerm);
            }

            for (int i=0; i<noOfRecentlyWrong;i++){
                Random rand = new Random();
                Boolean found = false;
                while (found == false){
                    int n = rand.nextInt(resourceSentences.size());
                    if (resourceSentences.get(n).getStatus() == false) {
                        sessionSentences.add(resourceSentences.get(n));
                        resourceSentences.remove(n);
                        found = true;
                    }
                }

            }

            while(sessionSentences.size() > studyItems) {
                sessionSentences.remove(-1);
            }
            while(sessionSentences.size() < studyItems && sessionSentences.size() < resourceSize) {
                Random rand = new Random();
                int n = rand.nextInt(resourceSentences.size());
                sessionSentences.add(resourceSentences.get(n));
                resourceSentences.remove(n);
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Resource Error");
            alert.setHeaderText("There has been an issue with retrieving the content of this resource. Please choose another one.");
            alert.showAndWait();
        }
    }


    public static void learn(Resource r) {
        int items;
        if(r.getType().equals("NS")){
            items = r.getSChildren(Main.database).size();
        } else {
            items = r.getTChildren(Main.database).size();
        }

        if(items < 4) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Resource Error");
            if (items == 0) {
                alert.setHeaderText("The resource is empty. Please add some items to the resource to run a study session.");
            }
            else {
                alert.setHeaderText("The session cannot be launched as the resource only has " + items + " items in it. Please add more.");
            }
            alert.showAndWait();
        } else {
            if (r.getType().equals("NS")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Resource Error");
                alert.setHeaderText("Sorry, you cannot use this study session for this type of resource. Please choose another type of session.");
                alert.showAndWait();
            } else {
                correctAns = 0;
                attempsNo = 0;
                getSessionItems(r);
                Main.stage.setScene(LearningSessionView.view(r, "Learn"));
            }
        }
    }
    public static void cards(Resource r) {

    }
    public static void blanks(Resource r) {
        int items;
        correctAns = 0;
        if(r.getType().equals("NS")){
            items = r.getSChildren(Main.database).size();
        } else {
            items = r.getTChildren(Main.database).size();
        }
        if(items < 4) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Resource Error");
            if (items == 0) {
                alert.setHeaderText("The resource is empty. Please add some items to the resource to run a study session.");
            }
            else {
                alert.setHeaderText("The session cannot be launched as the resource only has " + items + " items in it. Please add more.");
            }
            alert.showAndWait();
        } else {
            if (r.getType().equals("TD")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Resource Error");
                alert.setHeaderText("Sorry, you cannot use this study session for this type of resource. Please choose another type of session.");
                alert.showAndWait();
            } else {
                int correctAns = 0;
                getSessionItems(r);
                Main.stage.setScene(LearningSessionView.view(r, "Blanks"));
            }
        }
    }
}
