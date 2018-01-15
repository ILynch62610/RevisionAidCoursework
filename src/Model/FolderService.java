package Model;

import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FolderService {
    public static void selectAll(List<Folder> targetList, DatabaseConnection database) {

        PreparedStatement statement = database.newStatement("SELECT FolderID, FolderName, FolderIcon, ParentFolderID FROM Folder");

        try {
            if (statement != null) {
                ResultSet results = database.executeQuery(statement);

                if (results != null) {
                    while (results.next()) {
                        targetList.add(new Folder(
                                results.getInt("FolderID"),
                                results.getString("FolderName"),
                                results.getString("FolderIcon"),
                                results.getInt("ParentFolderID")));
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

    public static Folder selectById(int iD, DatabaseConnection database) {
        Folder result = null;

        PreparedStatement statement = database.newStatement("SELECT FolderName, FolderIcon, ParentFolderID FROM Folder WHERE FolderID = ?");

        try {
            if (statement != null) {

                statement.setInt(1, iD);
                ResultSet results = database.executeQuery(statement);

                if (results != null) {
                    result = new Folder(iD, results.getString("FolderName"), results.getString("FolderIcon"), results.getInt("ParentFolderID"));
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

    public static void save(Folder folderToSave, DatabaseConnection database) {
        Folder existingItem = null;

        if (folderToSave.getiD() != 0) existingItem = selectById(folderToSave.getiD(), database);
        try {
            if (existingItem == null) {
                PreparedStatement statement = database.newStatement("INSERT INTO Folder (FolderID, FolderName, FolderIcon, ParentFolderID) VALUES (?, ?, ?, ?)");
                statement.setInt(1, folderToSave.getiD());
                statement.setString(2, folderToSave.getName());
                statement.setString(3, folderToSave.getIcon());
                statement.setInt(4, folderToSave.getParent());
                database.executeUpdate(statement);
            }
            else {
                PreparedStatement statement = database.newStatement("UPDATE Folder SET FolderName = ?, FolderIcon = ?, ParentFolderID = ? WHERE FolderID = ?");
                statement.setString(1, folderToSave.getName());
                statement.setString(2, folderToSave.getIcon());
                statement.setInt(3, folderToSave.getParent());
                statement.setInt(4, folderToSave.getiD());
                database.executeUpdate(statement);
            }
        } catch (SQLException resultsException) {
            System.out.println("Database saving error: " + resultsException.getMessage());
        }
    }

    public static void delete(int iD, DatabaseConnection database) {
        PreparedStatement statement = database.newStatement("DELETE FROM Folder WHERE FolderID = ?");
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
