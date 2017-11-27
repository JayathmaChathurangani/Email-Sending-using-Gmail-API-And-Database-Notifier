package database.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Check_LM_LIBRARY {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public String readDatabase(){
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("");

            preparedStatement = connect
                    .prepareStatement("SELECT * from TestLicenceManager.LM_LIBRARY WHERE LM_ARTIFACT_ID IS NULL OR LM_GROUP_ID  order by LIB_ID");
            resultSet = preparedStatement.executeQuery();

            String result = MailBody.createEmailBody(resultSet);

            return result;
        } catch (Exception e) {
            System.out.println("Error in read database: "+ e.getMessage());
        } finally {
            close();
        }
        return "Error";
    }

    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {
            System.out.println("Error in database close: "+ e.getMessage());
        }
    }
}
