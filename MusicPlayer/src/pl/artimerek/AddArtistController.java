package pl.artimerek;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import pl.artimerek.model.Datasource;

import java.sql.SQLException;

public class AddArtistController {
    @FXML
    private TextField name;

    public void processResults() throws SQLException {
        String nameOfArtist = name.getText().trim();
        Datasource.getInstance().insertArtist(nameOfArtist);
    }
}
