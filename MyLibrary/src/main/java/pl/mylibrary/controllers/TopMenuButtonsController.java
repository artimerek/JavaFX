package pl.mylibrary.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class TopMenuButtonsController {

    private MainController mainController;

    @FXML
    private ToggleGroup toggleButtons;

    @FXML
    public void openLibrary() {
        mainController.setCenter("/fxml/Library.fxml");
    }

    @FXML
    public void openListBooks() {
        mainController.setCenter("/fxml/ListBooks.fxml");
    }

    @FXML
    public void openStats() {
        mainController.setCenter("/fxml/Stats.fxml");
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void addBook() {
        if(toggleButtons.getSelectedToggle()!=null)
            toggleButtons.getSelectedToggle().setSelected(false);

        mainController.setCenter("/fxml/AddBook.fxml");
    }
}
