package todoproject;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import todoproject.data.Items;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<Items> itemsTodo;

    @FXML
    private ListView<Items> listView;
    @FXML
    private TextArea centerTextArea;
    @FXML
    private Label deadLineLabel;

    @FXML
    public void initialize(){
        // some data for tests
        Items item1 = new Items(LocalDate.of(2021,8,31),"Birthday Party",
                "Prepare a cake, buy some drinks etc");
        Items item2 = new Items(LocalDate.of(2019,8,31),"Birthday Party",
                "Prepare a cake, buy some drinks etc");
        Items item3 = new Items(LocalDate.of(2018,8,31),"Birthday Party",
                "Prepare a cake, buy some drinks etc");
        // init arraylist with type of items
        this.itemsTodo = new ArrayList<Items>();

        itemsTodo.add(item1);
        itemsTodo.add(item2);
        itemsTodo.add(item3);

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

        listView.getItems().setAll(itemsTodo);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);  // only 1 item can be selected at a time
        listView.getSelectionModel().selectFirst(); // display first by the default
    }

    @FXML
    public void handleClickListView(){
        Items item = listView.getSelectionModel().getSelectedItem();  // getting selected item
        centerTextArea.setText(item.getFullDescription());
        deadLineLabel.setText(item.getDeadline().toString());
    }
}
