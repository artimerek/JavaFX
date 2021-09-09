package pl.artimerek;

import pl.artimerek.model.Artist;
import pl.artimerek.model.Datasource;
import pl.artimerek.model.SongArtist;

import java.util.List;


public class Main {

    public static void main(String[] args) {
        Datasource datasource = new Datasource();
        if(!datasource.openConnection()){
            System.out.println("Can't open datasource");
            return;
        }

        List<Artist> artists = datasource.queryArtists(Datasource.ORDER_BY_NONE);
//        for (Artist artist : artists){
//            System.out.println(artist.getId() + " " + artist.getName());
//        }

        List<String> albums = datasource.queryAlbumsForArtists("Iron Maiden",1);
//        System.out.println("Artist: " + "Carole King\nAlbums: ");
//        for (String album : albums){
//            System.out.println(album);
//        }

        List<SongArtist> songArtists = datasource.queryArtistForSong("Heartless",Datasource.ORDER_BY_DESC);
        for(SongArtist songArtist : songArtists){
            System.out.println("Artist : " + songArtist.getArtistName() +
                    " Album name: " + songArtist.getAlbumName() +
                    " Track number: " + songArtist.getTrack());
        }

        datasource.querySongsMetadata();
        int count = datasource.getCount(Datasource.TABLE_ALBUMS);
        System.out.println(count);

        datasource.createViewForSongArtists();

        songArtists = datasource.querySongInfoView("Go Your Own Way");

        for(SongArtist artist : songArtists){
            System.out.println("Artist name: " + artist.getArtistName() +
                    " Album name: " + artist.getAlbumName() +
                    " Track no." + artist.getTrack());
        }

        datasource.closeConnection();
    }
}
