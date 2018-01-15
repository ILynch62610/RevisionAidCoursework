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
                        targetList.add(new Sentence(
                                results.getInt("SentenceID"),
                                results.getString("Content"),
                                results.getInt("CorrectNo"),
                                results.getInt("AppearanceNo"),
                                100,
                                results.getString("DateLast"),
                                results.getBoolean("StatusLast"),
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

    public static Sentence selectById(int iD, DatabaseConnection database) {
        Sentence result = null;

        PreparedStatement statement = database.newStatement("SELECT Content, CorrectNo, AppearanceNo, DateLast, StatusLast, ResourceID FROM Sentence WHERE SentenceID = ?");

        try {
            if (statement != null) {

                statement.setInt(1, iD);
                ResultSet results = database.executeQuery(statement);

                if (results != null) {
                    result = new Sentence(iD,results.getString("Content"), results.getInt("CorrectNo"), results.getInt("AppearanceNo"), 100, results.getString("DateLast"), results.getBoolean("StatusLast"),results.getInt("ResourceID"));
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

    public static void save(Sentence itemToSave, DatabaseConnection database) {
        Sentence existingItem = null;

        if (itemToSave.getiD() != 0) existingItem = selectById(itemToSave.getiD(), database);
        try {
            if (existingItem == null) {
                PreparedStatement statement = database.newStatement("INSERT INTO Sentence (SentenceID, Content, CorrectNo, AppearanceNo, DateLast, StatusLast, ResourceID) VALUES (?, ?, ?, ?, ?, ?, ?)");
                statement.setInt(1, itemToSave.getiD());
                statement.setString(2, itemToSave.getContent());
                statement.setInt(3, itemToSave.getCorrect());
                statement.setInt(4, itemToSave.getAppear());
                statement.setString(5, itemToSave.getDate());
                statement.setBoolean(6, itemToSave.getStatus());
                statement.setInt(7,itemToSave.getiD());
                database.executeUpdate(statement);
            }
            else {
                PreparedStatement statement = database.newStatement("UPDATE Sentence SET Content = ?, CorrectNo = ?, AppearanceNO = ?, DateLast = ?, StatusLast = ?, ResourceID = ? WHERE SentenceID = ?");
                statement.setString(1, itemToSave.getContent());
                statement.setInt(2, itemToSave.getCorrect());
                statement.setInt(3, itemToSave.getAppear());
                statement.setString(4, itemToSave.getDate());
                statement.setBoolean(5,itemToSave.getStatus());
                statement.setInt(6, itemToSave.getParent());
                statement.setInt(7, itemToSave.getiD());
                database.executeUpdate(statement);
            }
        } catch (SQLException resultsException) {
            System.out.println("Database saving error: " + resultsException.getMessage());
        }
    }

    public static void delete(int iD, DatabaseConnection database) {
        PreparedStatement statement = database.newStatement("DELETE FROM Sentence WHERE SentenceID = ?");
        try {
            if (statement != null) {
                statement.setInt(1, iD);
                database.executeUpdate(statement);
            }
        } catch (SQLException resultsException) {
            System.out.println("Database deletion error: " + resultsException.getMessage());
        }
    }
}
