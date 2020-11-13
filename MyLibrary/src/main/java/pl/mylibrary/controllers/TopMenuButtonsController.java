package pl.mylibrary.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class TopMenuButtonsController {

    private MainController mainController;

    @FXML
    public void openLibrary() {
    }

    @FXML
    public void openListBooks() {
    }

    @FXML
    public void openStats() {
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
