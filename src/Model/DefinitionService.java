package Model;

import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DefinitionService {
    public static void selectAll(List<Definition> targetList, DatabaseConnection database) {

        PreparedStatement statement = database.newStatement(  "SELECT DefinitionID, Description, TermID, CorrectNo, AppearanceNo, DateLast, StatusLast FROM Definition");

        try {
            if (statement != null) {
                ResultSet results = database.executeQuery(statement);

                if (results != null) {
                    while (results.next()) {
                        targetList.add(new Definition(
                                results.getInt("DefinitionID"),
                                results.getString("Description"),
                                results.getInt("CorrectNo"),
                                results.getInt("AppearanceNo"),
                                100,
                                results.getString("DateLast"),
                                results.getBoolean("StatusLast"),
                                results.getInt("TermID")));
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

    public static Definition selectById(int iD, DatabaseConnection database) {

        Definition result = null;

        PreparedStatement statement = database.newStatement("SELECT Description, TermID, CorrectNo, AppearanceNo, DateLast, StatusLast FROM Definition WHERE DefinitionID = ?");

        try {
            if (statement != null) {

                statement.setInt(1, iD);
                ResultSet results = database.executeQuery(statement);

                if (results != null) {
                    result = new Definition(iD,results.getString("Description"), results.getInt("CorrectNo"), results.getInt("AppearanceNo"), 100, results.getString("DateLast"), results.getBoolean("StatusLast"),results.getInt("TermID"));
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

    public static void save(Definition itemToSave, DatabaseConnection database) {
        Definition existingItem = null;

        if (itemToSave.getiD() != 0) existingItem = selectById(itemToSave.getiD(), database);
        try {
            if (existingItem == null) {
                PreparedStatement statement = database.newStatement("INSERT INTO Definition (DefinitionID, Description, TermID, CorrectNo, AppearanceNo, DateLast, StatusLast) VALUES (?, ?, ?, ?, ?, ?, ?)");
                statement.setInt(1, itemToSave.getiD());
                statement.setString(2, itemToSave.getDesc());
                statement.setInt(3, itemToSave.getParent());
                statement.setInt(4, itemToSave.getCorrect());
                statement.setInt(5, itemToSave.getAppear());
                statement.setString(6, itemToSave.getDate());
                statement.setBoolean(7, itemToSave.getStatus());
                database.executeUpdate(statement);
            }
            else {
                PreparedStatement statement = database.newStatement("UPDATE Definition SET Description = ?, TermID = ?, CorrectNo = ?, AppearanceNO = ?, DateLast = ?, StatusLast = ? WHERE DefinitionID = ?");
                statement.setString(1, itemToSave.getDesc());
                statement.setInt(2, itemToSave.getParent());
                statement.setInt(3, itemToSave.getCorrect());
                statement.setInt(4, itemToSave.getAppear());
                statement.setString(5, itemToSave.getDate());
                statement.setBoolean(6,itemToSave.getStatus());
                statement.setInt(7, itemToSave.getiD());
                database.executeUpdate(statement);
            }
        } catch (SQLException resultsException) {
            System.out.println("Database saving error: " + resultsException.getMessage());
        }


    }

    public static void delete(int iD, DatabaseConnection database) {
        PreparedStatement statement = database.newStatement("DELETE FROM Definition WHERE DefinitionID = ?");
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
