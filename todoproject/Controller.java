package todoproject;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import todoproject.data.Items;
import todoproject.data.ItemsData;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Controller {
    private List<Items> itemsTodo;
    private FilteredList<Items> filteredList;
    private Predicate<Items> allItemsPredicate;
    private Predicate<Items> todayItemsPredicate;

    @FXML
    private ListView<Items> listView;
    @FXML
    private TextArea centerTextArea;
    @FXML
    private Label deadLineLabel;
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private ContextMenu contextMenu;
    @FXML
    private ToggleButton filterToggleButton;

    @FXML
    public void initialize(){
        contextMenu = new ContextMenu();
        MenuItem deleteMenu = new MenuItem("Delete");
        deleteMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Items item = listView.getSelectionModel().getSelectedItem();
                deleteItem(item);
            }
        });

        contextMenu.getItems().addAll(deleteMenu);
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

         allItemsPredicate = new Predicate<Items>() {
             @Override
             public boolean test(Items items) {
                 return true;
             }
         };

         todayItemsPredicate = new Predicate<Items>() {
             @Override
             public boolean test(Items item) {
                 return (item.getDeadline().equals(LocalDate.now()));
             }
         };

        filteredList = new FilteredList<Items>(ItemsData.getInstance().getItems(),allItemsPredicate);

        SortedList<Items> sortedList = new SortedList<>(filteredList, new Comparator<Items>() {
            @Override
            public int compare(Items o1, Items o2) {
                return o1.getDeadline().compareTo(o2.getDeadline());  // local date own comparator
            }
        });

//        listView.setItems(ItemsData.getInstance().getItems());   // to sort by deadline date
        listView.setItems(sortedList);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);  // only 1 item can be selected at a time
        listView.getSelectionModel().selectFirst(); // display first by the default

        listView.setCellFactory(new Callback<ListView<Items>, ListCell<Items>>() { // if today is deadline text gonna be red
            @Override
            public ListCell<Items> call(ListView<Items> itemsListView) {
                ListCell<Items> cell = new ListCell<>(){
                    @Override
                    protected void updateItem(Items item, boolean empty) {
                        super.updateItem(item,empty);  // if deleted bugs everywhere
                        if(empty){
                            setText(null);
                        }else {
                            setText(item.getShortDescription());
                            if(item.getDeadline().isBefore(LocalDate.now())){
                                setTextFill(Color.DARKRED);
                            }else if(item.getDeadline().equals(LocalDate.now().plusDays(1L))){
                                setTextFill(Color.RED);
                            }else {
                                setTextFill(Color.GREEN);
                            }
                        }
                    }
                };

                cell.emptyProperty().addListener(
                        (obs, wasEmpty, isNowEmpty) -> {
                            if(isNowEmpty){
                                cell.setContextMenu(null);
                            }else{
                                cell.setContextMenu(contextMenu);
                            }
                         });

                return cell;
            }
        });
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
    public void handleKeyPressed(KeyEvent keyEvent){
        Items selected = listView.getSelectionModel().getSelectedItem();
        if(selected != null){
            if(keyEvent.getCode().equals(KeyCode.DELETE)){
                deleteItem(selected);
            }
        }
    }

    @FXML
    public void handleClickListView(){
        Items item = listView.getSelectionModel().getSelectedItem();  // getting selected item
        centerTextArea.setText(item.getFullDescription());
        deadLineLabel.setText(item.getDeadline().toString());
    }

    public void deleteItem(Items item){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete item");
        alert.setHeaderText("Delete item " + item.getShortDescription());
        alert.setContentText("Press ok to confirm , or cancel to back out");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && (result.get() == ButtonType.OK)){
            ItemsData.getInstance().deleteItem(item);
        }
    }

    @FXML
    public void handleFilterToggleButton(){     // displaying today tasks
        Items item =  listView.getSelectionModel().getSelectedItem();
        if(filterToggleButton.isSelected()){
            filteredList.setPredicate(todayItemsPredicate);
            if(filteredList.isEmpty()){
                centerTextArea.clear();
                deadLineLabel.setText("");
            }else if (filteredList.contains(item)){
                listView.getSelectionModel().select(item);
            }else{
                listView.getSelectionModel().selectFirst();
            }
        }else {
            filteredList.setPredicate(allItemsPredicate); // if button isn't clicked we want to see everything
            listView.getSelectionModel().select(item);
        }
    }

    @FXML
    public void handleExit(){
        Platform.exit();
    }
}
