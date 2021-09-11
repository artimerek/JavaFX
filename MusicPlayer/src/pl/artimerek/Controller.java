package pl.artimerek;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import pl.artimerek.model.Album;
import pl.artimerek.model.Artist;
import pl.artimerek.model.Datasource;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class Controller {
    @FXML
    private TableView artistTableView;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private BorderPane mainBorderPane;


    @FXML
    public void updateArtist(){
        final Artist artist = (Artist) artistTableView.getItems().get(2);
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                return Datasource.getInstance().updateArtistName(artist.getId(), "AC/DC");
            }
        };

        task.setOnSucceeded(e -> {
            if (task.valueProperty().get()) {
                artist.setName("AC/DC");
                artistTableView.refresh();
            }
        });

        new Thread(task).start();
    }

    @FXML
    public void addArtist() throws SQLException {
        showAddArtistDialog();
    }

    @FXML
    public void showAddArtistDialog() throws SQLException {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("addArtistdialog.fxml"));
        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            AddArtistController controller = fxmlLoader.getController();
            controller.processResults();
            System.out.println("OK pressed");
        }

        Task<ObservableList<Artist>> task = new GetAllArtistTask();
        artistTableView.itemsProperty().bind(task.valueProperty());
        progressBar.progressProperty().bind(task.progressProperty());
        progressBar.setVisible(true);

        task.setOnSucceeded(e -> progressBar.setVisible(false));
        task.setOnFailed(e -> progressBar.setVisible(false));
        new Thread(task).start();
    }

    @FXML
    public void listArtist(){
        Task<ObservableList<Artist>> task = new GetAllArtistTask();
        artistTableView.itemsProperty().bind(task.valueProperty());
        progressBar.progressProperty().bind(task.progressProperty());
        progressBar.setVisible(true);

        task.setOnSucceeded(e -> progressBar.setVisible(false));
        task.setOnFailed(e -> progressBar.setVisible(false));

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
