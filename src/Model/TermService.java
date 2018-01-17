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
                        targetList.add(new Term(
                                results.getInt("TermID"),
                                results.getString("TermDescription"),
                                results.getInt("ResourceID")));
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

    public static Term selectById(int iD, DatabaseConnection database) {
        Term result = null;

        PreparedStatement statement = database.newStatement("SELECT TermDescription, ResourceID FROM Term WHERE TermID = ?");

        try {
            if (statement != null) {

                statement.setInt(1, iD);
                ResultSet results = database.executeQuery(statement);

                if (results != null) {
                    result = new Term(iD,results.getString("TermDescription"), results.getInt("ResourceID"));
                }
            }
        } catch (SQLException resultsException) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Database Error");
            error.setHeaderText("There seems to have been an error with the database:");
            error.setContentText("Select all error: " + resultsException.getMessage());
        }

        return result;
    }

    public static void save(Term itemToSave, DatabaseConnection database) {
        Term existingItem = null;

        if (itemToSave.getiD() != 0) existingItem = selectById(itemToSave.getiD(), database);
        try {
            if (existingItem == null) {
                PreparedStatement statement = database.newStatement("INSERT INTO Term (TermID, TermDescription, ResourceID) VALUES (?, ?, ?)");
                statement.setInt(1, itemToSave.getiD());
                statement.setString(2, itemToSave.getContent());
                statement.setInt(3, itemToSave.getParent());
                database.executeUpdate(statement);
            }
            else {
                PreparedStatement statement = database.newStatement("UPDATE Term SET TermDescription = ?, ResourceID = ? WHERE TermID = ?");
                statement.setString(1, itemToSave.getContent());
                statement.setInt(2, itemToSave.getParent());
                statement.setInt(3, itemToSave.getiD());
                database.executeUpdate(statement);
            }
        } catch (SQLException resultsException) {
            System.out.println("Database saving error: " + resultsException.getMessage());
        }
    }

    public static void delete(int iD, DatabaseConnection database) {
        //needs to also delete corresponding answers
        PreparedStatement statement2 = database.newStatement("DELETE FROM Definition WHERE TermID = ?");
        PreparedStatement statement1 = database.newStatement("DELETE FROM Term WHERE TermID = ?");
        try {
            if (statement1 != null && statement2 != null) {
                statement1.setInt(1, iD);
                statement2.setInt(1, iD);
                database.executeUpdate(statement2);
                database.executeUpdate(statement1);
            }
        } catch (SQLException resultsException) {
            System.out.println("Database deletion error: " + resultsException.getMessage());
        }
    }
}
