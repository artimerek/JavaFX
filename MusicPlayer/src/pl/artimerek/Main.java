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

        List<Artist> artists = datasource.queryArtists();
        for (Artist artist : artists){
            System.out.println(artist.getId() + " " + artist.getName());
        }

        datasource.closeConnection();
    }
}
