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

        PreparedStatement statement = database.newStatement(  "SELECT DefinitionID, " +
                                                                            "Description, " +
                                                                            "TermID, " +
                                                                            "CorrectNo, " +
                                                                            "AppearanceNo, " +
                                                                            "DateLast, " +
                                                                            "StatusLast " +
                                                                                "FROM Definition");

        try {
            if (statement != null) {
                ResultSet results = database.executeQuery(statement);

                if (results != null) {
                    while (results.next()) {

                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        Date testDate = null;
                        try {
                            testDate = formatter.parse(results.getString("DateLast"));
                            System.out.println(results.getString("DateLast")
                                         + " -> " + testDate.toString()
                                            + " -> " + formatter.format(testDate));


                        }
                        catch (ParseException parp) {
                            System.out.print ("Can't convert date: " + parp.getMessage());
                        }


                        targetList.add(new Definition(results.getInt("DefinitionID"),
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

    public static Definition selectById(int id, DatabaseConnection database) {

        Definition result = null;

        PreparedStatement statement = database.newStatement("SELECT Description, TermID, CorrectNo, AppearanceNo, DateLast, StatusLast FROM Definition WHERE id ="+id);

        try {
            if (statement != null) {

                statement.setInt(1, id);
                ResultSet results = database.executeQuery(statement);

                if (results != null) {
                    result = new Definition(id,results.getString("Description"), results.getInt("CorrectNo"), results.getInt("AppearanceNo"), 100, results.getString("DateLast"), results.getBoolean("StatusLast"),results.getInt("TermID"));
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

    public static void save() {

    }

    public static void delete() {

    }
}
