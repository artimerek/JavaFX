package pl.artimerek;

import pl.artimerek.model.Artist;
import pl.artimerek.model.Datasource;

import java.util.List;

// TODO: 07.09.2021 albums, artists, songs classes 

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
        System.out.println("Artist: " + "Carole King\nAlbums: ");
        for (String album : albums){
            System.out.println(album);
        }

        datasource.closeConnection();
    }
}
