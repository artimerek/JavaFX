package pl.artimerek.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Datasource {
    //  Constants for DB connection, tables etc.
    public static final String DATABASE_NAME = "music.db";

    public static final String CONNECTION = "jdbc:sqlite:.\\" + DATABASE_NAME;

    //  Tables, columns
    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME= "name";
    public static final String COLUMN_ALBUM_ARTIST= "artist";

    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTIST_ID = "_id";
    public static final String COLUMN_ARTIST_NAME = "name";

    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONG_ID = "_id";
    public static final String COLUMN_SONG_TRACK= "track";
    public static final String COLUMN_SONG_TITLE= "title";
    public static final String COLUMN_SONG_ALBUM= "album";

    // Connection

    private Connection connection;

    public boolean openConnection(){
        try{
         connection = DriverManager.getConnection(CONNECTION);
            System.out.println("Connection opened");
         return true;
        } catch (SQLException throwables) {
            System.out.println("Can't connect to db");
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
            return false;
        }
    }

    public void closeConnection(){
        try {
            if(connection != null){
                connection.close();
            }
        } catch (SQLException throwables) {
            System.out.println("Can't close connection with db");
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
        }
    }

}
