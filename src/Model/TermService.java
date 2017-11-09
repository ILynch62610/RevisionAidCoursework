package Model;

import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TermService {
    public static void selectAll(List<Term> targetList, DatabaseConnection database) {

        PreparedStatement statement = database.newStatement("SELECT TermID, TermDescription, ResourceID FROM Term");

        try {
            if (statement != null) {
                ResultSet results = database.executeQuery(statement);

                if (results != null) {
                    while (results.next()) {
                        targetList.add(new Term(results.getInt("TermID"), results.getString("TermDescription"), results.getInt("ResourceID")));
                    }
                }
            }
        } catch (SQLException resultsException) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Database Error");
            error.setHeaderText("There seems to have been an error with the database:");
            error.setContentText("Select all error: " + resultsException.getMessage());
        }
    }

    public static void selectByID() {

    }

    public static void save() {

    }

    public static void delete() {
        //needs to also delete corresponding answers
    }

    public static ArrayList<String> getAnswers() {
        ArrayList<String> answers = new ArrayList<>();
        return answers;
    }
}
