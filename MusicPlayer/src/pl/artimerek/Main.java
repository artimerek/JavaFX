package pl.artimerek;

import pl.artimerek.model.Datasource;

// TODO: 07.09.2021 albums, artists, songs classes 

public class Main {

    public static void main(String[] args) {
        Datasource datasource = new Datasource();
        if(!datasource.openConnection()){
            System.out.println("Can't open datasource");
            return;
        }

        datasource.closeConnection();
    }
}
