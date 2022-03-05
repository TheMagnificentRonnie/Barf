package Database.Data.Utils;

import java.sql.*;

public class ExampleGetter {

    public static String getExample(String URL, String schemaName, String tableName, String columnName) {
        String example = "";

        try {
            String url = URL;
            Connection conn = DriverManager.getConnection(url, "sa", "Banana12");
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery(String.format("SELECT top 1 [%s] FROM [%s].[%s]", columnName, schemaName, tableName));
            while (rs.next()) {
                example = rs.getString(columnName);

            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        if (example == null) {
            return "example";
        }


        if (example.length() > 50) {
            return "example";
        } else {
            return example;
        }
    }
}
