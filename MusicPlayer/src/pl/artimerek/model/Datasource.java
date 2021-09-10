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
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";
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
    public static final String COLUMN_SONG_TRACK = "track";
    public static final String COLUMN_SONG_TITLE = "title";
    public static final String COLUMN_SONG_ALBUM = "album";
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
                    " INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST +
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


    public static final String QUERY_VIEW_SONG_INFO_PREP = "SELECT " + COLUMN_ARTIST_NAME + ", " +
            COLUMN_SONG_ALBUM + ", " + COLUMN_SONG_TRACK + " FROM " + TABLE_ARTIST_SONG_VIEW +
            " WHERE " + COLUMN_SONG_TITLE + " = ?";

    // Inserting

    public static final String INSERT_ARTIST = "INSERT INTO " + TABLE_ARTISTS +
            '(' + COLUMN_ARTIST_NAME + ") VALUES(?)";
    public static final String INSERT_ALBUMS = "INSERT INTO " + TABLE_ALBUMS +
            '(' + COLUMN_ALBUM_NAME + ", " + COLUMN_ALBUM_ARTIST + ") VALUES(?, ?)";

    public static final String INSERT_SONGS = "INSERT INTO " + TABLE_SONGS +
            '(' + COLUMN_SONG_TRACK + ", " + COLUMN_SONG_TITLE + ", " + COLUMN_SONG_ALBUM +
            ") VALUES(?, ?, ?)";

    public static final String QUERY_ARTIST = "SELECT " + COLUMN_ARTIST_ID + " FROM " +
            TABLE_ARTISTS + " WHERE " + COLUMN_ARTIST_NAME + " = ?";

    public static final String QUERY_ALBUM = "SELECT " + COLUMN_ALBUM_ID + " FROM " +
            TABLE_ALBUMS + " WHERE " + COLUMN_ALBUM_NAME + " = ?";

    // Connection

    private Connection connection;

    //  Prepared Statements to avoid sql inj attacks


    // View
    private PreparedStatement querySongInfoView;

    // Inserting records
    private PreparedStatement insertArtist;
    private PreparedStatement insertAlbums;
    private PreparedStatement insertSongs;

    // Checking tables id's
    private PreparedStatement queryArtist;
    private PreparedStatement queryAlbum;

    public boolean openConnection() {
        try {
            connection = DriverManager.getConnection(CONNECTION);
            System.out.println("Connection opened");
            querySongInfoView = connection.prepareStatement(QUERY_VIEW_SONG_INFO_PREP);
            insertArtist = connection.prepareStatement(INSERT_ARTIST, Statement.RETURN_GENERATED_KEYS);
            insertAlbums = connection.prepareStatement(INSERT_ALBUMS, Statement.RETURN_GENERATED_KEYS);
            insertSongs = connection.prepareStatement(INSERT_SONGS);
            queryArtist = connection.prepareStatement(QUERY_ARTIST);
            queryAlbum = connection.prepareStatement(QUERY_ALBUM);
            return true;
        } catch (SQLException throwables) {
            System.out.println("Can't connect to db");
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
            return false;
        }
    }

    //  Firstly we have to close querySongInfoView not connection. The order here is important

    public void closeConnection() {
        try {
            if(querySongInfoView != null){
                querySongInfoView.close();
            }
            if (insertArtist != null){
                insertArtist.close();
            }
            if (insertAlbums != null){
                insertAlbums.close();
            }
            if (insertSongs != null){
                insertSongs.close();
            }
            if(queryArtist != null){
                queryArtist.close();
            }
            if(queryAlbum != null){
                queryAlbum.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException throwables) {
            System.out.println("Can't close connection with db");
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
        }
    }


    // try with resources helps with closing
    public List<Artist> queryArtists(int sortOrder) {

        // StringBuilder for creating queries

        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM ");
        stringBuilder.append(TABLE_ARTISTS);
        if (sortOrder != ORDER_BY_NONE) {
            stringBuilder.append(" ORDER BY ");
            stringBuilder.append(COLUMN_ARTIST_NAME);
            stringBuilder.append(" COLLATE NOCASE ");
            if (sortOrder == ORDER_BY_DESC) {
                stringBuilder.append("DESC");
            } else {
                stringBuilder.append("ASC");
            }
        }

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(stringBuilder.toString())) {
            List<Artist> artists = new ArrayList<>();
            while (resultSet.next()) {
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

    public List<String> queryAlbumsForArtists(String artistName, int sortOrder) {
        StringBuilder stringBuilder = new StringBuilder(QUERY_ALBUMS_BY_ARTIST_START);
        stringBuilder.append(artistName);
        stringBuilder.append("\"");

        if (sortOrder != ORDER_BY_NONE) {
            stringBuilder.append(QUERY_ALBUMS_BY_ARTIST_SORT);
            if (sortOrder == ORDER_BY_DESC) {
                stringBuilder.append("DESC");
            } else {
                stringBuilder.append("ASC");
            }
        }

        System.out.println("SQL Statement = " + stringBuilder.toString());

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(stringBuilder.toString())) {
            List<String> albums = new ArrayList<>();
            while (resultSet.next()) {
                albums.add(resultSet.getString(1));
            }
            return albums;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(throwables.getMessage());
            return null;
        }
    }

    public List<SongArtist> queryArtistForSong(String songName, int sortOrder) {
        StringBuilder stringBuilder = new StringBuilder(QUERY_ARTIST_FOR_SONG_START);
        stringBuilder.append(songName);
        stringBuilder.append("\"");

        if (sortOrder != ORDER_BY_NONE) {
            stringBuilder.append(QUERY_ARTIST_FOR_SONG_SORT);
            if (sortOrder == ORDER_BY_DESC) {
                stringBuilder.append("DESC");
            } else {
                stringBuilder.append("ASC");
            }
        }

        System.out.println(stringBuilder.toString());

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(stringBuilder.toString())) {

            List<SongArtist> songArtists = new ArrayList<>();

            while (resultSet.next()) {
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
    public void querySongsMetadata() {
        String sql = "SELECT * FROM " + TABLE_SONGS;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int numColumns = resultSetMetaData.getColumnCount();
            for (int i = 1; i < numColumns; i++) {
                System.out.println("Column in songs table no." + i + " " + resultSetMetaData.getColumnName(i));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(throwables.getMessage());
        }
    }

    // Simply method for retrieve amount of records in given table name
    public int getCount(String table) {
        String sql = "SELECT COUNT(*) AS count, MIN(_id) AS min_id FROM " + table;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            int count = resultSet.getInt("count");
            int min = resultSet.getInt("min_id");
            return count;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(throwables.getMessage());
            return -1;
        }
    }

    public List<SongArtist> querySongInfoView(String title) {

        try {
            querySongInfoView.setString(1, title);
            ResultSet resultSet = querySongInfoView.executeQuery();

            List<SongArtist> songArtists = new ArrayList<>();
            while (resultSet.next()) {
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

    public boolean createViewForSongArtists() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(CREATE_ARTIST_FOR_SONG_VIEW);
            return true;
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            return false;
        }
    }


    // User should pass name of wanted artist, if this artist isn't exist method will insert him
    private int insertArtist(String name) throws SQLException {
        queryArtist.setString(1, name);
        ResultSet resultSet = queryArtist.executeQuery();
        if(resultSet.next()){
            return resultSet.getInt(1);
        }else {
            insertArtist.setString(1, name);
            int affectedRows =  insertArtist.executeUpdate();

            if(affectedRows != 1){
                throw new SQLException("Problem with inserting artist");
            }

            ResultSet generatedKey = insertArtist.getGeneratedKeys();
            if(generatedKey.next()){
                return generatedKey.getInt(1);
            }else {
                throw new SQLException("Problem with getting id for artist");
            }
        }
    }

    // Similar method to method above but for inserting albums
    private int insertAlbum(String name, int artistId) throws SQLException {
        queryAlbum.setString(1, name);
        ResultSet resultSet = queryAlbum.executeQuery();
        if(resultSet.next()){
            return resultSet.getInt(1);
        }else {
            insertAlbums.setString(1, name);
            insertAlbums.setInt(2, artistId);
            int affectedRows =  insertAlbums.executeUpdate();

            if(affectedRows != 1){
                throw new SQLException("Problem with inserting album");
            }

            ResultSet generatedKey = insertAlbums.getGeneratedKeys();
            if(generatedKey.next()){
                return generatedKey.getInt(1);
            }else {
                throw new SQLException("Problem with getting id for album");
            }
        }
    }

    // Transaction test
    public void insertSong(String name, String artistName, String albumName, int track) {
        try{
            connection.setAutoCommit(false);

            int artistId = insertArtist(artistName);
            int albumId = insertAlbum(albumName, artistId);
            insertSongs.setInt(1, track);
            insertSongs.setString(2, name);
            insertSongs.setInt(3, albumId);
            int affectedRows =  insertSongs.executeUpdate();
            if(affectedRows == 1){
                connection.commit();
            }else {
                throw new SQLException("Problem with inserting song");
            }

        }catch (SQLException throwables){
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
            try{
                connection.rollback();
            }catch (SQLException throwables1){
                System.out.println(throwables1.getMessage());
            }
        }finally {
            try{
                connection.setAutoCommit(true);
            }catch (SQLException throwables){
                System.out.println(throwables.getMessage());
            }
        }
    }
}
