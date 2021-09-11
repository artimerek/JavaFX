package pl.artimerek;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import pl.artimerek.model.Album;
import pl.artimerek.model.Artist;
import pl.artimerek.model.Datasource;

public class Controller {
    @FXML
    private TableView artistTableView;


    @FXML
    public void listArtist(){
        Task<ObservableList<Artist>> task = new GetAllArtistTask();
        artistTableView.itemsProperty().bind(task.valueProperty());
        new Thread(task).start();
    }

    @FXML
    public void listAlbumsForArtist(){
        final Artist artist = (Artist) artistTableView.getSelectionModel().getSelectedItem();
        if(artist == null){
            System.out.println("No artist selected");
            return;
        }

        Task<ObservableList<Album>> task = new Task<ObservableList<Album>>() {
            @Override
            protected ObservableList<Album> call() throws Exception {
                return FXCollections.observableArrayList(Datasource.getInstance()
                        .queryAlbumForArtistId(artist.getId()));
            }
        };
        artistTableView.itemsProperty().bind(task.valueProperty());

        new Thread(task).start();
    }
}

class GetAllArtistTask extends Task{

    @Override
    protected ObservableList<Artist> call() {
        return FXCollections.observableArrayList(Datasource.getInstance()
                            .queryArtists(Datasource.ORDER_BY_ASC));
    }
}
