package Model;

import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResourceService {
    public static void selectAll(List<Resource> targetList, DatabaseConnection database) {

        PreparedStatement statement = database.newStatement("SELECT ResourceID, ResourceName, ResourceType, DateLast, ParentFolderID FROM Resource");

        try {
            if (statement != null) {
                ResultSet results = database.executeQuery(statement);

                if (results != null) {
                    while (results.next()) {
                        targetList.add(new Resource(
                                results.getInt("ResourceID"),
                                results.getString("ResourceName"),
                                results.getString("ResourceType"),
                                results.getInt("ParentFolderID"),
                                results.getString("DateLast")));
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

    public static Resource selectById(int iD, DatabaseConnection database) {
        Resource result = null;

        PreparedStatement statement = database.newStatement("SELECT ResourceName, ResourceType, DateLast, ParentFolderID FROM Resource WHERE ResourceID = ?");

        try {
            if (statement != null) {

                statement.setInt(1, iD);
                ResultSet results = database.executeQuery(statement);

                if (results != null) {
                    result = new Resource(iD,results.getString("ResourceName"), results.getString("ResourceType"), results.getInt("ParentFolderID"), results.getString("DateLast"));
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

    public static void save(Resource itemToSave, DatabaseConnection database) {
        Resource existingItem = null;

        if (itemToSave.getiD() != 0) existingItem = selectById(itemToSave.getiD(), database);
        try {
            if (existingItem == null) {
                PreparedStatement statement = database.newStatement("INSERT INTO Resource (ResourceID, ResourceName, ResourceType, DateLast, ParentFolderID) VALUES (?, ?, ?, ?, ?)");
                statement.setInt(1, itemToSave.getiD());
                statement.setString(2, itemToSave.getName());
                statement.setString(3, itemToSave.getType());
                statement.setString(4, itemToSave.getLastUsed());
                statement.setInt(5, itemToSave.getParent());
                database.executeUpdate(statement);
            }
            else {
                PreparedStatement statement = database.newStatement("UPDATE Resource SET ResourceName = ?, ResourceType = ?, DateLast = ?, ParentFolderID = ? WHERE ResourceID = ?");
                statement.setString(1, itemToSave.getName());
                statement.setString(2, itemToSave.getType());
                statement.setString(3, itemToSave.getLastUsed());
                statement.setInt(4, itemToSave.getParent());
                statement.setInt(5, itemToSave.getiD());
                database.executeUpdate(statement);
            }
        } catch (SQLException resultsException) {
            System.out.println("Database saving error: " + resultsException.getMessage());
        }
    }

    public static void delete(int iD, DatabaseConnection database) {
        PreparedStatement statement = database.newStatement("DELETE FROM Resource WHERE ResourceID = ?");
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
