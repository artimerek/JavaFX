package menu.controllers;

import javafx.event.ActionEvent;

public class OptionsScreenController {

    private MainScreenController mainScreenController;

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    public void onActionButton(ActionEvent actionEvent) {
            mainScreenController.loadMenu();
    }
}
