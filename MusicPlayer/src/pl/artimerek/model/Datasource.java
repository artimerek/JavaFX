package pl.artimerek.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource {
    //  Constants for DB connection, tables etc.
    public static final String DATABASE_NAME = "music.db";

    public static final String CONNECTION = "jdbc:sqlite:.\\" + DATABASE_NAME;

    //  Tables, columns
    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME= "name";
    public static final String COLUMN_ALBUM_ARTIST= "artist";
    public static final int INDEX_ALBUM_ID = 1;
    public static final int INDEX_ALBUM_NAME = 2;
    public static final int INDEX_ALBUM_ARTIST = 3;

    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTIST_ID = "_id";
    public static final String COLUMN_ARTIST_NAME = "name";
    public static final int INDEX_ARTIST_ID = 1;
    public static final int INDEX_ARTIST_NAME = 2;

    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONG_ID = "_id";
    public static final String COLUMN_SONG_TRACK= "track";
    public static final String COLUMN_SONG_TITLE= "title";
    public static final String COLUMN_SONG_ALBUM= "album";
    public static final int INDEX_SONG_ID = 1;
    public static final int INDEX_SONG_TRACK = 2;
    public static final int INDEX_SONG_TITLE = 3;
    public static final int INDEX_SONG_ALBUM = 4;


    // How user want to sort records
    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    public static final String QUERY_ALBUMS_BY_ARTIST_START =
            "SELECT " + TABLE_ALBUMS + '.' + COLUMN_ALBUM_NAME + " FROM " + TABLE_ALBUMS +
                    " INNER JOIN " +TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST +
                    " = " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID +
                    " WHERE " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + " = \"";

    public static final String QUERY_ALBUMS_BY_ARTIST_SORT =
            " ORDER BY " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " COLLATE NOCASE ";

    public static final String QUERY_ARTIST_FOR_SONG_START =
            "SELECT " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " +
                    TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + ", " +
                    TABLE_SONGS + "." + COLUMN_SONG_TRACK + " FROM " + TABLE_SONGS +
                    " INNER JOIN " + TABLE_ALBUMS + " ON " +
                    TABLE_SONGS + "." + COLUMN_SONG_ALBUM + " = " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ID +
                    " INNER JOIN " + TABLE_ARTISTS + " ON " +
                    TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + " = " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID +
                    " WHERE " + TABLE_SONGS + "." + COLUMN_SONG_TITLE + " = \"";

    public static final String QUERY_ARTIST_FOR_SONG_SORT =
            " ORDER BY " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " +
                    TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " COLLATE NOCASE ";

    public static final String TABLE_ARTIST_SONG_VIEW = "artist_list";

    public static final String CREATE_ARTIST_FOR_SONG_VIEW = "CREATE VIEW IF NOT EXISTS " +
            TABLE_ARTIST_SONG_VIEW + " AS SELECT " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " +
            TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " AS " + COLUMN_SONG_ALBUM + ", " +
            TABLE_SONGS + "." + COLUMN_SONG_TRACK + ", " + TABLE_SONGS + "." + COLUMN_SONG_TITLE +
            " FROM " + TABLE_SONGS +
            " INNER JOIN " + TABLE_ALBUMS + " ON " + TABLE_SONGS +
            "." + COLUMN_SONG_ALBUM + " = " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ID +
            " INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST +
            " = " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID +
            " ORDER BY " +
            TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " +
            TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + ", " +
            TABLE_SONGS + "." + COLUMN_SONG_TRACK;

    public static final String QUERY_VIEW_SONG_INFO = "SELECT " + COLUMN_ARTIST_NAME + ", " +
            COLUMN_SONG_ALBUM + ", " + COLUMN_SONG_TRACK + " FROM " + TABLE_ARTIST_SONG_VIEW +
            " WHERE " + COLUMN_SONG_TITLE + " = \"";


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


    // try with resources helps with closing
    public List<Artist> queryArtists(int sortOrder){

        // StringBuilder for creating queries

        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM ");
        stringBuilder.append(TABLE_ARTISTS);
        if(sortOrder != ORDER_BY_NONE){
            stringBuilder.append(" ORDER BY ");
            stringBuilder.append(COLUMN_ARTIST_NAME);
            stringBuilder.append(" COLLATE NOCASE ");
            if(sortOrder == ORDER_BY_DESC){
                stringBuilder.append("DESC");
            }else {
                stringBuilder.append("ASC");
            }
        }

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(stringBuilder.toString())){
            List<Artist> artists = new ArrayList<>();
            while(resultSet.next()){
                Artist artist = new Artist();
                artist.setId(resultSet.getInt(INDEX_ARTIST_ID));
                artist.setName(resultSet.getString(INDEX_ARTIST_NAME));
                artists.add(artist);
            }

            return artists;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(throwables.getMessage());
            return null;
        }
    }

    public List<String> queryAlbumsForArtists(String artistName, int sortOrder){
        StringBuilder stringBuilder = new StringBuilder(QUERY_ALBUMS_BY_ARTIST_START);
        stringBuilder.append(artistName);
        stringBuilder.append("\"");

        if(sortOrder != ORDER_BY_NONE){
            stringBuilder.append(QUERY_ALBUMS_BY_ARTIST_SORT);
            if(sortOrder == ORDER_BY_DESC){
                stringBuilder.append("DESC");
            }else {
                stringBuilder.append("ASC");
            }
        }

        System.out.println("SQL Statement = " + stringBuilder.toString());

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(stringBuilder.toString())) {
            List<String> albums = new ArrayList<>();
            while(resultSet.next()){
                albums.add(resultSet.getString(1));
            }
            return albums;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(throwables.getMessage());
            return null;
        }
    }

    public List<SongArtist> queryArtistForSong(String songName, int sortOrder){
        StringBuilder stringBuilder = new StringBuilder(QUERY_ARTIST_FOR_SONG_START);
        stringBuilder.append(songName);
        stringBuilder.append("\"");

        if(sortOrder != ORDER_BY_NONE){
            stringBuilder.append(QUERY_ARTIST_FOR_SONG_SORT);
            if(sortOrder == ORDER_BY_DESC){
                stringBuilder.append("DESC");
            }else {
                stringBuilder.append("ASC");
            }
        }

        System.out.println(stringBuilder.toString());

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(stringBuilder.toString())){

            List<SongArtist> songArtists = new ArrayList<>();

            while(resultSet.next()){
                SongArtist songArtist = new SongArtist();
                songArtist.setArtistName(resultSet.getString(1));
                songArtist.setAlbumName(resultSet.getString(2));
                songArtist.setTrack(resultSet.getInt(3));
                songArtists.add(songArtist);
            }

            return songArtists;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(throwables.getMessage());
            return null;
        }
    }

    // info about rows, columns etc
    public void querySongsMetadata(){
        String sql = "SELECT * FROM " + TABLE_SONGS;

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int numColumns = resultSetMetaData.getColumnCount();
            for(int i = 1; i < numColumns; i++){
                System.out.println("Column in songs table no."+ i + " " + resultSetMetaData.getColumnName(i));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(throwables.getMessage());
        }
    }

    // Simply method for retrieve amount of records in given table name

    public int getCount(String table){
        String sql = "SELECT COUNT(*) AS count, MIN(_id) AS min_id FROM " + table;
        try(Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql)){
            int count = resultSet.getInt("count");
            int min = resultSet.getInt("min_id");
            return count;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(throwables.getMessage());
            return -1;
        }
    }

    public List<SongArtist> querySongInfoView(String title){
        StringBuilder stringBuilder = new StringBuilder(QUERY_VIEW_SONG_INFO);
        stringBuilder.append(title);
        stringBuilder.append("\"");

        System.out.println(stringBuilder.toString());

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(stringBuilder.toString())){
            List<SongArtist> songArtists = new ArrayList<>();
            while (resultSet.next()){
                SongArtist songArtist =  new SongArtist();
                songArtist.setArtistName(resultSet.getString(1));
                songArtist.setAlbumName(resultSet.getString(2));
                songArtist.setTrack(resultSet.getInt(3));
                songArtists.add(songArtist);
            }
            return songArtists;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(throwables.getMessage());
            return null;
        }
    }

    public boolean createViewForSongArtists() {
        try(Statement statement = connection.createStatement()) {
            statement.execute(CREATE_ARTIST_FOR_SONG_VIEW);
            return true;
        } catch(SQLException throwables) {
            System.out.println(throwables.getMessage());
            return false;
        }
    }
}
