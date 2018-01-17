package Model;

import javafx.scene.control.Alert;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationService {
    public static void getConfigurations(ArrayList<Configuration> settingConfigurations, DatabaseConnection database){
        PreparedStatement statement = database.newStatement(  "SELECT SettingName, SettingValue FROM Configuration");

        try {
            if (statement != null) {
                ResultSet results = database.executeQuery(statement);

                if (results != null) {
                    while (results.next()) {
                        System.out.println("Adding new config"+results.getString("SettingName")+results.getString("SettingValue"));
                        settingConfigurations.add(new Configuration(
                                results.getString("SettingName"),
                                results.getString("SettingValue")));
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
    public static void setConfigurations(ArrayList<Configuration> settingConfigurations, DatabaseConnection database) {
        try {
            for (int i=0;i<settingConfigurations.size();i++) {
                PreparedStatement statement = database.newStatement("UPDATE Configuration SET SettingValue = ? WHERE SettingName = ?");
                statement.setString(1, settingConfigurations.get(i).getSettingValue());
                statement.setString(2, settingConfigurations.get(i).getSettingName());
                database.executeUpdate(statement);
            }
        } catch (SQLException resultsException) {
            System.out.println("Database saving error: " + resultsException.getMessage());
        }
    }
}
