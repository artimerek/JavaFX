package todoproject;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import todoproject.data.Items;
import todoproject.data.ItemsData;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Controller {
    private List<Items> itemsTodo;

    @FXML
    private ListView<Items> listView;
    @FXML
    private TextArea centerTextArea;
    @FXML
    private Label deadLineLabel;
    @FXML
    private BorderPane mainBorderPane;

    @FXML
    public void initialize(){
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Items>() {
            @Override
            public void changed(ObservableValue<? extends Items> observableValue, Items items, Items t1) {
                if(t1 != null){
                    Items item = listView.getSelectionModel().getSelectedItem();
                    centerTextArea.setText(item.getFullDescription());
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
                    deadLineLabel.setText(dateTimeFormatter.format(item.getDeadline()));
                }
            }
        });

        listView.setItems(ItemsData.getInstance().getItems());
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);  // only 1 item can be selected at a time
        listView.getSelectionModel().selectFirst(); // display first by the default
    }

    @FXML
    public void showItemDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add new item");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("todoDialog.fxml"));
        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException e){
            System.out.println("Couldn't load dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            DialogController controller = fxmlLoader.getController();
            Items item = controller.processResults();
            listView.getSelectionModel().select(item);
        }
    }

    @FXML
    public void handleClickListView(){
        Items item = listView.getSelectionModel().getSelectedItem();  // getting selected item
        centerTextArea.setText(item.getFullDescription());
        deadLineLabel.setText(item.getDeadline().toString());
    }
}
