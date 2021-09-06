package pl.artimerek;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
       /* Connecting to db and creating statement instance for using sql's.
          Connection is automatically closed cause of try from version 8 of java
       */
	    try(Connection connection = DriverManager.getConnection("jdbc:sqlite:.\\mydb.db");
            Statement statement = connection.createStatement()){
            statement.execute("CREATE TABLE contacts (name TEXT, phone INTEGER, email TEXT)");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
