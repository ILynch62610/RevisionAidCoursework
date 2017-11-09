package Model;

import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SentenceService {
    public static void selectAll(List<Sentence> targetList, DatabaseConnection database) {

        PreparedStatement statement = database.newStatement("SELECT SentenceID, Content, CorrectNo, AppearanceNo, DateLast, StatusLast, ResourceID FROM Sentence");

        try {
            if (statement != null) {
                ResultSet results = database.executeQuery(statement);

                if (results != null) {
                    while (results.next()) {
                        targetList.add(new Sentence(results.getInt("SentenceID"), results.getString("Content"), results.getInt("CorrectNo"), results.getInt("AppearanceNo"), 100, results.getDate("DateLast"), results.getBoolean("StatusLast"),results.getInt("ResourceID")));
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

    }
}
