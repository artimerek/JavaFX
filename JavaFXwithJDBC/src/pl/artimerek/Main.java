package pl.artimerek;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
       /* Connecting to db and creating statement instance for using sql's.
          Connection is automatically closed cause of try from version 8 of java
       */
	    try(Connection connection = DriverManager.getConnection("jdbc:sqlite:.\\mydb.db");
            Statement statement = connection.createStatement()){
	        // If false changes aren't be commited for example next row wont be added after changing name
//	        connection.setAutoCommit(false);
            statement.execute("CREATE TABLE IF NOT EXISTS " +
                    "contacts (name TEXT, phone INTEGER, email TEXT)");
//            statement.execute("INSERT INTO contacts (name, phone, email) VALUES " +
//                    "('Konrad',123456789,'default@defaul.com')");
//            statement.execute("UPDATE contacts SET phone = 666666666 WHERE  name = 'Bronisław'");
//            statement.execute("DELETE  FROM contacts  WHERE name = 'Maciek'");

           // Getting records from db saves them to result set then iterating and printing
            statement.execute("SELECT * FROM contacts");
            ResultSet resultSet = statement.getResultSet();
            while(resultSet.next()){
                System.out.println(resultSet.getString("name") + " " +
                        resultSet.getInt("phone") + " " +
                        resultSet.getString("email"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
